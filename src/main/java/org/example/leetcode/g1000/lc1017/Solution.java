package org.example.leetcode.g1000.lc1017;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().baseNeg2(3));
    }

    // 时间复杂度很差

    // 先计算出每个数位能表示的上限
    // 奇数位代表偶数次方，为正数，取正数之和
    // 偶数位代表奇数次方，为负数，取负数之和

    // 从最高位开始，用类似分治的思路，确认最高位后在剩下的数位中寻找 n - 最高位的幂
    // 由于每次寻找的值可能为负，所以要分开统计。
    public String baseNeg2(int n) {
        if (n == 0) {
            return "0";
        }
        List<Integer> limit = new ArrayList<>();
        Map<Integer, Integer> limitToPow = new HashMap<>();
        int pow = 1;
        int i = 0;
        while (true) {
            int sum = (i < 2 ? 0 : limit.get(i - 2)) + pow;
            limit.add(sum);
            limitToPow.put(sum, pow);

            if (sum >= n) {
                break;
            }
            pow *= -2;
            i++;
        }

        boolean[] bit = new boolean[i + 1];
        bit[i] = true;
        var left = n - limitToPow.get(limit.getLast());
        while (left != 0) {
            if (left < 0) {
                for (int j = 1; j < limit.size(); j += 2) {
                    if (limit.get(j) <= left) {
                        bit[j] = true;
                        left -= limitToPow.get(limit.get(j));
                        break;
                    }
                }
            } else {
                for (int j = 0; j < limit.size(); j += 2) {
                    if (limit.get(j) >= left) {
                        bit[j] = true;
                        left -= limitToPow.get(limit.get(j));
                        break;
                    }
                }
            }
        }

        var sb = new StringBuilder();
        for (int j = bit.length - 1; j >= 0; j--) {
            sb.append(bit[j] ? "1" : "0");
        }
        return sb.toString();
    }
}