package org.spring.core.controller;

import org.spring.core.annotation.MyAutowired;
import org.spring.core.annotation.MyController;
import org.spring.core.annotation.MyRequestMapping;
import org.spring.core.service.MyService;

/**
 * @author Administrator
 */
@MyController
public class MyDefineController {

    @MyAutowired
    private MyService myService;

    @MyRequestMapping(url = "/get1", type = "GET")
    public String get1() {
        System.out.println("get1");
        return "get1 " + myService.say();
    }

    @MyRequestMapping(url = "/get2", type = "GET")
    public String get2() {
        System.out.println("get2");
        return "get2" + myService.say();
    }
}
