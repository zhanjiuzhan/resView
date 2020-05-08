package org.jpcl.resview.demo.task;

import org.jpcl.resview.scheduled.DynamicTask;
import org.jpcl.resview.scheduled.DynamicTaskManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Administrator
 */
@Controller
public class DynamicScheduledTaskController {

    @Autowired
    private DynamicTaskManagement dynamicTaskManagement;

    @RequestMapping("/startTask")
    @ResponseBody
    public void stratTask() {
        dynamicTaskManagement.addTask(new DynamicTask("testTask", "0/2 * * * * ?") {
            @Override
            public void run() {
                System.out.println("我每两秒执行一次");
            }
        });
    }

    @RequestMapping("/stopTask")
    @ResponseBody
    public void stopTask(String name) {
        dynamicTaskManagement.closeTask(name);
    }
}
