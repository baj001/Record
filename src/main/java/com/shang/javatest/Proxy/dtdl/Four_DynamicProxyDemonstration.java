package com.shang.javatest.Proxy.dtdl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author baj
 * @creat 2022-05-31 18:16
 */




public class Four_DynamicProxyDemonstration {
    public static void main(String[] args)
    {
        //代理的真实对象
        One_Subject realSubject = new Two_RealSubject();
        //使用构造方法如下
        InvocationHandler handler = new Three_InvocationHandlerImpl(realSubject);

        ClassLoader loader = realSubject.getClass().getClassLoader();
        Class[] interfaces = realSubject.getClass().getInterfaces();


        One_Subject subject = (One_Subject) Proxy.newProxyInstance(loader, interfaces, handler);

        System.out.println("动态代理对象的类型："+subject.getClass().getName());

        String hello = subject.SayHello("jiankunking");
        System.out.println(hello);

}
}



