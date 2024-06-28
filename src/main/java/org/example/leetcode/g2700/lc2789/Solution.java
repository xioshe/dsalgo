package org.example.leetcode.g2700.lc2789;

class Solution {
    public int numberOfEmployeesWhoMetTarget(int[] hours, int target) {
        int reuslt = 0;
        for (int hour : hours) {
            if (hour >= target) {
                reuslt++;
            }
        }
        return reuslt;
    }
}