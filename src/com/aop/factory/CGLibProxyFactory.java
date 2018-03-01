package com.aop.factory;

import java.lang.reflect.Method;
import org.apache.log4j.Logger;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 代理工厂类，能够动态生成目标类的代理类，采用CGLib 动态代理方式，即MethodInterceptor
 * 接口，对比JDK的方式，优点是目标类可以不用实现任何接口，需要导入cglib的类库。
 * @author zt
 */
public class CGLibProxyFactory implements MethodInterceptor {
    
    private static CGLibProxyFactory instance = new CGLibProxyFactory();
    //日志打印对象
    private static Logger logger = Logger.getLogger(CGLibProxyFactory.class);
    
    private CGLibProxyFactory() {
    }
 
    public static CGLibProxyFactory getInstance() {
        return instance;
    }
 
    /**
     * 得到目标对象的代理对象。
     * @param cls 目标类
     * @param <T> 目标对象，可以是任意类型
     * @return 目标对象的代理对象
     */
    @SuppressWarnings("unchecked")
    public <T> T getProxy(Class<T> cls) {
        return (T) Enhancer.create(cls, this);
    }
 
    @Override
    public Object intercept(Object target, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        before();
        Object result = proxy.invokeSuper(target, args);
        after();
        return result;
    }
 
    private void before() {
        logger.info("CGLib前置增强");
    }
 
    private void after() {
        logger.info("CGLib后置增强");
    }

}
