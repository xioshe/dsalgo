package org.example.leetcode.g0.lc60;


// 第 k 个排列
class Solution {

    // 用数组记录 n 个数的话，下标 0 对应 1，下标 1 对应 2，下标 n-1 对应 n
    // n 个数的全排列有 n! 种，固定第一位数字，则每种数字有 (n-1)! 种
    // k / (n-1)! 向上取整可以得到第一位数组在第几组，ceil(k / (n-1)!) - 1 就是对应的数字下标
    // 向上取整是为了处理每组最后一个数与其余数不同的问题，每组 6 种时，第一组 1-5 除以 6 为 0，6 除以 6 得 1
    // 为了统一，可以简化为 (k-1) / (n-1)!，就是左起第 1 位的数字的下标。

    // 确定了第一位所在分组，还可以得到在该分组下的第几个 k' = k % (n-1)! + 1
    // 由于模运算范围 [0, (n-1)! -1]，所以需要再加 1

    // 问题转变为 n-2 个数一组时，第 k' 的第一位是多少。注意此时的数组要移除上一步已经选择的数。


    public String getPermutation(int n, int k) {
        // 最多计算到 n-1 的阶乘
        int[] factorial = new int[n];
        factorial[0] = 1;
        for (int i = 1; i < n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        // 构造 k-1
        k--;
        boolean[] used = new boolean[n + 1];
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            int order = k / factorial[n - i] + 1;
            for (int j = 1; j < used.length; j++) {
                if (!used[j]) {
                    order--;
                    if (order == 0) {
                        result.append(j);
                        used[j] = true;
                        break;
                    }
                }
            }

            // 相当于 k' - 1
            k %= factorial[n - i];
        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().getPermutation(2, 2));
    }
}