package org.example.leetcode.g1200.lc1235;


import java.util.Arrays;
import java.util.Comparator;

// 兼职工作规划
class Solution {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        var n = endTime.length;
        int[][] jobs = new int[n][3];
        for (int i = 0; i < n; i++) {
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(jobs, Comparator.comparingInt(j -> j[1]));

        // 按结束时间排序，前 i 个工作的最大报酬
        var dp = new int[n + 1];
        for (int i = 0; i < n; i++) {
            // 不选择第 i 个兼职，dp[i]
            // 选择第 i 个兼职，找到 end_k <= start_i，dp[k] + p[i]
            int k = le(jobs, i, jobs[i][0]);
            dp[i + 1] = Math.max(dp[i], dp[k] + jobs[i][2]);
        }
        return dp[n];
    }

    private int le(int[][] jobs, int right, int targetTime) {
        var left = 0;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (jobs[mid][1] > targetTime) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}