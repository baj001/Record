package com.shang.javatest.fourWaysThread;

/**
 * @author baj
 * @creat 2022-06-13 21:49
 */
public class MyThread2 {
    public static void main(String[] args) {
        Thread2 thread2 = new Thread2();
        Thread test1 = new Thread(thread2);
        Thread test2 = new Thread(thread2);
        test1.start();
        test2.start();
    }
}

class Thread2 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }
}
