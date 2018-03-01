package com.ioc.example;

import org.apache.log4j.Logger;

/**
 * 一个用于IOC测试设值注入的类。
 * @author zt
 */
public class A {
    private static Logger logger = Logger.getLogger(A.class);
    
    /**
     * A的构造方法。
     */
    public A() {
        logger.info("这里是A的构造方法，一个A对象被创建了");
    }
    
    /**
     * A的方法，用于查验IOC创建的对象。
     */
    public void name() {
        logger.info("这是一个A对象");
    }
}
