package org.jpcl.resview.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * 定时任务管理器
 * @author Administrator
 */
@Configuration
public class DynamicTaskManagement {
    private final static Logger logger = LoggerFactory.getLogger(DynamicTaskManagement.class);
    private static ConcurrentHashMap<String, ScheduledFuture> taskMap;

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        // 定义调度任务池
        ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();
        executor.setPoolSize(20);
        executor.setThreadNamePrefix("jc-task-");
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        return executor;
    }


    static {
        // 定义执行任务管理池
        taskMap = new ConcurrentHashMap<>();
    }

    /**
     * 添加任务到任务管理器
     * @param task
     */
    public void addTask(DynamicTask task) {
        taskMap.put(task.getName(), startTask(task));
        logger.info("[{}] 任务被添加到执行计划池和管理池中, 当前任务数[{}]", task.getName(), taskMap.size());
    }

    /**
     * 将添加到的任务添加到执行计划中
     */
    private ScheduledFuture startTask(DynamicTask task) {
        ScheduledFuture scheduledFuture = threadPoolTaskScheduler.schedule(task, new CronTrigger(task.getCron()));
        return scheduledFuture;
    }


    /**
     * 取得执行的任务
     * @param taskName
     * @return
     */
    public ScheduledFuture getTask(String taskName) {
        return taskMap.get(taskName);
    }

    /**
     * 取得任务的数量
     * @return
     */
    public int getSize() {
        return taskMap.size();
    }

    /**
     * 取消执行任务
     * @param taskName
     * @return
     */
    public boolean closeTask(String taskName) {
        boolean closeFlag = false;
        ScheduledFuture task = taskMap.get(taskName);
        if (task != null) {
            task.cancel(true);
            // 查看任务是否在正常执行之前结束,正常true
            boolean cancelled = task.isCancelled();
            while (!cancelled) {
                task.cancel(true);
            }
            boolean flag = taskMap.remove(taskName, task);
            if (flag) {
                closeFlag = true;
                logger.info("[{}] 任务关闭且移除管理池, 当前任务数[{}]", taskName, taskMap.size());
            }
        }
        return closeFlag;
    }
}
