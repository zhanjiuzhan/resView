# Spring Boot 事件监听
## 1. 简单的事件监听实现
### 1.1 定义自己的事件类型
```java
public class MyDefineEvent extends ApplicationEvent {

    private String message;

    /**
     * Create a new ApplicationEvent.
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public MyDefineEvent(Object source) {
        super(source);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
```
### 1.2 添加事件监听器
#### 1.2.1 添加事件监听器方案一
```java
/**
 * 自定义时间监听器
 * @author Administrator
 */
// 需要放入容器中
@Component
public class MyDefineEventListener implements ApplicationListener<MyDefineEvent> {
    @Override
    public void onApplicationEvent(MyDefineEvent event) {
        System.out.println(event.getMessage());
    }
}
```
#### 1.2.1 添加事件监听器方案二
```java
/**
 * 自定义时间监听器
 * @author Administrator
 */
// 需要放入容器中
@Component
public class MyDefineEventListener {

    @EventListener
    public void onApplicationEvent(MyDefineEvent event) {
        System.out.println(event.getMessage());
    }
}
```
* 当有多个相同的监听器时, 对于接口实现的监听器可以使用@Order来管理其响应顺序, 对于同一个Bean中@EventListener注册的监听器, 响应的顺序自上而下。同时具有接口实现和 @EventListener实现, @EventListener优先, 多个bean中有@EventListener顺序控制不成。
### 1.3 发送事件
```java
@SpringBootApplication
public class AppApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(AppApplication.class);
        // 创建一个事件 构造器中的object 是发生这个事件的对象 你想随便填也行
        MyDefineEvent event =  new MyDefineEvent(new Object());
        event.setMessage("测试事件!");

        // 发布一个事件 ApplicationContext 是一个容器bean 可以再其它地方拿到 用它发布事件即可
        context.publishEvent(event);
    }
}
```
* 运行后就可以看见控制台打印的事件了, 说明该事件被监听着

## 2. 异步的事件监听实现
### 2.1 定义自己的事件类型
和 1.1 一样的
### 1.2 添加事件监听器
### 2.1 定义自己的事件类型
和 1.2 一样的, 不同之处在于:
* 方法1
```java
@Component
@Async
public class MyDefineEventListener implements ApplicationListener<MyDefineEvent>
```
* 方法2
```java
@EventListener
@Async
public void onApplicationEvent(MyDefineEvent event) {
    System.out.println(event.getMessage());
}
```

### 2.3 发送事件
```java
@SpringBootApplication
@EnableAsync
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
}
```
* 同步的情况 事件发布完成! 会在最后打印。
* 异步的话 事件发布完成! 将在for循环完成后就打印
### 2.3 线程池配置
* 事件监听器默认使用的是默认的内部线程池, 所以也可以配置自己的
```java
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
```