package com.cdw;

import com.cdw.beans.Person;
import com.cdw.config.MainConfigOfPropertyValues;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author: cdw
 * @date: 2021/12/16 10:35
 * @description:
 */
public class IOCTest_PropertyValues {


    @Test
    public void test1(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfigOfPropertyValues.class);
        Person bean = context.getBean(Person.class);
        System.out.println(bean);
    }


}
