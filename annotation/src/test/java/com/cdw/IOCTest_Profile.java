package com.cdw;

import com.cdw.config.MainConfigOfProfile;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

/**
 * @author: cdw
 * @date: 2021/12/16 16:23
 * @description:
 */
public class IOCTest_Profile {

    /**
     * 1.命令行动态设置参数： -Dspring.profiles.active=dev
     * 2.代码启动
     */


    @Test
    public void test1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfigOfProfile.class);

//        String[] names = context.getBeanNamesForType(DataSource.class);
//        for (String name : names)
//            System.out.println(name);

        String[] names1 = context.getBeanDefinitionNames();
        for (String name : names1)
            System.out.println(name);


        context.close();
    }

    @Test
    public void test2() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("dev");
        context.register(MainConfigOfProfile.class);
        context.refresh();

        /*String[] names = context.getBeanNamesForType(DataSource.class);
        for (String name : names)
            System.out.println(name);*/


        String[] names1 = context.getBeanDefinitionNames();
        for (String name : names1)
            System.out.println(name);


        context.close();
    }
}
