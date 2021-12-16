package com.cdw.config;

import com.cdw.beans.Car;
import com.cdw.beans.Color;
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
 *  3.@Autowired 属性、方法、构造器的装配：从容器中获取参数组件的值进行装配
 *      可标注在方法：@Bean+方法参数
 *      构造器：若组件只有一个有参构造器 @Autowired可以省略
 *      参数位置：
 *
 *  4.自定义组件想要使用Spring容器底层的一些组件（ApplicationContext，BeanFactory ...）
 *      自定义组件实现 xxxAware：创建对象时，会实现接口规定的方法注入相关的组件：Aware
 *      把Spring底层组件注入Bean中
 *      xxxAware：功能使用xxxProcessor处理
 *
 */
@Configuration
@ComponentScan({"com.cdw.dao", "com.cdw.service", "com.cdw.controller","com.cdw.beans"})
public class MainConfigOfAutowired {


    @Primary
    @Bean("bookDao2") //会报错
    public BookDao bookDao() {
        BookDao bookDao2 = new BookDao();
        bookDao2.setLabel("2");
        return bookDao2;
    }


    /**
     * @Bean标注的方法创建对象时，方法参数的值从容器中获取
     * @param car
     * @return
     */
    @Bean
    public Color color(Car car){
        Color color1 = new Color();
        color1.setCar(car);
        return color1;
    }



}
