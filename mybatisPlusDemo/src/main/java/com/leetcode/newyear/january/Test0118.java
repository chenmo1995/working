package com.leetcode.newyear.january;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author fdn
 * @since 2022-01-18 09:48
 */
public class Test0118 {

    /**
     * 539. 最小时间差
     *
     * @param timePoints
     * @return
     */
    public int findMinDifference(List<String> timePoints) {
        int max = 24 * 60;
        int size = timePoints.size();
        if (size > max) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        List<Integer> minutes = new ArrayList<>();
        // HH:MM 转成分钟数
        for (String timePoint : timePoints) {
            int minute = toMinute(timePoint);
            minutes.add(minute);
        }
        Collections.sort(minutes);
        for (int i = 1; i < minutes.size(); i++) {
            min = Math.min(minutes.get(i) - minutes.get(i - 1), min);
        }

        return Math.min(min, 1440 - (minutes.get(size - 1) - minutes.get(0)));
    }

    private int toMinute(String timePoint) {
        int hours = Integer.parseInt(timePoint.substring(0, 2));
        int minute = Integer.parseInt(timePoint.substring(3, 5));
        return hours * 60 + minute;
    }

    public static void main(String[] args) {
        Test0118 test0118 = new Test0118();
        int i = test0118.findMinDifference(new ArrayList<>());
        System.out.println(i);
        System.out.println(23 * 24 + 59);
        System.out.println(23 * 24);
        System.out.println(01);
    }
}
