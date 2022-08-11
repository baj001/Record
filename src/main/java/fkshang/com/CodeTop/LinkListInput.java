package fkshang.com.CodeTop;
/**
 * @author baj
 * @creat 2022-08-11 10:16
 *
 * 链表输入
 *
 * LC206 反转链表
 */
import java.util.*;

public class LinkListInput {
    public static class ListNode{
        int val;
        ListNode next;
        ListNode(){};
        ListNode(int val){this.val = val;}
    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String[] arr = in.nextLine().split(",");
            int[] nums = new int[arr.length];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = Integer.parseInt(arr[i]);
            }

            Stack<ListNode> stack = new Stack<>();
            ListNode head = new ListNode(0);
            ListNode p = head;
            //链表初始化并放入stack中
            for(int i = 0; i < nums.length; i++){
                p.next = new ListNode(nums[i]);
                p = p.next;
                stack.add(p);
            }
            head = head.next;

            // 调用函数
            ListNode res = reverseList(head);
            // 打印结果
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

    public static ListNode reverseList(ListNode head){
        ListNode pre = null;
        ListNode temp = null;
        ListNode cur = head;
        while(cur != null){
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }
}
