package com.shang.javatest.standard.practice;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author baj
 * @creat 2022-05-22 18:27
 * 先建立一个节点类，然后手动实现数组中的节点赋值到stack中取最后实现
 */
public class LinkList1{
    public static class ListNode{
        int val;
        ListNode next;
        ListNode(){};
        ListNode(int val){this.val = val;}
    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String[] str = in.nextLine().split(",");
            int[] arr = new int[str.length];
            for(int i = 0; i < arr.length; i++){
                arr[i] = Integer.parseInt(str[i]);
            }
            Stack<ListNode> stack = new Stack<>();
            ListNode head = new ListNode(0);
            ListNode pre = head;
            for(int i = 0; i < arr.length; i++){
                pre.next = new ListNode(arr[i]);
                pre = pre.next;
                stack.add(pre);
            }
            head = head.next;
            ListNode res = fun(head);
            while(res != null){
                if(res.next == null){
                    System.out.print(res.val);
                }else{
                    System.out.print(res.val+",");
                }
                res = res.next;
            }
        }
    }
    public static ListNode fun(ListNode head){
        ListNode pre = null;
        ListNode cur = head;
        ListNode tmp = null;
        while(cur != null){
            tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }
}
























