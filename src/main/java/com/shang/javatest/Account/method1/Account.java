package com.shang.javatest.Account.method1;

/**
 * @author baj
 * @creat 2022-05-14 11:02
 */
public class Account {
    volatile private int value;
    //synchronized可以添加在变量方法或者类上
    synchronized void put(int i)
    {
        try {
            Thread.sleep(300);
            } catch (InterruptedException e) {
                    }
        value = value + i;
        System.out.println(Thread.currentThread().getName()+" 生产者--存入了 "+i+" 余量为："+value);
    }
    synchronized int get(int i)
    {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
        }
        if (value>i)
            value = value - i;
        else
        {   i = value;
            value = 0;
        }
        System.out.println(Thread.currentThread().getName()+ " 消费者--消费了 "+i+" 余量为："+value);
        return i;
    }
}

class  Save implements Runnable {
    private Account a1;
    //构造器 只有一个参数
    public Save(Account a1)
    {
        this.a1 = a1;
    }
    public void run()
    {
        while(true){
            a1.put(1);
        }
    }
}

class Fetch implements Runnable {
    private Account a1;
    public Fetch(Account a1) {
        this.a1 = a1 ;
    }
    public void run()
    {
        while(true){
            a1.get(1);
        }
    }
}


