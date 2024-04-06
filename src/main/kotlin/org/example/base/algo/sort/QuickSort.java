package org.example.base.algo.sort;


import java.util.Arrays;

/**
 * 我觉得快排是算法之路的第一个小 boss，打败了快排才算出了新手村。
 * 分治思想 + 一些算法小技巧
 */
public class QuickSort {

    public static void main(String[] args) {
        var arr = new int[]{3, 2, 1, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right) {
        var pivot = left + (right - left) / 2;
        var pivotValue = arr[pivot];
        var i = left;
        var j = right;
        while (i <= j) {
            while (arr[i] < pivotValue) {
                i++;
            }
            while (arr[j] > pivotValue) {
                j--;
            }
            if (i <= j) {
                swap(arr, i, j);
                i++;
                j--;
            }
            if (left < j) {
                quickSort(arr, left, j);
            }
            if (i < right) {
                quickSort(arr, i, right);
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        var temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

