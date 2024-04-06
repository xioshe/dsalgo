package org.example.leetcode.g800.lc875;

import java.util.Arrays;

class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        if (h < piles.length) {
            return 0;
        }
        var max = Arrays.stream(piles).max().orElse(0);
        if (h == piles.length) {
            return max;
        }

        var k = max;
        var left = 1;
        var right = max;
        while (left <= right) {
            var mid = left + ((right - left) >> 1);
            long hour = getTime(piles, mid);
            if (hour <= h) {
                k = Math.min(k, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return k;
    }

    private long getTime(int[] nums, int k) {
        var hours = 0L;
        for (int num : nums) {
            hours += (num + k - 1) / k;
        }
        return hours;
    }

    public static void main(String[] args) {
//        System.out.println(new Solution().minEatingSpeed(new int[]{2, 3, 4, 5}, 4));
//        System.out.println(new Solution().minEatingSpeed(new int[]{2, 3, 4, 5}, 3));
//        System.out.println(new Solution().minEatingSpeed(new int[]{3, 6, 7, 11}, 8));
//        System.out.println(new Solution().minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 6));
//        System.out.println(new Solution().minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 5));
        System.out.println(new Solution().minEatingSpeed(new int[]{805306368,805306368,805306368}, 1000000000));
    }
}
