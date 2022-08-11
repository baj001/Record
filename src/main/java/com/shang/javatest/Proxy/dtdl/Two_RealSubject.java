package com.shang.javatest.Proxy.dtdl;



/**
 * @author baj
 * @creat 2022-05-31 18:10
 * 实现了这个接口
 */



public class Two_RealSubject implements One_Subject{

    /**
     * 你好
     *
     * @param name
     * @return
     */
    public String SayHello(String name) {
        return "hello " + name;
    }

    /**
     * 再见
     *
     * @return
     */
    public String SayGoodBye() {
        return " good bye ";
    }

}