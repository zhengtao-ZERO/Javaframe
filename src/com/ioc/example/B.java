package com.ioc.example;

import org.apache.log4j.Logger;

/**
 * 用于测试时被注入的类。
 * @author zt
 */
public class B {
    //日志打印对象
    private static Logger logger = Logger.getLogger(B.class);
    @SuppressWarnings("unused")
    private A a;
    
    /**
     * B的构造方法。
     */
    public B() {
        logger.info("这里是B的构造方法，一个B对象被创建了");
    }
    
    /**
     * B类的方法，用于测试验证B类对象.
     */
    public void name() {
        logger.info("这是一个B对象");
    }

    /**
     * 属性A的set方法
     * @param a B类的一个属性
     */
    public void setA(A a) {
        this.a = a;
    }

    /**
     * 属性A的get方法
     * @param a B类的一个属性
     */
    public A getA() {
        return a;
    }
    
    
}
