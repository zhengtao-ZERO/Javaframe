package com.aop.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理工厂类，能够为有接口的目标类动态生成代理类，采用JDK 动态代理方式，
 * 即实现InvocationHandler接口。
 * @author zt
 */
public class JDKProxyFactory implements InvocationHandler {
    //目标类
    private Object target;
    //增强类
    private Advice advice;
    
        /**
         * 根据接口和类加载器找到对象的代理类。
         * @param <T> 目标类，可任意类型
         * @return 目标类的代理对象
         */
    @SuppressWarnings("unchecked")
    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(
            target.getClass().getClassLoader(),
            target.getClass().getInterfaces(),
            this
        );
    }
       
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        advice.beforeMethod(method);
        Object result = method.invoke(target, args);
        advice.afterMethod(method);
        return result;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public void setTarget(Object target) {
        this.target = target;
    }
    
   
}
