package org.jpcl.resview.view.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * @author Administrator
 */
@Component
public class ResCodeInit implements EnvironmentAware {

    private Logger logger = LoggerFactory.getLogger(ResCodeInit.class);

    private Environment env;

    public ResCodeInit() {
        initialize();
    }

    /**
     * 默认在classPath下
     */
    private final String DEF_PATH = "res-code.properties";

    /**
     * 容器启动中执行的方法
     */
    public void initialize() {
        String resCodePath = env.getProperty("res.code.path");
        if (resCodePath == null || resCodePath.trim().length() < 0) {
            getResCode(DEF_PATH);
        } else {
            getResCode(resCodePath);
        }
    }

    private Map<Integer, String> getResCode(String path) {
        ClassPathResource classPathResource = new ClassPathResource(path);
        try {
            File pro = classPathResource.getFile();
            if (!pro.exists()) {
                throw new IOException();
            }
            Properties properties = new Properties();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(pro));
            properties.load(bufferedReader);

        } catch (IOException e) {
            logger.warn("返回码文件[{}]不存在，使用初始化配置。", path);
        }
        return null;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }
}
