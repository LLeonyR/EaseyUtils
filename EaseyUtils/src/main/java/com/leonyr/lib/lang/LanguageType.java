package com.leonyr.lib.lang;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * ==============================================================
 * Description:多语言切换语言类型
 * <p>
 * Created by leonyr on 2019.05.05
 * (C) Copyright LeonyR Corporation 2014 All Rights Reserved.
 * ==============================================================
 */
public final class LanguageType {
    public static final int LANGUAGE_FOLLOW_SYSTEM = 0; //跟随系统
    public static final int LANGUAGE_EN = 1;    //英文
    public static final int LANGUAGE_CHINESE_SIMPLIFIED = 2; //简体
    public static final int LANGUAGE_CHINESE_TRADITIONAL = 3;  //香港台湾繁体

    @IntDef({LANGUAGE_FOLLOW_SYSTEM, LANGUAGE_EN, LANGUAGE_CHINESE_SIMPLIFIED, LANGUAGE_CHINESE_TRADITIONAL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Language {
    }
}
