package com.fudn.minimybatisplus.demo.mybatisplus.utils;

import java.beans.Introspector;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * 获取Lambda入参的方法名
 *
 * @author bravo1988
 * @date 2021-05-29 19:43
 */
public class Reflections {
    private static final Pattern GET_PATTERN = Pattern.compile("^get[A-Z].*");
    private static final Pattern IS_PATTERN = Pattern.compile("^is[A-Z].*");

    /**
     * 注意: 非标准变量（非小驼峰）调用这个方法可能会有问题
     *
     * @param fn
     * @param <T>
     * @return
     */
    public static <T> String fnToColumnName(ConditionFunction<T, ?> fn) {
        try {
            Method method = fn.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(Boolean.TRUE);
            SerializedLambda serializedLambda = (SerializedLambda) method.invoke(fn);
            String getter = serializedLambda.getImplMethodName();
            // 对于非标准变量生成的Get方法这里可以直接抛出异常，或者打印异常日志
            if (GET_PATTERN.matcher(getter).matches()) {
                getter = getter.substring(3);
            } else if (IS_PATTERN.matcher(getter).matches()) {
                getter = getter.substring(2);
            }
            return Introspector.decapitalize(getter);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }
}
