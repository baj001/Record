package fkshang.com.CodeTop.codetop1;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author baj
 * @creat 2022-08-11 9:24
 */
public class LC15 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
//        接收一个数组
        while(in.hasNext()){
            String[] str = in.nextLine().split(",");
            int[] arr = new int[str.length];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = Integer.parseInt(str[i]);
            }

            List<List<Integer>> res = threeSum(arr);
            System.out.println(res);
        }
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums.length == 0) return res;
        //先排序
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > 0) break;
            if(i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1;
            int right = nums.length - 1;
            while(left < right){
                if((nums[i] + nums[left] + nums[right]) > 0) right--;
                else if((nums[i] + nums[left] + nums[right]) < 0) left++;
                else {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while(left < right && nums[right] == nums[right - 1]) right--;
                    while(left < right && nums[left] == nums[left + 1]) left++;
                    left++;
                    right--;
                }
            }
        }
        return res;
    }

}
