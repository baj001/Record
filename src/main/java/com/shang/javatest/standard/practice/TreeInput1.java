package com.shang.javatest.standard.practice;
import java.util.*;

/**
 * @author baj
 * @creat 2022-05-23 8:58
 */
public class TreeInput1 {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(){};
        public TreeNode(int val){this.val = val;}
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String[] str = in.nextLine().split(" ");
            int[] arr = new int[str.length];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = Integer.parseInt(str[i]);
            }
            TreeNode root = build(arr);
            List<List<Integer>> res = help(root);
            for(List<Integer> ans : res){
                System.out.print(ans);
            }
        }
    }
    public static List<List<Integer>> help(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        Deque<TreeNode> que = new LinkedList<>();
        que.offer(root);
        while(!que.isEmpty()){
            List<Integer> item = new ArrayList<>();
            int len = que.size();
            while(len-- > 0){
                TreeNode node = que.poll();
                item.add(node.val);
                if(node.left != null) que.offer(node.left);
                if(node.right != null) que.offer(node.right);
            }
            res.add(item);
        }
        return res;
    }
    public static TreeNode build(int[] arr){
        List<TreeNode> list = new ArrayList<>();
        Collections.fill(list, null);
        TreeNode root = null;
        for (int i = 0; i < arr.length; i++) {
            TreeNode node = null;
            if(arr[i] != -1){
                //若不是-1，则将其转化为节点
                node = new TreeNode(arr[i]);
            }
            list.add(i, node);
            if(i == 0) root = node;
        }
        for (int i = 0; i * 2 + 2 < arr.length; i++) {
            if(list.get(i) != null){
                list.get(i).left = list.get(2 * i + 1);
                list.get(i).right = list.get(2 * i + 2);
            }
        }
        return root;
    }

}
























