package com.ioc.factory;

/**
 * 一个模拟IOC机制的工厂。
 * @author zt
 */
public interface IOCFactory {
    /**
     * 根据bean名称获得bean实例。
     * @param beanName bean的名字
     * @return 在容器中找到的Object
     */
    Object getBean(String beanName);
    
    /**
     * 根据bean名称获得bean实例。
     * @param beanName bean的名字
     * @param clazz bean对应对象的类型
     * @param <T> 所需对象类型的泛型
     * @return 在容器中找到的Object
     */
    <T> T getBean(String beanName, Class<T> clazz);
}
