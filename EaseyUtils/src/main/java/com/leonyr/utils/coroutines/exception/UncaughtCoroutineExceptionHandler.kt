package com.leonyr.utils.coroutines.exception

import com.leonyr.utils.coroutines.coroutineErrorListener
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

/**
 *
 * @FileName:
 *          com.leonyr.utils.coroutines.exception.UncaughtCoroutineExceptionHandler
 * @author: Tony Shen
 * @date: 2019-10-18 02:13
 * @version: V1.0 <描述当前版本功能>
 */
class UncaughtCoroutineExceptionHandler(private val errorHandler: coroutineErrorListener?=null)  :
    CoroutineExceptionHandler, AbstractCoroutineContextElement(CoroutineExceptionHandler.Key) {

    override fun handleException(context: CoroutineContext, throwable: Throwable) {
        throwable.printStackTrace()

        errorHandler?.let {
            it(throwable)
        }
    }
}