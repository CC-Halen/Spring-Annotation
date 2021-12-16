package com.cdw.config;

import com.cdw.aop.LogAspects;
import com.cdw.aop.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author: cdw
 * @date: 2021/12/16 16:53
 * @description: 面向切面编程
 * 1.日志切面类中的方法要动态切入到MathCalculator.div的运行:
 *      通知方法：
 *          前置通知(@Before)：logStart div运行之前
 *          后置通知(@After)：logEnd div运行之后
 *          返回通知(@AfterReturning)：logReturn div正常返回
 *          异常通知(@AfterThrowing)：logException div异常发生时
 *          环绕通知(@Around)：动态代理，手动推动目标方法的执行
 *
 * 2.给切面类的目标反法标注运行时间
 * 3.将切面类与目标逻辑的方法类加入到容器中
 * 4.注解添加
 *    @Aspect: 告诉Spring切面类的存在
 * 🔺 @EnableAspectJAutoProxy: 启用基于注解的aop模式
 *
 *
 * AOP原理：[给容器注册了什么组件，组件什么时候工作，组件的功能]
 *  1.@EnableAspectJAutoProxy：
 *      @Import(AspectJAutoProxyRegistrar.class): 导入组件
 *          利用AspectJAutoProxyRegistrar给容器注册bean
 *          internalAutoProxyCreator = AnnotationAwareAspectJAutoProxyCreator
 *      给容器创建一个AnnotationAwareAspectJAutoProxyCreator
 *
 *  2.AnnotationAwareAspectJAutoProxyCreator(idea中右键show diagram看继承和实现关系)：
 *      实现了(SmartInstantiationAware)BeanPostProcessor（后置处理器）和 BeanFactoryAware（自动装配）
 *
 *
 *      AbstractAutoProxyCreator.setBeanFactory()
 *      后置处理器的逻辑：
 *      AbstractAutoProxyCreator.postProcessBeforeInstantiation()
 *      AbstractAutoProxyCreator.postProcessAfterInitialization()
 *
 *
 *      AbstractAdvisorAutoProxyCreator.setBeanFactory()->AbstractAdvisorAutoProxyCreator.initBeanFactory()
 *
 *
 *      AspectJAwareAdvisorAutoProxyCreator
 *
 *
 *      AnnotationAwareAspectJAutoProxyCreator.initBeanFactory()
 *
 *  流程：
 *      1）传入配置类，创建ioc容器
 *      2）注册配置类，调用refresh()方法刷新容器
 *      3）registerBeanPostProcessors(beanFactory);注册bean的后置处理器来拦截bean的创建
 *          1）先获取ioc容器中已经定义了的需要创建的BeanPostProcessor
 *          2）给容器中添加别的BeanProcessor
 *          3）优先注册实现了PriorityOrdered接口的BeanProcessor
 *          4）再注册实现了Ordered接口的BeanProcessor
 *          5）注册没实现优先级接口的BeanProcessors
 *          6）注册BeanPostProcessor，实际上就是创建BeanPostProcessor对象，保存在容器中：
 *              创建internalAutoProxyCreator的BeanPostProcessor[AnnotationAwareAspectJAutoProxyCreator]
 *                  1)创建Bean的实例
 *                  2）populateBean(beanName, mbd, instanceWrapper); 给Bean的属性赋值
 *                  3）initializeBean(beanName, exposedObject, mbd); 初始化Bean：
 *                      1）invokeAwareMethods()：处理Aware接口的方法回调
 *                      2）applyBeanPostProcessorsBeforeInitialization()：应用后置处理器的postProcessBeforeInitialization()方法
 *                      3）invokeInitMethods()：执行自定义的初始化方法
 *                      4）applyBeanPostProcessorsAfterInitialization()：执行后置处理器的postProcessAfterInitialization()方法
 *                  4）BeanPostProcessor[AnnotationAwareAspectJAutoProxyCreator]创建成功：-->aspectJAdvisorsBuilder
 *          7）把BeanPostProcessor注册到beanFactory中：
 *              beanFactory.addBeanPostProcessor(postProcessor);
 *
 * ======= 以上是创建和注册AnnotationAwareAspectJAutoProxyCreator的过程 ========
 *          AnnotationAwareAspectJAutoProxyCreator ===> InstantiationAwareBeanPostProcessor
 *
 *      4）finishBeanFactoryInitialization(beanFactory); 完成beanFactory的初始化工作：创建剩下的单实例bean
 *          1）遍历获取容器中的所有bean，依次创建对象
 *              getBean()->doGetBean()->getSingleton()->
 *          2）创建bean
 *              【AnnotationAwareAspectJAutoProxyCreator会在任何bean创建之前会有一个拦截，会调用postProcessBeforeInstantiation()方法】
 *              1）先从缓存中获取当前bean，如果能获取到，说明bean是之前被创建过的，直接使用，否则再创建
 *                  只要创建好的bean都会被缓存起来
 *              2）createBean()；创建bean：  AnnotationAwareAspectJAutoProxyCreator会在任何bean创建之前先尝试返回bean的实例
 *                  【BeanPostProcessor是在Bean对象创建完成初始化前后调用的】
 *                  【InstantiationAwareBeanPostProcessor是在创建Bean实例之前先尝试用后置处理器返回对象的】
 *                  1）resolveBeforeInstantiation(beanName, mbdToUse);解析BeforeInstantiation
 *                      希望后置处理器能返回一个代理对象，如果能返回就使用，如果不能就继续第二步
 *                      1）后置处理器尝试先返回对象：
 *                          bean = applyBeanPostProcessorsBeforeInstantiation(targetType, beanName);
 *                              拿到所有后置处理器，如果是InstantiationAwareBeanPostProcessor，就执行postProcessBeforeInstantiation()方法
 *                          if (bean != null) {
 * 						        bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
 *                          }
 *                  2）doCreateBean(beanName, mbdToUse, args); 真正地创建一个bean实例；和上述 3）-> 6）一致
 *
 *
 */
@EnableAspectJAutoProxy
@Configuration
public class MainConfigOfAOP {

    @Bean
    public MathCalculator mathCalculator(){
        return new MathCalculator();
    }

    @Bean
    public LogAspects logAspects(){
        return new LogAspects();
    }

}
