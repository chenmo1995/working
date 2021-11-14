package com.fudn.spring.context;

import com.fudn.spring.beans.factory.BeanFactory;

/**
 * 定义非延时加载功能
 *
 * @author fdn
 * @since 2021-09-17 00:09
 */
public interface ApplicationContext extends BeanFactory {

    /**
     * 非延时加载功能
     * @throws Exception
     */
    void refresh() throws Exception;
}
