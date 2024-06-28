package org.example.leetcode.g0.lc39;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        var result = new ArrayList<List<Integer>>();
        Arrays.sort(candidates);
        dfs(target, 0, candidates, new ArrayList<>(), result);
        return result;
    }

    private void dfs(int sum, int index, int[] candidates, List<Integer> state, List<List<Integer>> result) {
        if (sum == 0) {
            result.add(new ArrayList<>(state));
            return;
        }
        if (sum < 0 || index == candidates.length) {
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            state.add(candidates[i]);
            dfs(sum - candidates[i], index, candidates, state, result);
            state.removeLast();
        }
    }


    /**
     * 内存占用 999，时间 2ms
     */
    private void dfs0(int sum, int index, int[] candidates, List<Integer> state, List<List<Integer>> result) {
        if (sum == 0) {
            result.add(new ArrayList<>(state));
            return;
        }
        if (sum < 0 || index >= candidates.length) {
            return;
        }
        int candidate = candidates[index];
        if (sum < candidate) {
            return;
        }
        state.add(candidate);
        dfs(sum - candidate, index, candidates, state, result);
        state.removeLast();
        dfs(sum, index + 1, candidates, state, result);
    }
}