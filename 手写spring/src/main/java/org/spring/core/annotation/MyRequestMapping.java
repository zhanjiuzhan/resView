package org.spring.core.annotation;

import java.lang.annotation.*;

/**
 * @author Administrator
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRequestMapping {
    String url() default "";
    String type() default "GET";
}
