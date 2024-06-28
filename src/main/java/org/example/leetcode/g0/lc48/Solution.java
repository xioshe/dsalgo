package org.example.leetcode.g0.lc48;

class Solution {
    // 矩形旋转的坐标转换规则很好推算，但不借助辅助数组来实现旋转很难
    // 可以把右旋转换为水平翻转 + 主对角线翻转，翻转过程中不需要临时变量保存原位置数
    public void rotate(int[][] matrix) {
        var n = matrix.length;
        // 水平翻转
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n / 2; col++) {
                swap(matrix, row, col, row, n - 1 - col);
            }
        }
        // 主对角线翻转
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n - 1 - row; col++) {
                swap(matrix, row, col, n - 1 - col, n - 1 - row);
            }
        }
    }

    private void swap(int[][] matrix, int r1, int c1, int r2, int c2) {
        var temp = matrix[r1][c1];
        matrix[r1][c1] = matrix[r2][c2];
        matrix[r2][c2] = temp;
    }
}