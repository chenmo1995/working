package com.fudn.minimybatisplus.wrapper;

import java.io.Serializable;
import java.util.function.Function;

/**
 *
 * 扩展java.util.function包下的Function接口：支持Serializable
 * 搭配Reflections工具类一起使用，用于获取Lambda表达式的方法名
 *
 * @author fdn
 * @since 2021-11-16 23:12
 */
public interface ConditionFunction<T, R> extends Function<T, R>, Serializable {
}
