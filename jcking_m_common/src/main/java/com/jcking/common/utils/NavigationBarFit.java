package com.jcking.common.utils;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

/**
 * 底部虚拟按键的适配工具
 * @author Jcking
 * @time 2019/5/21 11:44
 */
public class NavigationBarFit {
    /**
     * 关联要监听的视图
     *
     * @param viewObserving
     */
    public static void assistActivity(View viewObserving) {
        new NavigationBarFit(viewObserving);
    }

    private View mViewObserved;//被监听的视图
    private int usableHeightPrevious;//视图变化前的可用高度
    private int stateBarHeight;//状态栏高度
    private ViewGroup.LayoutParams frameLayoutParams;

    private NavigationBarFit(View viewObserving) {
        mViewObserved = viewObserving;
        //给View添加全局的布局监听器
        mViewObserved.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                resetLayoutByUsableHeight(computeUsableHeight());
            }
        });
        frameLayoutParams = mViewObserved.getLayoutParams();
        stateBarHeight = getStateBarHeight(viewObserving.getContext());
    }

    private void resetLayoutByUsableHeight(int usableHeightNow) {
        //比较布局变化前后的View的可用高度
        if (usableHeightNow != usableHeightPrevious) {
            //如果两次高度不一致,将当前的View的可用高度设置成View的实际高度
            frameLayoutParams.height = usableHeightNow;
            mViewObserved.requestLayout();//请求重新布局
            usableHeightPrevious = usableHeightNow;
        }
    }

    /**
     * 计算视图可视高度
     *
     * @return
     */
    private int computeUsableHeight() {
        Rect r = new Rect();
        mViewObserved.getWindowVisibleDisplayFrame(r);
        return (r.bottom - r.top) + stateBarHeight;
    }

    /**
     * 获取状态栏高度
     *
     * @param context
     */
    private int getStateBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
