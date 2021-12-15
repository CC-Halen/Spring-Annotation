package com.cdw;

import com.cdw.beans.Person;
import com.cdw.config.MainConfig2;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

/**
 * @author: cdw
 * @date: 2021/12/15 16:53
 * @description:
 */
public class IOCTest {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig2.class);

    @Test
    public void test1() {

        Object person = context.getBean("person");
//        Object person1 = context.getBean("person");
//        System.out.println(person);
    }


    @Test
    public void test2() {
        String[] names = context.getBeanNamesForType(Person.class);
        for (String name : names)
            System.out.println(name);


        Map<String, Person> map = context.getBeansOfType(Person.class);
        System.out.println(map);

        ConfigurableEnvironment environment = context.getEnvironment();
        String property = environment.getProperty("os.name");
        System.out.println(property);
    }

    @Test
    public void test3() {
        Object colorFactoryBean = context.getBean("colorFactoryBean");
        Object colorFactoryBean1 = context.getBean("&colorFactoryBean");
        System.out.println(colorFactoryBean.getClass());
        System.out.println(colorFactoryBean1.getClass());
        String[] names = context.getBeanDefinitionNames();
        for (String name : names)
            System.out.println(name);
    }

}
