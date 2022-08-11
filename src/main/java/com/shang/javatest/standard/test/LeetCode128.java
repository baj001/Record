package com.shang.javatest.standard.test;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author baj
 * @creat 2022-05-23 14:50
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 *
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 *接收一个数组
 */
public class LeetCode128 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String[] str = in.nextLine().split(" ");
            int[] arr = new int[str.length];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = Integer.parseInt(str[i]);
            }
            int res = longestConsecutive(arr);
            System.out.println("最长序列长度为"+res);
        }
    }
    public static int longestConsecutive(int[] nums) {
        int res = 1;
        Set<Integer> set = new HashSet<>();
        for(int num : nums){
            set.add(num);
        }
        //现在set中保存了所有的nums值
        for(int x : nums){
            if(!set.contains(x - 1)){
                int y = x;
                //现在使用y保存了x，来判断set中是否有y+1，若有则 2 3 5
                while(set.contains(y + 1)){
                    res = Math.max(res, y - x + 2);
                    y++;
                }
            }
        }
        return res;
    }
}



















