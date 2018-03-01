package com.aop.entity;

/**
 * 
 * @author zt
 */
public class Student implements Person{
    
    @Override
    public void say(String str) {
        System.out.println("student say "+str);
    }

}
