package com.fudn.spring.beans.factory.xml;

import com.fudn.spring.beans.BeanDefinition;
import com.fudn.spring.beans.MutablePropertyValue;
import com.fudn.spring.beans.PropertyValue;
import com.fudn.spring.beans.factory.support.BeanDefinitionReader;
import com.fudn.spring.beans.factory.support.BeanDefinitionRegistry;
import com.fudn.spring.beans.factory.support.SimpleBeanDefinitionRegistry;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * 针对XML配置文件进行解析的类
 *
 * @author fdn
 * @since 2021-09-16 01:01
 */
public class XmlBeanDefinitionReader implements BeanDefinitionReader {

    /**
     * 声明注册表对象
     */
    private BeanDefinitionRegistry registry;

    public XmlBeanDefinitionReader(){
        registry = new SimpleBeanDefinitionRegistry();
    }

    /**
     * 获取注册表
     *
     * @return
     */
    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    /**
     * 加载配置文件，并在注册表中进行注册
     *
     * @param configLocation
     * @throws Exception
     */
    @Override
    public void loadBeanDefinitions(String configLocation) throws Exception {
        //使用dom4j进行XML配置文件的解析
        SAXReader reader = new SAXReader();
        //获取类路径下XML配置文件
        InputStream is = XmlBeanDefinitionReader.class.getClassLoader().getResourceAsStream(configLocation);
        Document document = reader.read(is);
        //根据Document对象获取根标签对象（beans）
        Element rootElement = document.getRootElement();
        //获取根标签下所有的bean标签
        List<Element> beanElements = rootElement.elements("bean");
        //遍历
        for (Element beanElement : beanElements) {
            //获取id属性
            String id = beanElement.attributeValue("id");
            //获取class属性
            String clazzName = beanElement.attributeValue("class");

            //将id属性和class属性封装到BeanDefinition对象中
            BeanDefinition beanDefinition = new BeanDefinition();
            beanDefinition.setId(id);
            beanDefinition.setClassName(clazzName);
            //创建MutablePropertyValue对象
            MutablePropertyValue mutablePropertyValue = new MutablePropertyValue();
            //获取bean标签下所有的property标签对象
            List<Element> propertyElements = beanElement.elements("property");
            for (Element propertyElement : propertyElements) {
                String name = propertyElement.attributeValue("name");
                String value = propertyElement.attributeValue("value");
                String ref = propertyElement.attributeValue("ref");
                PropertyValue propertyValue = new PropertyValue(name,ref,value);
                mutablePropertyValue.addPropertyValue(propertyValue);
            }
            beanDefinition.setPropertyValue(mutablePropertyValue);

            //注册BeanDefinition
            registry.registryBeanDefinition(id,beanDefinition);
        }
    }
}
