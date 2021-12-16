package com.cdw.beans;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author: cdw
 * @date: 2021/12/15 20:41
 * @description:
 */
@Component
public class Dog implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    public Dog() {
        System.out.println("dog constructor ...");
    }


    @PostConstruct
    public void init(){
        System.out.println("dog postConstructor ...");
    }


    @PreDestroy
    public void destroy(){
        System.out.println("dog preDestroy ...");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
