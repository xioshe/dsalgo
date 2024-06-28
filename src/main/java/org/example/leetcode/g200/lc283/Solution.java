package org.example.leetcode.g200.lc283;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 示例 2:
 *
 * 输入: nums = [0]
 * 输出: [0]
 *
 *
 * 提示:
 *
 * 1 <= nums.length <= 104
 * -231 <= nums[i] <= 231 - 1
 *
 *
 * 进阶：你能尽量减少完成的操作次数吗？
 */
class Solution {
    /**
     * 正向双指针，将所有的 0 合并
     * j 向右移动，遇到非 0，与 i 交换 i++
     * 遇到 0 ，j++
     */
    void moveZeroes(int[] nums) {
        var startOfZero = 0;
        var index = 0;
        while (index < nums.length) {
            if (nums[index] != 0) {
                nums[startOfZero] = nums[index];
                startOfZero++;
            }
            index++;
        }
        while (startOfZero < nums.length) {
            nums[startOfZero++] = 0;
        }
    }

    private void swap(int[] arr, int i, int j) {
        if (i == j) return;
        var temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}