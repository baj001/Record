package com.shang.javatest.Account.method5;

/**
 * @author baj
 * @creat 2022-05-15 11:02
 */

/**
 * 消费者类Consumer继承线程类Thread
 * <p>
 * Email:530025983@qq.com
 *
 * @author MONKEY.D.MENG 2011-03-15
 */
public class Consumer extends Thread {
    // 每次消费的产品数量
    private int num;

    // 所在放置的仓库
    private Storage storage;

    // 构造函数，设置仓库

    public Consumer(Storage storage) {
        this.storage = storage;
    }
//    public Consumer(Storage storage)
//    {
//        this.storage = storage;
//    }

    // 线程run函数
    public void run() {
        storage.consume(num);
    }

    // get/set方法
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}