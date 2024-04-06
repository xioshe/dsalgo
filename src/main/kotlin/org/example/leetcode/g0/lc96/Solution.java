package org.example.leetcode.g0.lc96;

class Solution {
    // 规律一：二叉搜索数的特点是 all 左子树 < 根节点 < all 右
    // 每个数 i 作为根节点时，分为左右两部分，[1:i-1] [i+i:n]，总的方案数为两者乘积
    // 总的方案数为每个 i 的方案数之和
    // 规律二：前 i 个和后 i 个的方案数一样，换而言之，长度相等的数组方案不一样但方案数一样

    // f(n) 为长度 为 n 的数组的方案数
    // g(i, n) 为以 i 为根节点，长度为 n 的数组的方案数
    // f(n) = sum(g(i, n), i in n)
    // f(0) = f(1) = 1
    // g(i, n) = f(i - 1) * f(n - i)

    // f(n) = sum(f(i-1) * f(n-i), i in [1, n])
    // n 从 2 开始构建
    public int numTrees(int n) {
        var dp = new int[n + 1];
        dp[0] = dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }

        return dp[n];
    }
}
