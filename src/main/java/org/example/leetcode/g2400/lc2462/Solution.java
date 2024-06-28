package org.example.leetcode.g2400.lc2462;

import java.util.PriorityQueue;

class Solution {

    // 时间复杂度不高
    // 高时间复杂度做法：一个最小堆就可以完成，出堆元素的下标 i <= lp 代表从左边选择
    // 当 len < 2 * candidates 时，可以不区分左右

    // 两个最小堆，每次从左右选最小之后再比较大小
    public long totalCost(int[] costs, int k, int candidates) {
        var len = costs.length;
        // 两个优先队列
        var left = new PriorityQueue<int[]>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        var right = new PriorityQueue<int[]>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        for (int i = 0; i < candidates; i++) {
            left.add(new int[]{costs[i], i});
            if (len - i - 1 >= candidates) {
                right.add(new int[]{costs[len - i - 1], len - i - 1});
            }
        }
        int lp = candidates - 1;
        int rp = len - candidates;
        long result = 0;
        while (!left.isEmpty() && !right.isEmpty() && k > 0) {
            var l = left.peek();
            var r = right.peek();
            if (l[0] > r[0]) {
                result += r[0];
                right.poll();
                if (rp > lp + 1) {
                    rp--;
                    right.add(new int[]{costs[rp], rp});
                }
            } else {
                result += l[0];
                left.poll();
                if (lp < rp - 1) {
                    lp++;
                    left.add(new int[]{costs[lp], lp});
                }
            }
            k--;
        }
        if (k == 0) {
            return result;
        }
        while (!left.isEmpty() && k > 0) {
            result += left.poll()[0];
            k--;
        }
        while (!right.isEmpty() && k > 0) {
            result += right.poll()[0];
            k--;
        }
        return result;
    }

    public static void main(String[] args) {
        var arr = new int[]{18, 64, 12, 21, 21, 78, 36, 58, 88, 58, 99, 26, 92, 91, 53, 10, 24, 25, 20, 92, 73, 63, 51, 65, 87, 6, 17, 32, 14, 42, 46, 65, 43, 9, 75};
        System.out.println(new Solution().totalCost(arr, 13, 23));
    }
}