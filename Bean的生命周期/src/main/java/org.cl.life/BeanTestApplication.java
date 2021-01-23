package org.cl.life;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author Administrator
 */
@SpringBootApplication
public class BeanTestApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BeanTestApplication.class);
        context.getBean("myBean", BeanLifeCycle.MyBean.class).show();
    }
}
