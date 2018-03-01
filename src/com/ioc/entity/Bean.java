package com.ioc.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Bean类用于存储IOC中的类的配置信息。
 * @author zt
 */
public class Bean {
    //存储bean的id
    private String id;
    //存储bean的类名
    private String className;
    
    private Map<String, Property> properties = new HashMap<String, Property>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Map<String, Property> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Property> properties) {
        this.properties = properties;
    }

        
        
        
}
