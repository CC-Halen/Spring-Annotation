package com.cdw;

import com.cdw.config.MainConfigOfLifeCycle;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author: cdw
 * @date: 2021/12/15 20:19
 * @description:
 */
public class IOCTest_LifeCycle {


    @Test
    public void test1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
        System.out.println("容器创建完成。");
//        Object car = context.getBean("car");


        context.close();


    }


    public void showBeanNames(AnnotationConfigApplicationContext context) {
        String[] names = context.getBeanDefinitionNames();
        for (String name : names)
            System.out.println(name);
    }
}
