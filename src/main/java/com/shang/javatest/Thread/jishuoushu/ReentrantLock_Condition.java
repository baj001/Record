package com.shang.javatest.Thread.jishuoushu;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author baj
 * @creat 2022-05-31 15:35
 */
public class ReentrantLock_Condition {
    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    private static boolean flag = false;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {

            for (int i = 1; i <= 10; i += 2) {
                lock.lock();
                while (flag) {
                    try {
                        condition.await();
                    } catch (Exception e) {

                    }
                }
                System.out.println(Thread.currentThread().getName() + "****" + i);
                condition.signal();
                flag = true;
                lock.unlock();
            }

        }, "线程t1");
        Thread t2 = new Thread(() -> {

            for (int i = 2; i <= 10; i += 2) {
                lock.lock();
                while (!flag) {
                    try {
                        condition.await();
                    } catch (Exception e) {

                    }
                }
                System.out.println(Thread.currentThread().getName() + "****" + i);
                condition.signal();
                flag = false;
                lock.unlock();
            }
        }, "线程t2");

        t1.start();
        t2.start();
    }
}

