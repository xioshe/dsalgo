package org.example.leetcode.g0.lc17;

import java.util.ArrayList;
import java.util.List;

class Solution {
    // 回溯
    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) {
            return new ArrayList<>();
        }
        var map = new char[][]{
                {'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'},
                {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'},
                {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}
        };
        var result = new ArrayList<String>();
        dfs(new StringBuilder(), digits, map, result);
        return result;
    }

    private void dfs(StringBuilder state, String digits, char[][] map, List<String> result) {
        if (state.length() == digits.length()) {
            result.add(state.toString());
            return;
        }
        var pos = state.length();
        var digit = digits.charAt(pos);
        for (char ch : map[digit - '2']) {
            state.append(ch);
            dfs(state, digits, map, result);
            state.deleteCharAt(state.length() - 1);
        }
    }
}