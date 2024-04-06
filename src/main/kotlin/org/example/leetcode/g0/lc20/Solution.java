package org.example.leetcode.g0.lc20;

import java.util.LinkedList;
import java.util.Map;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "()"
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：s = "()[]{}"
 * 输出：true
 * 示例 3：
 * <p>
 * 输入：s = "(]"
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 104
 * s 仅由括号 '()[]{}' 组成
 */
class Solution {
    public boolean isValid(String s) {
        if ((s.length() & 1) == 1) {
            return false;
        }
        var stack = new LinkedList<Character>();
        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    if (ch == ')') {
                        if (stack.peek() != '(') return false;
                        else stack.pop();
                    } else if (ch == ']') {
                        if (stack.peek() != '[') return false;
                        else stack.pop();
                    } else if (ch == '}') {
                        if (stack.peek() != '{') return false;
                        else stack.pop();
                    }
                }
            }
        }
        return stack.isEmpty();
    }

    // 变体求最大括号深度
    public int maxDepth(String s) {
        if ((s.length() & 1) == 1) {
            return 0;
        }
        var max = 0;
        var stack = new LinkedList<Character>();
        var map = Map.of('(', ')', '{', '}', '[', ']');
        for (char ch : s.toCharArray()) {
            if (map.containsKey(ch)) {
                stack.push(ch);
                max = Math.max(max, stack.size());
            } else {
                if (stack.isEmpty()) {
                    return 0;
                }
                if (map.get(stack.pop()) != ch) {
                    return 0;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maxDepth(")("));
        System.out.println(new Solution().maxDepth("[]"));
        System.out.println(new Solution().maxDepth("(()({[{}]}))"));
        System.out.println(new Solution().maxDepth("([])([{}]"));
    }
}
