package org.example.leetcode.g0.lc33;

class Solution {
    // mid 把数组分成了两部分：有序和无序
    // 左边有序时，l<= target < mid，[l, mid-1]；target > mid 或 target < l，[mid+1, r]
    // 右边有序时，mid <target <= r，[mid+1, r]; 否则 [l,mid-1]
    public int search(int[] nums, int target) {
        if (target == nums[0]) {
            return 0;
        }

        var left = 0;
        var right = nums.length - 1;
        while (left <= right) {
            var mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] >= nums[0]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
