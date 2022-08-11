package com.shang.javatest.LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author baj
 * @creat 2022-05-16 19:27
 */
public class Solution560 {
    public int subarraySum(int[] nums, int k) {
        // key：前缀和，value：key 对应的前缀和的个数
        Map<Integer, Integer> map = new HashMap<>();

        map.put(0, 1);
        int preSum = 0;
        int count = 0;
        for (int num : nums) {
            preSum += num;

            /*
            统计和为k的子数组
            使用前缀和的思想
            找到后，将 preSum - k 的值累加，
            取出preSum的值，若值为null，说明之前的map中没有保存过key 为 preSum的项，则将其值置为1，反之将值+1
            关于为何将map(0,1) 可以借助 1 1 1 k = 2 的用例理解
            1 1 1
            k = 2
            ans = 2
             */
            if (map.containsKey(preSum - k)) {
                count += map.get(preSum - k);
            }

            Integer value = map.get(preSum);
            if (value != null) {
                map.put(preSum, value + 1);
            } else {
                map.put(preSum, 1);
            }
        }
        return count;
    }
}
