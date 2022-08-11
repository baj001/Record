package com.shang.javatest.standard.test;


/**
 * @author baj
 * @creat 2022-05-21 21:15
 */
/**
 * 首先获取一行数据，然后将其转化为int类型的数组
 * 通过测试发现要先获取数组才行
 * 然后 才获取整形
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 *
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 *
 * 你可以认为每种硬币的数量是无限的。
 *
 输入：coins = [1, 2, 5], amount = 11
 输出：3
 解释：11 = 5 + 5 + 1
 */
import java.util.*;
public class Main322 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
//            String[] s = in.nextLine().split(" ");
            String[] s = in.nextLine().split(",");
            int[] coins = new int[s.length];
            for (int i = 0; i < s.length; i++) {
                coins[i] = Integer.parseInt(s[i]);
            }
            int amount = in.nextInt();
            //现在获取好了参数进行传参
            int ans = coinChange(coins, amount);
            System.out.println(ans);
        }
    }
    public static int coinChange(int[] coins, int amount) {
        //初始化dp数组的值为最大值，dp数组的含义是，组成金额i所需要的硬币数
        int val = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, val);
        dp[0] = 0;
        for(int i = 1; i <= amount; i++){
            for(int j = 0; j < coins.length; j++){
                if(coins[j] <= i)
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
            }
        }
        return dp[amount] == val ? -1 : dp[amount];
    }
}
