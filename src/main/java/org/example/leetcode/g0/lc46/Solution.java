package org.example.leetcode.g0.lc46;

import java.util.ArrayList;
import java.util.List;

class Solution {
    // 回溯
    public List<List<Integer>> permute(int[] nums) {
        var result = new ArrayList<List<Integer>>();
        dfs(0, nums, result);
        return result;
    }

    private void dfs(int i, int[] state, List<List<Integer>> result) {
        if (i == state.length - 1) {
            var permutation = new ArrayList<Integer>();
            for (int num : state) {
                permutation.add(num);
            }
            result.add(permutation);
            return;
        }
        for (int j = i; j < state.length; j++) {
            swap(state, i, j);
            dfs(i + 1, state, result);
            swap(state, i, j);
        }
    }

    private void swap(int[] nums, int i, int j) {
        var temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}