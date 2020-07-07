package com.leonyr.utils

import java.io.BufferedOutputStream
import java.io.ByteArrayOutputStream
import java.io.Closeable
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.nio.channels.FileChannel
import java.nio.charset.Charset


object IOUtils {
    private const val BUFFER_SIZE = 0x400 // 1024

    /**
     * 从输入流读取数据
     * @param inStream
     * @return
     * @throws Exception
     */
    @Throws(IOException::class)
    fun readInputStream(inStream: InputStream): ByteArray {
        val outSteam = ByteArrayOutputStream()
        val buffer = ByteArray(BUFFER_SIZE)
        var len = 0
        while (inStream.read(buffer).also({ len = it }) != -1) {
            if (len != 0) {
                outSteam.write(buffer, 0, len)
            }
        }
        outSteam.close()
        inStream.close()
        return outSteam.toByteArray()
    }

    /**
     * 从输入流读取数据
     * @param inStream
     * @return
     * @throws Exception
     */
    @Throws(IOException::class)
    fun inputStream2String(inStream: InputStream): String {
        return String(readInputStream(inStream), Charset.defaultCharset())
    }

    /**
     *
     * @param is
     * @param os
     * @throws IOException
     */
    @Throws(IOException::class)
    fun copyStream(`is`: InputStream, os: OutputStream) {
        val bytes = ByteArray(BUFFER_SIZE)
        while (true) {
            val count: Int = `is`.read(bytes, 0, BUFFER_SIZE)
            if (count == -1) break
            os.write(bytes, 0, count)
        }
    }

    /**
     * 文件拷贝
     * @param src source [File]
     * @param dst destination [File]
     * @throws IOException
     */
    @Throws(IOException::class)
    fun copyFile(src: File?, dst: File?) {
        val `in` = FileInputStream(src)
        val out = FileOutputStream(dst)
        val inChannel: FileChannel = `in`.getChannel()
        val outChannel: FileChannel = out.getChannel()
        try {
            inChannel.transferTo(0, inChannel.size(), outChannel)
        } finally {
            inChannel.close()
            outChannel.close()
        }
        `in`.close()
        out.close()
    }

    /**
     * 将流写入文件
     * @param in
     * @param target
     * @throws IOException
     */
    @Throws(IOException::class)
    fun writeToFile(`in`: InputStream, target: File?) {
        val bos = BufferedOutputStream(
                FileOutputStream(target))
        var count: Int
        val data = ByteArray(BUFFER_SIZE)
        while (`in`.read(data, 0, BUFFER_SIZE).also { count = it } != -1) {
            bos.write(data, 0, count)
        }
        bos.close()
    }

    /**
     * 安全关闭io流
     * @param closeable
     */
    fun closeQuietly(closeable: Closeable?) {
        if (closeable != null) {
            try {
                closeable.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    /**
     * 安全关闭io流
     * @param closeables
     */
    fun closeQuietly(vararg closeables: Closeable?) {
        if (!closeables.isNullOrEmpty()) {
            for (closeable in closeables) {
                closeQuietly(closeable) // 系统先匹配确定参数的方法，没有再去匹配调用不定项参数的方法
            }
        }
    }
}