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
        var nums = new int[]{3, 2, 1, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        System.out.println(findKth(nums, 5));
    }

    public static void quickSort(int[] num, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivot = partition(num, left, right);
        quickSort(num, left, pivot - 1);
        quickSort(num, pivot + 1, right);
    }

    private static int partition(int[] nums, int left, int right) {
        var median = median(nums, left, left + (right - left) / 2, right);
        swap(nums, left, median);
        int pivot = nums[left];

        var i = left;
        var j = right;
        while (i < j) {
            while (i < j && nums[j] >= pivot) {
                j--;
            }
            while (i < j && nums[i] <= pivot) {
                i++;
            }
            swap(nums, i, j);
        }
        swap(nums, left, i);
        return i;
    }

    // 三个数的中位数
    private static int median(int[] nums, int left, int mid, int right) {
        if ((nums[left] < nums[right]) ^ (nums[left] < nums[mid])) {
            return left;
        }
        if ((nums[mid] < nums[left]) ^ (nums[mid] < nums[right])) {
            return mid;
        }
        return right;
    }

    private static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        var temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 快速排序查找第 k 大的数
    public static int findKth(int[] nums, int k) {
        if (nums.length < k) {
            return -1;
        }
        int target = nums.length - k;
        int start = 0;
        int end = nums.length - 1;
        int index = partition(nums, start, end);
        // 第 k 大的数下标是 len - k
        while (index != target) {
            if (index < target) {
                start = index + 1;
            } else {
                end = index - 1;
            }

            index = partition(nums, start, end);
        }

        return nums[index];
    }
}

