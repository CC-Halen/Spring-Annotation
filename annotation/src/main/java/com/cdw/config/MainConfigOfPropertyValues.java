package com.cdw.config;

import com.cdw.beans.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author: cdw
 * @date: 2021/12/16 10:34
 * @description:
 */
@PropertySource(value = {"classpath:/person.properties"}) //使用注解讲配置文件信息加载到运行环境中保存
@Configuration
public class MainConfigOfPropertyValues {
    @Bean
    public Person person(){
        return new Person();
    }
}
