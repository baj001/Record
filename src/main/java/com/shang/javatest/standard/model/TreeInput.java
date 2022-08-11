package com.shang.javatest.standard.model;//package com.shang.javatest.standard.model;

import java.util.*;

/**

 */
public class TreeInput {
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
            String s = scanner.nextLine();
            String[] split = s.split(" ");
            int[] arr = new int[split.length];
            for(int i = 0; i < arr.length; i++){
                arr[i] = Integer.parseInt(split[i]);
            }
            //构建二叉树
            TreeNode root = build(arr);
            //层序遍历二叉树
            List<List<Integer>> res = help(root);
            //打印结果
            for (List<Integer> ans : res){
                System.out.print(ans);
            }
        }
    }
    //层序遍历二叉树
    private static List<List<Integer>> help(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(queue.size() > 0){
            List<Integer> tmp = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                tmp.add(node.val);
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
            }
            res.add(tmp);
        }
        return res;
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
