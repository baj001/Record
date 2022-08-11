package com.shang.javatest.patterns.singleton3;

/**
 * @author baj
 * @creat 2022-05-15 20:15
 */
public class EHanDemo {
    //创建一个私有的构造方法
    private EHanDemo(){}
    //创建好当前类的私有静态的对象
    private static EHanDemo eHanDemo = new EHanDemo();

    public static EHanDemo getInstance(){
        return eHanDemo;
    }

    public static void main(String[] args) {
        EHanDemo eHanDemo = EHanDemo.getInstance();
        EHanDemo eHanDemo1 = EHanDemo.getInstance();
        System.out.println(eHanDemo == eHanDemo1);
    }
}

