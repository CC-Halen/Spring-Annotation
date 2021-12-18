package com.cdw;

import com.cdw.aop.MathCalculator;
import com.cdw.config.MainConfigOfAOP;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author: cdw
 * @date: 2021/12/16 17:16
 * @description:
 */
public class AopTest {


    @Test
    public void test1(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);

        MathCalculator bean = context.getBean(MathCalculator.class);
        bean.div(3, 2);

        context.close();
    }
}
