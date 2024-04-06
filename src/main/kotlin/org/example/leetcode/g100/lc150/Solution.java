package org.example.leetcode.g100.lc150;

import java.util.LinkedList;

// 逆波兰表达式求值
// 后缀表达式
class Solution {
    // 用栈辅助计算，存储中间值
    public int evalRPN(String[] tokens) {
        var stack = new LinkedList<Integer>();
        for (String token : tokens) {
            switch (token) {
                case "+":
                case "-":
                case "*":
                case "/":
                    var right = stack.pop();
                    var left = stack.pop();
                    stack.push(calc(left, right, token.charAt(0)));
                    break;
                default:
                    var op = Integer.parseInt(token);
                    stack.push(op);
            }
        }
        return stack.peek();
    }

    private int calc(int leftOp, int rightOp, char type) {
        if (type == '+') {
            return leftOp + rightOp;
        }
        if (type == '-') {
            return leftOp - rightOp;
        }
        if (type == '*') {
            return leftOp * rightOp;
        }
        return leftOp / rightOp;
    }
}