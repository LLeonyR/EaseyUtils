package com.leonyr.lib.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

    private static Toast toast = null; //Toast的对象！

    public static void showToast(Context mContext, String id) {
        if (toast == null) {
            toast = Toast.makeText(mContext, id, Toast.LENGTH_SHORT);
        } else {
            toast.setText(id);
        }
        toast.show();
    }

    public static void showToast(Context mContext, int id) {
        String text = mContext.getResources().getString(id);
        showToast(mContext, text);
    }

}
