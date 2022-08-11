package fkshang.com.LeetCode;

import java.util.Random;
import java.util.Scanner;

/**
 * @author baj
 * @creat 2022-06-19 18:14
 * 实现堆排序
 */
public class LeetCode912 {
        private static final int LENGTH = 7;
        private static final Random RANDOM = new Random();
        public static void main(String[] args){
            Scanner in = new Scanner(System.in);
            while(in.hasNext()){
                //接受一个字符数组，然后转化为int类型
                String[] arr = in.nextLine().split(",");
                int[] nums = new int[arr.length];
                for (int i = 0; i < nums.length; i++) {
                    nums[i] = Integer.parseInt(arr[i]);
                }
                int[] res = sortArray(nums);
                //打印res
                for(int num : nums){
                    System.out.print(num + " ");
                }
            }
        }
        public static int[] sortArray(int[] nums) {
            int len = nums.length;
            queickSort(nums, 0, len - 1);
            return nums;
        }
        public static void queickSort(int[] nums, int l, int r){
            if(r - l + 1 < LENGTH){
                insertSort(nums, l, r);
                return;
            }
            //使用切片函数获取一个mid位置
            int mid = helper(nums, l, r);
            queickSort(nums, l, mid - 1);
            queickSort(nums, mid + 1, r);
            return;
        }
        public static void insertSort(int[] nums, int l, int r){
            for(int i = l + 1; i <= r; i++){
                int j = i;
                int tmp = nums[i];
                while(j > l && tmp < nums[j - 1]){
                    nums[j] = nums[j - 1];
                    j--;
                }
                nums[j] = tmp;
            }
        }
        public static int helper(int[] nums, int l, int r){
            int randomIndex = RANDOM.nextInt(r - l + 1) + l;
            swap(nums, l, randomIndex);
            int midVal = nums[l];
            int i = l;
            int j = r;
            while(i != j){
                while(i < j && nums[j] >= midVal) j--;
                while(i < j && nums[i] <= midVal) i++;
                swap(nums, i, j);
            }
            swap(nums, l, i);
            return i;
        }
        public static void swap(int[] nums, int left, int right){
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
        }

}
