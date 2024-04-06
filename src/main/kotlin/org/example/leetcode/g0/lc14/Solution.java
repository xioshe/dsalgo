package org.example.leetcode.g0.lc14;

class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 1) {
            return strs[0];
        }

        var min = strs[0].length();
        for (String str : strs) {
            min = Math.min(min, str.length());
        }

        var result = new StringBuilder();
        for (int i = 0; i < min; i++) {
            var ch = strs[0].charAt(i);
            for (String str : strs) {
                if (str.charAt(i) != ch) {
                    return result.toString();
                }
            }
            result.append(ch);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        var sb = new StringBuilder();
        System.out.println(sb);
        var strs = new String[]{"0"};
        System.out.println(new Solution().longestCommonPrefix(strs));
    }
}