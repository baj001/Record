package com.shang.javatest.standard.model;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author baj
 * @creat 2022-05-21 21:07
 */

public class ArrayInput {
    // 题目描述
    // 一条长l的笔直的街道上有n个路灯，若这条街的起点为0，终点为l，第i个路灯坐标为ai ，
    // 每盏灯可以覆盖到的最远距离为d，为了照明需求，所有灯的灯光必须覆盖整条街，
    // 但是为了省电，要使这个d最小，请找到这个最小的d。
    public static void main(String[] args) {
        // 输入描述:
        // 每组数据第一行两个整数n和l（n大于0小于等于1000，l小于等于1000000000大于0）。
        // 第二行有n个整数(均大于等于0小于等于l)，为每盏灯的坐标，多个路灯可以在同一点。
        // 输入
        // 7 15
        // 15 5 3 7 9 14 0
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            /**
             * 如下是输入一个数组的形式
            int n = sc.nextInt();
            long l = sc.nextLong();
            long[] nums = new long[n];
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextLong();
            }
             */
            int n = sc.nextInt();
            long l = sc.nextLong();
            long[] nums = new long[n];
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextLong();
            }
            Arrays.sort(nums);
            long gap = nums[1] - nums[0];
            for (int i = 1; i < n; i++) {
                gap = Math.max(gap, nums[i] - nums[i - 1]);
            }
            //下标最小和最大位置的路灯需要单独判断
            //如下标为3是最小，那么0-3这一段必须被覆盖到，所以最小下标必须单独判断
            gap = Math.max(gap, nums[0] * 2);
            gap = Math.max(gap, (l - nums[n - 1]) * 2);
            // 输出描述:
            // 输出答案，保留两位小数。
            // 输出
            // 2.50
            System.out.println(String.format("%.2f", gap / 2.0));
        }
    }
}
