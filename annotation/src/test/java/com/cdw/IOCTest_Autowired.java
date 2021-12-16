package com.cdw;

import com.cdw.beans.Person;
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
    public void test1(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);
        BookService bean = context.getBean(BookService.class);
        System.out.println(bean);

        context.close();
    }


}
