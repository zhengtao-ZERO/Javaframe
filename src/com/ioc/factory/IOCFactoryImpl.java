package com.ioc.factory;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import com.ioc.entity.Bean;
import com.ioc.entity.Property;

/**
 * IOC工厂的实现类。
 * @author zt
 */
public class IOCFactoryImpl implements  IOCFactory {
    //承载所有bean信息的map，配置容器
    private Map<String, Bean> config = new HashMap<String, Bean>();
    //承载所有实例对象的map，对象容器
    private Map<String, Object> context = new HashMap<String, Object>();
    //日志打印对象
    private static Logger logger = Logger.getLogger(IOCFactoryImpl.class); 
    
    /**
     * 根据配置文件创建bean信息map,再创建实例对象map，从而创建了工厂对象。
     * @param path 配置文件路径
     */
    public IOCFactoryImpl(String path) {
        try {
            this.config = this.getConfig(path);
            for (Bean bean : config.values()) {
                String name = bean.getClassName();
                Object obj = this.creatBean(bean);
                this.context.put(name, obj);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
    }

    /**
     * 根据bean名称获得bean实例。
     * @param beanName bean的名字
     * @return 在容器中找到的Object
     */
    public Object getBean(String beanName) {  
        Object obj = context.get(beanName);  
        return obj;  
    }  
    
    /**
     * 根据bean名称获得bean实例。
     * @param beanName bean的名字
     * @param clazz bean对应对象的类型
     * @param <T> 所需对象类型的泛型
     * @return 在容器中找到的Object
     */
    @SuppressWarnings("unchecked")
    public <T> T getBean(String beanName, Class<T> clazz) {  
        Object obj = context.get(beanName);
        return (T) obj;  
    }    
    
    /**
     * 得到对象容器的方法。
     * @return 对象容器
     */
    public Map<String, Object> getContext() {
        return context;
    }

    /**
     * 根据配置文件加载所有bean到配置信息map中。
     * @param path 配置文件路径
     * @return 配置信息map
     * @throws DocumentException 文件读取异常
     */
    @SuppressWarnings("unchecked")
    public Map<String, Bean> getConfig(String path) throws DocumentException {
        Map<String, Bean> beansMap = new HashMap<String, Bean>();
        SAXReader reader = new SAXReader();
        //读取路径文件
        org.dom4j.Document document = reader.read(new File(path));
        org.dom4j.Element root = document.getRootElement();
        //找到所有的bean元素的集合
        List<org.dom4j.Element> beansElements = root.elements("bean");
        //遍历bean元素的集合
        for (Element e : beansElements) {
            Bean bean = new Bean();
            String id = e.attributeValue("id");
            String clazz = e.attributeValue("class");
            Map<String, Property> propertiesMap = new HashMap<String, Property>();
            //找到该bean所有的property属性
            List<org.dom4j.Element> propertis = e.elements("property");
            //遍历该bean下的所有property属性
            for (Element p : propertis) {
                String pName = p.attributeValue("name");
                String pValue = p.attributeValue("value");
                String pRef = p.attributeValue("ref");
                //将查到的property信息用Property对象存储起来
                Property property = new Property();
                property.setName(pName);
                property.setRef(pRef);
                property.setValue(pValue);
                //将Property对象放入对象map中
                propertiesMap.put(pName, property);
            }
            //将bean信息放入bean对象中
            bean.setId(id);
            bean.setClassName(clazz);
            bean.setProperties(propertiesMap);
            //将bean对象放入配置容器中
            beansMap.put(id, bean);
        }
        return beansMap;
    }
    
  //根据bean配置创建bean对象，放入容器中  
    @SuppressWarnings("rawtypes")
    private Object creatBean(Bean bean) throws NoSuchFieldException {  
        //获取要创建的bean的class  
        String className = bean.getClassName();  
        Class clazz = null;  
        try {  
            clazz = Class.forName(className);  
        } catch (ClassNotFoundException e) {  
            logger.error(e.getMessage(),e);
        }  
        //将class对象应的对创建出来  
        Object beanObj = null;  
        try {  
            beanObj = clazz.newInstance();  
        } catch (Exception e) {  
            logger.error(e.getMessage(),e);
        }   
        //获得bean的属性，将其注入  
        if (bean.getProperties() != null) {  
            for (Property property:bean.getProperties().values()) {  
                //获取要注入的属性名称  
                String name = property.getName();  
                Field field = null;
                Object parm = null;
                //根据bean名找到对应属性
                field = clazz.getDeclaredField(name);
                //value注入  
                if (property.getValue() != null) {  
                    //获取要注入的属性值  
                    String value = property.getValue();  
                    parm = value;  
                }  
                //其他类型bean的注入  
                if (property.getRef() != null) {  
                    //先从容器中查找当前要注入的bean是否已经创建并放入容器中  
                    Object exsitBean = context.get(property.getRef());  
                    if (exsitBean == null) {  
                        //如果容器中不存在该对象，则要创建  
                        exsitBean = creatBean(config.get(property.getRef()));  
                         //将创建好的bean放入容器  
                        context.put(property.getRef(), (Bean) exsitBean);  
                    }  
                    parm = exsitBean;  
                }  
                //使用set方法注入  
                try {  
                    //set语句访问权限设为true
                    field.set(beanObj, parm);
                } catch (Exception e) {  
                    logger.error(e.getMessage(),e);
                }   
            } 
        }  
        return beanObj;
    }
}
