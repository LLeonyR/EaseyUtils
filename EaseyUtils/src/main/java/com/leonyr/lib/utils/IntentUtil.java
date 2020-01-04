package com.leonyr.lib.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;

import java.util.List;

/**
 * ==============================================================
 * Description:
 * <p>
 * Created by leonyr on 2019/6/28
 * (C) Copyright LeonyR Corporation 2014 All Rights Reserved.
 * ==============================================================
 */
public class IntentUtil {

    /**
     * activity 跳转
     * @param c 当前上下文
     * @param a 目的Activity
     */
    public static void start(Context c, Class<Activity> a) {
        start(c, a, null);
    }

    /**
     * activity 跳转
     * @param c 当前上下文
     * @param a 目的Activity
     * @param b Bundle参数
     */
    public static void start(Context c, Class<Activity> a, Bundle b) {
        Intent i = new Intent(c, a);
        if (null != b) {
            i.putExtras(b);
        }
        c.startActivity(i);
    }

    /**
     * activity 跳转
     * @param c 当前上下文
     * @param a 目的Activity
     */
    public static void startClearTop(Context c, Class<Activity> a){
        startClearTop(c, a, null);
    }

    /**
     * activity 跳转
     * @param c 当前上下文
     * @param a 目的Activity
     * @param b Bundle参数
     */
    public static void startClearTop(Context c, Class<Activity> a, Bundle b){
        Intent i = new Intent(c, a);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if (null != b) {
            i.putExtras(b);
        }
        c.startActivity(i);
    }

    /**
     * 跳转到时间日期设置界面
     *
     * @param c
     */
    public static void ToDateSetting(Context c) {
        ToPage(c, Settings.ACTION_DATE_SETTINGS);
    }

    /**
     * 跳转手机状态界面
     *
     * @param c
     */
    public static void ToDeviceInfoSetting(Context c) {
        ToPage(c, Settings.ACTION_DEVICE_INFO_SETTINGS);
    }

    /**
     * 跳转到NFC共享设置界面
     *
     * @param c
     */
    public static void ToNFCShareSetting(Context c) {
        ToPage(c, Settings.ACTION_NFCSHARING_SETTINGS);
    }

    /**
     * 跳转到系统辅助功能页面
     *
     * @param c
     */
    public static void ToAccessIbilitySettings(Context c) {
        ToPage(c, Settings.ACTION_ACCESSIBILITY_SETTINGS);
    }

    /**
     * 显示添加账户创建一个新的账户屏幕
     *
     * @param c
     */
    public static void ToAddAccount(Context c) {
        ToPage(c, Settings.ACTION_ADD_ACCOUNT);
    }

    /**
     * 飞行模式，无线网和网络设置界面
     *
     * @param c
     */
    public static void ToAirplaneModeSet(Context c) {
        ToPage(c, Settings.EXTRA_AIRPLANE_MODE_ENABLED);
    }

    /**
     * WiFi 设置界面
     *
     * @param c
     */
    public static void ToWirlessSetting(Context c) {
        ToPage(c, Settings.ACTION_WIRELESS_SETTINGS);
    }


    /**
     * 跳转至指定页面
     *
     * @param c
     * @param page
     */
    public static void ToPage(Context c, String page) {
        Intent intent = new Intent(page);
        c.startActivity(intent);
    }

    public static void playMp4(Context c, String path) {
        Uri uri = UriUtil.getUri(c, path);
        //调用系统自带的播放器
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(uri, "video/mp4");
        c.startActivity(intent);
    }

    public static void playMp3(Context c, String path) {
        Uri uri = UriUtil.getUri(c, path);
        //调用系统自带的播放器
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(uri, "audio/*");
        c.startActivity(intent);
    }


    public static void playImage(Context c, String path) {
        Uri uriForFile = UriUtil.getUri(c, path);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(uriForFile, "image/*");
        c.startActivity(intent);
    }

    /**
     * 判断某个界面是否在前台
     *
     * @param context
     * @param className 某个界面名称
     */
    public static boolean isActivityForeground(Context context, String className) {
        if (context == null || TextUtils.isEmpty(className)) {
            return false;
        }

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(1);
        if (list != null && list.size() > 0) {
            ComponentName cpn = list.get(0).topActivity;
            if (className.equals(cpn.getClassName())) {
                return true;
            }
        }

        return false;
    }

}

