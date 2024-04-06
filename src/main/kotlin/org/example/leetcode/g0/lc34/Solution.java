package org.example.leetcode.g0.lc34;

class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        var left = gte(nums, target);
        if (left == nums.length || nums[left] != target) {
            left = -1;
        }
        var right = gte(nums, target + 0.5) - 1;
        if (right < 0 || nums[right] != target) {
            right = -1;
        }
        return new int[]{left, right};
    }

    // 寻找最小的大于等于 target 的数的位置
    // == target 时左边可能还有数字，应该继续往左缩小寻找区间
    private int gte(int[] nums, double target) {
        var left = 0;
        var right = nums.length - 1;
        while (left <= right) {
            var mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }
}