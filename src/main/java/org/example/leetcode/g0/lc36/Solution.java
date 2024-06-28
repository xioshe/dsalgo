package org.example.leetcode.g0.lc36;

public class Solution {
    // 用 int 的第 0 - 8 位数来表示 1 - 9
    public boolean isValidSudoku(char[][] board) {
        var colSet = new int[9];
        var rowSet = new int[9];
        var subSet = new int[9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char ch = board[i][j];
                if (ch != '.') {
                    if (exist(colSet[j], ch)) {
                        return false;
                    } else {
                        colSet[j] = mark(colSet[j], ch);
                    }
                    if (exist(rowSet[i], ch)) {
                        return false;
                    } else {
                        rowSet[i] = mark(rowSet[i], ch);
                    }
                    var subIdx = i / 3 * 3 + j / 3;
                    if (exist(subSet[subIdx], ch)) {
                        return false;
                    } else {
                        subSet[subIdx] = mark(subSet[subIdx], ch);
                    }
                }
            }
        }
        return true;
    }

    private boolean exist(int checksum, char digit) {
        return (checksum & (1 << (digit - '1'))) != 0;
    }

    private int mark(int checksum, char digit) {
        return checksum | (1 << (digit - '1'));
    }
}
