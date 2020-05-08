## Java定时器
1. Timer：这是java自带的java.util.Timer类，这个类允许你调度一个java.util.TimerTask任务。使用这种方式可以让你的程序按照某一个频度执行，但不能在指定时间运行。一般用的较少。
2. ScheduledExecutorService：也jdk自带的一个类；是基于线程池设计的定时任务类,每个调度任务都会分配到线程池中的一个线程去执行,也就是说,任务是并发执行,互不影响。
3. Spring Task：Spring3.0以后自带的task，可以将它看成一个轻量级的Quartz，而且使用起来比Quartz简单许多。
4. Quartz：这是一个功能比较强大的的调度器，可以让你的程序在指定时间执行，也可以按照某一个频度执行，配置起来稍显复杂。

### 使用Timer
这是让你按照固定的频率去执行一个任务，不能指定时间。
```java
public class TestTimer {
    public static void main(String[] args) {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("task  run:"+ new Date());
            }
        };
        Timer timer = new Timer();
        //安排指定的任务在指定的时间开始进行重复的固定延迟执行。这里是每3秒执行一次
        timer.schedule(timerTask,10,3000);
    }
}
```
### ScheduledExecutorService
```java
public class TestScheduledExecutorService {
    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        // 参数：1、任务体 2、首次执行的延时时间
        //      3、任务执行间隔 4、间隔时间单位
        service.scheduleAtFixedRate(()->System.out.println("task ScheduledExecutorService "+new Date()), 0, 3, TimeUnit.SECONDS);
    }
}
```
### Spring Boot 的定时器
##### @EnableScheduling
1. 在Application中设置启用定时任务功能@EnableScheduling  
    * @EnableScheduling 注解的作用是发现注解@Scheduled的任务并后台执行。
2. @Scheduled参数描述(添加到要定时执行的方法上)
    > @Scheduled(fixedRate=3000)   上一次开始执行时间点后3秒再次执行  
    @Scheduled(fixedDelay=3000)  上一次执行完毕时间点3秒再次执行  
    @Scheduled(initialDelay=1000, fixedDelay=3000)  第一次延迟1秒执行，然后在上一次执行完毕时间点3秒再次执行  
    @Scheduled(cron="* * * * * ?")  按cron规则执行  
    ###### cron规则

    |秒(0-59)|分钟(0-59)|小时(0-23)|天(0-31)|月(0-11)|星期(1-7)|年份(1970-2099)|描述|
    |-----|-----|-----|-----|-----|-----|-----|-----|
    | 0   | 0   | 10,14,16   | *   | *   | ?   | 可选 留空 | ?用来解决冲突 表示每天10点 2点 4点执行一次   |
    | 0   | 0/10   | 9-17   | *   | *   | ?   |    | 表示每天9-17点 每10分钟执行一次   |
    | 0   | * | 14 | * | * | ? |  | 每天的2点的每一分钟执行 |
    | 0   | 0-5 | 14 | * | * | ? |  | 每天的2:00-2:05期间的每一分钟执行 |
    | 0   | 10,44 | 14 | ? | 3 | WED |  | 每年的3月周三 下午2:10分和2:44执行 |
    | 0   | 15 | 10 | L | * | ? |  | 每月的最后一日的10:15触发 |
    | 0   | 15 | 10 | ? | * | 6L |  |  每月的最后一个星期五上午10:15触发  |
    | 0   | 15 | 10 | ? | * | 6#3 |  | 每月的第三个星期五上午10:15触发 |
    |    | |   |   |   |  |  |   |

    * \* 表示所有可能
    * / 字符用来指定数值的增量
    * ? 字符仅被用于天（月）和天（星期）两个子表达式，表示不指定值
    * 天（星期）（1~7 1=SUN 或 SUN，MON，TUE，WED，THU，FRI，SAT）
    * L 字符仅被用于天（月）和天（星期）两个子表达式，它是单词last的缩写

### ThreadPoolTaskScheduler来进行任务调度
springboot中有一个bean，ThreadPoolTaskScheduler，可以很方便的对重复执行的任务进行调度管理；相比于通过java自带的周期性任务线程池ScheduleThreadPoolExecutor，此bean对象支持根据cron表达式创建周期性任务。