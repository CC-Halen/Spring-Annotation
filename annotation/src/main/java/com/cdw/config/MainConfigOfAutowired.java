package com.cdw.config;

import com.cdw.dao.BookDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author: cdw
 * @date: 2021/12/16 10:52
 * @description: Spring的自动装配
 *  1.@Autowired[Spring定义的规范]
 *      a.默认优先按照类型去容器中找到对应的组件，找到就赋值
 *      b.找到多个会报错，
 *          可以设置 @Primary 来指定首选的bean
 *          也可以通过 @Qualifier来指定
 *
 *  2.Spring还支持使用@Resource(JSR250)和 @Inject(JSR330)[java规范的注解]
 *      @Resource(JSR250): 默认按照组件名称进行装配，不支持@Primary，name = "bookDao2" 指定装配的bean
 *
 *      @Inject(JSR330): 自动装配，支持@Primary
 *
 */
@Configuration
@ComponentScan({"com.cdw.dao", "com.cdw.service", "com.cdw.controller"})
public class MainConfigOfAutowired {


    @Primary
    @Bean("bookDao2") //会报错
    public BookDao bookDao() {
        BookDao bookDao2 = new BookDao();
        bookDao2.setLabel("2");
        return bookDao2;
    }

}
