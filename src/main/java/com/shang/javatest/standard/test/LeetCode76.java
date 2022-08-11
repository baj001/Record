package com.shang.javatest.standard.test;

import java.util.Scanner;

/**
 * @author baj
 * @creat 2022-05-21 22:00
 */
public class LeetCode76 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入长字符串");
        String s = scanner.next();
//        String s = scanner.next().toString();
        System.out.println("输入短字符串");
        String t = scanner.next();
//        String t = scanner.next().toString();
        String ans = minWindow(s, t);
        System.out.println("结果为");
        System.out.println(ans);
    }
    public static String minWindow(String s, String t) {
        if(s == null || s.length()==0||t==null||t.length()==0) return "";
        int l = 0, r = 0, start = 0, maxLen = Integer.MAX_VALUE, count = t.length();
        int[] need = new int[128];
        for(int i = 0; i < t.length(); i++){
            need[t.charAt(i)]++;
        }
        while(r < s.length()){
            if(need[s.charAt(r)] > 0){
                count--;
            }
            need[s.charAt(r)]--;
            if(count == 0){
                while(l < r && need[s.charAt(l)] < 0){
                    need[s.charAt(l)]++;
                    l++;
                }
                //到了关键位置
                if(r - l + 1 < maxLen){
                    maxLen = r - l + 1;
                    start = l;
                }
                need[s.charAt(l)]++;
                count++;
                l++;
            }
            r++;
        }
        return maxLen == Integer.MAX_VALUE ? "" : s.substring(start, start + maxLen);
    }
}
