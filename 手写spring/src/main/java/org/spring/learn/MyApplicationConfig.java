package org.spring.learn;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author Administrator
 */
@SpringBootApplication
@ServletComponentScan(basePackages="org.spring.core")
public class MyApplicationConfig {


}
