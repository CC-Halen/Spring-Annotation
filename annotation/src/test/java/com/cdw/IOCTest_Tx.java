package com.cdw;


import com.cdw.tx.TxConfig;
import com.cdw.tx.UserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author: cdw
 * @date: 2021/12/16 17:16
 * @description:
 */

public class IOCTest_Tx {


    @Test
    public void test1(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TxConfig.class);

        UserService service = context.getBean(UserService.class);
        service.insertUser();


        context.close();
    }
}
