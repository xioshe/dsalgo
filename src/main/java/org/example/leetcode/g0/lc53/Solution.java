package org.example.leetcode.g0.lc53;

class Solution {
    public int maxSubArray(int[] nums) {
        var result = nums[0];
        // 记录以当前数字为结尾的子数组之和的最大值
        // 可能是前一个子数组 + 当前数，也可能不包括前一个子数组
        var sum = 0;
        for (int num : nums) {
            sum += num;
            if (sum < num) {
                sum = num;
            }
            result = Math.max(result, sum);
        }
        return result;
    }
}
