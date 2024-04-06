package org.example.leetcode.g0.lc13;


import java.util.Map;

class Solution {
    // 前面一个值由后面一个数决定
    public int romanToInt(String s) {
        var values = Map.of(
                'I', 1, 'V', 5,
                'X', 10, 'L', 50,
                'C', 100, 'D', 500,
                'M', 1000);
        var result = 0;
        var prev = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            var ch = s.charAt(i);
            if (((ch == 'V' || ch == 'X') && prev == 'I') ||
                ((ch == 'L' || ch == 'C') && prev == 'X') ||
                ((ch == 'D' || ch == 'M') && prev == 'C')) {
                result -= values.get(prev);
            } else {
                result += values.get(prev);
            }
            prev = ch;
        }
        result += values.get(prev);
        return result;
    }
}
