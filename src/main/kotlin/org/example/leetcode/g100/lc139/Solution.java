package org.example.leetcode.g100.lc139;

import java.util.HashSet;
import java.util.List;

class Solution {
    // f(i) 前 i 个字符满足条件，也就是 [0, i-1] 子串
    // f(i) = any(f[p] && s[p:i] in dict)
    // f(0) = true
    public boolean wordBreak(String s, List<String> wordDict) {
        var dict = new HashSet<>(wordDict);
        var dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }
}
