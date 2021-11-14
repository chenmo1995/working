package com.fudn.spring.context.support;

import com.fudn.spring.beans.factory.support.BeanDefinitionReader;
import com.fudn.spring.beans.factory.support.BeanDefinitionRegistry;
import com.fudn.spring.context.ApplicationContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用于立即加载
 *
 * @author fdn
 * @since 2021-09-17 00:12
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

    /**
     * 声明解析器变量
     */
    protected BeanDefinitionReader beanDefinitionReader;

    /**
     * 定义用于存储bean对象的map容器
     */
    protected Map<String,Object> singletonObjects = new ConcurrentHashMap<>();

    /**
     * 声明配置文件路径的变量
     */
    protected String configLocation;

    /**
     * 非延时加载功能
     *
     * @throws Exception
     */
    @Override
    public void refresh() throws Exception {
        //加载BeanDefinition对象
        beanDefinitionReader.loadBeanDefinitions(configLocation);
        //初始化bean，也就是根据BeanDefinition来创建Bean
        finishBeanInitialization();
    }

    /**
     * 初始化bean
     *
     * @throws Exception
     */
    private void finishBeanInitialization() throws Exception{
        //获取注册表对象
        BeanDefinitionRegistry registry = beanDefinitionReader.getRegistry();
        //获取BeanDefinition
        String[] beanNames = registry.getBeanDefinitionNames();
        //bean的初始化
        for (String beanName : beanNames) {
            getBean(beanName);
        }
    };
}
