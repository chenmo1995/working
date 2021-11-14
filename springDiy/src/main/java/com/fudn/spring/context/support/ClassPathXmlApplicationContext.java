package com.fudn.spring.context.support;

import com.fudn.spring.beans.BeanDefinition;
import com.fudn.spring.beans.MutablePropertyValue;
import com.fudn.spring.beans.PropertyValue;
import com.fudn.spring.beans.factory.support.BeanDefinitionRegistry;
import com.fudn.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.fudn.spring.utils.StringUtils;

import java.lang.reflect.Method;

/**
 * IOC容器具体的子实现类
 * 用于加载类路径下面的XML格式的配置文件
 *
 * @author fdn
 * @since 2021-09-17 00:25
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    /**
     * 构造方法
     * -给父类中configLocation赋具体值
     * -
     *
     * @param configLocation
     */
    public ClassPathXmlApplicationContext(String configLocation) {
        super.configLocation = configLocation;
        //构建解析器对象
        beanDefinitionReader = new XmlBeanDefinitionReader();
        try {
            this.refresh();
        } catch (Exception e) {

        }

    }

    /**
     * 根据beanName获取bean
     *
     * @param beanName
     * @return
     * @throws Exception
     */
    @Override
    public Object getBean(String beanName) throws Exception {
        //判断对象容器中是否存在beanName的bean对象
        Object obj = singletonObjects.get(beanName);
        if (obj != null) {
            return obj;
        }
        //获取BeanDefinition对象
        BeanDefinitionRegistry registry = beanDefinitionReader.getRegistry();
        BeanDefinition beanDefinition = registry.getBeanDefinition(beanName);
        //获取BeanDefinition信息中的className
        String className = beanDefinition.getClassName();
        Class<?> clazz = Class.forName(className);
        //bean对象
        Object beanObj = clazz.newInstance();

        //进行依赖注入操作
        MutablePropertyValue propertyValue = beanDefinition.getPropertyValue();
        for (PropertyValue property : propertyValue) {
            String propertyName = property.getName();
            //value属性是给基本数据类型和字符串类型赋值的
            String value = property.getValue();
            String ref = property.getRef();
            if (ref != null && !"".equals(ref)) {
                //获取依赖的bean对象
                Object bean = getBean(ref);
                //拼接set方法名
                String methodName = StringUtils.getMethodByFieldName(propertyName);
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    if (methodName.equals(method.getName())) {
                        //只I型那个setter方法
                        method.invoke(beanObj, bean);
                    }
                }
            }
            if (value != null && !"".equals(value)) {
                //拼接set方法名
                String methodName = StringUtils.getMethodByFieldName(propertyName);
                //这里是直接获取的String赋值的方法，是假设value是String类型的，其它类型没做讨论
                Method method = clazz.getMethod(methodName, String.class);
                method.invoke(beanObj, value);
            }
        }
        //在返回beanObj之前，将创建好的bean对象放入容器中，下次再getBean()就可以直接获取了
        singletonObjects.put(beanName, beanObj);
        return beanObj;
    }

    /**
     * 获取bean并转换类型
     *
     * @param beanName
     * @param clazz
     * @return
     * @throws Exception
     */
    @Override
    public <T> T getBean(String beanName, Class<? extends T> clazz) throws Exception {
        Object bean = getBean(beanName);
        if (bean == null) {
            return null;
        }
        return clazz.cast(bean);
    }

}
