package com.fudn.spring.beans.factory.support;

import com.fudn.spring.beans.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注册表接口的子实现类
 *
 * @author fdn
 * @since 2021-09-16 00:42
 */
public class SimpleBeanDefinitionRegistry implements BeanDefinitionRegistry {

    /**
     * 定义一个容器，用来存储BeanDefinition对象
     */
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    /**
     * 注册BeanDefinition对象到注册表中
     *
     * @param beanName
     * @param beanDefinition
     */
    @Override
    public void registryBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    /**
     * 从注册表中移除指定名称的BeanDefinition对象
     *
     * @param beanName
     * @throws Exception
     */
    @Override
    public void removeBeanDefinition(String beanName) throws Exception {
        beanDefinitionMap.remove(beanName);
    }

    /**
     * 根据名称从注册表中获取BeanDefinition
     *
     * @param beanName
     * @return
     * @throws Exception
     */
    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws Exception {
        return beanDefinitionMap.get(beanName);
    }

    /**
     * 判断注册表中是否存在指定名称的BeanDefinition
     *
     * @param beanName
     * @return
     */
    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    /**
     * 获取注册表中BeanDefinition的个数
     *
     * @return
     */
    @Override
    public int getBeanDefinitionCount() {
        return beanDefinitionMap.size();
    }

    /**
     * 获取注册表中所有BeanDefinition的beanName
     *
     * @return
     */
    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }
}
