package com.cdw.ext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author: cdw
 * @date: 2021/12/21 9:59
 * @description:
 */
@Service
public class UserService {
    @EventListener(classes = {ApplicationEvent.class})
    public void listen(ApplicationEvent event) {
        System.out.println("UserService ... 监听到的事件：" + event);
    }


}
