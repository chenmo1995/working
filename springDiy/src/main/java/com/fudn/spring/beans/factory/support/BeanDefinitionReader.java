package com.fudn.spring.beans.factory.support;

/**
 * 解析配置文件的规范
 * 解析器
 *
 * @author fdn
 * @since 2021-09-16 00:58
 */
public interface BeanDefinitionReader {

    /**
     * 获取注册表
     * @return
     */
    BeanDefinitionRegistry getRegistry();

    /**
     * 加载配置文件，并在注册表中进行注册
     * @param configLocation
     * @throws Exception
     */
    void loadBeanDefinitions(String configLocation) throws Exception;
}
