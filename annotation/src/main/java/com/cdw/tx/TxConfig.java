package com.cdw.tx;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author: cdw
 * @date: 2021/12/18 15:13
 * @description: 声明式事务
 *
 * 原理：
 *  1）@EnableTransactionManagement
 *      利用TransactionManagementConfigurationSelector给容器导入组件：
 *      导入两个组件：
 *      AutoProxyRegistrar
 *      ProxyTransactionManagementConfiguration
 *  2）AutoProxyRegistrar：
 *      给容器中注册组件 InfrastructureAdvisorAutoProxyCreator：
 *          利用后置处理器机制在对象创建之后包装对象，返回一个代理对象（有增强器），代理对象利用拦截器链执行方法
 *
 *  3）ProxyTransactionManagementConfiguration：
 *      1）注册事务增强器：
 *          1）事务增强器要用事务注解的信息，AnnotationTransactionAttributeSource 解析事务注解
 *          2）事务拦截器：
 *              TransactionInterceptor：保存了事务属性信息、事务管理器
 *              是一个MethodInterceptor
 *              在目标方法执行的时候：
 *                  执行拦截器链：
 *                  事务拦截器：
 *                      1）先获取事务相关属性
 *                      2）再获取 PlatformTransactionManager，若实现没有添加指定任何 transactionManager，
 *                      最终会在容器中按照类型获取一个PlatformTransactionManager
 *                      3）执行目标方法
 *                          如果异常，获取事务管理器，利用事务管理器回滚操作
 *                          如果正常，利用事务管理器提交事务
 *
 *
 *
 */
@EnableTransactionManagement
@ComponentScan("com.cdw.tx")
@Configuration
public class TxConfig {

    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("980921");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test?characterEncoding=utf8&useSSL=false&serverTimezone=GMT&rewriteBatchedStatements=true");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        return jdbcTemplate;
    }

    //配置事务管理器，管理的是dataSource
    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

}
