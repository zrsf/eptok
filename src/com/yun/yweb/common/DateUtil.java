package com.yun.yweb.common;

import hikefa.core.exception.BizException;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 时间操作工具类
 * 
 * @author liudw
 * 
 */
public class DateUtil {
	
	private static String formatDate = "yyyyMMdd";
	
	private DateUtil() {
		 // Do nothing 
	}

    /**
     * <p>
     * 创建时间
     * </p>
     * 
     * @param year
     *            "2007" 形式的字符串
     * @param month
     *            "09" 形式的字符串
     * @param date
     *            "05" 形式的字符串
     * @return 返回"2007-09-05"格式化的时间(java.util.Date)
     */
    public static final java.util.Date getDate(String year, String month, String date) {

        int day = Integer.parseInt(date);
        int monthT = Integer.parseInt(month);
        int yearT = Integer.parseInt(year);
        Calendar rightNow = Calendar.getInstance();
        rightNow.set(yearT, monthT - 1, day);
        return new Date(rightNow.getTime().getTime());

    }

    /**
     * <p>
     * 创建时间
     * </p>
     * 
     * @param year
     *            2007 形式的数字
     * @param month
     *            7 形式的数字
     * @param date
     *            7 形式的数字
     * @return 返回"2007-07-07"格式化的时间(java.util.Date)
     */
    public static final java.util.Date getDate(int year, int month, int date) {

        Calendar rightNow = Calendar.getInstance();
        rightNow.set(year, month - 1, date);
        return new Date(rightNow.getTime().getTime());

    }

    /**
     * <p>
     * 时间类型转换
     * </p>
     * 
     * @param date
     *            "2007-09-05" 形式的字符串,年必须是四个字符，月是两个字符，日是两个字符
     * @return 返回"2007-09-05"格式化的时间(java.util.Date)
     */
    public static final java.util.Date getDate(String date) {

        return getDate(getYear(date), getMonth(date), getDay(date));
    }

    /**
     * <p>
     * 取一个时间的字段:年
     * </p>
     * 
     * @param newDate
     *            "2007-09-05" 形式的字符串,年必须是四个字符，月是两个字符，日是两个字符
     * @return 返回 int 类型的数字 <b>年</b> 如：2007
     */
    public static int getYear(String newDate) {
        if (newDate == null || newDate.trim().equals("") || newDate.trim().length() < 4) {
            return 0;
        }
        else {
            return Integer.parseInt(newDate.substring(0, 4));
        }
    }

    /**
     * <p>
     * 取一个时间的字段:年
     * </p>
     * 
     * @param newDate
     *            java.util.Date 类型的时间对象Date
     * @return 返回 int 类型的数字 <b>年</b> 如：2007
     */
    public static int getYear(java.util.Date newDate) {

        if (newDate == null) {
            return 0;
        }
        else {
            Calendar mycal = Calendar.getInstance();
            mycal.setTime(newDate);
            return mycal.get(Calendar.YEAR);
        }

    }

    /**
     * <p>
     * 取一个时间的字段:月
     * </p>
     * 
     * @param date
     *            java.util.Date 类型的时间对象Date
     * @return 返回 int 类型的数字 <b>月</b> 如：9
     */
    public static int getMonth(java.util.Date date) {

        if (date == null) {
            return 0;
        }
        else {
            Calendar mycal = Calendar.getInstance();
            mycal.setTime(date);
            return mycal.get(Calendar.MONTH) + 1;
        }

    }

    /**
     * <p>
     * 取一个时间的字段:月
     * </p>
     * 
     * @param date
     *            "2007-09-05" 形式的字符串,年必须是四个字符，月是两个字符，日是两个字符
     * @return 返回 int 类型的数字 <b>月</b> 如：9
     */
    public static int getMonth(String date) {
        if (date == null || date.trim().equals("") || date.trim().length() < 7) {
            return 0;
        }
        else {
            return Integer.parseInt(date.substring(5, 7));
        }
    }