/*


        4. ACTION_APN_SETTINGS： // 跳转 APN设置界面

        Intent intent = new Intent(Settings.ACTION_APN_SETTINGS);
        startActivity(intent);

        5. 【需要参数】 ACTION_APPLICATION_DETAILS_SETTINGS： // 根据包名跳转到系统自带的应用程序信息界面

        Uri packageURI = Uri.parse("package:" + "com.tencent.WBlog");
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,packageURI);
        startActivity(intent);

        6. ACTION_APPLICATION_DEVELOPMENT_SETTINGS : // 跳转开发人员选项界面

        Intent intent = new Intent(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS);
        startActivity(intent);

        7. ACTION_APPLICATION_SETTINGS ： // 跳转应用程序列表界面

        Intent intent = new Intent(Settings.ACTION_APPLICATION_SETTINGS);
        startActivity(intent);

        或者：

        ACTION_MANAGE_ALL_APPLICATIONS_SETTINGS // 跳转到应用程序界面【所有的】

        Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_APPLICATIONS_SETTINGS);
        startActivity(intent);

        或者：

        ACTION_MANAGE_APPLICATIONS_SETTINGS ：// 跳转 应用程序列表界面【已安装的】

        Intent intent = new Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
        startActivity(intent);



        8. ACTION_BLUETOOTH_SETTINGS ： // 跳转系统的蓝牙设置界面



        Intent intent = new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
        startActivity(intent);

        9. ACTION_DATA_ROAMING_SETTINGS ： // 跳转到移动网络设置界面

        Intent intent = new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS);
        startActivity(intent);

        10. ACTION_DATE_SETTINGS ： // 跳转日期时间设置界面

        Intent intent = new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS);
        startActivity(intent);

        11. ACTION_DEVICE_INFO_SETTINGS ： // 跳转手机状态界面

        Intent intent = new Intent(Settings.ACTION_DEVICE_INFO_SETTINGS);
        startActivity(intent);

        12. ACTION_DISPLAY_SETTINGS ： // 跳转手机显示界面

        Intent intent = new Intent(Settings.ACTION_DISPLAY_SETTINGS);
        startActivity(intent);

        13. ACTION_DREAM_SETTINGS 【API 18及以上 没测试】

        Intent intent = new Intent(Settings.ACTION_DREAM_SETTINGS);
        startActivity(intent);



        14. ACTION_INPUT_METHOD_SETTINGS ： // 跳转语言和输入设备



        Intent intent = new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);
        startActivity(intent);

        15. ACTION_INPUT_METHOD_SUBTYPE_SETTINGS 【API 11及以上】 // 跳转 语言选择界面 【多国语言选择】

        Intent intent = new Intent(Settings.ACTION_INPUT_METHOD_SUBTYPE_SETTINGS);
        startActivity(intent);

        16. ACTION_INTERNAL_STORAGE_SETTINGS // 跳转存储设置界面【内部存储】

        Intent intent = new Intent(Settings.ACTION_INTERNAL_STORAGE_SETTINGS);
        startActivity(intent);

        或者：

        ACTION_MEMORY_CARD_SETTINGS ： // 跳转 存储设置 【记忆卡存储】

        Intent intent = new Intent(Settings.ACTION_MEMORY_CARD_SETTINGS);
        startActivity(intent);


        17. ACTION_LOCALE_SETTINGS ： // 跳转语言选择界面【仅有English 和 中文两种选择】

        Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
        startActivity(intent);


        18. ACTION_LOCATION_SOURCE_SETTINGS : // 跳转位置服务界面【管理已安装的应用程序。】

        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);

        19. ACTION_NETWORK_OPERATOR_SETTINGS ： // 跳转到 显示设置选择网络运营商。

        Intent intent = new Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS);
        startActivity(intent);

        20. ACTION_NFCSHARING_SETTINGS ： // 显示NFC共享设置。 【API 14及以上】

        Intent intent = new Intent(Settings.ACTION_NFCSHARING_SETTINGS);
        startActivity(intent);

        21. ACTION_NFC_SETTINGS ： // 显示NFC设置。这显示了用户界面,允许NFC打开或关闭。 【API 16及以上】

        Intent intent = new Intent(Settings.ACTION_NFC_SETTINGS);
        startActivity(intent);

        22. ACTION_PRIVACY_SETTINGS ： // 跳转到备份和重置界面

        Intent intent = new Intent(Settings.ACTION_PRIVACY_SETTINGS);
        startActivity(intent);

        23. ACTION_QUICK_LAUNCH_SETTINGS ： // 跳转快速启动设置界面

        Intent intent = new Intent(Settings.ACTION_QUICK_LAUNCH_SETTINGS);
        startActivity(intent);

        24. ACTION_SEARCH_SETTINGS ： // 跳转到 搜索设置界面

        Intent intent = new Intent(Settings.ACTION_SEARCH_SETTINGS);
        startActivity(intent);

        25. ACTION_SECURITY_SETTINGS ： // 跳转到安全设置界面

        Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
        startActivity(intent);

        26. ACTION_SETTINGS ： // 跳转到设置界面

        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        startActivity(intent);

        27. ACTION_SOUND_SETTINGS // 跳转到声音设置界面

        Intent intent = new Intent(Settings.ACTION_SOUND_SETTINGS);
        startActivity(intent);

        28. ACTION_SYNC_SETTINGS ： // 跳转账户同步界面

        Intent intent = new Intent(Settings.ACTION_SYNC_SETTINGS);
        startActivity(intent);

        29. ACTION_USER_DICTIONARY_SETTINGS ： // 跳转用户字典界面

        Intent intent = new Intent(Settings.ACTION_USER_DICTIONARY_SETTINGS);
        startActivity(intent);

        30. ACTION_WIFI_IP_SETTINGS ： // 跳转到IP设定界面

        Intent intent = new Intent(Settings.ACTION_WIFI_IP_SETTINGS);
        startActivity(intent);

        */