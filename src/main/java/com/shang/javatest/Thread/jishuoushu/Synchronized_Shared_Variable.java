package com.shang.javatest.Thread.jishuoushu;

/**
 * @author baj
 * @creat 2022-05-31 15:23
 * 新建一个对象，并定义一个共享变量。只要flag不为1，线程t2就会阻塞，释放锁资源，
 * 所以t1线程先执行，此时flag为0，跳过while判断，然后修改flag为1，打印奇数，并唤醒t2，
 * 由于flag被改为1，下次循环，t1就会阻塞。t2被唤醒后，t1已经释放了锁资源，所以t2可以获取锁资源，
 * 并且flag为1，跳过while循环，修改flag为0，打印偶数，唤醒t1。
 *
 */
public class Synchronized_Shared_Variable {

    static final Object obj = new Object();
    private static int flag = 0;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (obj) {
                //此处当flag非0的时候，才执行后序的
                for (int i = 1; i <= 100; i += 2) {
                    while (flag != 0) {
                        try {
                            obj.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    flag = 1;
                    System.out.println(Thread.currentThread().getName() + "打印" + i);
                    obj.notify();

                }
            }
        }, "线程t1");
        Thread t2 = new Thread(() -> {
            synchronized (obj) {
                for (int i = 2; i <= 100; i += 2) {
                    while (flag != 1) {
                        try {
                            obj.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    flag = 0;
                    System.out.println(Thread.currentThread().getName() + "打印" + i);
                    obj.notify();
                }
            }
        }, "线程t2");
        t1.start();
        t2.start();
    }
}

