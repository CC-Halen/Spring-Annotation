package com.cdw.beans;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author: cdw
 * @date: 2021/12/15 20:34
 * @description:
 */
@Component
public class Cat implements InitializingBean, DisposableBean {
    public Cat(){
        System.out.println("Cat constrictor ....");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Cat destroy ....");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Cat afterPropertiesSet ....");
    }
}
