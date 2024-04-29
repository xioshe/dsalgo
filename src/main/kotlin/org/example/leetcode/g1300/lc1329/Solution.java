package org.example.leetcode.g1300.lc1329;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

class Solution {
    // 时间复杂度很差

    // 对角线排序，模拟题，排序后再放回去
    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        HashMap<Integer, List<Integer>> groups = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                groups.putIfAbsent(i - j, new ArrayList<>());
                groups.get(i - j).add(mat[i][j]);
            }
        }
        for (List<Integer> list : groups.values()) {
            Collections.sort(list);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                var value = groups.get(i - j).removeFirst();
                mat[i][j] = value;
            }
        }
        return mat;
    }
}