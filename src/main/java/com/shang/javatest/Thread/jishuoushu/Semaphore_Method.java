package com.shang.javatest.Thread.jishuoushu;

import java.util.concurrent.Semaphore;

/**
 * @author baj
 * @creat 2022-05-31 15:34
 * 定义两个信号量，一个许可为1，一个许可为0。
 * 首先许可为0的会阻塞，所以t1线程先执行，
 * 通过s1.acquire()消耗许可，打印奇数，
 * 此时s1许可为0，t1阻塞，
 * 同时s2.release()获得一个许可，t2线程通过s2.acquire()消耗许可，打印偶数，
 * 此时s2许可又变为0，t2阻塞，
 * 同时s1.release()获得一个许可。如此反复执行，直到打印完所有的数。
 */
public class Semaphore_Method {

    public static void main(String[] args) {
        Semaphore s1 = new Semaphore(1);
        Semaphore s2 = new Semaphore(0);
        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 10; i += 2) {
                try {
                    s1.acquire();
                    System.out.println(Thread.currentThread().getName() + "****" + i);
                    s2.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, "线程t1");
        Thread t2 = new Thread(() -> {
            for (int i = 2; i <= 10; i += 2) {
                try {
                    s2.acquire();
                    System.out.println(Thread.currentThread().getName() + "****" + i);
                    s1.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "线程t2");
        t1.start();
        t2.start();
    }
}

