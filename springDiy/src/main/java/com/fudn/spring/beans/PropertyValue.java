package com.fudn.spring.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 封装bean标签下的property标签的属性
 * name属性
 * ref属性
 * value属性：给基本类型和String类型数据赋值
 *
 *
 * @author fdn
 * @since 2021-09-15 23:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyValue {
    private String name;
    private String ref;
    private String value;
}
