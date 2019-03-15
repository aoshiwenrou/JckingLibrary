package com.jcking.widget.wheel;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

/**
 * @author Jcking
 * @time 2019/3/7 14:52
 */
public class DialogUtil {

    /**
     * 设置窗口显示
     */
    public static void windowDeploy(Dialog dialog, float widthScale) {
        Window window = dialog.getWindow(); // 得到对话框
        window.setBackgroundDrawableResource(android.R.color.transparent); // 设置对话框背景为透明
        WindowManager.LayoutParams wl = window.getAttributes();
        // // 根据x，y坐标设置窗口需要显示的位置

        wl.width = (int) (getScreenWidth(dialog.getContext()) * widthScale);
        wl.height = WindowManager.LayoutParams.WRAP_CONTENT;

        wl.gravity = Gravity.CENTER; // 设置重力
        window.setAttributes(wl);
    }

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }
}
