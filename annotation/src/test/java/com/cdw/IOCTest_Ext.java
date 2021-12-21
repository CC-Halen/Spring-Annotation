package com.cdw;

import com.cdw.ext.ExtConfig;
import com.sun.org.apache.xpath.internal.operations.String;
import org.junit.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author: cdw
 * @date: 2021/12/19 12:54
 * @description:
 */
public class IOCTest_Ext {


    @Test
    public void test1(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ExtConfig.class);

        context.publishEvent(new ApplicationEvent(new String()) {
        });
        context.close();
    }
}
