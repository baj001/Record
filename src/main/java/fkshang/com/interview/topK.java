package fkshang.com.interview;

import java.util.*;

/**
 * @author baj
 * @creat 2022-06-04 15:41
 */
public class topK {
    public static List<String> topKFrequent(String[] words, int k) {
        // 1.先用哈希表统计单词出现的频率
        Map<String, Integer> count = new HashMap();
        for (String word : words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        // 2.构建小根堆 这里需要自己构建比较规则 此处为 lambda 写法 Java 的优先队列默认实现就是小根堆
        PriorityQueue<String> minHeap = new PriorityQueue<>((s1, s2) -> {
            if (count.get(s1).equals(count.get(s2))) {
                return s2.compareTo(s1);
            } else {
                return count.get(s1) - count.get(s2);
            }
        });
        // 3.依次向堆加入元素。
        for (String s : count.keySet()) {
            minHeap.offer(s);
            // 当堆中元素个数大于 k 个的时候，需要弹出堆顶最小的元素。
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        // 4.依次弹出堆中的 K 个元素，放入结果集合中。
        List<String> res = new ArrayList<String>(k);
        while (minHeap.size() > 0) {
            res.add(minHeap.poll());
        }
        // 5.注意最后需要反转元素的顺序。
        Collections.reverse(res);
        return res;
    }
    public static void main(String[] args){
        String[] words = new String[]{"i", "love", "leetcode", "i", "love", "coding"};
        List<String> res = topKFrequent(words, 2);
        System.out.println(Arrays.toString(res.toArray()));
    }

}
