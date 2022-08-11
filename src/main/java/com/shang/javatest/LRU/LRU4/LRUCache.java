package com.shang.javatest.LRU.LRU4;

import java.util.HashMap;
import java.util.Map;

/**
 * @author baj
 * @creat 2022-05-15 21:38
 */
class LRUCache {
    private class Node {
        Node prev, next;
        int key, value;
        private Node(int k, int v) {
            this.key = k;
            this.value = v;
        }
    }
    private class DoubleList {
        Node head = new Node(0, 0);
        Node tail = new Node(0, 0);
        int size;

        private DoubleList() {
            head.next = tail;
            tail.prev = head;
            size = 0;
        }
        private void addFirst(Node n) {
            Node headNext = head.next;
            head.next = n;
            headNext.prev = n;
            n.prev = head;
            n.next = headNext;
            size++;
        }
        private void remove(Node n) {
            n.prev.next = n.next;
            n.next.prev = n.prev;
            size--;
        }
        private Node removeLast() {
            Node last = tail.prev;
            remove(last);
            return last;
        }
        private int size() {
            return size;
        }
    }

    // key -> Node(key, val)
    private Map<Integer, Node> map;
    // node(k1, v1) <-> Node(k2, v2)...
    private DoubleList cache;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
        cache = new DoubleList();
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        // 利⽤ put ⽅法把该数据提前
        int val = map.get(key).value;
        put(key, val);
        return val;
    }

    public void put(int key, int value) {
        Node n = new Node(key, value);
        if (map.containsKey(key)) {
            cache.remove(map.get(key));
            cache.addFirst(n);
            map.put(key, n);
        } else {
            if (cache.size() == capacity) {
                // delete last element in list
                Node last = cache.removeLast();
                map.remove(last.key);
            }
            cache.addFirst(n);
            map.put(key, n);
        }
    }
}