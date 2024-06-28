package org.example.leetcode.g0.lc16;

import java.util.Arrays;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        // 排序前取和相当于随机取三数之和
        var result = nums[0] + nums[1] + nums[2];
        Arrays.sort(nums);
        var len = nums.length;
        for (int i = 0; i < len - 2; ) {
            // 求最接近的两数之和
            var left = i + 1;
            var right = len - 1;
            while (left < right) {
                // 优化 1：如果 target 已经小于剩下范围的最小值，可以提前结束本次遍历
                var min = nums[i] + nums[left] + nums[left + 1];
                if (target < min) {
                    if (Math.abs(min - target) < Math.abs(result - target)) {
                        result = min;
                    }
                    // 提前结束本次遍历
                    break;
                }
                // target > 剩下最大值的情况
                var max = nums[i] + nums[right - 1] + nums[right];
                if (target > max) {
                    if (Math.abs(max - target) < Math.abs(result - target)) {
                        result = max;
                    }
                    // 提前返回
                    break;
                }
                var sum = nums[i] + nums[left] + nums[right];
                // 优化 3：不可能有比 target 更接近的数
                if (sum == target) {
                    return target;
                }
                if (Math.abs(sum - target) < Math.abs(result - target)) {
                    result = sum;
                }
                if (sum < target) {
                    left++;
                    // 优化 4：跳过相同的数字
                    while (left < right && nums[left - 1] == nums[left]) {
                        left++;
                    }
                } else {
                    right--;
                    // 跳过相同数字
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }

                }
            }
            // 跳过相同数字
            while (i < len - 2 && nums[i] == nums[++i]) ;
        }
        return result;
    }
}
