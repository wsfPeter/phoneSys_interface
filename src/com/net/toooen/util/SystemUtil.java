package com.net.toooen.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * 系统的工具类.
 * 
 * @author Ken
 * @since 2014年12月31日
 */
public class SystemUtil {

    public static final String yyyy_MM_dd = "yyyy-MM-dd";
    public static final String MM_dd = "MM-dd";
    public static final String yyyyMM = "yyyyMM";
    public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
    public static final String DATE_SQ = "yyyyMMddHHmmssSSS";

    public static final String yyyyMMddHHmm = "yyyyMMddHHmm";

    /** get yyyy_MM_dd now() */
    public static String getDateNow() {
        SimpleDateFormat format = new SimpleDateFormat(yyyy_MM_dd);
        return format.format(new Date());
    }

    /** get MM_dd now() */
    public static String getDateMDNow() {
        SimpleDateFormat format = new SimpleDateFormat(MM_dd);
        return format.format(new Date());
    }

    /** get yyyy_MM_dd estarDay */
    public static String getDateYestarDay() {
        Calendar cnow = Calendar.getInstance();
        cnow.set(Calendar.DAY_OF_YEAR, cnow.get(Calendar.DAY_OF_YEAR) - 1);
        SimpleDateFormat format = new SimpleDateFormat(yyyy_MM_dd);
        return format.format(cnow.getTime());
    }

    /** get yyyyMM now() */
    public static String getDateYearMonth() {
        SimpleDateFormat format = new SimpleDateFormat(yyyyMM);
        return format.format(new Date());
    }

    /** get yyyy_MM_dd */
    public static String getDate(Date date) {
        if (date == null)
            return null;
        SimpleDateFormat format = new SimpleDateFormat(yyyy_MM_dd);
        return format.format(date);
    }

    /**
     * get yyyy_MM_dd
     * 
     * @throws ParseException
     */
    public static Date getDateFromStr(String date) throws ParseException {
        if (date == null)
            return null;
        try {
            SimpleDateFormat format = new SimpleDateFormat(yyyy_MM_dd);
            return format.parse(date);
        } catch (Exception e) {
            return null;
        }
    }

    /** get yyyy_MM_dd_HH_mm_ss NOW() */
    public static String getDateTimeNow() {
        return getDateTime(new Date());
    }

    /** get yyyy_MM_dd_HH_mm_ss */
    public static String getDateTime(Date date) {
        if (date == null)
            return null;
        SimpleDateFormat format = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss);
        return format.format(date);
    }

    /** get yyyy_MM_dd_HH_mm_ss */
    public static String getDateTime(java.sql.Timestamp date) {
        if (date == null)
            return "";
        SimpleDateFormat format = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss);
        return format.format(date);
    }

    /**
     * 将时间按指定的格式返回 .
     */
    public static String getDateTimeByFormate(Date date, String formate) {
        if (date == null)
            return null;
        SimpleDateFormat format = new SimpleDateFormat(formate);
        return format.format(date);
    }

    /**
     * 将当前时间按指定的格式返回 .
     */
    public static String getDateTimeSeq() {
        SimpleDateFormat format = new SimpleDateFormat(DATE_SQ);
        return format.format(new Date());
    }

    /**
     * 获取昨天时间的起始时间
     * 
     * @return
     */
    public static Date getYesterdayStartTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) - 1);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取昨天时间的结束时间
     * 
     * @return
     */
    public static Date getYesterdayEndTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) - 1);
        cal.set(Calendar.HOUR, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    /**
     * 返回20位随机数.
     */
    public static String get20SeqNum() {
        return getDateTimeSeq() + (new java.util.Random().nextInt(900) + 100);
    }

    /**
     * @param randomMax
     *            指定获取随机数的大小
     * @return int
     */
    public static int getRandom(int randomMax) {
        return new java.util.Random().nextInt(randomMax);
    }

    /**
     * 产生随机字符串
     */
    public static String randString(int length) {
        Random r = new Random();
        String ssource = "0123456789";
        char[] src = ssource.toCharArray();
        char[] buf = new char[length];
        int rnd;
        for (int i = 0; i < length; i++) {
            rnd = Math.abs(r.nextInt()) % src.length;
            buf[i] = src[rnd];
        }
        return new String(buf);
    }

    /**
     * 调用该方法，产生随机字符串 参数i: 为字符串的长度
     */
    public static String runVerifyCode() {
        String VerifyCode = randString(6);
        return VerifyCode;
    }

}
