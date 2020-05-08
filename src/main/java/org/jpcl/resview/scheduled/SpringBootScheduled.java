package org.jpcl.resview.scheduled;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 */
@EnableScheduling
@Component
public class SpringBootScheduled {

    @Scheduled(cron = "0 0/2 * * * ?")
    public String execute1() {
        System.out.println("每两分钟执行一次...");
        return null;
    }
}
