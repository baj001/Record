package com.shang.javatest.standard.test;

import java.util.Scanner;

/**
 * @author baj
 * @creat 2022-05-23 17:35
 */
public class LeetCode8 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String s = in.nextLine();
            int res = myAtoi(s);
            System.out.println(res);
        }
    }
    public static int myAtoi(String s) {
        int len = s.length();
        int index = 0;
        int res = 0;
        //去除前导0
        while(index < len && s.charAt(index) == ' ') index++;
        //判断是否存在全0的情况
        if(index == len) return 0;
        //记录符号
        int flag = 1;
        if(s.charAt(index) == '-'){
            index++;
            flag = -1;
        }else if(s.charAt(index) == '+'){
            index++;
        }
        //进入循环计算res
        while(index < len){
            if(s.charAt(index) < '0' || s.charAt(index) > '9') break;
            //判断大数
            if((res > Integer.MAX_VALUE / 10) || ((res == Integer.MAX_VALUE / 10) && (s.charAt(index) - '0') > Integer.MAX_VALUE % 10)) return Integer.MAX_VALUE;
            if((res < Integer.MIN_VALUE / 10) || ((res == Integer.MIN_VALUE / 10) && (s.charAt(index) - '0') > -(Integer.MIN_VALUE % 10))) return Integer.MIN_VALUE;
            //若上述不满足则计算res，带符号计算
            res = res * 10 + flag * (s.charAt(index) - '0');
            index++;
        }
        return res;
    }
}
