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
 * 🔺 @Aspect: 告诉Spring切面类的存在
 *
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
 *                  1）resolveBeforeInstantiation(beanName, mbdToUse);解析 BeforeInstantiation
 *                      希望后置处理器能返回一个代理对象，如果能返回就使用，如果不能就继续第二步
 *                      1）后置处理器尝试先返回对象：
 *                          bean = applyBeanPostProcessorsBeforeInstantiation(targetType, beanName);
 *                              拿到所有后置处理器，如果是InstantiationAwareBeanPostProcessor，就执行postProcessBeforeInstantiation()方法
 *                          if (bean != null) {
 * 						        bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
 *                          }
 *                  2）doCreateBean(beanName, mbdToUse, args); 真正地创建一个bean实例；和上述 3）. 6）一致
 *
 *
 *
 * AnnotationAwareAspectJAutoProxyCreator[InstantiationAwareBeanPostProcessor]的作用：
 * 1）在每一个bean创建之前，调用postProcessBeforeInstantiation()：
 *      关心MathCalculator和LogAspects的创建
 *      1）判断当前bean是否在advisedBeans中（保存了所有需要增强的bean）
 *      2）判断当前bean是否是基础类型的Advice、Pointcut、Advisor、AopInfrastructureBean
 *          或者是否是切面（@Aspect）
 *      3）判断是否需要跳过
 *          1）获取候选的增强器（切面里的通知方法） [List<Advisor> candidateAdvisors = findCandidateAdvisors()]
 *              每一个封装的通知方法的增强器是InstantiationModelAwarePointcutAdvisor：
 *              判断每一个增强器是否是AspectJPointcutAdvisor：是就返回true
 *          2）返回false
 *
 * 2）创建对象
 *  postProcessAfterInitialization：
 *      return wrapIfNecessary(bean, beanName, cacheKey);//如果需要就包装
 *      1）获取当前bean的所有增强器（通知方法）  Object[] specificInterceptors
 *          1）找到当前候选的所有增强器（找到哪些方法是需要切入到当前bean方法的）
 *          2）获取到能在当前bean使用的增强器
 *          3）给增强器排序
 *      2）保存当前bean到advisedBeans中
 *      3）如果当前bean需要增强，创建当前bean的代理对象
 *          1）获取所有增强器（通知方法）
 *          2）保存到proxyFactory中
 *          3）创建代理对象：Spring自动决定
 *              JdkDynamicAopProxy(config); jdk动态代理
 *              ObjenesisCglibAopProxy(config); cglib的动态代理
 *      4）给容器中返回当前cglib增强了的代理对象
 *      5）容器中获取到的就是这个组件的代理对象，执行目标方法的时候，代理对象就会执行通知方法的流程
 *
 * 3）目标方法的执行
 *      容器中保存了组件的代理对象（cglib增强后的对象），这个对象里面保存了详细信息（比如增强器、目标对象、xxx）
 *      1）CglibAopProxy.intercept(); 拦截目标方法的执行
 *      2）根据ProxyFactory获取将要执行的目标方法的拦截器链（拦截器链：每一个通知方法又被称为方法拦截器，利用MethodInterceptor机制）
 *          List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
 *          1）List<Object> interceptorList= new ArrayList<>(advisors.length);被创建，用以保存所有拦截器
 *              一个默认的ExposeInvocationInterceptor 和 4个增强器
 *          2）遍历所有的增强器，将其转为interceptor：
 *              registry.getInterceptors(advisor);
 *          3）将增强器转为需要使用的List<MethodInterceptor> interceptors = new ArrayList<>(3);:
 *              如果是MethodInterceptor，直接加入到集合中；
 *              如果不是，使用适配器AdvisorAdapter将增强器转为MethodInterceptor；
 *              转换完成，返回数组interceptors
 *
 *      3）如果没有拦截器链，直接执行目标方法
 *      4）如果有拦截器链，把需要执行的目标对象、目标方法、拦截器链等信息传入创建一个CglibMethodInvocation对象，
 *          并调用 proceed()方法得到返回值 Object retVal
 *      5）拦截器链的触发过程：
 *          已有的拦截器链（除了默认的）：LogException、LogReturn、LogEnd、LogStart
 *          1）如果没有拦截器，直接执行目标方法，或者拦截器的索引和拦截器数组大小-1一样（也就是执行到了最后一个拦截器）
 *          2）链式获取每一个拦截器，拦截器执行invoke方法，每一个拦截器等待下一个拦截器执行完成返回之后再执行
 *              拦截器链的机制保证通知方法与目标方法的执行顺序
 *
 *
 * 总结：
 *      1）@EnableAspectJAutoProxy 开启AOP功能
 *      2）@EnableAspectJAutoProxy 会给容器注册一个组件AnnotationAwareAspectJAutoProxyCreator
 *      3）AnnotationAwareAspectJAutoProxyCreator是一个后置处理器
 *      4）容器的创建流程：
 *          1）registerBeanPostProcessors()，注册后置处理器，创建 AnnotationAwareAspectJAutoProxyCreator
 *          2）finishBeanFactoryInitialization()，初始化剩下的单实例bean
 *              1）创建业务逻辑组件和切面组件
 *              2）AnnotationAwareAspectJAutoProxyCreator会拦截组件的创建过程
 *              3）组件创建完成之后，判断组件是否需要增强
 *                  是：切面的通知方法包装成增强器（Advisor）；给业务逻辑组件创建一个代理对象（cglib动态代理）
 *       5）执行目标方法：
 *          1）代理对象执行目标方法
 *          2）CglibAopProxy.intercept();
 *              1）得到目标方法的拦截器链（增强器包装成拦截器MethodInterceptor）
 *              2）利用拦截器的链式机制，依次进入每一个拦截器中执行
 *              3）效果：
 *                  正常执行：前置通知 ->目标方法 ->后置通知 ->返回通知
 *                  出现异常：前置通知 ->目标方法 ->后置通知 ->异常通知
 *
 *
 *
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
