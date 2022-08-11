package com.shang.javatest.standard.test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author baj
 * @creat 2022-05-21 21:55
 */
public class Main32 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入一串括号");
        String str = scanner.next();
        int ans = longestValidParentheses(str);
        System.out.println(ans);
    }
    public static int longestValidParentheses(String s) {
        int maxLen = 0;
        Deque<Integer> stack = new LinkedList<>();
        stack.push(-1);
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                stack.push(i);
            }else{
                stack.pop();
                if(stack.isEmpty()){
                    stack.push(i);
                }else{
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }
        return maxLen;
    }
}
