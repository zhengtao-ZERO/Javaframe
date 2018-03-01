package com.ioc.example;

import org.apache.log4j.Logger;

/**
 * 用于IOC测试设值注入的类。
 * @author zt
 */
public class C {
    //日志打印对象
    private static Logger logger = Logger.getLogger(C.class);
    //一个String属性
    private String cStr;
   
    /**
     * C的构造方法。
     */
    public C() {
        logger.info("这里是C的构造方法，一个C对象被创建了");
    }
    
    /**
     * C类的方法，用于测试验证C类对象.
     */
    public void name() {
        logger.info("这是一个C对象");
    }
    
    /**
     * C类的方法，用于测试验证C类对象的String属性值。
     */
    public void getcStr() {
        logger.info("对象c的cStr值为" + cStr);
    }

    /**
     * C的cStr属性的set方法。
     * @param cStr C类的属性
     */
    public void setcStr(String cStr) {
        this.cStr = cStr;
    }
    
    
}
