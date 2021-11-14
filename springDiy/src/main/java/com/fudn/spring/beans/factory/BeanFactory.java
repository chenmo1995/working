package com.fudn.spring.beans.factory;

/**
 * IOC容器父接口
 *
 * @author fdn
 * @since 2021-09-17 00:02
 */
public interface BeanFactory {

    /**
     * 获取bean
     * @param beanName
     * @return
     * @throws Exception
     */
    Object getBean(String beanName) throws Exception;

    /**
     * 获取bean并转换类型
     * @param beanName
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> T getBean(String beanName,Class<? extends T> clazz) throws Exception;

}
