package com.shang.javatest.Account.method1;

/**
 * @author baj
 * @creat 2022-05-14 11:04
 */

public class Test{
    public static void main(String[] args){
        Account a1 = new Account();
        new Thread(new Save(a1)).start();
        new Thread(new Save(a1)).start();
        new Thread(new Save(a1)).start();
        new Thread(new Save(a1)).start();
        new Thread(new Save(a1)).start();
        new Thread(new Save(a1)).start();
        new Thread(new Save(a1)).start();
        new Thread(new Save(a1)).start();
        new Thread(new Save(a1)).start();
        new Thread(new Save(a1)).start();
        //10个生产者和消费者
//        new Thread(new Fetch(a1)).start();
//        new Thread(new Fetch(a1)).start();
//        new Thread(new Fetch(a1)).start();
//        new Thread(new Fetch(a1)).start();
//        new Thread(new Fetch(a1)).start();
//        new Thread(new Fetch(a1)).start();
//        new Thread(new Fetch(a1)).start();
//        new Thread(new Fetch(a1)).start();
//        new Thread(new Fetch(a1)).start();
//        new Thread(new Fetch(a1)).start();
    }
}