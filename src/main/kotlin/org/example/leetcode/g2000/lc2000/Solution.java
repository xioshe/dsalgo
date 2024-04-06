package org.example.leetcode.g2000.lc2000;


class Solution {
    // 定位 ch，反向双指针交换
    public String reversePrefix(String word, char ch) {
        var pos = word.indexOf(ch);
        if (pos < 0) {
            return word;
        }
        var chars = word.toCharArray();
        var left = 0;
        while (left < pos) {
            var temp = chars[left];
            chars[left] = chars[pos];
            chars[pos] = temp;
            left++;
            pos--;
        }
        return new String(chars);
    }
}