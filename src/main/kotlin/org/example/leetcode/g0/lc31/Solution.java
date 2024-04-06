package org.example.leetcode.g0.lc31;

class Solution {
    // 规律一：降序排列内部，无法通过交换使数字变大，一定要与外部数交换才行
    // 规律二：降序部分左边的数至少小于降序部分最大的数
    // 进位
    // 将右边降序部分和左边的数交换，并使新的数尽量小
    // 找到降序部分左边第一位数作为交换数，与降序部分大于交换数的最小值交换，然后对剩下的数字进行升序排列
    // 注意，交换后一定要对剩余部分升序排列， 因为已经发生了一次「进位」
    public void nextPermutation(int[] nums) {
        var len = nums.length;
        // 定位最右降序排列
        var left = len - 2;
        while (left >= 0 && nums[left] >= nums[left + 1]) {
            left--;
        }
        // 最小交换
        if (left >= 0) {
            var right = len - 1;
            while (nums[right] <= nums[left]) {
                right--;
            }
            swap(nums, left, right);
        }
        // 将降序转为升序排列
        reverse(nums, left + 1, len - 1);
    }

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums, left++, right--);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


}