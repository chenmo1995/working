package com.fudn.mybatisplusdemo.aop.annotation;

import com.fudn.mybatisplusdemo.aop.ApiLogAspect;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 开启参数日志打印
 * @author fdn
 * @since 2021-08-19 23:46
 */

@Import(ApiLogAspect.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EnableApiLog {

}
