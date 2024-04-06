package org.example.leetcode.g0.lc22;

import java.util.ArrayList;
import java.util.List;

class Solution {
    // 回溯法
    // 状态的表示，用 left 个左括号和 right 个右括号组合字符串
    // left 初始值为 n-1，right 初始值为 n
    // left == right 时只能选择补充左括号
    // left < right 时，用掉的左括号比右括号多，下一个填充左右括号都可以
    // left > right 时，不论填充什么都不满足条件，应该剪枝
    public List<String> generateParenthesis(int n) {
        var result = new ArrayList<String>();
        dfs(n - 1, n, new StringBuilder("("), result);
        return result;
    }

    private void dfs(int left, int right, StringBuilder state, List<String> result) {
        if (left == 0 && right == 0) {
            result.add(state.toString());
            return;
        }
        if (left > right || left < 0) {
            return;
        }
        // 不管怎么样都可以添加左括号
        dfs(left - 1, right, state.append('('), result);
        state.deleteCharAt(state.length() - 1);
        // 特殊情况才可以添加右括号
        if (left < right) {
            dfs(left, right - 1, state.append(')'), result);
            state.deleteCharAt(state.length() - 1);
        }
    }
}