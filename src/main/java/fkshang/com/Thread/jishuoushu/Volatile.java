package fkshang.com.Thread.jishuoushu;

/**
 * @author baj
 * @creat 2022-05-31 15:31
 * 定义一个volatile修饰的共享变量flag，
 * 当flag为false时，t2线程让出系统资源，自己进入就绪状态，
 * 让t1先执行，t1获取资源后，首先跳过while判断，
 * 打印奇数，修改flag为true，下次循环时，就会让出资源，
 * 此时t2从就绪状态进入执行状态，跳过while判断，
 * 打印偶数，修改flag为false，下次循环时，就会让出资源。
 * 如此不断交替执行，直到打印完所有奇数偶数。
 */
public class Volatile {
    private static volatile boolean flag = false;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {

            for (int i = 1; i <= 100; i += 2) {
                while (flag) {
                    Thread.yield();
                }
                System.out.println(Thread.currentThread().getName() + "****" + i);
                flag = true;
            }

        }, "线程t1");
        Thread t2 = new Thread(() -> {

            for (int i = 2; i <= 100; i += 2) {
                while (!flag) {
                    Thread.yield();
                }
                System.out.println(Thread.currentThread().getName() + "****" + i);
                flag = false;
            }
        }, "线程t2");

        t1.start();
        t2.start();
    }
}


