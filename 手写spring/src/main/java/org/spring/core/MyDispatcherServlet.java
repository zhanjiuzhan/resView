package org.spring.core;

import com.alibaba.fastjson.JSON;
import org.spring.core.annotation.MyAutowired;
import org.spring.core.annotation.MyComponent;
import org.spring.core.annotation.MyController;
import org.spring.core.annotation.MyRequestMapping;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.Introspector;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author Administrator
 */
@WebServlet(name = "mySpring", urlPatterns = "/my/*")
public class MyDispatcherServlet extends HttpServlet {

    private final static String PROPERTIES_PATH = "application.properties";
    private final static String COMPONENT_SCAN = "package-path";
    private Properties configProperties = new Properties();
    private List<String> classNames = new ArrayList<>();
    private Map<String, Object> ioc = new HashMap<>();
    private Map<String, MethodHandler> methodHandlerMap = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI().substring(3);
        MethodHandler handler = methodHandlerMap.get(url);
        if (handler != null && handler.getType().equals(req.getMethod())) {
            try {
                Object result = handler.getMethod().invoke(handler.getObject());
                String res = JSON.toJSONString(result);
                resp.setContentType("application/json; charset=utf-8");
                resp.setContentLength(res.getBytes("UTF-8").length);
                Writer out = resp.getWriter();
                out.write(res);
                out.flush();
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("do post");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 加载配置文件
        doLoadConfig();
        // 扫描bean
        doScanner(configProperties.getProperty(COMPONENT_SCAN));
        // Bean实例化
        doInstance();
        // 依赖注入
        doAutowired();
        // handlerMapping
        initHandlerMapping();
    }

    private void initHandlerMapping() {
        for (Object instance : ioc.values()) {
            if(!instance.getClass().isAnnotationPresent(MyController.class)) {
                continue;
            }
            Method[] methods = instance.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.getModifiers() == 1 && method.isAnnotationPresent(MyRequestMapping.class)) {
                    MyRequestMapping requestMapping = method.getAnnotation(MyRequestMapping.class);
                    MethodHandler tmpMethod = new MethodHandler(requestMapping.url(), requestMapping.type(), method, instance);
                    methodHandlerMap.put(requestMapping.url(), tmpMethod);
                }
            }
        }
    }

    private void doAutowired() {
        for (Object instance : ioc.values()) {
            Class clazz = instance.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if(!field.isAnnotationPresent(MyAutowired.class)) {
                    continue;
                }
                try {
                    field.setAccessible(true);
                    field.set(instance, ioc.getOrDefault(field.getName(), null));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void doInstance() {
        for (String className : classNames) {
            try {
                Class tmpClass = Class.forName(className);
                if (tmpClass.isAnnotationPresent(MyComponent.class)) {
                    MyComponent component = (MyComponent) tmpClass.getAnnotation(MyComponent.class);
                    String val = component.value();
                    String beanName = val;
                    if (StringUtils.isEmpty(val)) {
                        beanName = Introspector.decapitalize(tmpClass.getSimpleName());
                    }
                    ioc.put(beanName, tmpClass.newInstance());
                }
                if (tmpClass.isAnnotationPresent(MyController.class)) {
                    ioc.put(Introspector.decapitalize(tmpClass.getSimpleName()), tmpClass.newInstance());
                }
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 扫描 MyComponent 添加到容器中
     * @param packagePath
     */
    private void doScanner(String packagePath) {
        Assert.notNull(packagePath, "扫描的路径不能是无效的!");
        PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        try {
            Resource[] resources = resourcePatternResolver.getResources(packagePath.replaceAll("\\.", "/") + "/**/*.class");
            for (Resource resource : resources) {
                String path = resource.getFile().getPath();
                String absoluteClassName = path.substring(path.indexOf("classes") + 8, path.lastIndexOf(".class"));
                classNames.add(absoluteClassName.replaceAll("\\\\", "."));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取配置文件中的信息
     */
    private void doLoadConfig() {
        try (InputStream in = Objects.requireNonNull(ClassUtils.getDefaultClassLoader()).getResourceAsStream(PROPERTIES_PATH)) {
            if (in != null) {
                configProperties.load(in);
            }
        } catch (IOException e) {
            System.err.println("读取配置文件异常!");
            e.printStackTrace();
        }
    }

    private static class MethodHandler {
        private String url;
        private String type;
        private Method method;
        private Object object;

        public MethodHandler(String url, String type, Method field, Object object) {
            this.url = url;
            this.type = type;
            this.method = field;
            this.object = object;
        }

        public String getUrl() {
            return url;
        }

        public String getType() {
            return type;
        }

        public Method getMethod() {
            return method;
        }

        public Object getObject() {
            return object;
        }
    }
}
