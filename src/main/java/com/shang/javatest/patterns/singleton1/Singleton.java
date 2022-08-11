package com.shang.javatest.patterns.singleton1;

/**
 * @author baj
 * @creat 2022-05-14 22:57
 */
public class Singleton{
    // [1]私有化构造方法。
    private Singleton(){ }
    // [2] 事先创建好当前类的一个私有静态对象
    private static volatile Singleton singletion;

    public static Singleton getInstance(){
        //第一层判断
        if(singletion == null){
            synchronized (Singleton.class){
                //第二层判断
                if(singletion == null){
                    singletion = new Singleton();
                }
            }
        }
        return singletion;
    }
}
