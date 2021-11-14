package com.fudn.mybatisplusdemo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 基础切面类，作废
 *
 * @author fdn
 * @since 2021-08-19 23:46
 */

@Slf4j
@Aspect
public class ApiTimeLogAspect {

    /**
     * 定义切点表达式
     */
    @Pointcut("execution(* com.fudn.springbootdemo.module.staticExcel.controller.*.*(..))")
    public void controllerPointcut() {

    }

    /**
     * 通过引用切点表达式，明确这个增强的规则
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        //实际执行目标方法
        Object result = proceedingJoinPoint.proceed();

        log.info("==========耗时:{}ms==========", System.currentTimeMillis() - startTime);

        //只做增强不做改变，
        return result;

    }


}
