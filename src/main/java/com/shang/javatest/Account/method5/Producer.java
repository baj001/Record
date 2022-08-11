package com.shang.javatest.Account.method5;

/**
 * 生产者类Producer继承线程类Thread
 * <p>
 * Email:530025983@qq.com
 *
 * @author MONKEY.D.MENG 2011-03-15
 */

public class Producer extends Thread {
    // 每次生产的产品数量
    private int num;

    // 所在放置的仓库
    private Storage storage;

    // 构造函数，设置仓库
    public Producer(Storage storage) {
        this.storage = storage;
    }

    // 线程run函数
    public void run() {
        storage.produce(num);
    }

    //设置get set 方法
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
