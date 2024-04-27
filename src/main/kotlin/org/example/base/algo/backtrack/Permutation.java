package org.example.base.algo.backtrack;

import java.util.ArrayList;
import java.util.List;

public class Permutation {

    public List<List<Integer>> permutation(int n) {
        List<List<Integer>> result = new ArrayList<>();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }
        dfs(0, nums, result);
        return result;
    }

    private void dfs(int i, int[] nums, List<List<Integer>> result) {
        if (i == nums.length - 1) {
            List<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }
            result.add(list);
            return;
        }

        // 对 i 位置，从 [i:] 挑选一个数字填入
        // 每次决策做的都是决定 i 位置的数，为了去重，就是将后面待使用的数交换到 i 位置
        // 这种方法没法保证字典序排序。
        for (int j = i; j < nums.length; j++) {
            swap(nums, i, j);
            dfs(i + 1, nums, result);
            swap(nums, i, j);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println(new Permutation().permutation(4));
    }

}
