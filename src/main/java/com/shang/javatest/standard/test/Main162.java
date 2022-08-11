package com.shang.javatest.standard.test;

import java.util.Scanner;

/**
 * @author baj
 * @creat 2022-05-21 21:37
 */
public class Main162 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
//            String[] s = in.nextLine().split(" ");
            String[] s = in.nextLine().split(",");

            int[] nums = new int[s.length];
            for (int i = 0; i < s.length; i++) {
                nums[i] = Integer.parseInt(s[i]);
            }
            //现在获取好了参数进行传参
            int ans = findPeakElement(nums);
            System.out.println(ans);
        }
    }
    public static int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while(left < right){
            int mid = left + (right - left) / 2;
            if(nums[mid] > nums[mid + 1]){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        // return right;
        return left;
    }
}
