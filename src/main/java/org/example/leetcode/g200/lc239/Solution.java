package org.example.leetcode.g200.lc239;


import java.util.LinkedList;

// 滑动窗口最大值
class Solution {
    // 单调队列，按值从大到小，下标从小到大排列，中间略去不满足条件的数字
    public int[] maxSlidingWindow(int[] nums, int k) {
        var result = new int[nums.length - k + 1];
        var maxDeque = new LinkedList<Integer>();
        for (int i = 0; i < k; i++) {
            while (!maxDeque.isEmpty() && nums[i] > nums[maxDeque.peekLast()]) {
                maxDeque.pollLast();
            }
            maxDeque.offerLast(i);
        }
        result[0] = nums[maxDeque.peekFirst()];
        for (int i = k; i < nums.length; i++) {
            while (!maxDeque.isEmpty() && nums[i] > nums[maxDeque.peekLast()]) {
                maxDeque.pollLast();
            }
            maxDeque.offerLast(i);
            while (maxDeque.peekFirst() <= i - k) {
                maxDeque.pollFirst();
            }
            result[i - k + 1] = nums[maxDeque.peekFirst()];
        }
        return result;
    }
}