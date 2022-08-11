package com.shang.javatest.Thread.Thread2;


/**
 * @author baj
 * @creat 2022-05-17 22:48
 */
public class Test {
    //此处定义两个volatile类型的量
    private volatile int number = 1;
    private volatile int value = 1;

    void firstPrint(){
        synchronized (this){
            while(value != 1){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+":"+number);
            number++;
            value = 2;
            notifyAll();
        }
    }
    void secondPrint(){
        synchronized (this){
            while(value != 2){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+":"+number);
            number++;
            value = 3;
            notifyAll();
        }
    }
    void thirdPrint(){
        synchronized (this){
            while(value != 3){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+":"+number);
            number++;
            value = 1;
            notifyAll();
        }
    }



}

class main{
    public static void main(String[] args) {
        Test test = new Test();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 34; i++) {
                    test.firstPrint();
                }
            }
        },"线程一").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 33; i++) {
                    test.secondPrint();
                }
            }
        },"线程二").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 33; i++) {
                    test.thirdPrint();
                }
            }
        },"线程三").start();
    }
}