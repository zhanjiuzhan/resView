package org.jpcl.resview.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Administrator
 */
@SpringBootApplication
@ComponentScan({"org.jpcl.resview.jsonp", "org.jpcl.resview.view.init",
        "org.jpcl.resview.scheduled", "org.jpcl.resview.demo", "org.jpcl.resview.swagger2"})
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class);
    }
}
