package fkshang.com.LeetCode;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author baj
 * @creat 2022-06-19 19:40
 */
public class LeetCode24 {
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
            ListNode res = swapPairs(head);

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

    /*
    实现两两反转

     */
    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode tmp = null;
        ListNode cur = head;
        while(cur != null && cur.next != null){
            tmp = cur.next.next;
            pre.next = cur.next;
            cur.next.next = cur;
            cur.next = tmp;
            pre = cur;
            cur = cur.next;
        }
        return dummy.next;
    }
}
