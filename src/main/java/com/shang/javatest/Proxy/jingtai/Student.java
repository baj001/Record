package com.shang.javatest.Proxy.jingtai;

/**
 * @author baj
 * @creat 2022-05-30 17:03
 */
public class Student implements Person {
    private String name;
    public Student(String name) {
        this.name = name;
    }

    public void giveMoney() {
        System.out.println(name + "上交班费50");
    }
}

