package com.cdw.ext;

import com.cdw.beans.Blue;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.stereotype.Component;

/**
 * @author: cdw
 * @date: 2021/12/20 15:38
 * @description:
 */
@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    //BeanDefinitionRegistry 就是Bean定义信息的而保存中心，BeanFactory就是按照registry中保存的信息创建bean
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("postProcessBeanDefinitionRegistry ....  bean的数量：" + registry.getBeanDefinitionCount());
//        RootBeanDefinition beanDefinition = new RootBeanDefinition(Blue.class);
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(Blue.class).getBeanDefinition();
        registry.registerBeanDefinition("hello", beanDefinition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("postProcessBeanFactory ....  bean的数量：" + beanFactory.getBeanDefinitionCount());
    }
}
