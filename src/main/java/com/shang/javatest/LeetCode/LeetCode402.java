//package com.shang.javatest.LeetCode;
//
//import java.util.ArrayDeque;
//import java.util.Deque;
//import java.util.Scanner;
//
///**
// * @author baj
// * @creat 2022-07-25 17:21
// */
//public class LeetCode402 {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while(in.hasNext()){
//            String num = in.nextLine();
//            int k = in.nextInt();
//            String res = removeKdigits(num, k);
//            System.out.println(res);
//        }
//    }
//    public static String removeKdigits(String num, int k) {
//        Deque<Character> stack = new ArrayDeque<>(num.length());
//        for(char c : num.toCharArray()){
//            //当栈中的元素大于当前的元素 且 k 大于0 且 栈是非空的的时候，对比大小然后pop
//            while(k > 0 && !stack.isEmpty() && c < stack.peek()){
//                stack.pop();
//                k--;
//            }
//            //如果是"0432219"，0 遇不到比它更小的，最后肯定被留在栈中，变成 0219，还得再去掉前导0
//            //若 当前的元素是非 0 的时候，或者栈是非空，则将元素加入栈中 主要用于剔除前导0
//            if( c != '0' || !stack.isEmpty()){
//                stack.push(c);
//            }
//        }
//        //遍历结束时，有可能还没删够 k 个字符，继续循环出栈，删低位
//        while( k > 0 && !stack.isEmpty()){
//            stack.pop();
//            k--;
//        }
//
//        StringBuffer buffer = new StringBuffer();
//        while(!stack.isEmpty()){
//            buffer.append(stack.pollLast());
//        }
//
//        return buffer.length() == 0 ? "0" : buffer.toString();
//    }
//
//}
