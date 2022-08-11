package com.shang.javatest.standard.model;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author baj
 * @creat 2022-05-21 20:57
 */
public class MultilineInput {
    // 题目
    // 小v今年有n门课，每门都有考试，为了拿到奖学金，小v必须让自己的平均成绩至少为avg。
    // 每门课由平时成绩和考试成绩组成，满分为r。
    // 现在他知道每门课的平时成绩为ai ,若想让这门课的考试成绩多拿一分的话，
    // 小v要花bi 的时间复习，不复习的话当然就是0分。
    // 同时我们显然可以发现复习得再多也不会拿到超过满分的分数。为了拿到奖学金，小v至少要花多少时间复习。

    public static void main(String[] args) {
        // 输入描述:
        // 第一行三个整数n,r,avg(n大于等于1小于等于1e5，r大于等于1小于等于1e9,avg大于等于1小于等于1e6)，
        // 接下来n行，每行两个整数ai和bi，均小于等于1e6大于等于1
        // 示例1
        // 输入
        // 5 10 9
        // 0 5
        // 9 1
        // 8 1
        // 0 1
        // 9 100
        //Scanner类默认的分隔符就是空格
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            /**
             * 如下是接收多行数据的形式
             int n = sc.nextInt();
             int full = sc.nextInt();
             int avg = sc.nextInt();
             int[][] nums = new int[n][2];
             for (int i = 0; i < n; i++) {
             nums[i][0] = sc.nextInt();
             nums[i][1] = sc.nextInt();
             }
             */
            int n = sc.nextInt();
            int full = sc.nextInt();
            int avg = sc.nextInt();
            int[][] nums = new int[n][2];
            for (int i = 0; i < n; i++) {
                nums[i][0] = sc.nextInt();
                nums[i][1] = sc.nextInt();
            }
            //假定不会出现拿不到奖学金的情况
            if (n == 1) {
                System.out.println((avg - nums[0][0]) * nums[0][1]);
                continue;
            }
            Arrays.sort(nums, (o1, o2) -> o1[1] - o2[1]);//按复习代价从小到大排序
            long sum = 0;
            for (int[] a : nums) {
                sum += a[0];
            }
            long limit = avg * n;
            int index = 0;
            long time = 0;
            while (sum < limit) {
                int tmp = full - nums[index][0];
                if (tmp + sum <= limit) {                  //如果一门课程复习到满分，小于限制，
                    time += tmp * nums[index][1];
                    sum += tmp;
                    index++;
                } else {                              //如果一门课程复习到满分，大于限制，
                    time += (limit - sum) * nums[index][1];
                    sum = limit;
                }
            }
            // 输出描述:
            // 一行输出答案。
            // 输出
            // 43
            System.out.println(time);
        }
    }
}
