package fkshang.com.Account.method5;

/**
 * 测试类Test
 * <p>
 * Email:530025983@qq.com
 *
 * @author MONKEY.D.MENG 2011-03-15
 */
public class Test {
    public static void main(String[] args) {
        // 仓库对象
        Storage storage = new Storage();

        // 生产者对象
        Producer p1 = new Producer(storage);
        Producer p2 = new Producer(storage);
        Producer p3 = new Producer(storage);
        Producer p4 = new Producer(storage);
        Producer p5 = new Producer(storage);
        Producer p6 = new Producer(storage);
        Producer p7 = new Producer(storage);

        // 消费者对象
        Consumer c1 = new Consumer(storage);
        Consumer c2 = new Consumer(storage);
        Consumer c3 = new Consumer(storage);

        // 设置生产者产品生产数量
        p1.setNum(10);
        p2.setNum(20);
        p3.setNum(30);
        p4.setNum(40);
        p5.setNum(50);
        p6.setNum(60);
        p7.setNum(70);

        // 设置消费者产品消费数量
        c1.setNum(50);
        c2.setNum(20);
        c3.setNum(30);

        // 线程开始执行
        c1.start();
        c2.start();
        c3.start();

        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
        p6.start();
        p7.start();
    }
}
