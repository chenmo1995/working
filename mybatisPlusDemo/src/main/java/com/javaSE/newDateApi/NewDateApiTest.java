package com.javaSE.newDateApi;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @author fdn
 * @since 2021-09-22 14:33
 */
public class NewDateApiTest {
    public static void main(String[] args) {
        //旧的方式，直接new
        Date date = new Date();
        System.out.println("old date "+date);

        // 新的方式，只能通过给定的方法获取
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDate"+localDate);
        System.out.println("localTime"+localTime);
        System.out.println("localDateTime"+localDateTime);

        //可以用of进行组合
        LocalDateTime combineDateTime  = LocalDateTime.of(localDate, localTime);
        System.out.println("combineDateTime "+combineDateTime );

        // 创建指定时间
        LocalDate customDate = LocalDate.of(2020, 11, 5);
        LocalTime customTime = LocalTime.of(16, 30, 0);
        LocalDateTime customDateTime = LocalDateTime.of(2020, 11, 5, 16, 30, 0);
        System.out.println("customDate" + customDate);
        System.out.println("customTime" + customTime);
        System.out.println("customDateTime" + customDateTime);

        // 获取时间参数的年、月、日（有时需求要用到）
        System.out.println("获取时间参数的年、月、日：");
        LocalDateTime param = LocalDateTime.now();
        System.out.println("year:" + param.getYear());
        System.out.println("month:" + param.getMonth());
        System.out.println("day:" + param.getDayOfMonth());
        System.out.println("hour:" + param.getHour());
        System.out.println("minute:" + param.getMinute());
        System.out.println("second:" + param.getSecond() + "\n");

        // 计算昨天的同一时刻（由于对象不可修改，所以返回的是新对象）
        System.out.println("计算前一天的当前时刻：");
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime yesterday = today.plus(-1, ChronoUnit.DAYS);
        System.out.println("today:" + today);
        System.out.println("yesterday:" + yesterday);
        System.out.println("same object:" + today.equals(yesterday) + "\n");

        // 计算当天的00点和24点（你看，这里就看到组合的威力了）
        System.out.println("计算当天的00点和24点：");
        LocalDateTime todayBegin = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime todayEnd = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        System.out.println("todayBegin:" + todayBegin);
        System.out.println("todayEnd:" + todayEnd + "\n");

        System.out.println("计算一周、一个月、一年前的当前时刻：");
        LocalDateTime oneWeekAgo = today.minus(1, ChronoUnit.WEEKS);
        LocalDateTime oneMonthAgo = today.minus(1, ChronoUnit.MONTHS);
        LocalDateTime oneYearAgo = today.minus(1, ChronoUnit.YEARS);
        System.out.println("oneWeekAgo" + oneWeekAgo);
        System.out.println("oneMonthAgo" + oneMonthAgo);
        System.out.println("oneYearAgo" + oneYearAgo + "\n");

        //如何修改时间：with
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now:" + now);
        // 将day修改为6号
        LocalDateTime modifiedDateTime = now.with(ChronoField.DAY_OF_MONTH, 6);
        System.out.println("modifiedDateTime:" + modifiedDateTime);

        //如何比较时间：isAfter/isBefore/isEqual
        LocalDateTime after = today.plusSeconds(1);
        boolean result = after.isAfter(today);
        System.out.println("result=" + result);

        //时区
        // 当地时间
        System.out.println("localDateTime:" + now);
        // 时区（id的形式），默认的是本国时区
        ZoneId zoneId = ZoneId.systemDefault();
        // 为localDateTime补充时区信息
        ZonedDateTime beijingTime = now.atZone(zoneId);
        System.out.println("beijingTime:" + beijingTime);

        //转换成秒
        long result1 = now.toEpochSecond(ZoneOffset.ofHours(8));
        System.out.println(result1);
        LocalDateTime localDateTime1 = LocalDateTime.ofEpochSecond(result1, 0, ZoneOffset.ofHours(8));
        System.out.println(localDateTime1);

        //格式化
        System.out.println("格式化前:" + now);
        String format = now.format(DateTimeFormatter.ISO_DATE_TIME);
        System.out.println("默认格式:" + format);
        String other = now.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println("其他格式:" + other);
        String format1 = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        System.out.println("自定义格式化后:" + format1);
        LocalDateTime parse = LocalDateTime.parse(format, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        System.out.println("反格式化:" + parse);
    }

}

final class DatePattern {

    public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.sss";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD_WITH_SLASH = "yyyy/MM/dd";
    public static final String YYYY_MM_DD_WITH_STRIKE = "yyyy-MM-dd";
    // ...
}