package com.jcking.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间格式化工具类
 *
 * @author Jcking
 * @time 2017/8/4 17:40
 */
public class TimeFormatter {

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String YMDHMS = "yyyy-MM-dd HH:mm:ss";
    /**
     * yyyy-MM-dd
     */
    public static final String YMD = "yyyy-MM-dd";
    /**
     * MM/dd HH:mm
     */
    public static final String MDHM = "MM/dd HH:mm";
    /**
     * HH:mm
     */
    public static final String HM = "HH:mm";

    public static long getLong(String timeStr, String formatter) {
        DateFormat df = new SimpleDateFormat(formatter);
        try {
            return df.parse(timeStr).getTime();
        } catch (ParseException e) {
        }
        try {
            return Long.valueOf(timeStr);
        } catch (Exception e) {
        }
        return 0;
    }

    public static String getString(long time, String formatter) {
        if (time == 0) {
            return "";
        }
        DateFormat df = new SimpleDateFormat(formatter);
        return df.format(new Date(time));
    }
}
