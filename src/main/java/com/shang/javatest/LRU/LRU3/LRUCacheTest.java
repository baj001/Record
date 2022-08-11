package com.shang.javatest.LRU.LRU3;

import java.util.HashMap;

/**
 * @author baj
 * @creat 2022-05-16 8:31
 */
public class LRUCacheTest {

    //首先自定义一个节点，具有kv和两个指针
    class Node{
    public int key, val;
    public Node next, prev;
    public Node(int k, int v) {
        this.key = k;
        this.val = v;
        }
    }

    //手动定义一个双向链表 插入、删除、删除第一个节点、返回长度
    class DoubleList{
        //定义链表虚拟头尾节点
        private Node head, tail;
        //定义链表元素
        private int size;
        //定义空参构造器
        public DoubleList(){
            head = new Node(0,0);
            tail = new Node(0,0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        //链表的尾部添加操作 head x tail
        public void addLast(Node x){
            x.prev = tail.prev;
            x.next = tail;
            tail.prev.next = x;
            tail.prev = x;
            size++;
        }
        //删除链表中某一节点
        public void remove(Node x){
            x.prev.next = x.next;
            x.next.prev = x.prev;
            size--;
        }
        //删除链表中的第一个节点，并返回该节点
        public Node removeFirst(){
            if(head.next == null){
                return null;
            }
            //获取第一个节点
            Node first = head.next;
            remove(first);
            return first;
        }
        //返回链表的长度
        public int size(){ return size;}
    }

    //初始化map cache
    private HashMap<Integer, Node> map;
    private DoubleList cache;
    private int cap;

    public LRUCacheTest(int capacity){
        this.cap = capacity;
        map = new HashMap<>();
        cache = new DoubleList();
    }
    //实现get
    public int get(int key){
        if(!map.containsKey(key)){
            return -1;
        }
        //提升为最近使用
        Node x = map.get(key);
        cache.remove(x);
        cache.addLast(x);
        return map.get(key).val;
    }
    //实现put方法
    public void put(int key, int val){
        Node n = new Node(key, val);
        if(map.containsKey(key)){
            cache.remove(map.get(key));
            map.remove(key);
            cache.addLast(n);
            map.put(key, n);
            return;
        }else {
            if(cache.size() == cap){
                Node first = cache.removeFirst();
                map.remove(first.key);
            }
            //若没有满
            cache.addLast(n);
            map.put(key,n);
        }
    }
}
