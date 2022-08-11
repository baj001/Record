package com.shang.javatest.Thread.Thread1;

/**
 * @author baj
 * @creat 2022-05-17 21:32
 */



public class MyThread1 implements Runnable {

    private static Object lock = new Object();

    private static int count = 1;

    int no;

    public MyThread1(int no) {
        this.no = no;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                if (count > 100) {
                    break;
                }
                /*
                count的值从1开始递增，则%3的结果为
                 1 2 0 1 2
                 */
                if (count % 3 == this.no) {
                    //一开始定义count为1
                    System.out.println("线程" + this.no + "--->" + count);
                    count++;
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notifyAll();
            }
        }

    }
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new MyThread1(0));
        Thread t2 = new Thread(new MyThread1(1));
        Thread t3 = new Thread(new MyThread1(2));
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
    }
}

