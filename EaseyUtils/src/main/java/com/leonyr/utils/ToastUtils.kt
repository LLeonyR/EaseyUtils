package com.leonyr.utils

import android.content.Context
import android.widget.Toast

fun showLong(context: Context?, resId: Int) {
    showToast(context, resId, Toast.LENGTH_LONG)
}

fun showLong(context: Context?, message: String) {
    showToast(context, message, Toast.LENGTH_LONG)
}

fun show(context: Context?, resId: Int) {
    showToast(context, resId)
}

fun show(context: Context?, message: String) {
    showToast(context, message)
}

fun showToast(context: Context?, resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    context?.let {
        Toast.makeText(context, resId, duration).show()
    }
}

fun showToast(context: Context?, message: String, duration: Int = Toast.LENGTH_SHORT) {
    context?.let {
        Toast.makeText(context, message, duration).show()
    }
}