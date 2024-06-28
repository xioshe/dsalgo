package org.example.leetcode.g100.lc136;

class Solution {
    public int singleNumber(int[] nums) {
        var result = nums[0];
        var n = nums.length;
        for (int i = 1; i < n; i++) {
            result ^= nums[i];
        }
        return result;
    }
}