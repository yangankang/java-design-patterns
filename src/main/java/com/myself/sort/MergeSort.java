package com.myself.sort;

import java.util.Arrays;

public class MergeSort implements Sort {
    public static void mergeSort(int[] array) {

        int length = array.length;
        int middle = length / 2;

        if (length > 1) {
            int[] left = Arrays.copyOfRange(array, 0, middle);//拷贝数组array的左半部分
            int[] right = Arrays.copyOfRange(array, middle, length);//拷贝数组array的右半部分
            mergeSort(left);//递归array的左半部分
            mergeSort(right);//递归array的右半部分
            merge(array, left, right);//数组左半部分、右半部分合并到Array
        }
    }

    //合并数组，升序
    private static void merge(int[] result, int[] left, int[] right) {

        int i = 0, l = 0, r = 0;

        while (l < left.length && r < right.length) {
            if (left[l] < right[r]) {
                result[i] = left[l];
                i++;
                l++;
            } else {
                result[i] = right[r];
                i++;
                r++;
            }
        }

        while (r < right.length) {//如果右边剩下合并右边的
            result[i] = right[r];
            r++;
            i++;
        }

        while (l < left.length) {
            result[i] = left[l];
            l++;
            i++;
        }
    }

    @Override
    public int[] sort(int[] source) {
        mergeSort(source);
        return source;
    }
}
