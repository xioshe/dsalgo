package org.example.leetcode.g300.lc392;

class Solution {
    public boolean isSubsequence(String s, String t) {
        if (s.isEmpty()) {
            return true;
        }
        if (t.isEmpty() || s.length() > t.length()) {
            return false;
        }
        var i = 0;
        for (char ch : t.toCharArray()) {
            if (s.charAt(i) == ch) {
                i++;
                if (i == s.length()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isSubsequenceDp(String s, String t) {
        if (s.isEmpty()) {
            return true;
        }
        if (t.isEmpty() || s.length() > t.length()) {
            return false;
        }
        var dp = new int[t.length() + 1][26];
        for (int i = 0; i < 26; i++) {
            dp[t.length()][i] = -1;
        }
        for (int i = t.length() - 1; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                if (t.charAt(i) - 'a' == j) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }
        // 利用 dp 数组判断 s
        var pos = 0;
        for (char ch : s.toCharArray()) {
            if (dp[pos][ch - 'a'] < 0) {
                return false;
            }
            pos = dp[pos][ch - 'a'] + 1;
        }
        return true;
    }
}