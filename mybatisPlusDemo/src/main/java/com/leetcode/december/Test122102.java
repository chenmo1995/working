package com.leetcode.december;

import java.util.*;

/**
 * @author fdn
 * @since 2021-12-21 13:45
 */
public class Test122102 {



    /**
     * 1154. 一年中的第几天
     * <p>
     * YYYY-MM-DD
     *
     * @param date
     * @return
     */
    public int dayOfYear(String date) {
        int sum = 0;
        List<Integer> monthList = new ArrayList<>();
        monthList.add(31);
        monthList.add(28);
        monthList.add(31);
        monthList.add(30);
        monthList.add(31);
        monthList.add(30);
        monthList.add(31);
        monthList.add(31);
        monthList.add(30);
        monthList.add(31);
        monthList.add(30);
        int year = Integer.parseInt(date.substring(0, 4));

        if ((year % 100 == 0 && year % 400 == 0) || (year % 100 != 0 && year % 4 == 0)) {
            monthList.set(1, 29);
        }
        int month = Integer.parseInt(date.substring(5, 7));
        for (int i = 0; i < month - 1; i++) {
            sum += monthList.get(i);
        }
        return sum + Integer.parseInt(date.substring(8));
    }
}

