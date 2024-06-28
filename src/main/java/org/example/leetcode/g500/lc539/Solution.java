package org.example.leetcode.g500.lc539;

import java.util.List;

class Solution {
    // 一般求数组最小区间，常见做法是预先将数组排序再遍历，时间复杂度为 O(nlogn)
    // 利用分钟区间不大的特点，采用类似计数数组的优化方法，构建一个数组，长度为 24 * 60 = 1440，值为对应时间的存在标记
    // 顺序遍历该分钟区间，找到最小相邻时间
    // 注意要把最小时间拷贝一份到第二天
    public int findMinDifference0(List<String> timePoints) {
        if (timePoints.size() > 1440) {
            return 0;
        }
        var minutes = new boolean[1440];
        for (String timePoint : timePoints) {
            var split = timePoint.split(":");
            var minute = Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
            if (minutes[minute]) {
                return 0;
            }
            minutes[minute] = true;
        }
        var min = minutes.length - 1;
        var max = -1;
        var prev = -1;
        var result = minutes.length - 1;
        for (int i = 0; i < minutes.length; i++) {
            if (minutes[i]) {
                if (prev >= 0) {
                    result = Math.min(result, i - prev);
                }
                min = Math.min(min, i);
                max = Math.max(max, i);
                prev = i;
            }
        }
        return Math.min(result, min + minutes.length - max);
    }

    // 优化一：时间格式固定为 5 个字符，利用这个特点，可以采用效率更高的方法计算分钟数
    // 优化二：对最多 1440 个数的排序一次的开销不大
    public int findMinDifference(List<String> timePoints) {
        if (timePoints.size() > 1440) {
            return 0;
        }
        var minutes = new boolean[1440];
        for (String timePoint : timePoints) {
            var minute = ((timePoint.charAt(0) - '0') * 10 + (timePoint.charAt(1) - '0')) * 60 +
                         ((timePoint.charAt(3) - '0') * 10 + (timePoint.charAt(4) - '0'));
            if (minutes[minute]) {
                return 0;
            }
            minutes[minute] = true;
        }
        var min = minutes.length - 1;
        var max = -1;
        var prev = -1;
        var result = minutes.length - 1;
        for (int i = 0; i < minutes.length; i++) {
            if (minutes[i]) {
                if (prev >= 0) {
                    result = Math.min(result, i - prev);
                }
                min = Math.min(min, i);
                max = Math.max(max, i);
                prev = i;
            }
        }
        return Math.min(result, min + minutes.length - max);
    }
}