    /**
     * <p>
     * 取一个时间的字段:日
     * </p>
     * 
     * @param date
     *            java.util.Date 类型的时间对象Date
     * @return 返回 int 类型的数字 <b>日</b> 如：4
     */
    public static int getDay(java.util.Date date) {
        if (date == null) {
            return 0;
        }
        else {
            Calendar mycal = Calendar.getInstance();
            mycal.setTime(date);
            return mycal.get(Calendar.DATE);
        }
    }

    /**
     * @param date
     *            java.util.Date 类型的时间对象Date
     * @param length
     *            String 长度是7或者是10
     * @return 返回 String
     *         类型的时间，如果length的值是7,返回结果的时间格式为<b>"2007-09"</b>,如果length的值是10
     *         ,返回结果的时间格式为<b>"2007-09-14"</b>,否则返回字符<b>"0000-00-00"</b>；
     */
    public static final String getDate(java.util.Date date, int length) {

        if (date == null) {
            return "0000-00-00";
        }
        Calendar mycal = Calendar.getInstance();
        mycal.setTime(date);
        String month = null;
        String dateT = null;
        if (getMonth(date) < 10) {
            month = "-0" + getMonth(date);
        }
        else {
            month = "-" + getMonth(date);
        }

        if (getDay(date) < 10) {
            dateT = "-0" + getDay(date);
        }
        else {
            dateT = "-" + getDay(date);
        }

        if (length == 7) {
            return getYear(date) + month;
        }
        else if (length == 10) {
            return getYear(date) + month + dateT;
        }
        else {
            return "0000-00-00";
        }

    }

    /**
     * <p>
     * 取一个时间的字段:日
     * </p>
     * 
     * @param date
     *            "2007-09-05" 形式的字符串,年必须是四个字符，月是两个字符，日是两个字符
     * @return 返回 int 类型的数字 <b>日</b> 如：5
     */
    public static int getDay(String date) {
        if (date == null || date.trim().equals("") || date.trim().length() < 10) {
            return 0;
        }
        else {
            return Integer.parseInt(date.substring(8));
        }
    }

