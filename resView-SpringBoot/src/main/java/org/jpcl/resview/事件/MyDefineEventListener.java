package org.jpcl.resview.事件;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 自定义时间监听器
 * @author Administrator
 */
// 需要放入容器中
@Component
@Async
public class MyDefineEventListener implements ApplicationListener<MyDefineEvent> {
    @Override
    public void onApplicationEvent(MyDefineEvent event) {
        System.out.println(Thread.currentThread().getName() + ":" + event.getMessage());
    }
}
