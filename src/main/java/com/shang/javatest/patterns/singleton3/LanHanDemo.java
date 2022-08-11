package com.shang.javatest.patterns.singleton3;

/**
 * @author baj
 * @creat 2022-05-15 20:20
 */
public class LanHanDemo {
    //创建私有的构造方法
    private LanHanDemo(){};
    //创建私有的静态对象
    private static volatile LanHanDemo lanHanDemo;
    public static LanHanDemo getInstance(){
        /*
        加锁：保证只有一个线程进入
        双重检查，防止多个线程同时进入第一层检查
         */
        //第一层判断
        if(lanHanDemo == null){
            synchronized(LanHanDemo.class){
                if(lanHanDemo == null){
                    lanHanDemo = new LanHanDemo();
                }
            }
        }
        return lanHanDemo;
    }

    public static void main(String[] args) {
        LanHanDemo lanHanDemo = LanHanDemo.getInstance();
        LanHanDemo lanHanDemo1 = LanHanDemo.getInstance();
        System.out.println(lanHanDemo == lanHanDemo1);
    }
}
