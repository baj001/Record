package com.shang.javatest.sorting;

import java.util.Random;

/**
 * @author baj
 * @creat 2022-06-30 0:13
 */

//public class Solution {
//    // 插入排序：稳定排序，在接近有序的情况下，表现优异
//    public int[] sortArray(int[] nums) {
//        int len = nums.length;
//        // 循环不变量：将 nums[i] 插入到区间 [0, i) 使之成为有序数组
//        for (int i = 1; i < len; i++) {
//            // 先暂存这个元素，然后之前元素逐个后移，留出空位
//            int temp = nums[i];
//            int j = i;
//            // 注意边界 j > 0
//            while (j > 0 && nums[j - 1] > temp) {
//                nums[j] = nums[j - 1];
//                j--;
//            }
//            nums[j] = temp;
//        }
//        return nums;
//    }
//}

public class Solution {
    private static final int LENGTH = 7;
    private static final Random RANDOM = new Random();
    public int[] sortArray(int[] nums) {
        int len = nums.length;
        quickSort(nums, 0, len - 1);
        return nums;
    }
    public void quickSort(int[] nums, int left, int right){
        //若是小区间则使用插入排序
        if(right - left <= LENGTH){
            insertionSort(nums, left, right);
            return;
        }
        int index = findIndex(nums, left, right);
        quickSort(nums, left, index - 1);
        quickSort(nums, index + 1, right);
    }
    //如下是插入排序
    public void insertionSort(int[] nums, int left, int right){
        for(int i = left + 1; i <= right; i++){
            int temp = nums[i];
            int j = i;
            while(j > left && nums[j - 1] > temp){
                nums[j] = nums[j - 1];
                j--;
            }
            nums[j] = temp;
        }
    }
    public int findIndex(int[] nums, int left, int right){
        //使用nextInt在指定的范围内生成一个随机数
        int randomIndex = RANDOM.nextInt(right - left + 1) + left;
        //进行交换，将这个随机值得值交换到最左边
        swap(nums, left, randomIndex);
        //将其设定为基准值
        int midVal = nums[left];
        int i = left;
        int j = right;
        while(i != j){
            //此时我的目的是寻找到右侧比mid小的，左侧比mid大的，然后交换
            while(nums[j] >= midVal && i < j){
                j--;
            }
            while(nums[i] <= midVal && i < j){
                i++;
            }
            //找到了
            swap(nums, i, j);
        }
        //当左右指针相遇，说明已经处理完成了，现在i 和 j执行同一个位置，需要将
        //mid的位置和该位置进行交换
        swap(nums, i, left);
        //使用swap函数只是进行值得交换，但是索引的位置并没有改变，因此返回i j 均可
        return j;
    }

    //定义交换连个位置的函数
    public void swap(int[] nums, int left, int right){
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
