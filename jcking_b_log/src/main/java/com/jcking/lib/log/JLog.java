package com.jcking.lib.log;

import android.text.TextUtils;
import android.util.Log;

import com.orhanobut.logger.Logger;

/**
 * @author Jcking
 * @time 2019/3/3 17:34
 */
public class JLog {
    private static String customTagPrefix = "Jcking_log";

    private static boolean DEBUG = false;

    public static void enable(boolean debug) {
        DEBUG = debug;
    }

    public static void e(String tag, String desc) {
        if (DEBUG) {
            Logger.e(tag, desc);
        }
    }

    public static void e(String desc) {
        if (DEBUG) {
            String tag = generateTag();
            Logger.e(tag, desc);
        }
    }

    public static void w(String tag, String desc) {
        if (DEBUG) {
            Logger.w(tag, desc);
        }
    }

    public static void w(String desc) {
        if (DEBUG) {
            String tag = generateTag();
            Logger.w(tag, desc);
        }
    }

    public static void i(String tag, String desc) {
        if (DEBUG) {
            Logger.i(tag, desc);
        }
    }

    public static void i(String desc) {
        if (DEBUG) {
            String tag = generateTag();
            Logger.i(tag, desc);
        }
    }

    public static void d(String tag, String desc) {
        if (DEBUG) {
            Logger.d(tag, desc);
        }
    }

    public static void d(String desc) {
        if (DEBUG) {
            String tag = generateTag();
            Logger.d(tag, desc);
        }
    }

    public static void v(String tag, String desc) {
        if (DEBUG) {
            Logger.v(tag, desc);
        }
    }

    public static void v(String desc) {
        if (DEBUG) {
            String tag = generateTag();
            Logger.v(tag, desc);
        }
    }

    private static String generateTag() {
        StackTraceElement caller = new Throwable().getStackTrace()[2];
        String tag = "%s.%s(L:%d)";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
        tag = TextUtils.isEmpty(customTagPrefix) ? tag : customTagPrefix + ":" + tag;
        return tag;
    }
}
