package org.jpcl.resview.scheduled;

/**
 * @author Administrator
 */
public abstract class DynamicTask implements Runnable {
    private String cron;
    private String taskName;

    public DynamicTask(String taskName, String cron) {
        this.taskName = taskName;
        this.cron = cron;
    }

    public String getCron() {
        return cron;
    }

    public String getName() {
        return taskName;
    }
}
