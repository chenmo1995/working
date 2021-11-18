package com.fudn.minimybatisplus.demo.mybatisplus.utils;

import java.io.Serializable;
import java.util.function.Function;

/**
 * 扩展java.util.function包下的Function接口：支持Serializable
 * 搭配Reflections工具类一起使用，用于获取Lambda表达式的方法名
 *
 * @author bravo1988
 * @date 2021-05-29 19:44
 */
@FunctionalInterface
public interface ConditionFunction<T, R> extends Function<T, R>, Serializable {
}
