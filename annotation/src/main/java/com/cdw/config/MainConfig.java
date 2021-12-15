package com.cdw.config;

import com.cdw.beans.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;


/**
 * @author: cdw
 * @date: 2021/12/15 16:14
 * @description:
 */
@Configuration
//@ComponentScan("com.cdw")
//@ComponentScan(value = "com.cdw",excludeFilters = {
//        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})
//})//排除
@ComponentScan(value = "com.cdw",includeFilters = {
        /*@ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class}),*/
        @ComponentScan.Filter(type = FilterType.CUSTOM,classes = {MyTypeFilter.class})
        },useDefaultFilters = false)//只包含
public class MainConfig {


    @Bean
    public Person person(){
        return new Person("lisi",45);
    }
}
