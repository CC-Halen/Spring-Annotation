package com.cdw;

import com.cdw.ext.ExtConfig;
import org.junit.Test;
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


        context.close();
    }
}
