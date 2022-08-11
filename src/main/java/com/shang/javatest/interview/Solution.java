package com.shang.javatest.interview;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @author baj
 * @creat 2022-06-04 17:24
 */
public class Solution {
    public static List<String> topK(Map<String, Integer> count, int k) {
        // 构建小根堆，按照count中的值进行排序
        PriorityQueue<String> minHeap = new PriorityQueue<>((s1, s2) -> count.get(s1) - count.get(s2));
        // 向堆加入元素
        for (String s : count.keySet()) {
            minHeap.offer(s);
            // 堆中元素个数大于 k 个的时候，弹出堆顶最小的元素
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        // 弹出堆中的 K 个元素，放入结果集
        List<String> res = new ArrayList<String>(k);
        while (minHeap.size() > 0) {
            String ans = minHeap.poll();
            res.add(ans);
        }
        // 因为小根堆中弹出的顺序是从小到大，为了结果将出现的次数从大到小显示，进行反转res
        Collections.reverse(res);
        return res;
    }
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\baj\\Desktop\\test.txt"));
            String tmp = null;
            while ((tmp = reader.readLine()) != null) {
                //将字符分隔
                String[] str = tmp.trim().split("\\s+");
                // 用哈希表统计单词出现的频率
                Map<String, Integer> count = new HashMap<>();
                for (String word : str) {
                    count.put(word, count.getOrDefault(word, 0) + 1);
                }
                //调用topK函数选出出现次数最多的100个单词
                List<String> res = topK(count, 100);
                System.out.println("如下是出现次数最多的"+ res.size() +"个单词");
                for(String ans : res){
                    System.out.println(ans + "出现次数为：" + count.get(ans));
                }
//                System.out.println(Arrays.toString(res.toArray()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


//public class Solution {
//    public static List<String> topKFrequent(Map<String, Integer> count, int k) {
//        // 构建小根堆 这里需要自己构建比较规则 此处为 lambda 写法 Java 的优先队列默认实现就是小根堆
//        PriorityQueue<String> minHeap = new PriorityQueue<>((s1, s2) -> count.get(s1) - count.get(s2));
////        PriorityQueue<String> minHeap = new PriorityQueue<>((s1, s2) -> {
////            if (count.get(s1).equals(count.get(s2))) {
////                return s2.compareTo(s1);
////            } else {
////                return count.get(s1) - count.get(s2);
////            }
////        });
//        // 依次向堆加入元素。
//        for (String s : count.keySet()) {
//            minHeap.offer(s);
//            // 当堆中元素个数大于 k 个的时候，需要弹出堆顶最小的元素。
//            if (minHeap.size() > k) {
//                minHeap.poll();
//            }
//        }
//        // 依次弹出堆中的 K 个元素，放入结果集合中。
//        List<String> res = new ArrayList<String>(k);
//        while (minHeap.size() > 0) {
//            String ans = minHeap.poll();
//            res.add(ans);
//        }
//        // 注意最后需要反转元素的顺序。
//        Collections.reverse(res);
//        return res;
//    }
//    public static void main(String[] args) {
//        HashMap<String, Integer> m = new HashMap<>();
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\baj\\Desktop\\11.txt"));
//            String ss = null;
//            while ((ss = reader.readLine()) != null) {
//                String[] str = ss.trim().split("\\s+");
//                // 先用哈希表统计单词出现的频率
//                Map<String, Integer> count = new HashMap<>();
//                for (String word : str) {
//                    count.put(word, count.getOrDefault(word, 0) + 1);
//                }
//                List<String> res = topKFrequent(count, 100);
//                System.out.println("如下是出现次数最多的"+ res.size() +"个单词");
//                for(String ans : res){
//                    System.out.println(ans + "出现次数为：" + count.get(ans));
//                }
////                System.out.println(Arrays.toString(res.toArray()));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
