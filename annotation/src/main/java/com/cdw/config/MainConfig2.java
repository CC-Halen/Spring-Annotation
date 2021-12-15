package com.cdw.config;

import com.cdw.beans.Color;
import com.cdw.beans.ColorFactoryBean;
import com.cdw.beans.Person;
import com.cdw.beans.Red;
import com.cdw.condition.LinuxCondition;
import com.cdw.condition.MyImportBeanDefinitionRegistrar;
import com.cdw.condition.MyImportSelector;
import com.cdw.condition.WinCondition;
import org.springframework.context.annotation.*;

/**
 * @author: cdw
 * @date: 2021/12/15 16:51
 * @description: 给容器注册组件
 * 1.包扫描+组件标注注解
 * 2.@Bean(导入第三方包里面的组件)
 * 3.@Import[快速给容器注册一个组件]
 *      1).@Import() id默认时全类名
 *      2).ImportSelector：返回需要读入的组件的全类名数组
 *      3).ImportBeanDefinitionRegistrar：手动注册bean到容器中
 * 4.使用Spring提供的FactoryBean
 *      1).默认获取的是工厂Bean的getObject方法
 *      2).获取的是工厂Bean本身，id前面加一个 &
 *
 *
 *
 */
@Configuration
@Import({Color.class, Red.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class MainConfig2 {


    /**
     * Scope
     * prototype:多实例的，ioc容器启动时不会调用方法创建对象，获取时调用
     * singleton:单实例的，ioc容器启动时调用方法创建对象放入容器中
     * 懒加载：
     * 单实例bean：默认在容器启动时创建对象
     * 懒加载就是容器启动不创建，获取时才创建
     */
//    @Scope("prototype")
    @Lazy
    @Bean("person")
    public Person person() {
        System.out.println("容器添加中。。。。。");
        return new Person("张三", 45);
    }

    /**
     * @Conditional： 按照条件进行判断，满足条件就给容器注册bean
     */
    @Conditional({WinCondition.class})
    @Bean("zs")
    public Person person01() {
        return new Person("赵四", 34);
    }

    @Conditional({LinuxCondition.class})
    @Bean("ln")
    public Person person02() {
        return new Person("刘能", 56);
    }

    @Bean
    public ColorFactoryBean colorFactoryBean() {
        return new ColorFactoryBean();
    }


}