    /**
     * <p>
     * 返回时间栈的时间
     * </p>
     * 
     * @param date
     *            "2007-09-05" 形式的时间字符串,年必须是四个字符，月是两个字符，日是两个字符
     * @param time
     *            "15:45" 形式的时间字符串,HOUR 必须是两个字符，MINUTE 是两个字符
     * @return 返回 java.sql.Timestamp 类型的时间 如：<b>2007-09-05 03:45:04.203</b>
     */
    public static final java.sql.Timestamp getTimestamp(String date, String time) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getDate(getYear(date), getMonth(date), getDay(date)));
        calendar.set(Calendar.HOUR, Integer.parseInt(time.substring(0, time.indexOf(':'))));
        calendar.set(Calendar.MINUTE, Integer.parseInt(time.substring(time.indexOf(':') + 1)));
        return new java.sql.Timestamp(calendar.getTime().getTime());

    }

    /**
     * <p>
     * 取系统当前时间
     * </p>
     * 
     * @return 返回系统当前时间(java.util.Date类型 0000-00-00)
     */
    public static final java.util.Date getTodayDate() {
        long date = System.currentTimeMillis();
        return new java.util.Date(date);
    }

    /**
     * <p>
     * 取系统当前时间
     * </p>
     * 
     * @param style
     *            DateStyleEnum 样式
     * @return 返回系统当前时间
     */
    public static final String getFormatDate(String style) {

        Calendar rightNow = Calendar.getInstance();
        SimpleDateFormat fmt = new SimpleDateFormat(style);
        return fmt.format(rightNow.getTime());

    }

    /**
     * <p>
     * 格式化日期
     * <p>
     * 
     * @param date
     *            java.util.Date类型的时间
     * @param style
     *            DateStyleEnum 样式
     * @return 返回时间字符串
     * @throws ParseException
     */
    public static final Date getCurrentDate(String dateStr) throws ParseException {
        // Date date = new Date();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return fmt.parse(dateStr);
    }

    /**
     * <p>
     * 格式化日期
     * <p>
     * 
     * @param date
     *            java.util.Date类型的时间
     * @param style
     *            DateStyleEnum 样式
     * @return 返回时间字符串
     */
    public static final String formatDate(String style) {

        SimpleDateFormat fmt = new SimpleDateFormat(style);
        return fmt.format(new Date());
    }

    /**
     * <p>
     * 日期按天累加
     * </p>
     * 
     * @param date
     *            java.util.Date类型的时间
     * @param days
     *            增加的天数
     * @return 返回java.util.Date类型的时间(0000-00-00)
     */
    public static final java.util.Date getDateAddDay(java.util.Date date, int days) {

        if (date == null) {
            return null;
        }
        // java.sql.Date dret=new
        // java.sql.Date(dd.getTime()+(days*60*60*1000*24));
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(new java.util.Date(date.getTime()));
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day + days);

        return new java.util.Date(calendar.getTime().getTime());
    }

    /**
     * <p>
     * 日期按月累加
     * </p>
     * 
     * @param date
     *            java.util.Date类型的时间
     * @param months
     *            增加的月数
     * @return 返回java.util.Date类型的时间(0000-00-00)
     */
    public static final java.util.Date getDateAddMonth(java.util.Date date, int months) {

        if (date == null) {
            return null;
        }
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(new java.util.Date(date.getTime()));
        int month = calendar.get(java.util.Calendar.MONTH);
        calendar.set(java.util.Calendar.MONTH, month + months);

        return new java.util.Date(calendar.getTime().getTime());
    }

    /**
     * <p>
     * 设置日期中的参数，可以对年、月、日、时、分、秒等进行设置
     * </p>
     * 
     * @param date
     *            java.util.Date类型的时间
     * @param param
     *            java.util.Calendar的常量值,设置日期中的参数，可以对年、月、日、时、分、秒等进行修改设置
     * @param value
     *            void
     */
    public static void setDateParameter(java.util.Date date, int param, int value) {

        if (date == null) {
            return;
        }
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(new java.util.Date(date.getTime()));
        calendar.set(param, value);
        date.setTime(calendar.getTime().getTime());

    }

    /**
     * <p>
     * 取日期中的参数，可以对年、月、日、时、分、秒等进行读取
     * </p>
     * 
     * @param date
     *            java.util.Date类型的时间
     * @param param
     *            java.util.Calendar的常量值,取日期中的参数，可以对年、月、日、时、分、秒等进行读取
     * @return 返回int类型的值(年、月、日、时、分、秒等)
     */
    public static final int getDateParameter(java.util.Date date, int param) {
        if (date == null) {
            return -1;
        }
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(new java.util.Date(date.getTime()));
        return calendar.get(param);

    }

    // 计算日期间隔
    /**
     * <p>
     * 计算日期间隔的天数
     * </p>
     * 
     * @param startdate
     *            开始时间java.util.Date类型
     * @param enddate
     *            结束时间java.util.Date类型
     * @return 返回long类型的值(表示相差的天数)
     */
    public static final long diffDate(java.util.Date startdate, java.util.Date enddate) {

        return (startdate.getTime() - enddate.getTime()) / 86400000;

    }

    /*
     * 下面的程序片断用简单的整数除法转换毫秒到秒：
     * 
     * long milliseconds = 1999; long seconds = 1999 / 1000;
     * 
     * 这种方法舍去小数部分转换毫秒到秒，所以1,999毫秒等于1秒，2,000毫秒等于2秒。
     * 计算更大的单位-例如天数，小时和分钟-给定一个时间数值，可以使用下面的过程： 1. 计算最大的单位，减去这个数值的秒数 2.
     * 计算第二大单位，减去这个数值的秒数 3. 重复操作直到只剩下秒 例
     * 如,如果你的时间的10,000秒，你想知道这个数值相应的是多少小时，多少分钟，多少秒，你从最大的单位开始：小时。10,000除以3600（一个小
     * 时的秒数）得到小时数。使用整数除法，答案是2小时（整数除法中小数舍去）计算剩下的秒数，10,000-(3,600 x 2) =
     * 2,800秒。所以你有2小时和2,800秒。 将2,800秒转换成分钟，2,800除以60。使用整数除法，答案是46。2,800 - (60 x
     * 46) = 40秒。最后答案是2小时，46分，40秒。
     */
    /**
     * <p>
     * 将毫秒转换成小时，分，秒
     * </p>
     * 
     * @param timeInSeconds
     *            毫秒值
     * @return 返回时分秒的时间字符串
     */
    public static final String calcHM(long timeInSeconds) {

        String shortdate = "";
        long hours;
        long minutes;
        long seconds;
        hours = timeInSeconds / 3600;
        timeInSeconds = timeInSeconds - (hours * 3600);
        minutes = timeInSeconds / 60;
        timeInSeconds = timeInSeconds - (minutes * 60);
        seconds = timeInSeconds;
        shortdate = hours + " hors " + minutes + " minute " + seconds + " second ";
        return shortdate;
    }

    /**
     * <p>
     * 计算两个时间间隔的天数
     * </p>
     * 
     * @param g1
     *            java.util.GregorianCalendar 类型的时间
     * @param g2
     *            java.util.GregorianCalendar 类型的时间
     * @return 返回int类型的天数
     */
    public static final int getDays(GregorianCalendar g1, GregorianCalendar g2) {

        int elapsed = 0;
        GregorianCalendar gc1;
        GregorianCalendar gc2;

        if (g2.after(g1)) {
            gc2 = (GregorianCalendar) g2.clone();
            gc1 = (GregorianCalendar) g1.clone();
        }
        else {
            gc2 = (GregorianCalendar) g1.clone();
            gc1 = (GregorianCalendar) g2.clone();
        }

        gc1.clear(Calendar.MILLISECOND);
        gc1.clear(Calendar.SECOND);
        gc1.clear(Calendar.MINUTE);
        gc1.clear(Calendar.HOUR_OF_DAY);

        gc2.clear(Calendar.MILLISECOND);
        gc2.clear(Calendar.SECOND);
        gc2.clear(Calendar.MINUTE);
        gc2.clear(Calendar.HOUR_OF_DAY);

        while (gc1.before(gc2)) {
            gc1.add(Calendar.DATE, 1);
            elapsed++;
        }
        return elapsed;
    }

    /**
     * <p>
     * 计算两个时间间隔的月数
     * </p>
     * 
     * @param g1
     *            java.util.GregorianCalendar 类型的时间
     * @param g2
     *            java.util.GregorianCalendar 类型的时间
     * @return 返回int类型的月数
     */
    public static final int getMonths(GregorianCalendar g1, GregorianCalendar g2) {
        int elapsed = 0;
        GregorianCalendar gc1;
        GregorianCalendar gc2;

        if (g2.after(g1)) {
            gc2 = (GregorianCalendar) g2.clone();
            gc1 = (GregorianCalendar) g1.clone();
        }
        else {
            gc2 = (GregorianCalendar) g1.clone();
            gc1 = (GregorianCalendar) g2.clone();
        }

        gc1.clear(Calendar.MILLISECOND);
        gc1.clear(Calendar.SECOND);
        gc1.clear(Calendar.MINUTE);
        gc1.clear(Calendar.HOUR_OF_DAY);
        gc1.clear(Calendar.DATE);

        gc2.clear(Calendar.MILLISECOND);
        gc2.clear(Calendar.SECOND);
        gc2.clear(Calendar.MINUTE);
        gc2.clear(Calendar.HOUR_OF_DAY);
        gc2.clear(Calendar.DATE);

        while (gc1.before(gc2)) {
            gc1.add(Calendar.MONTH, 1);
            elapsed++;
        }
        return elapsed;
    }

    /**
     * @param date
     * @return java.util.Date 有分析跟踪功能的日期
     */
    public static final java.util.Date transferDate(String date, String style) {

        SimpleDateFormat fmt = new SimpleDateFormat(style);
        return fmt.parse(date, new ParsePosition(0));

    }

    /**
     * <p>
     * 比较两个时间的大小<br>
     * 如果dateOne>dateTwo返回1 ,dateOne<dateTwo返回-1, dateOne>dateTwo返回0 ;
     * </p>
     * 
     * @param dateOne
     * @param dateTwo
     * @param style
     *            DateStyleEnum 样式
     * 
     * @return dateOne 和 dateTwo 的比较值
     */
    public static final int compareDate(String dateOne, String dateTwo, String style) {

        java.util.Date dateTempOne = transferDate(dateOne, style);
        java.util.Date dateTempTwo = transferDate(dateTwo, style);
        return dateTempOne.compareTo(dateTempTwo);
    }

    /**
     * 取时间字段月的最大天数
     * 
     * @param date
     * @return int 最大天数
     */
    public static final int maxDay(java.util.Date date) {

        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.DATE, 1);// 把日期设置为当月第一天
        ca.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
        return ca.get(Calendar.DATE);

    }

    /**
     * 设置上个月的第一天时间
     * 
     * @param date
     * @return java.util.Date 上个月的第一天时间
     */
    public static final java.util.Date monthFirstDay(java.util.Date date) {

        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.MONTH, ca.get(Calendar.MONTH) - 1); // 把日期设置为上一个月
        ca.set(Calendar.DATE, 1);// 把日期设置为当月第一天
        return ca.getTime();

    }

    /**
     * 设置当前时间月的第一天
     * 
     * @param date
     * @return java.util.Date 当前时间月的第一天
     */
    public static final java.util.Date currentMonthFirstDay() {

        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DATE, 1);// 把日期设置为当月第一天
        return ca.getTime();

    }

    /**
     * 设置上个月的最后一天时间
     * 
     * @param date
     * @return java.util.Date 上个月的最后一天时间
     */
    public static final java.util.Date monthLastDay(java.util.Date date) {

        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.MONTH, ca.get(Calendar.MONTH) - 1); // 把日期设置为上一个月
        ca.set(Calendar.DATE, 1);// 把日期设置为当月第一天
        ca.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
        return ca.getTime();

    }

    /**
     * 设置当前时间月的最后一天时间
     * 
     * @param date
     * @return java.util.Date 当前时间月的最后一天时间
     */
    public static final java.util.Date currentMonthLastDay() {

        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DATE, 1);// 把日期设置为当月第一天
        ca.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
        return ca.getTime();

    }

    /**
     * 取得当前日期所在周的第一天 注意：周的第一天是从星期天到星期六
     * 
     * @return java.util.Date
     */
    public static java.util.Date getFirstDayOfWeek() {

        Calendar c = new GregorianCalendar();
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
        return c.getTime();

    }

    /**
     * 取得当前日期所在周的最后一天
     * 
     * @return java.util.Date
     */
    public static java.util.Date getLastDayOfWeek() {

        Calendar c = new GregorianCalendar();
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
        return c.getTime();

    }

    public static final java.util.Date test1() {

        Calendar ca = Calendar.getInstance();
        ca.roll(Calendar.DATE, -1);// 日期回滚一天
        ca.set(Calendar.HOUR, 0);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        ca.set(Calendar.MILLISECOND, 0);
        return ca.getTime();

    }

    public static final String genTimeTraceId() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        return sdf.format(new Date());
    }

    public static long getDiffSecond(String datetime1, String datetime2) throws Exception{
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = myFormatter.parse(datetime2);
            Date mydate = myFormatter.parse(datetime1);
            return (date.getTime() - mydate.getTime()) / 1000L;
        }catch (Exception e) {
        	throw new Exception(e);
        }
    }

    /**
     * 增加 默认为时间当天时间
     * 
     * @param startDate
     * @param endDate
     * @return
     */
    public static Map<String, String> dateValue(String startDate, String endDate) {
        Map<String, String> map = new HashMap<String, String>();
        if (startDate != null && !"".equals(startDate)) {
            if (startDate.length() < 15) {// 比较长度 如果重复查询 字符串会一直加00:00:00
                startDate = startDate + " 00:00:00";
            }
        }
        else {
            if (endDate == null || "".equals(endDate)) {
                startDate = DateUtil.formatDate(new Date(), "yyyy-MM-dd") + " 00:00:00";
            }
        }
        if (endDate != null && !"".equals(endDate)) {
            if (endDate.length() < 15) {
                endDate = endDate + " 23:59:59";
            }
        }
        else {
            endDate = DateUtil.formatDate(new Date(), "yyyy-MM-dd") + " 23:59:59";
        }
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        return map;
    }

    /**
     * 格式化当前日期字符串，返回yyyyMMdd
     * 
     * @return
     */
    public static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
        return sdf.format(new Date());
    }

    /**
     * 获取昨天的日期，返回yyyyMMdd
     * 
     * @return
     */
    public static String getLastDate() {
        SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return sdf.format(calendar.getTime());
    }

    /**
     * 格式化当前时间字符串，返回HHmmssSSSsss
     * 
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmssSSSsss");
        return sdf.format(new Date());
    }

    /**
     * 格式化当前日期时间字符串，返回yyyyMMddHHmmssSSS
     * 
     * @return
     */
    public static String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(new Date());
    }

    /**
     * 格式化当前日期时间字符串
     * 
     * @return
     */
    public static String getCurrentDateTime(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(new Date());
    }

    /**
     * 格式化指定时间和指定格式字符串
     * 
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 把字符串按照指定格式转为日期时间
     * 
     * @return
     */
    public static Date toDate(String dateStr, String pattern) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			throw new ParseException("日期转换出错："+ e.getMessage(), 0);
		}
    }

    /**
     * 对指定日期增加或减少多少天
     * 
     * @param sourceDate
     *            基准日期,格式yyyyMMdd
     * @param days
     *            增加或减少天数
     * @return 格式yyyyMMdd
     * @throws Exception
     */
    public static String addDay(String sourceDate, int days) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(sdf.parse(sourceDate));
        }
        catch (Exception ex) {
            throw new BizException(ex);
        }
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return sdf.format(calendar.getTime());
    }

    /**
     * 对指定日期增加或减少多少天
     * 
     * @param sourceDate
     *            基准日期,格式yyyyMMdd
     * @param days
     *            增加或减少天数
     * @return 格式yyMMdd
     * @throws Exception
     */
    public static String addDayEx(String sourceDate, int days) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyMMdd");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(sdf.parse(sourceDate));
        }
        catch (Exception ex) {
            throw new BizException(ex);
        }
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return sdf2.format(calendar.getTime());
    }

    /**
     * 对指定日期增加或减少多少天
     * 
     * @Title: 对指定日期增加或减少多少天
     * @Description: 对指定日期增加或减少多少天
     * @param sourceDate
     * @param days
     * @return
     * @return Date
     * @throws
     */
    public static Date addDay(Date sourceDate, int days) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(sourceDate);
        }
        catch (Exception ex) {
            throw new BizException(ex);
        }
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }
    
    /**
     * 获得两个日期间相隔的天数
     * @param date1 日期大的 格式yyyyMMdd
     * @param date2 日期小的 格式yyyyMMdd
     * @return
     */
    public static long getDateSpace(String date1,String date2) {
    	SimpleDateFormat df = new SimpleDateFormat(formatDate);
    	long to = 0L;
    	long from =0L;
    	try{
    		to = df.parse(date1).getTime();
    		from = df.parse(date2).getTime();
    	} catch (Exception ex) {
            throw new BizException(ex);
        }
		return (to - from) / (1000 * 60 * 60 * 24);
    }
    /**
     * 取出一个指定长度大小的随机正整数.
     *
     * @param length int 设定所取出随机数的长度。length小于11
     * @return int 返回生成的随机数。
     */
    public static int getRandom(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }
    
    /**
     * <p>
     * 格式化日期
     * <p>
     * 
     * @param date
     *            java.util.Date类型的时间
     * @param style
     *            DateStyleEnum 样式
     * @return 返回时间字符串
     * @throws ParseException
     */
    public static final String getFormatDate(Date date, String style) throws ParseException {
        // Date date = new Date();
        SimpleDateFormat fmt = new SimpleDateFormat(style);
        return fmt.format(date);
    }
}
