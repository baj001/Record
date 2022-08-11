package fkshang.com.standard.test;

/**
 * @author baj
 * @creat 2022-05-22 9:57
 */

import java.util.*;
public class LeetCode98 {
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(){}
        public TreeNode(int val){
            this.val = val;
        }
    }
    //方法入口
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String[] s = scanner.nextLine().split(" ");
            int[] arr = new int[s.length];
            for(int i = 0; i < arr.length; i++){
                arr[i] = Integer.parseInt(s[i]);
            }
            //构建二叉树
            TreeNode root = build(arr);
            boolean ans = isValidBST(root);
            System.out.println(ans);
        }
    }
    //判定函数
    public static boolean isValidBST(TreeNode root) {
        return recur(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    public  static boolean recur(TreeNode root, long lower, long upper){
        if(root == null) return true;
        if(root.val <= lower || root.val >= upper) return false;
        return recur(root.left, lower, root.val)
                && recur(root.right, root.val, upper);

    }
    //构建二叉树
    private static TreeNode build(int[] arr) {
        List<TreeNode> list = new ArrayList<>();
        Collections.fill(list, null);
        TreeNode root = null;
        for(int i = 0; i < arr.length; i++){
            TreeNode node = null;
            if(arr[i] != -1){
                node = new TreeNode(arr[i]);
            }
            list.add(i,node);
            if(i == 0){
                root = node;
            }
        }
        for (int i = 0;  2 * i + 2 < arr.length ; i++) {
            if(list.get(i) != null){
                list.get(i).left = list.get(2 * i + 1);
                list.get(i).right = list.get(2 * i + 2);
            }
        }
        return root;
    }

}
