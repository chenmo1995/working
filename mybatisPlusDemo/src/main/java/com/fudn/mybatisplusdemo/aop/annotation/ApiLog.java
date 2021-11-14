package com.fudn.mybatisplusdemo.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在需要记录接口请求信息的类或方法上添加
 * @author fdn
 * @since 2021-08-23 09:46
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface ApiLog {
}
