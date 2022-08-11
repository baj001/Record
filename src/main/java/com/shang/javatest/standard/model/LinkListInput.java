package com.shang.javatest.standard.model;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author baj
 * @creat 2022-05-21 21:41
 */
public class LinkListInput {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String[] arr = scanner.next().split(",");
            //初始化一个整数数组
            int[] ints = new int[arr.length];
            for (int j = 0; j < ints.length; j++) {
                ints[j] = Integer.parseInt(arr[j]);
            }

            Stack<ListNode> stack = new Stack<>();
            ListNode head = new ListNode(0);
            ListNode p = head;
            //链表初始化并放入stack中
            for (int i = 0; i < ints.length; i++) {
                p.next = new ListNode(ints[i]);
                p = p.next;
                stack.add(p);
            }
            head = head.next;

            //调用函数
            ListNode res = reverseList(head);

            while (res != null) {
                if(res.next == null){
                    System.out.print(res.val);
                }else{
                    System.out.print(res.val + ",");
                }
                res = res.next;
            }
        }
    }

    public static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode tmp = null;
        while (cur != null) {
            tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }
}

/**
public class LinkListInput {
    //题目描述
    //对于一个链表 L: L0→L1→…→Ln-1→Ln,
    //将其翻转成 L0→Ln→L1→Ln-1→L2→Ln-2→…
    //先构建一个节点类，用于链表构
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //以字符串形式作为输入
        while(scanner.hasNext()){
            String str = scanner.next();
            //通过分隔符将其转为字符串数组
            String[] arr = str.split(",");
            //初始化一个整数数组
            int[] ints = new int[arr.length];
            //给整数数组赋值
            for (int j = 0; j < ints.length; j++) {
                ints[j] = Integer.parseInt(arr[j]);
            }
            Stack<ListNode> stack = new Stack<>();
            ListNode head = new ListNode(0);
            ListNode p = head;
            //链表初始化并放入stack中
            for (int i = 0; i < ints.length; i++) {
                p.next = new ListNode(ints[i]);
                p = p.next;
                stack.add(p);
            }
            head = head.next;
            //调用函数
            ListNode res = reverseList(head);
            while (res != null) {
                if(res.next == null){
                    System.out.print(res.val);
                }else{
                    System.out.print(res.val + ",");
                }
                res = res.next;
            }
        }
    }

    public static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode tmp = null;
        while (cur != null) {
            tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }
}
 */
