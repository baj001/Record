package com.shang.javatest.Proxy.dtdl;

/**
 * @author baj
 * @creat 2022-05-31 18:09
 */

/**
 * 需要动态代理的接口
 */
public interface One_Subject {
    /**
     * 你好
     * @param name
     * @return
     */
    public String SayHello(String name);

    /**
     * 再见
     *
     * @return
     */
    public String SayGoodBye();
}