package com.fudn.spring.utils;


/**
 * @author fdn
 * @since 2021-09-17 01:03
 */
public class StringUtils {
    private StringUtils(){

    }

    /**
     * 返回方法名        userDao-->setUserDao
     * @param fieldName
     * @return
     */
    public static String getMethodByFieldName(String fieldName){
        return "set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
    }
}
