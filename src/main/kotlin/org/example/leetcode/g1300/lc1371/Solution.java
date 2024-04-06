package org.example.leetcode.g1300.lc1371;

import java.util.Arrays;

class Solution {
    // 每个元音包含偶数次的最长子字符串
    // 前缀和 + 状态压缩
    // 前缀和中记录的是五个元音出现的次数
    // 但基于题目要求，不需要统计完整的出现次数，统计其奇偶性即可
    // 五个元音的奇偶性可以压缩到一个 5 位 int 中，总共 2^5 中情况
    // 状态数组下标为奇偶状态 status，值为字符串位置下标（实际情况略有不同）
    // 初始化：
    // 有一种特殊情况，status 为 0 代表子串 [0,i] 中各元音出现次数都为偶数
    // 这种状态本来就满足要求，最长子串长度就为 i+1，为了表示这种情况，positions[0] = 0
    // 但这样一来，0 这个字符串下标就被用了，后续记录下标时需要 i+1，反正计算长度时是取差值
    // 因此，positions 数组的值其实是 i+1
    public int findTheLongestSubstring(String s) {
        var max = 0;
        var positions = new int[1 << 5];
        // 用 -1 代表没有遇见过 status 的情况
        Arrays.fill(positions, -1);
        positions[0] = 0;
        var status = 0;
        for (int i = 0; i < s.length(); i++) {
            var ch = s.charAt(i);
            // 计算当前位置的元音奇偶状态
            switch (ch) {
                // 用异或可以将 0 变成 1，将 1 变为 0，满足奇偶转换的特点
                case 'a' -> status ^= 1;
                case 'e' -> status ^= 1 << 1;
                case 'i' -> status ^= 1 << 2;
                case 'o' -> status ^= 1 << 3;
                case 'u' -> status ^= 1 << 4;
            }
            // 每遇见依次 status，就会计算依次最大值
            // 每增加一个非元音字母，都会统计依次最大值，这也符合题意
            // 注意这里是 >= 0，0 也是一个下标
            if (positions[status] >= 0) {
                max = Math.max(max, i - positions[status]);
            } else {
                // 记录下满足 status 状态的最左位置 i
                // 如果有下一个 status 位置 j，j 的最大值 - i 即为一个解，子串区间 [i+1, max_j]
                positions[status] = i;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findTheLongestSubstring("leetcodeisgreat"));
    }
}