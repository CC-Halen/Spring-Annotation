package com.cdw;

import com.cdw.beans.Person;
import com.cdw.config.MainConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author: cdw
 * @date: 2021/12/15 16:15
 * @description:
 */
public class MainTest {

    private final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);

    @Test
    public void getPersonTest() {
        Person person = context.getBean(Person.class);
        System.out.println(person);
    }

    @Test
    public void testNames() {
        String[] names = context.getBeanDefinitionNames();
        for (String name : names)
            System.out.println(name);
    }
}
