package com.shang.javatest.fourWaysThread;

/**
 * @author baj
 * @creat 2022-06-13 21:43
 */
public class MyThread1 {
    public static void main(String[] args) {
        Thread1 test1 = new Thread1();
        Thread1 test2 = new Thread1();
        test1.start();
        test2.start();
    }
}
class Thread1 extends Thread{
    //遍历100以内的整数
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            //调用getName()方法显示当前线程名，便于观察
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }
}
