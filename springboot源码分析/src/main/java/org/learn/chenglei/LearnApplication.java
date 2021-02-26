package org.learn.chenglei;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigUtils;

import java.beans.Introspector;

/**
 * @author Administrator
 */
@SpringBootApplication
public class LearnApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(LearnApplication.class);
        ApplicationArguments arguments = context.getBean("springApplicationArguments", ApplicationArguments.class);
        Banner banner = context.getBean("springBootBanner", Banner.class);
        System.out.println(banner);
        System.out.println(Introspector.decapitalize("SpringApplicationArguments"));
    }
}
