package com.fudn.spring.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 用来封装bean标签属性
 * id属性
 * class属性
 * property属性
 *
 * @author fdn
 * @since 2021-09-16 00:23
 */
@Data
public class BeanDefinition {

    private String id;
    private String className;
    private MutablePropertyValue propertyValue;

    public BeanDefinition(){
        propertyValue = new MutablePropertyValue();
    }

}
