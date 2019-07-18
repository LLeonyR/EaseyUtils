package com.leonyr.lib.utils;

import android.app.ActivityManager;
import android.content.Context;

import java.util.Iterator;

public class ProcessUtil {

    /**
     * 判断该进程是否是app进程
     *
     * @return
     */
    public static boolean isAppProcess(Context c) {
        String processName = getProcessName(c);
        if (processName == null || !processName.equalsIgnoreCase(c.getPackageName())) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获取运行该方法的进程的进程名
     *
     * @return 进程名称
     */
    public static String getProcessName(Context c) {
        int processId = android.os.Process.myPid();
        String processName = null;
        ActivityManager manager = (ActivityManager) c.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        Iterator iterator = manager.getRunningAppProcesses().iterator();
        while (iterator.hasNext()) {
            ActivityManager.RunningAppProcessInfo processInfo = (ActivityManager.RunningAppProcessInfo) (iterator.next());
            try {
                if (processInfo.pid == processId) {
                    processName = processInfo.processName;
                    return processName;
                }
            } catch (Exception e) {
//                LogD(e.getMessage())
            }
        }
        return processName;
    }
}
