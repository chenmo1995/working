package com.leetcode.practice;

import java.util.Arrays;

/**
 * @author fdn
 * @since 2021-12-16 14:43
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {5, 9, 55, 87, 54, 1, 65, 98, 51, 65, 98, 45, 15, 5};
        quicksort2(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    private static void quicksort2(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int standard = arr[left];
        int l = left, r = right;
        while (right > left) {
            while (arr[right] >= standard && right > left) {
                right--;
            }
            arr[left] = arr[right];
            while (arr[left] <= standard && left < right) {
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = standard;
        quicksort(arr, l, left - 1);
        quicksort(arr, left + 1, r);
    }

    private static void quicksort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int standard = arr[left];
        int l = left, r = right;
        while (right > left) {
            while (arr[right] >= standard && right > left) {
                right--;
            }
            while (arr[left] <= standard && left < right) {
                left++;
            }
            int temp = arr[right];
            arr[right] = arr[left];
            arr[left] = temp;
        }
        //riht = left
        arr[l] = arr[left];
        arr[left] = standard;
        quicksort(arr, l, left - 1);
        quicksort(arr, left + 1, r);
    }
}
