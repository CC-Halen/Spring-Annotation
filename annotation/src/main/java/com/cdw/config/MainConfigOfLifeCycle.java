package com.cdw.config;

import com.cdw.beans.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author: cdw
 * @date: 2021/12/15 20:11
 * @description: Bean的生命周期： 创建--->初始化--->销毁
 * 创建：
 *      单实例：容器启动时创建
 *      多实例：每次获取时创建
 *
 *  BeanPostProcessor.postProcessBeforeInitialization
 * 初始化：
 *      对象创建完成，并赋值好，带调用初始化方法
 * BeanPostProcessor.postProcessAfterInitialization
 *
 * 销毁：
 *      单实例：容器关闭的时候
 *      多实例：容器不会调用销毁方法，要手动调用
 *
 * 1.指定初始化方法和销毁方法
 * 2.让Bean实现初始化逻辑和销毁逻辑(InitializingBean DisposableBean)
 * 3.使用JSR250：
 *      @PostConstruct: 在bean创建完成并且属性赋值完成之后来执行初始化方法
 *      @PreDestroy:  在容器销毁bean之前通知清理工作
 * 4.BeanPostProcessor：bean的后置处理器
 *      在bean初始化前后进行处理：
 *      postProcessBeforeInitialization：初始化之前
 *      postProcessAfterInitialization：初始化之后
 */
@ComponentScan("com.cdw.beans")
@Configuration
public class MainConfigOfLifeCycle {


    @Scope("prototype")
    @Bean(initMethod = "init",destroyMethod = "destroy")
    public Car car(){
        return new Car();
    }

}
