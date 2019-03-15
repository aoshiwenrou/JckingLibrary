package com.jcking.lib.log;

import android.text.TextUtils;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * @author Jcking
 * @time 2019/3/3 17:34
 */
public class JLog {
    private static String customTagPrefix = "JLOG";

    private static boolean DEBUG = false;

    public static void init(boolean debug) {
        DEBUG = debug;

        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)      //（可选）是否显示线程信息。 默认值为true
                .methodCount(2)               // （可选）要显示的方法行数。 默认2
                .methodOffset(1)               // （可选）设置调用堆栈的函数偏移值，0的话则从打印该Log的函数开始输出堆栈信息，默认是0
                .tag(customTagPrefix)                  //（可选）每个日志的全局标记。 默认PRETTY_LOGGER（如上图）
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }

    public static void e(String desc) {
        if (DEBUG) {
            String tag = generateTag();
            Logger.e(desc);
        }
    }

    public static void e(String tag, String desc) {
        if (DEBUG) {
            Logger.e(desc);
        }
    }

    public static void e(String tag, Throwable e) {
        if (DEBUG) {
            Logger.e(e, e.getMessage());
        }
    }

    public static void w(String tag, String desc) {
        if (DEBUG) {
            Logger.w(desc);
        }
    }

    public static void w(String desc) {
        if (DEBUG) {
            String tag = generateTag();
            Logger.w(desc);
        }
    }

    public static void i(String tag, String desc) {
        if (DEBUG) {
            Logger.i(desc);
        }
    }

    public static void i(String desc) {
        if (DEBUG) {
            String tag = generateTag();
            Logger.i(desc);
        }
    }

    // d级别调用i，是为了防止部分手机日志显示级别过滤导致的不显示问题
    public static void d(String tag, String desc) {
        if (DEBUG) {
            Logger.d(desc);
        }
    }

    // d级别调用i，是为了防止部分手机日志显示级别过滤导致的不显示问题
    public static void d(String desc) {
        if (DEBUG) {
            String tag = generateTag();
            Logger.d(desc);
        }
    }

    public static void v(String tag, String desc) {
        if (DEBUG) {
            Logger.v(desc);
        }
    }

    public static void v(String desc) {
        if (DEBUG) {
            String tag = generateTag();
            Logger.v(desc);
        }
    }

    public static void json(String json){
        if(DEBUG){
            Logger.json(json);
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
