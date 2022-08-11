package com.shang.javatest.LeetCode;

import java.util.LinkedList;

/**
 * @author baj
 * @creat 2022-07-26 17:44
 * 用两个栈来实现队列
 */
public class Offer09 {
    class CQueue {
        //使用双端队列作为栈
        LinkedList<Integer> A;
        LinkedList<Integer> B;
        public CQueue() {
            A = new LinkedList<>();
            B = new LinkedList<>();
        }
        //实现尾部插入
        public void appendTail(int value) {
            A.offerLast(value);
        }
        //若要实现队列出队列，使用辅助栈B，接收A的出栈结果，然后将B中的元素弹出
        public int deleteHead() {
           if(!B.isEmpty()){
               return B.pollLast();
           }
           if(A.isEmpty()) return -1;
           while(!A.isEmpty()){
               B.offerLast(A.pollLast());
           }
           return B.pollLast();
        }
    }
//    class CQueue {
//        //使用两个栈来实现队列 实现尾部插入和头部删除的功能
//        LinkedList<Integer> A;
//        LinkedList<Integer> B;
//        public CQueue() {
//            //定义两个栈
//            A = new LinkedList<Integer>();
//            B = new LinkedList<Integer>();
//        }
//        //实现尾部插入
//        public void appendTail(int value) {
//            A.addLast(value);
//        }
//        //实现头部删除  保持A 来插入和删除，而B是辅助栈
//        public int deleteHead() {
//            if(!B.isEmpty()){
//                return B.removeLast();
//            }
//            if(A.isEmpty()) return -1;
//            while(!A.isEmpty()){
//                B.addLast(A.removeLast());
//            }
//            return B.removeLast();
//        }
//    }

}
