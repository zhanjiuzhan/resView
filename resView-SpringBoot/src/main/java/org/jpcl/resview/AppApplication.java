package org.jpcl.resview;

import org.jpcl.resview.事件.MyDefineEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Administrator
 */
@SpringBootApplication
@EnableAsync
@Configuration
public class AppApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(AppApplication.class);

        for (int i = 0; i < 1000; i++) {
            // 创建一个事件 构造器中的object 是发生这个事件的对象 你想随便填也行
            MyDefineEvent event =  new MyDefineEvent(new Object());
            event.setMessage("测试事件：" + i);
            // 发布一个事件 ApplicationContext 是一个容器bean 可以再其它地方拿到 用它发布事件即可
            context.publishEvent(event);
        }
        System.out.println("事件发布完成!");
    }

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(20);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("myDefineExecutor-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }
}
