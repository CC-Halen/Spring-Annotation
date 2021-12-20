package com.cdw.ext;

import com.alibaba.druid.sql.visitor.functions.BitLength;
import com.cdw.beans.Blue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author: cdw
 * @date: 2021/12/19 12:45
 * @description: 扩展原理
 *  BeanPostProcessor：bean后置处理器，bean创建对象初始化前后进行拦截工作的
 *  BeanFactoryPostProcessor：beanFactory的后置处理器
 *      在beanFactory标准初始化之后调用，所有的bean已经保存加载到beanFactory，但是bean的实例还未创建
 *
 *  1）IOC容器创建对象
 *  2）invokeBeanFactoryPostProcessors(beanFactory); 执行BeanFactoryPostProcessors
 *      如何让找到BeanFactoryPostProcessors并执行方法：
 *          1）直接在BeanFactory中找到所有类型是BeanFactoryPostProcessor的组件，并执行他们的方法
 *          2）在初始化创建其他组件前面执行
 *
 *
 * BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor
 *      postProcessBeanDefinitionRegistry()
 *      在所有bean定义信息将要被加载，bean实例还没创建时执行
 *
 *      优先于BeanFactoryPostProcessor执行，利用 BeanDefinitionRegistryPostProcessor 给容器中再额外添加组件
 *
 *
 *  BeanDefinitionRegistryPostProcessor的方法先于BeanFactoryPostProcessor的方法执行
 * 原理：
 *  1）ioc创建容器
 *  2）refresh() -> invokeBeanFactoryPostProcessors(beanFactory);
 *  3）从容器中获取到所有的BeanDefinitionRegistryPostProcessor组件
 *      1）依次触发所有的 postProcessBeanDefinitionRegistry() 方法
 *      2）再来触发postProcessBeanFactory()方法 BeanFactoryPostProcessor
 *  4）再来从容器中找到 BeanFactoryPostProcessor的组件，然后依次触发postProcessBeanFactory()方法
 *
 *
 *
 *
 *
 *
 *
 */
@ComponentScan("com.cdw.ext")
@Configuration
public class ExtConfig {

    @Bean
    public Blue blue(){
        return new Blue();
    }

}
