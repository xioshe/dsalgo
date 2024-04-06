package org.example.leetcode.g0.lc70;

class Solution {
    // f(i) 表示爬上 i 层的方案数
    // f(i) = f(i-1) + f(i-2)
    // f(o) 没意义为 0
    // f(1) = 1
    // f(2) = 2
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        var a = 1;
        var b = 2;
        for (int i = 3; i < n; i++) {
            int temp = b;
            b = a + b;
            a = temp;
        }
        return b;
    }
}