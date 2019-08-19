package com.jcking.common.utils;

import android.text.TextUtils;

/**
 * 断言工具，判断一些参数是否为空
 *
 * @author Jcking
 * @time 2019/3/19 21:38
 */
public class AssertUtil {

    public static void checkNull(Object object, String errorMsg) {
        if (object == null)
            throw new IllegalArgumentException(errorMsg);
    }

    public static void checkEmpty(String s, String errorMsg) {
        if (TextUtils.isEmpty(s))
            throw new IllegalArgumentException(errorMsg);
    }

    public static void checkZero(Number num, String errorMsg) {
        if (num.floatValue() == 0)
            throw new IllegalArgumentException(errorMsg);
    }
}
