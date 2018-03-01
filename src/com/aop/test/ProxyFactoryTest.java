package com.aop.test;

import org.junit.Ignore;
import org.junit.Test;
import com.aop.entity.Car;
import com.aop.entity.Person;
import com.aop.entity.Student;
import com.aop.factory.CGLibProxyFactory;
import com.aop.factory.JDKProxyFactory;
import com.aop.factory.MyAdvice;

/**
 * 用于测试不同代理工厂的代理。
 * @author zt
 */
public class ProxyFactoryTest {

    @Test
    public void testJDKProxyFactory() { //检查JDK代理工厂的的代理效果，Person类是Student类的接口
        JDKProxyFactory factory = new JDKProxyFactory();//创建对象工厂
        factory.setTarget(new Student()); //注入目标对象
        factory.setAdvice(new MyAdvice());// 注入增强
        Person person = factory.getProxy();//获得增强后的代理对象
        person.say("good job");
    }
    
    @Ignore
    public void testCGLibProxyFactory() { //检查CGLib代理工厂的的代理效果,car类型没有接口
        Car car = CGLibProxyFactory.getInstance().getProxy(Car.class);
        car.whistle();
    }

}
