package org.example.leetcode.g0.lc84;

import java.util.LinkedList;

class Solution {
    // 单调栈
    // 求一个数左边更小值，右边最小值 -> 出栈时栈内相邻的数为左边更小，即将入栈的数为右边更小
    // 栈中最后剩下最小值，栈顶最大，碰到相等的数字也要出栈
    // 为了方便计算最小高度的面积，可以提前添加一个辅助数字 -1 来统一计算宽度的代码
    public int largestRectangleArea(int[] heights) {
        if (heights.length == 1) {
            return heights[0];
        }
        var result = 0;
        var minStack = new LinkedList<Integer>();
        minStack.push(-1);
        for (int i = 0; i < heights.length; i++) {
            // 栈中存放的是数组下标，可以 == 0
            while (minStack.peek() >= 0 && heights[minStack.peek()] >= heights[i]) {
                var height = heights[minStack.pop()];
                var width = i - 1 - minStack.peek();
                result = Math.max(result, width * height);
            }
            minStack.push(i);
        }
        while (minStack.peek() >= 0) {
            var height = heights[minStack.pop()];
            var width = heights.length - 1 - minStack.peek();
            result = Math.max(result, width * height);
        }
        return result;
    }
}