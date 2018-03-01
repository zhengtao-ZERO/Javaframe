package com.ioc.test;


import org.dom4j.DocumentException;
import org.junit.Test;
import com.ioc.example.B;
import com.ioc.example.C;
import com.ioc.factory.IOCFactory;
import com.ioc.factory.IOCFactoryImpl;

/**
 * 测试IOC工厂的创建对象和设置注入功能。
 * @author zt
 */
public class TestIOCFactory {

    /**
     * 测试IOC工厂是否能够根据配置文件创建对象，并完成将A对象注入进B对象中。
     * @throws DocumentException
     */
    @Test
    public void testSetBean() {
        IOCFactory context  = new IOCFactoryImpl("src/beans.xml");
        B b = (B) context.getBean("com.ioc.example.B");
        b.name();
        b.getA().name();
    }
    
    /**
     * 测试IOC工厂是否能够根据配置文件创建对象，并完成设值注入。
     * @throws DocumentException
     */
    @Test
    public void testSetValue() {
        IOCFactory context  = new IOCFactoryImpl("src/beans.xml");
        C c= (C) context.getBean("com.ioc.example.C",C.class);
        c.getcStr();
    }
}
