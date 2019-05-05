package com.leonyr.lib.lang;

/**
 * ==============================================================
 * Description:多语言切换完成监听类
 * <p>
 * Created by leonyr on 2019.05.05
 * (C) Copyright LeonyR Corporation 2014 All Rights Reserved.
 * ==============================================================
 */
public interface OnChangeLanguageListener {

    void OnChangeLanguage(@LanguageType.Language int languageType);

}
