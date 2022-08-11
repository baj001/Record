package com.shang.javatest.LeetCode;

import java.util.Scanner;

/**
 * @author baj
 * @creat 2022-05-19 19:18
 */
public class Main1 {
    //    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        System.out.println("在下面一行中以空格为间隙输入测试用例");
//        String[] str = in.nextLine().split(" ");
//        int[] nums = new int[str.length];
//        for (int i = 0; i < nums.length; i++) {
//            nums[i] = Integer.parseInt(str[i]);
//        }
//        //上述输入
//        System.out.println("结果是："+ maxSubArray(nums));
//    }
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("在下面一行中以空格为间隙输入元素:");
//        //得出的是string类型
//        String[] str = sc.nextLine().split(" ");
//        //将String类型数组转成Integer类型数组。
//        int[] nums = new int[str.length];
//        for (int i = 0; i < nums.length; i++) {
//            nums[i] = Integer.parseInt(str[i]);
////            nums[i] = Integer.valueOf(str[i]);
//        }
//        System.out.println("结果是：" + maxSubArray(nums));
//    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("在下面输入。。。");
        //直接使用整形的数组接收
        while(in.hasNextLine()){
            String[] str = in.nextLine().split(" ");
            int[] nums = new int[str.length];
            for (int i = 0; i < str.length; i++) {
                nums[i] = Integer.parseInt(str[i]);
            }
            System.out.println("结果是"+maxSubArray(nums));
        }
    }

    public static int maxSubArray(int[] nums) {
        //输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
        // -2 1 -3 4 -1 2 1 -5 4
        // 贪心算法
        int sum = 0;
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (sum > 0) {
                sum += nums[i];
            } else {
                sum = nums[i];
            }
            //保存每一次的最大的sum
            ans = Math.max(ans, sum);
        }
        return ans;
    }
}

//    public static void main(String[] args) {
//        Scanner sc=new Scanner(System.in);
//        System.out.println("在下面一行中以空格为间隙输入元素:");
//        //得出的是string类型
//        String[] str = sc.nextLine().split(" ");
//        //将String类型数组转成Integer类型数组。
//        int[] nums=new int[str.length];
//        for (int i = 0; i < nums.length; i++) {
//            nums[i]=Integer.valueOf(str[i]);
//        }
//        System.out.println("结果是："+maxSubArray(nums));
//    }
