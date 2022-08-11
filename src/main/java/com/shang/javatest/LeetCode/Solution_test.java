package com.shang.javatest.LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author baj
 * @creat 2022-05-16 17:05
 * <p>
 * 1 3 4 9
 * 1 2 3 4 5 6 7 8 9
 */
public class Solution_test {
    public int subarraySum(int[] nums, int k){
        //使用map来保存前缀和
        Map<Integer, Integer> map = new HashMap<>();
        //在其中加入（0 1）的用例
        //若检测到发现 有个0，则说明其中有个3，即可以单独作为一个子数组
        map.put(0, 1);
        int preSum = 0;
        int count  = 0;
        for(int num : nums){
            preSum += num;
            //从之前的map中进行查看
            if(map.containsKey(preSum - k)){
                count += map.get(preSum - k);
            }
            Integer value = map.get(preSum);
            if(value != null){
                map.put(preSum, value + 1);
            }else{
                map.put(preSum, 1);
            }
        }
        return count;
    }
}
//public class Solution_test {
//    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//        int n = nums1.length;
//        int m = nums2.length;
//        //n为4m为9，则left为 7 right 为15/2 也是7
//        int left = (n + m + 1) / 2;
//        int right = (n + m + 2) / 2;
//        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;
//    }
//
//    //函数的作用是计算 两种情况下的得到的第k大的值 选取两个是为计算平均数
//    private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
//        //计算字符串的长度 因为设计到字符串的后序递归操作，所以会更新，则此处需一直计算最新的长度
//        int len1 = end1 - start1 + 1;
//        int len2 = end2 - start2 + 1;
//        //因为不知道两个字符串的长度，所以此处设定让len1一直小的，若len1较大，则进行交换:即在函数中靠前的位置是长度最大的
//        if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
//        //若len1 为 0了，则直接返回nums2中的start2 + k - 1即可，因为每次k的值都会更新
//        if (len1 == 0) return nums2[start2 + k - 1];
//        //若k为1，则选取两个数组中start索引处 值较小的一个
//        if (k == 1) return Math.min(nums1[start1], nums2[start2]);
//        //为了防止数组的长度小于 k/2 则每次比较都要从数组长度和k/2中较小的一个位置来比较，但是要-1
//        //例如 1 3 4 9 选取k/2 = 3 第3小的值，则从索引为2的位置出发，即第三个数的位置
//        int i = start1 + Math.min(len1, k / 2) - 1;
//        int j = start2 + Math.min(len2, k / 2) - 1;
//        //递归实现
//        if(nums1[i] > nums2[j]){
//            //此时要舍去nums2 中的j之前的部分 使用j+1开始往后比较  因为已经比较出了j个例如 j = 3 要比较的 k为 6 则index为0123的前四个舍弃，只需
//            //比较k-j-1=2个
//            return getKth(nums1, start1, end1, nums2, j+1, end2, k - (j - start2 + 1));
//        }else{
//            return getKth(nums1, i + 1,end1, nums2, start2, end2, k - (i - start1) - 1);
//        }
//    }
//}
