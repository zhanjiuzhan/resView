package org.jpcl.resview.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator
 */
@Controller
public class AccessController {

    @RequestMapping("/app/test")
    public void isApp() {
        System.out.println("app....");
    }

    @RequestMapping("/user/test")
    public void isUser() {
        System.out.println("user....");
    }

    @RequestMapping("/admin/test")
    public void isAdmin() {
        System.out.println("admin....");
    }
}
