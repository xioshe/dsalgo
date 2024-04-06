package org.example.leetcode.g700.lc739;

import java.util.LinkedList;

class Solution {
    // 单调栈求右边更大->出栈计算，栈顶最小
    public int[] dailyTemperatures(int[] temperatures) {
        if (temperatures.length == 1) {
            return new int[]{0};
        }
        var result = new int[temperatures.length];
        var stack = new LinkedList<Integer>();
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[0]) {
                var prev = stack.pop();
                result[prev] = i - prev;
            }
            stack.push(i);
        }
        // 剩下的日子没有最大温度
        return result;
    }
}