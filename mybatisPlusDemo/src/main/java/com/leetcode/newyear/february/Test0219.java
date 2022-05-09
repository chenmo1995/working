package com.leetcode.newyear.february;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author fdn
 * @since 2022-02-21 12:33
 */
public class Test0219 {

    /**
     * 969. 煎饼排序
     * 每次都做两次翻转，一次是从最大的数 index = a 处翻转，将最大数翻转到第一的位置
     * 第二次翻转是整个翻转，让最大值到最后一位
     *
     * @param arr
     * @return
     */
    List<Integer> list = new ArrayList<>();

    public List<Integer> pancakeSort(int[] arr) {
        int n = arr.length;
        pancakeSort2(arr, 0, n - 1);
        return list;
    }

    private void pancakeSort2(int[] arr, int start, int end) {
        if (end == 0) {
            return;
        }
        int index = getMaxIndex(arr, start, end);
        reversal(arr, start, index);
        reversal(arr, start, arr.length - 1 - list.size() / 2);
        list.add(index + 1);
        list.add(arr.length - 1 - list.size() / 2 + 1);
        pancakeSort2(arr, start, end - 1);
    }

    private void reversal(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[end];
            arr[end--] = arr[start];
            arr[start++] = temp;
        }
    }

    private int getMaxIndex(int[] arr, int start, int end) {
        int max = arr[start], index = start;
        while (start <= end) {
            if (arr[start] > max) {
                max = arr[start];
                index = start;
            }
            start++;
        }
        return index;
    }

    public static void main(String[] args) {
        Test0219 test0219 = new Test0219();
        test0219.pancakeSort(new int[]{1,2,3});
    }
}
