package com.cdw.config;

import com.cdw.aop.LogAspects;
import com.cdw.aop.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author: cdw
 * @date: 2021/12/16 16:53
 * @description: 面向切面编程
 * 1.日志切面类中的方法要动态切入到MathCalculator.div的运行:
 *      通知方法：
 *          前置通知(@Before)：logStart div运行之前
 *          后置通知(@After)：logEnd div运行之后
 *          返回通知(@AfterReturning)：logReturn div正常返回
 *          异常通知(@AfterThrowing)：logException div异常发生时
 *          环绕通知(@Around)：动态代理，手动推动目标方法的执行
 *
 * 2.给切面类的目标反法标注运行时间
 * 3.将切面类与目标逻辑的方法类加入到容器中
 * 4.注解添加
 *    @Aspect: 告诉Spring切面类的存在
 * 🔺 @EnableAspectJAutoProxy: 启用基于注解的aop模式
 */
@EnableAspectJAutoProxy
@Configuration
public class MainConfigOfAOP {

    @Bean
    public MathCalculator mathCalculator(){
        return new MathCalculator();
    }

    @Bean
    public LogAspects logAspects(){
        return new LogAspects();
    }

}
