package com.shang.javatest.patterns.singleton1;

/**
 * @author baj
 * @creat 2022-05-14 22:55
 */
public class EHanDemo {
    private EHanDemo(){}

    private static EHanDemo eHanDemo=new EHanDemo();

    public static EHanDemo getInstance(){
        return eHanDemo;
    }

    public static void main(String[] args) {
        EHanDemo instance = EHanDemo.getInstance();
        EHanDemo instance1 = EHanDemo.getInstance();
        System.out.println(instance == instance1);
    }
}
