package com.cdw.ext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author: cdw
 * @date: 2021/12/20 16:46
 * @description: 应用监听器
 *  ApplicationListener监听的是ApplicationEvent及其子类
 *
 *  步骤：
 *      1）写一个监听器监听某个事件，监听器实现ApplicationListener接口
 *          也可以加注解 @EventListener监听事件
 *          原理：使用EventListenerMethodProcessor  implements SmartInitializingSingleton, ApplicationContextAware, BeanFactoryPostProcessor 处理器解析注解
 *              SmartInitializingSingleton原理 -> afterSingletonsInstantiated()：
 *                  1）ioc容器创建对象并刷新容器 refresh()
 *                  2）finishBeanFactoryInitialization(beanFactory); 初始化剩下的单实例bean
 *                      1）先创建所有的单实例bean ：getBean(beanName);
 *                      2）获取所有创建好的单实例bean，判断是否是 SmartInitializingSingleton类型的，
 *                          如果是，则调用afterSingletonsInstantiated()方法
 *
 *
 *
 *      2）将监听器加入到容器中
 *      3）只要容器中有相关事件的发布，我们就能监听到这个事件：
 *          ContextRefreshedEvent 容器刷新完成事件
 *          ContextClosedEvent  容器关闭事件
 *      4）自己发布一个事件
 *           context.publishEvent(new ApplicationEvent(new String()) { });
 *
 *  原理：
 *      ContextRefreshedEvent事件：
 *          1）容器创建对象：refresh()
 *          2）finishRefresh();容器刷新完成
 *          自己发布的事件【】IOCTest_Ext$1[source=com.sun.org.apache.xpath.internal.operations.String@69b2283a]与容器关闭的事件【ContextClosedEvent】：
 *          3）publishEvent(new ContextRefreshedEvent(this));
 *          事件发布流程：
 *              1）获取事件的多播器：getApplicationEventMulticaster()
 *              2）派发事件：multicastEvent(applicationEvent, eventType);
 *              3）拿到每一个监听器  for (ApplicationListener<?> listener : getApplicationListeners(event, type))
 *                  1）如果有Executor，可以支持Executor进行异步派发：
 *                      Executor executor = getTAskExecutor();
 *                  2）否之，同步的方法直接执行listener方法；invokeListener()
 *                  拿到listener回调onApplicationEvent()方法
 *
 *  【事件多播器（派发器） ApplicationEventMulticaster】
 *      1）容器创建对象；refresh()
 *      2）initApplicationEventMulticaster()
 *          1）先去容器中寻找有没有名为 “applicationEventMulticaster”的组件
 *          2）如果没有，则 this.applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
 *              并加入到容器中，就可以在其他组件要派发事件时，自动注入applicationEventMulticaster就行
 *
 *  【容器中有哪些监听器】
 *      1）容器创建对象；refresh()
 *      2）registerListeners()；
 *          从容器中拿到所有的监听器，把他们注册到 applicationEventMulticaster 中
 *          String[] listenerBeanNames = getBeanNamesForType(ApplicationListener.class, true, false);
 *          将listener注册到 applicationEventMulticaster 中
 *          getApplicationEventMulticaster().addApplicationListenerBean(listenerBeanName);
 *
 *
 *
 *
 *
 */
@Component
public class MyApplicationListener implements ApplicationListener<ApplicationEvent> {
    //当容器中发布时间时会触发方法
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("收到事件：" + event);
    }
}
