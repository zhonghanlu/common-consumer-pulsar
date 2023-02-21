package com.ymxc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

public final class DateUtils {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    private static final SimpleDateFormat sDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat wdate = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat wmonth = new SimpleDateFormat("yyyyMM");

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");


    /**
     * 获取date类型的当前时间
     */
    public static String getDate(Date date) {
        return sDate.format(date);
    }


    /**
     * 获取date类型的当前时间
     */
    public static Date getDate() {
        Date date = new Date();
        sDate.format(date);
        return date;
    }

    public static Date getDateTime(Long time) throws ParseException {
        if (time == null) {
            return null;
        }
        return sDate.parse(sDate.format(time));
    }

    /**
     * 获取date类型的当天开始时间
     */
    public static Date getStartDate() {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_MONTH, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date dayStart = calendar.getTime();
        sDate.format(dayStart);
        return dayStart;
    }

    public static Integer daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days + 1));
    }

    /**
     * 计算两个时间间隔天数
     * @param startDate
     * @param endDate
     * @return
     */
    public static Integer daysBetweenByTimeStamp(Date startDate, Date endDate){
        Instant startInstant = Instant.ofEpochMilli(startDate.getTime());
        Instant endInstant = Instant.ofEpochMilli(endDate.getTime());
        Duration duration =  Duration.between(startInstant,endInstant);
        return Integer.parseInt(String.valueOf(duration.toDays()));
    }

    /**
     * 获取date类型的当天结束时间
     */
    public static Date getEndDate() {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_MONTH, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date dayEnd = calendar.getTime();
        sDate.format(dayEnd);
        return dayEnd;
    }

    /**
     * 当前时间的字符串
     *
     * @param date 时间
     * @return 时间的数字字符串格式
     */
    public static String mathString(Date date) {
        SimpleDateFormat time = new SimpleDateFormat("yyyyMMddHHmmss", Locale.UK);
        return time.format(date);
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static long getCurrentTime() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 获取链式类型的日期： yyyy-mm-dd  HH:mm:ss
     *
     * @return
     * @author Moon Yang
     * @since 2018-05-11
     */
    public static String getDate(Long time) {
        if (time == null) {
            return null;
        }
        return sDate.format(new Date(time * 1000));
    }

    /**
     * 获取链式类型的日期： yyyymmdd
     *
     * @return
     * @author Moon Yang
     * @since 2018-05-11
     */
    public static String getLinkDate() {
        return sdf.format(new Date());
    }

    public static String getSyncLinkDate() {
        SimpleDateFormat time = new SimpleDateFormat("yyyyMMddHHmmss", Locale.UK);
        return time.format(new Date());
    }

    /**
     * 获取指定时间戳的链式格式yyyymmdd
     *
     * @param times
     * @return
     */
    public static String getLinkDate(Long times) {
        return sdf.format(new Date(times));
    }

    /**
     * 获取指定时间戳的链式格式:yyyy-mm-dd
     *
     * @param times
     * @return
     */
    public static String getLinkWorkDate(Long times) {
        return wdate.format(new Date(times));
    }


    /**
     * 获取指定时间戳的链式格式:yyyy-mm
     *
     * @param times
     * @return
     */
    public static String getLinkWorkMonth(Long times) {
        return wmonth.format(new Date(times));
    }

    /**
     * 获取当天凌晨0点毫秒数
     *
     * @return java.lang.Long
     * @param4
     * @date 2018/6/8
     */
    public static Long getMilliseconds() {
        Long current = System.currentTimeMillis();
        Long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
        return zero / 1000;
    }

    /**
     * 获取昨天凌晨0点毫秒数
     *
     * @param
     * @return java.la
     * ng.Long
     * @date 2018/6/8
     */
    public static Long getMillisecondsYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DATE, -1); // 明天的就是1，昨天是负1
        return calendar.getTimeInMillis() / 1000;
    }

    /**
     * 获取昨天凌晨的日期
     *
     * @param
     * @return java.la
     * ng.Long
     * @date 2018/6/8
     */
    public static Date getDateYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DATE, -1); // 明天的就是1，昨天是负1
        Date dayStart = calendar.getTime();
        sDate.format(dayStart);
        return dayStart;
    }

    /**
     * 将日期的long类型,加上月份时长，算出最后的日期 ，例如：药品的生产日期，和过期月数，算出最后的过期时间
     *
     * @return
     * @author Moon Yang
     * @since 2018-05-15
     */
    public static long parse(long ymd, Integer keepMonth) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(ymd);
        cal.add(Calendar.MONTH, keepMonth);
        Date date = cal.getTime();
        return date.getTime();
    }

    public static Date parseDate(Date date, Integer keepMonth) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String data = format.format(date);
        String dataStr[] = data.split("-");
        //年份
        int year = (Integer.parseInt(dataStr[1]) + keepMonth) / 12;
        //月份
        int yue = (Integer.parseInt(dataStr[1]) + keepMonth) % 12;
        String a = "";
        if (yue < 10) {
            if (yue < 1) {
                a = "12";
            } else {
                a = "0" + yue;
            }
        } else {
            a = yue + "";
        }
        dataStr[0] = String.valueOf(Integer.parseInt(dataStr[0]) + year);
        dataStr[1] = a;
        String newdata = dataStr[0] + "-" + dataStr[1] + "-" + dataStr[2];
        Date newDate = format.parse(newdata);
        return newDate;
    }


    public static long parseAddDay(long ymd, Integer day) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(ymd);
        cal.add(Calendar.DAY_OF_MONTH, day);
        Date date = cal.getTime();
        return date.getTime();
    }

    public static Long parseTime(Long time) {
        if (time == null) {
            return null;
        }
        Long date = time / 1000L;
        return date;
    }


    /**
     * 获取当天凌晨0点秒数
     *
     * @param
     * @return java.lang.Long
     * @date 2018/6/8
     */
    public static Long getTodayStartMilliseconds() {
        Long current = System.currentTimeMillis();
        Long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
        return zero / 1000;
    }


    /**
     * 获取当天23:59分秒数
     *
     * @param
     * @return java.lang.Long
     * @date 2018/6/8
     */
    public static Long getMillisecondsEnd() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                23, 59, 59);
        Date date = calendar.getTime();
        return date.getTime() / 1000;
    }

    /**
     * 计算结束时间，开始时间与单次时长，开始时间为毫秒数，单次时长为小时
     *
     * @param
     * @return java.lang.Long
     * @date 2018/6/8
     */
    public static Long getMillisecondsFinish(Long startTime, Integer hour) {
        return (startTime + hour * 3600000) / 1000;
    }

    /**
     * 计算结束时间，开始时间与单次时长，开始时间为毫秒数，单次时长为小时
     *
     * @param
     * @return java.lang.Long
     * @date 2018/6/8
     */
    public static Long getMillisecondsMinuteFinish(Long startTime, Integer minute) {
        return (startTime + minute * 60000) / 1000;
    }

    /**
     * 根据时间戳计算指定时刻的毫秒数
     *
     * @param
     * @return java.lang.Long
     * @date 2018/7/26
     */
    public static Long getStartMillisecondsAtHour(Integer h, Long startTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(startTime);
        //时
        calendar.set(Calendar.HOUR_OF_DAY, h);
        //分
        calendar.set(Calendar.MINUTE, 0);
        //秒
        calendar.set(Calendar.SECOND, 0);
        //毫秒
        calendar.set(Calendar.MILLISECOND, 0);
        //返回指定时刻的毫秒数
        return calendar.getTimeInMillis() / 1000;
    }

    /**
     * 根据时间戳计算当天凌晨秒数
     *
     * @param
     * @return java.lang.Long
     * @date 2018/7/26
     */
    public static Long getStartMilliseconds(Long startTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(startTime);
        //时
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        //分
        calendar.set(Calendar.MINUTE, 0);
        //秒
        calendar.set(Calendar.SECOND, 0);
        //毫秒
        calendar.set(Calendar.MILLISECOND, 0);
        //返回凌晨毫秒数
        return calendar.getTimeInMillis() / 1000;
    }

    /**
     * 根据时间戳计算当天7:00毫秒数
     *
     * @param
     * @return java.lang.Long
     * @date 2018/7/26
     */
    public static Long getStartMillisecondsAtSeven(Long startTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(startTime);
        //时
        calendar.set(Calendar.HOUR_OF_DAY, 7);
        //分
        calendar.set(Calendar.MINUTE, 0);
        //秒
        calendar.set(Calendar.SECOND, 0);
        //毫秒
        calendar.set(Calendar.MILLISECOND, 0);
        //返回凌晨毫秒数
        return calendar.getTimeInMillis() / 1000;
    }

    /**
     * 根据时间戳计算指定时刻59分毫秒数
     *
     * @param
     * @return java.lang.Long
     * @date 2018/7/26
     */
    public static Long getEndMillisecondsAtHour(Integer h, Long startTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(startTime);
        //时
        calendar.set(Calendar.HOUR_OF_DAY, h);
        //分
        calendar.set(Calendar.MINUTE, 59);
        //秒
        calendar.set(Calendar.SECOND, 59);
        //毫秒
        calendar.set(Calendar.MILLISECOND, 999);
        //返回23:59毫秒数
        return calendar.getTimeInMillis() / 1000;
    }

    /**
     * 根据时间戳计算当天21:59毫秒数
     *
     * @param
     * @return java.lang.Long
     * @date 2018/7/26
     */
    public static Long getEndMillisecondsAtTen(Long startTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(startTime);
        //时
        calendar.set(Calendar.HOUR_OF_DAY, 21);
        //分
        calendar.set(Calendar.MINUTE, 59);
        //秒
        calendar.set(Calendar.SECOND, 59);
        //毫秒
        calendar.set(Calendar.MILLISECOND, 999);
        //返回凌晨毫秒数
        return calendar.getTimeInMillis() / 1000;
    }

    /**
     * 根据时间戳计算当天23:59秒数
     *
     * @param
     * @return java.lang.Long
     * @date 2018/7/26
     */
    public static Long getEndMilliseconds(Long startTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(startTime);
        //时
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        //分
        calendar.set(Calendar.MINUTE, 59);
        //秒
        calendar.set(Calendar.SECOND, 59);
        //毫秒
        calendar.set(Calendar.MILLISECOND, 999);
        //返回23:59秒数
        return calendar.getTimeInMillis() / 1000;
    }

    /**
     * 获取当前月第一天的00:00:00毫秒数
     *
     * @param
     * @return java.lang.Long
     * @date 2018/8/30
     */
    public static Long getStartMillisecondsMonth() {
        //获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH),
                00, 00, 00);
        return c.getTimeInMillis() / 1000;
    }

    /**
     * 获取当前月最后一天的23:59:59毫秒数
     *
     * @param
     * @return java.lang.Long
     * @date 2018/8/30
     */
    public static Long getEndMillisecondsMonth() {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        //将小时至0
        ca.set(Calendar.HOUR_OF_DAY, 23);
        //将分钟至0
        ca.set(Calendar.MINUTE, 59);
        //将秒至0
        ca.set(Calendar.SECOND, 59);
        //将毫秒至0
        ca.set(Calendar.MILLISECOND, 999);
        return ca.getTimeInMillis() / 1000;
    }

    public static Long getYearStartTime() {
        Calendar calendar = Calendar.getInstance();// 获取当前日期
        calendar.add(Calendar.YEAR, 0);
        calendar.add(Calendar.DATE, 0);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis() / 1000;
    }


    public static Long getYearEndTime() {
        Calendar calendar = Calendar.getInstance();// 获取当前日期
        int year = calendar.get(Calendar.YEAR);
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        return calendar.getTimeInMillis() / 1000;
    }

    /**
     * 获取年月
     *
     * @return
     */
    public static String getYearMonthDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        String str = "";
        if (month < 10) {
            str = String.valueOf(year) + "0" + String.valueOf(month);
        } else {
            str = String.valueOf(year) + String.valueOf(month);
        }
        return str;
    }


    /**
     * 获取年份后两位
     *
     * @return
     */
    public static String getYearDate() {
//        Calendar cal = Calendar.getInstance();
//        int year = cal.get(Calendar.YEAR);
//        return String.valueOf(year);
        Calendar startTime = Calendar.getInstance();
        int year = startTime.get(Calendar.YEAR) - 20;
        // 这里初始化时间，然后设置年份。只以年份为基准，不看时间
        startTime.clear();
        startTime.set(Calendar.YEAR, year);
        SimpleDateFormat formatter = new SimpleDateFormat("yy");
        formatter.setLenient(false);
        formatter.set2DigitYearStart(startTime.getTime());
        return formatter.format(new Date());
    }


    public static String paseMillToDate(Long time) {
        if (time == null || time == 0) {
            return "";
        }
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis((System.currentTimeMillis() / 1000) * 1000);
        Date date = c.getTime();
        return dateFormat.format(date);
    }


    /**
     * 根据起始时间戳毫秒值和结束时间戳毫秒值计算月份
     *
     * @param
     * @return java.la
     * ng.Long
     * @date 2018/6/8
     */
    public static Integer getMonth(Long startTime, Long endTime) {
        String format = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String start = sdf.format(new Date(startTime));
        String end = sdf.format(new Date(endTime));
        String[] startArr = start.split("-");
        String[] endArr = end.split("-");
        int startYear = Integer.valueOf(startArr[0]);
        int endYear = Integer.valueOf(endArr[0]);
        int startMonth = Integer.valueOf(startArr[1]);
        int endMonth = Integer.valueOf(endArr[1]);
        int month = (endYear - startYear) * 12 + endMonth - startMonth;
        return month;
    }

    /**
     * 判断两个日期是不是同一个月
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean equalsDate(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        int year1 = calendar1.get(Calendar.YEAR);
        int year2 = calendar2.get(Calendar.YEAR);
        int month1 = calendar1.get(Calendar.MONTH);
        int month2 = calendar2.get(Calendar.MONTH);
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH);
    }

    /**
     * 计算两个日期之间相差多少钱天
     */
    public static Long twoDateAllDays(Date startDate, Date endDate) {
        LocalDate date1 = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate date2 = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return ChronoUnit.DAYS.between(date1, date2);
    }
}
