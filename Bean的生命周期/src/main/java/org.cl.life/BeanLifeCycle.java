package org.cl.life;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Administrator
 */
@Configuration
public class BeanLifeCycle {


    @Bean(name = "myBean", initMethod = "init_", destroyMethod = "destroy_")
    public MyBean getMyBean() {
        return new MyBean();
    }

    @Bean(name = "myBean1", initMethod = "init_", destroyMethod = "destroy_")
    public MyBean getMyBean1() {
        return new MyBean();
    }

    @Configuration
    public class TestConfig implements BeanPostProcessor, BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean, MyInterface {

        public TestConfig() {
            System.out.println("1. 构造方法先执行!");
        }

        @Override
        public void setBeanName(String name) {
            System.out.println("2. bean name :" + name);
        }

        @Override
        public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
            System.out.println("3. beanFactoryAware:" + beanFactory.toString());
        }

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            System.out.println("4. applicationAware: " + applicationContext.toString());
        }


        @PostConstruct
        public void init() {
            System.out.println("6. @PostConstruct init...");
        }


        @Override
        public void afterPropertiesSet() throws Exception {
            System.out.println("7. afterPropertiesSet...");
        }


        public void init_() {
            System.out.println("8. bean initMethod...");
        }


        public void show() {
            System.out.println("10. show...");
        }

        @PreDestroy
        public void destroy1() {
            System.out.println("11. @PreDestroy execute...");
        }


        @Override
        public void destroy() throws Exception {
            System.out.println("12. disposable execute...");
        }

        public void destroy_() {
            System.out.println("13. bean destroyMethod...");
        }

        @Override
        public void myInit() {
            System.out.println("8. myInit...");
        }

        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
            if (bean instanceof MyBean) {
                System.out.println("5. 后置处理器 before init...");
            }
            return null;
        }


        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            if (bean instanceof MyBean) {
                System.out.println("9. 后置处理器 after init...");
            }
            return bean;
        }
    }


    public class MyBean implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean, MyInterface {

        public MyBean() {
            System.out.println("1. 构造方法先执行!");
        }

        @Override
        public void setBeanName(String name) {
            System.out.println("2. bean name :" + name);
        }

        @Override
        public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
            System.out.println("3. beanFactoryAware:" + beanFactory.toString());
        }

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            System.out.println("4. applicationAware: " + applicationContext.toString());
        }


        @PostConstruct
        public void init() {
            System.out.println("6. @PostConstruct init...");
        }


        @Override
        public void afterPropertiesSet() throws Exception {
            System.out.println("7. afterPropertiesSet...");
        }


        public void init_() {
            System.out.println("8. bean initMethod...");
        }


        public void show() {
            System.out.println("10. show...");
        }

        @PreDestroy
        public void destroy1() {
            System.out.println("11. @PreDestroy execute...");
        }


        @Override
        public void destroy() throws Exception {
            System.out.println("12. disposable execute...");
        }

        public void destroy_() {
            System.out.println("13. bean destroyMethod...");
        }

        @Override
        public void myInit() {
            System.out.println("8. myInit...");
        }
    }
}
