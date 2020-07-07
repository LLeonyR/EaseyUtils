package com.leonyr.lib.utils;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

/**
 * ==============================================================
 *
 * Description: Fragment工具类
 * fragment替换
 * <p>
 * Created by leonyr on 2019.04.09
 * (C) Copyright LeonyR Corporation 2014 All Rights Reserved.
 * ==============================================================
 */
public class FragmentUtil {
    /**
     * 替换Activity中为id的Fragment位置
     *
     * @param c
     * @param id
     * @param t
     * @param <T>
     */
    public static <T extends Fragment> void replaceFragment(@NonNull AppCompatActivity c, @IdRes int id, @NonNull T t) {
        replaceFragment(c, id, t, false);
    }


    /**
     * @param back 是否添加返回栈
     */
    public static <T extends Fragment> void replaceFragment(@NonNull AppCompatActivity c, @IdRes int id, @NonNull T t, boolean back) {
        FragmentTransaction transaction = c.getSupportFragmentManager().beginTransaction();
        transaction.replace(id, t, t.getClass().getSimpleName());
        if (back) {
            transaction.addToBackStack(t.getClass().getSimpleName());
        }
        transaction.commit();
    }

}
