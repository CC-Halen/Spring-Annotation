package com.cdw.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.cdw.beans.Blue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.security.PrivateKey;

/**
 * @author: cdw
 * @date: 2021/12/16 15:55
 * @description:
 * Profile：
 * Spring提供的可以动态地激活和切换一系列组件的功能
 *
 * @Profile
 *      1.加了环境标识的bean只有等到激活之后才可以注册到容器中，默认时default环境
 *      2.写在配置类上，只有是指定环境时才会使得配置环境生效
 *      3.没有标注环境的bean在任何环境下都会加载
 *
 */
//@Profile("test")
@PropertySource("classpath:/dbConfig.properties")
@Configuration
public class MainConfigOfProfile {
    @Value("${db.username}")
    private String username;

    @Value("${db.password}")
    private String password;

    @Value("${db.driver}")
    private String driver;

//    @Profile("test")
    @Bean
    public Blue blue(){
        return new Blue();
    }


    @Profile("test")
    @Bean("testDataSource")
    public DataSource dataSourceTest(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setDriverClassName(driver);

        return dataSource;
    }

    @Profile("dev")
    @Bean("devDataSource")
    public DataSource dataSourceDev(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl("jdbc:mysql://localhost:3306/ssm");
        dataSource.setDriverClassName(driver);

        return dataSource;
    }

    @Profile("prod")
    @Bean("prodDataSource")
    public DataSource dataSourceProd(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl("jdbc:mysql://localhost:3306/mp");
        dataSource.setDriverClassName(driver);

        return dataSource;
    }
}
