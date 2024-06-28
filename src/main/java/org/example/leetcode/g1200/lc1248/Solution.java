package org.example.leetcode.g1200.lc1248;


// 统计奇数数量 = k 的子串数量
class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        // 前缀和，哈希反转，统计每一种奇数个数的数量
        var result = 0;
        var counts = new int[nums.length + 1];
        counts[0] = 1;
        var cnt = 0;
        for (int num : nums) {
//            if ((num & 1) == 1) {
//                cnt++;
//            }
            cnt += num & 1;
            counts[cnt]++;
            if (cnt >= k) {
                result += counts[cnt - k];
            }
        }
        return result;
    }
}