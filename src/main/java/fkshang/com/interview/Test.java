package fkshang.com.interview;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @author baj
 * @creat 2022-06-04 16:46
 */
public class Test {
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
    public static void main(String[] args) {
        HashMap<String, Integer> m = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\baj\\Desktop\\11.txt"));
            String ss = null;
            while ((ss = reader.readLine()) != null) {
                String[] str = ss.trim().split("\\s+");
                List<String> res = topKFrequent(str, 2);
                System.out.println(Arrays.toString(res.toArray()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



//        for (String key : map.keySet()) {
//            System.out.println(key + ": " + map.get(key));
//        }








//package fkshang.com.interview;
//
//import java.io.BufferedReader;
//import java.io.FileOutputStream;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Set;
//import java.util.TreeMap;
//
///**
// * @author baj
// * @creat 2022-06-04 16:46
// */
//public class Test {
//    public static void main(String[] args) {
//        HashMap<String, Integer> m = new HashMap<>();
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\baj\\Desktop\\11.txt"));
//            String ss = null;
//            while ((ss = reader.readLine()) != null) {
//                String[] str = ss.trim().split("\\s+");
//                for (int i = 0; i < str.length; i++) {
//                    String word = str[i].trim();
//                    if (m.containsKey(word)) {
//                        m.put(word, m.get(word) + 1);
//                    } else {
//                        m.put(word, 1);
//                    }
//                }
//            }
//            int cnt = 0;
//            System.out.println("统计各单词个数为:");
//            Set<String> st = m.keySet();
//            for (String k : st) {
//                if (k.hashCode() != 0) {
//                    char[] str = k.toCharArray();
//                    for (int i = 0; i < str.length; i++) {
//                        if ((str[i] >= 'a' && str[i] <= 'z') || (str[i] >= 'A' && str[i] <= 'Z')) {
//                            System.out.print(str[i]);
//                        }
//                    }
//                    System.out.println("出现了" + m.get(k.trim()) + "次");
//                    cnt++;
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}