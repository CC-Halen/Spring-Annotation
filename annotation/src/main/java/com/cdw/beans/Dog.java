package com.cdw.beans;

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
public class Dog {
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
}
