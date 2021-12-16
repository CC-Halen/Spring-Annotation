package com.cdw.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author: cdw
 * @date: 2021/12/16 16:59
 * @description:
 * @Aspect: 告诉Spring切面类的存在
 */
@Aspect
public class LogAspects {

    @Pointcut("execution(public int *.div(..))")
    public void pointCut() {

    }

    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.println(joinPoint.getSignature() + "运行。。。参数列表是：{" + Arrays.asList(args) + "}");
    }

    @After("pointCut()")
    public void logEnd(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature() + "结束。。。");
    }

    @AfterReturning(value = "pointCut()", returning = "result")
    public void logReturn(JoinPoint joinPoint, Object result) {
        System.out.println(joinPoint.getSignature() +"正常返回。。。返回值是：{" + result + "}");
    }


    @AfterThrowing(value = "pointCut()", throwing = "e")
    public void LogException(JoinPoint joinPoint, Exception e) {
        System.out.println(joinPoint.getSignature() + "异常。。。异常信息是：{" + e + "}");
    }


}
