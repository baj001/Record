package com.shang.javatest.patterns.singleton2;

/**
 * @author baj
 * @creat 2022-05-15 19:30
 */
public class ThreadTest{
    public static void main(String[] args) {
        Singleton singleton=Singleton.getInstance();
        System.out.println(singleton);
    }
}