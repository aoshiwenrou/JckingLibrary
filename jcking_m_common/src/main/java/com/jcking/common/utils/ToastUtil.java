package com.jcking.common.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * toast工具类
 *
 * @author Jcking
 * @time 2019/3/4 23:17
 */
public class ToastUtil {

    public static void show(Context context, @StringRes int msg) {
        show(context, context.getString(msg));
    }

    public static void show(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
