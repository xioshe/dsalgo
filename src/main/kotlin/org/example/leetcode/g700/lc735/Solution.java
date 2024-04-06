package org.example.leetcode.g700.lc735;

import java.util.LinkedList;

class Solution {
    // 用栈来辅助计算
    public int[] asteroidCollision(int[] asteroids) {
        var stack = new LinkedList<Integer>();
        for (int asteroid : asteroids) {
            while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < -asteroid) {
                stack.pop();
            }
            // 注意两者相等的情况
            if (!stack.isEmpty() && asteroid < 0 && stack.peek() == -asteroid) {
                stack.pop();
            } else if (stack.isEmpty() || stack.peek() < 0 || asteroid > 0) {
                stack.push(asteroid);
            }
            // 还剩下 asteroid 为负数且被消灭的情况
        }
        return stack.stream().mapToInt(i -> i).toArray();
    }
}