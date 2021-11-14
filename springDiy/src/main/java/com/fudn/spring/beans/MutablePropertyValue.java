package com.fudn.spring.beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用来存储和管理多个{@link PropertyValue}对象
 *
 * @author fdn
 * @since 2021-09-15 23:33
 */
public class MutablePropertyValue implements Iterable<PropertyValue> {

    /**
     * 定于list集合，用来存储{@link PropertyValue}对象
     */
    private final List<PropertyValue> propertyValueList;

    public MutablePropertyValue() {
        this.propertyValueList = new ArrayList<>();
    }

    public MutablePropertyValue(List<PropertyValue> propertyValueList) {
        if (propertyValueList == null) {
            this.propertyValueList = new ArrayList<>();
        } else {
            this.propertyValueList = propertyValueList;
        }
    }

    /**
     * 获取所有{@link PropertyValue}对象
     *
     * @return 以数组的形式返回
     */
    public PropertyValue[] getPropertyValues() {
        return propertyValueList.toArray(new PropertyValue[0]);
    }

    /**
     * 根据name属性值获取PropertyValue对象
     *
     * @param propertyName
     * @return
     */
    public PropertyValue getPropertyValue(String propertyName) {
        List<PropertyValue> collect = propertyValueList.stream()
                .filter(propertyValue -> propertyValue.getName().equals(propertyName))
                .collect(Collectors.toList());
        return collect.size() != 0 ? collect.get(0) : null;
    }

    /**
     * 判断集合是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return propertyValueList.isEmpty();
    }

    /**
     * 添加PropertyValue对象
     * @param propertyValue
     * @return
     */
    public MutablePropertyValue addPropertyValue(PropertyValue propertyValue) {
        for (int i = 0; i < propertyValueList.size(); i++) {
            if (propertyValueList.get(i).getName().equals(propertyValue.getName())){
                propertyValueList.set(i,propertyValue);
                return this;
            }
        }
        propertyValueList.add(propertyValue);
        return this;
    }

    public boolean contains(String propertyName){
        return getPropertyValue(propertyName) != null;
    }

    /**
     * 获取迭代器对象
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<PropertyValue> iterator() {
        return propertyValueList.iterator();
    }
}





























