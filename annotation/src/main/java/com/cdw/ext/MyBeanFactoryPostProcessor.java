package com.cdw.ext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @author: cdw
 * @date: 2021/12/19 12:52
 * @description:
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanFactoryPostProcessor ....  postProcessBeanFactory");
        System.out.println(beanFactory.getBeanDefinitionCount());
        String[] names = beanFactory.getBeanDefinitionNames();
        for (String name : names)
            System.out.println(name);
    }
}
