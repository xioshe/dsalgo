package org.example.leetcode.g0.lc79;

class Solution {
    // 回溯 + 图的 dfs 搜索
    public boolean exist(char[][] board, String word) {
        var m = board.length;
        var n = board[0].length;
        if (m * n < word.length()) {
            return false;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(0, i, j, word, board)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int pos, int i, int j, String word, char[][] board) {
        if (pos == word.length()) {
            return true;
        }
        if (i < 0 || i >= board.length ||
            j < 0 || j >= board[0].length ||
            board[i][j] != word.charAt(pos)) {
            return false;
        }
        var ch = board[i][j];
        board[i][j] = '.';

        var result = false;
        result = dfs(pos + 1, i + 1, j, word, board) ||
                 dfs(pos + 1, i - 1, j, word, board) ||
                 dfs(pos + 1, i, j + 1, word, board) ||
                 dfs(pos + 1, i, j - 1, word, board);
        board[i][j] = ch;
        return result;
    }

    public static void main(String[] args) {
        var board = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'E', 'S'}, {'A', 'D', 'E', 'E'}};
        var word = "ABCESEEEFS";
        System.out.println(new Solution().exist(board, word));
    }
}