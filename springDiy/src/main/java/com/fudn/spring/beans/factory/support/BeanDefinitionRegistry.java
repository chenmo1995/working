package com.fudn.spring.beans.factory.support;

import com.fudn.spring.beans.BeanDefinition;

/**
 * 注册表对象
 *
 * @author fdn
 * @since 2021-09-16 00:28
 */
public interface BeanDefinitionRegistry {

    /**
     * 注册BeanDefinition对象到注册表中
     *
     * @param beanName
     * @param beanDefinition
     */
    void registryBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 从注册表中移除指定名称的BeanDefinition对象
     *
     * @param beanName
     * @throws Exception
     */
    void removeBeanDefinition(String beanName) throws Exception;

    /**
     * 根据名称从注册表中获取BeanDefinition
     * @param beanName
     * @return
     * @throws Exception
     */
    BeanDefinition getBeanDefinition(String beanName) throws Exception;

    /**
     * 判断注册表中是否存在指定名称的BeanDefinition
     * @param beanName
     * @return
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * 获取注册表中BeanDefinition的个数
     * @return
     */
    int getBeanDefinitionCount();

    /**
     * 获取注册表中所有BeanDefinition的beanName
     * @return
     */
    String[] getBeanDefinitionNames();
}
