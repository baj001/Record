package com.shang.javatest.patterns.singleton1;

/**
 * @author baj
 * @creat 2022-05-14 22:56
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author evanYang
 * @version 1.0
 * @date 09/20/2019 10:06
 */
public class LanHanDemo {
    //私有化构造方法
    private LanHanDemo(){}
    //创建好当前类的一个私有的静态对象
    private static volatile LanHanDemo lanHanDemo;
    public static LanHanDemo getInstance(){
        if (lanHanDemo==null){
            synchronized (LanHanDemo.class){
                if (lanHanDemo==null){
                    lanHanDemo=new LanHanDemo();
                    return lanHanDemo;
                }
            }
        }
        return lanHanDemo;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(LanHanDemo.getInstance());

                    }
                });

    }
}

