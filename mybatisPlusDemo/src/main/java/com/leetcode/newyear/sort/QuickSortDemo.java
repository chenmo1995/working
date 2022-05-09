package com.leetcode.newyear.sort;

/**
 * 理想的情况是，每次划分所选择的中间数恰好将当前序列几乎等分，经过log2n趟划分，便可得到长度为1的子表。
 * 这样，整个算法的时间复杂度为O(nlog2n)。
 * <p>
 * 最坏的情况是，每次所选的中间数是当前序列中的最大或最小元素，这使得每次划分所得的子表中一个为空表，
 * 另一子表的长度为原表的长度-1。这样，长度为n的数据表的快速排序需要经过n趟划分，使得整个排序算法的时间复杂度为O(n2)。
 *
 * 还可以调整标准值的选择，为了避免极端最坏情况，可以每次去取 arr[l],arr[r],arr[l+r >> 1] 三者的中间值标准值
 * 然后将他与第一个元素交换，后续就是继续按照第一个元素为标准值继续快排
 *
 * @author fdn
 * @since 2022-01-26 10:34
 */
public class QuickSortDemo {

    public static void main(String[] args) {
        int[] arr = new int[]{5, 4, 8, 56, 2, 1, 8, 9, 6, 54, 98, 51, 54, 5, 6, 15, 32};
        int n = arr.length;
        quickSort(arr, 0, n - 1);
        for (int i : arr) {
            System.out.print(i + "\t");
        }
    }

    private static void quickSort(int[] arr, int left, int right) {
        int l = left, r = right;
        if (left >= right) {
            return;
        }
        int standard = arr[left];
        while (left < right) {
            while (left < right && arr[right] >= standard) {
                right--;
            }
            if (left < right) {
                arr[left++] = arr[right];
            }
            while (left < right && arr[left] <= standard) {
                left++;
            }
            if (left < right) {
                arr[right--] = arr[left];
            }
        }
        arr[left] = standard;
        quickSort(arr, l, left - 1);
        quickSort(arr, left + 1, r);
    }


}
