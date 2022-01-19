package com.leetcode.newyear.january;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fdn
 * @since 2022-01-03 14:43
 */
public class Test0103 {

    /**
     * 1185. 一周中的第几天
     * <p>
     * 1970-12-31   Thursday
     *
     * @param day
     * @param month
     * @param year
     * @return
     */
    public String dayOfTheWeek(int day, int month, int year) {
        List<String> result = new ArrayList<>();
        result.add("Sunday");
        result.add("Monday");
        result.add("Tuesday");
        result.add("Wednesday");
        result.add("Thursday");
        result.add("Friday");
        result.add("Saturday");
        int[] months = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        // year 之前年份的天数
        int yearDays = (year - 1971) * 365 + (year - 1969) / 4;
        int monthDays = 0;
        for (int i = 2; i <= month; i++) {
            monthDays += months[i - 2];
        }
        // 计算 year 年，月份的天数，如果 year 闰年，且三月之后，加一天
        if ((year % 400 == 0) || (year % 4 == 0 && year % 100 != 0) && month >= 3) {
            monthDays++;
        }
        int sum = yearDays + monthDays + day;
        return result.get((sum + 4) % 7);
    }
}
