package com.myself.sort;

public class SortStart {
    public static void main(String[] args) {
        int[] ints = new int[]{4, 5, 6, 1, 4, 5, 7, 8, 6, 8, 9};

        ints = sort(1, ints);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }

    public static int[] sort(int type, int[] source) {
        Sort sort = null;
        if (type == 1) sort = new MergeSort();

        if (sort != null) {
            return sort.sort(source);
        }
        return null;
    }
}
