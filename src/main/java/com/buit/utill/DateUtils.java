package com.buit.utill;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 处理时间的常用工具类
 */
public class DateUtils {

    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

    public static final String YEAR_MONTH_DAY = "yyyy-MM-dd";
    public static final String YEAR_MONTH_DAY_HOUR_MINUTE_SECOND = "yyyy-MM-dd HH:mm:ss";
    public static final String PRECISION_MILLISECOND = "yyyy-MM-dd HH:mm:ss.SSS";


    public static Timestamp getMonthStartTime(Timestamp timeStamp) {
        if (null == timeStamp) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        calendar.setTimeInMillis(timeStamp.getTime());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    public static Timestamp getMonthEndTime(Timestamp timeStamp) {
        if (null == timeStamp) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        calendar.setTimeInMillis(timeStamp.getTime());
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * 获取第二天凌晨的0点0时0分0秒
     *
     * @param timestamp
     * @return
     */
    public static Date getTomorrowBeginning(Timestamp timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        calendar.setTimeInMillis(timestamp.getTime());
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Date(calendar.getTimeInMillis());
    }

    /**
     * 日期加减
     *
     * @param date   需要加减的日期
     * @param offset 增加的天数
     */
    public static Timestamp dateAdd(Timestamp date, int offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        calendar.add(Calendar.DATE, offset);
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * 秒数加减
     *
     * @param dateTime 需要加减的时间
     * @param offset   增加的秒数
     */
    public static Timestamp secondAdd(Timestamp dateTime, int offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime);
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        calendar.add(Calendar.SECOND, offset);
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * 获取当前时间之后的最小时间点
     */
    /*public static Date getTheTimeAfterThis(Date timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        calendar.setTimeInMillis(timestamp.getTime());
        calendar.add(Calendar.MILLISECOND, 1);
        return new Date(calendar.getTimeInMillis());
    }*/

    /**
     * 计算日期间隔, 精确到天（不足一天也算一天）。
     *
     * @param start
     * @param end
     * @return
     */
    public static int getDaysDiff(Date start, Date end) {
        long diffMillis = end.getTime() - start.getTime();
        return (int) Math.ceil(Float.valueOf(diffMillis) / (24 * 60 * 60 * 1000));
    }

    /**
     * 获取一天的最小时间
     */
    public static Timestamp getDailyStartTime(Long timeStamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        calendar.setTimeInMillis(timeStamp);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * 获取一天的最小时间
     *
     * @param date yyyy-mm-dd 格式字符串
     * @return
     */
    public static String getDailyStartTime(String date) {
        return date.trim() + " 0:00:00.000";
    }

    /**
     * 获取一天的最大时间
     *
     * @param timeStamp
     * @param
     * @return
     */
    public static Timestamp getDailyEndTime(Long timeStamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        calendar.setTimeInMillis(timeStamp);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * 获取一天的最大时间
     *
     * @param date yyyy-mm-dd 格式字符串
     * @return
     */
    public static String getDailyEndTime(String date) {
        return date.trim() + " 23:59:59.999";
    }

    /**
     * 时间字符串转换成Timestamp
     *
     * @param
     * @return
     * @throws ParseException
     */
    public static Timestamp convertTimestamp(String dateFormat, String s) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        Date date = null;
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            logger.error("DateUtils.convertTimestamp error: dateFormat[" + dateFormat + "],s[" + s + "]", e);
            System.err.println(e.getStackTrace());
        }
        long ts = date.getTime();
        return new Timestamp(ts);
    }

    /**
     * Timestamp转换为指定格式的字符串
     */
    public static String toString(Date timestamp, String patten) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patten);
        return simpleDateFormat.format(timestamp);
    }

    /**
     * 获取当前时间 返回字符串
     *
     * @return
     */
    public static String getCurrentDateStr() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    /**
     * 计算两个日期的差，参数null表示当前日期。
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int getDifferDays(Date date1, Date date2) {
        if (date1 != null && date2 != null) {
            return (int) ((date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000));
        } else {
            return 0;
        }
    }

    /**
     * 获取当前时间
     */
    public static Timestamp getNow() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * 时间字符串转换成Timestamp yyyy-mm-dd hh:mm:ss
     */
    public static Timestamp parseToDateTime(String s) {
        return convertTimestamp(YEAR_MONTH_DAY_HOUR_MINUTE_SECOND, s);
    }

    /**
     * 时间字符串转换成Timestamp yyyy-mm-dd
     */
    public static Timestamp parseToDate(String s) {
        return convertTimestamp(YEAR_MONTH_DAY, s);
    }

    /**
     * 时间转换为yyyy-MM-dd HH:mm:ss时间字符串
     *
     * @param date 时间
     * @return 时间字符串
     */
    public static String formatToDateTime(Date date) {
        return toString(date, YEAR_MONTH_DAY_HOUR_MINUTE_SECOND);
    }

    public static void main(String[] args) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(getMonthStartTime(timestamp));
        System.out.println(getMonthEndTime(timestamp));
    }

}
