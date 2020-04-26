package org.jpcl.resview.view.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * @author Administrator
 */
@Component
public class ResCodeInit implements ApplicationContextInitializer {

    private Logger logger = LoggerFactory.getLogger(ResCodeInit.class);

    @Autowired
    private Environment env;

    /**
     * 默认在classPath下
     */
    private final String DEF_PATH = "res-code.properties";

    /**
     * 容器启动前执行的方法
     * @param configurableApplicationContext
     */
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
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
                throw new IOException("文件不存在！");
            }
        } catch (IOException e) {
            logger.warn("返回code的配置文件不存在，使用默认值。");
        }
    }
}
