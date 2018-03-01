package com.aop.factory;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;

public class MyAdvice implements Advice{
  //日志打印对象
    private static Logger logger = Logger.getLogger(JDKProxyFactory.class); 
    
    @Override
    public void beforeMethod(Method method) {
        logger.info("前置增强");
    }

    @Override
    public void afterMethod(Method method) {
        logger.info("后置增强");
    }

}
