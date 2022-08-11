package com.shang.javatest.LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author baj
 * @creat 2022-06-16 22:46
 */
public class LeetCode15 {
    public static void main(String[] args){
        //接收一个数组
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String[] str = in.nextLine().split(",");
            int[] nums = new int[str.length];
            for(int i = 0; i < str.length; i++){
                nums[i] = Integer.parseInt(str[i]);
            }
            List<List<Integer>> res = threeSum(nums);
            System.out.println(res);
        }
    }
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums.length == 0) return res;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > 0) break;
            if(i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1;
            int right = nums.length - 1;
            while(left < right){
                if((nums[i] + nums[left] + nums[right]) > 0) right--;
                else if((nums[i] + nums[left] + nums[right]) < 0) left++;
                else{
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while(left < right && nums[right] == nums[right - 1]) right--;
                    while(left < right && nums[left] == nums[left + 1]) left++;
                    right--;
                    left++;
                }
            }
        }
        return res;
    }
}
