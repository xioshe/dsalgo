package org.example.leetcode.g2900.lc2924;

class Solution {
    public int findChampion(int n, int[][] edges) {
        // 有向无环图统计入度，入度为 0 代表优胜
        var degree = new int[n];
        for (int[] edge : edges) {
            degree[edge[1]]++;
        }
        int champion = -1;
        for (int i = 0; i < degree.length; i++) {
            if (degree[i] == 0) {
                if (champion != -1) {
                    return -1;
                }
                champion = i;
            }
        }
        return champion;
    }
}