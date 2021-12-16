package com.cdw;

import com.cdw.beans.*;
import com.cdw.config.MainConfigOfAutowired;
import com.cdw.config.MainConfigOfPropertyValues;
import com.cdw.dao.BookDao;
import com.cdw.service.BookService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author: cdw
 * @date: 2021/12/16 10:35
 * @description:
 */
public class IOCTest_Autowired {


    @Test
    public void test1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);
        Boss bean = context.getBean(Boss.class);
        System.out.println(bean);

        Car car = context.getBean(Car.class);
        System.out.println(car);


        Color color = context.getBean(Color.class);
        System.out.println(color);

        context.close();
    }

    @Test
    public void test2() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);
        Red bean = context.getBean(Red.class);
        System.out.println(bean);

        context.close();
    }


}
