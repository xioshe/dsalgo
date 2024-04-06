package org.example.leetcode.g0.lc59;


class Solution {
    // 模拟题
    public int[][] generateMatrix(int n) {
        // 按圈填充数字
        // 确定每一层圈圈的左右上下边界，然后按照右下左上的顺序填充数字
        // 在构建完一条边界后，缩短该边界，下次再判断就不需要处理缩短了
        var matrix = new int[n][n];
        var start = 1;
        var left = 0;
        var right = n - 1;
        var top = 0;
        var bottom = n - 1;
        while (start <= n * n) {
            for (int i = left; i <= right; i++) {
                matrix[top][i] = start++;
            }
            top++;
            for (int i = top; i <= bottom; i++) {
                matrix[i][right] = start++;
            }
            right--;
            for (int i = right; i >= left; i--) {
                matrix[bottom][i] = start++;
            }
            bottom--;
            for (int i = bottom; i >= top; i--) {
                matrix[i][left] = start++;
            }
            left++;
        }
        return matrix;
    }
}