package org.example.leetcode.g0.lc62;

class Solution {
    // DP
    // f(i, j) 代表到达 ij 时的方案数
    // f(i, j) = f(i-1, j) + f(i, j-1)
    // f(0, x) = 1, f(x, 0) = 1，可以添加一圈辅助空格进行计算
    // 节省空间复杂度
    public int uniquePaths(int m, int n) {
        var dp = new int[n + 1];
        dp[1] = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[j + 1] += dp[j];
            }
        }

        return dp[n];
    }
}