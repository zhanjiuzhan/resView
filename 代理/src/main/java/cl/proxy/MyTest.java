package cl.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author Administrator
 */
public class MyTest {

    public static void main(String[] args) {
        StudentService ss = new StudentServiceImpl();
        InvocationHandler ih = new ProxyService(ss);
        StudentService sProxy = (StudentService) Proxy.newProxyInstance(ih.getClass().getClassLoader(), ss.getClass().getInterfaces(), ih);
        ss.add("chenglei");
        sProxy.get();
    }
}
