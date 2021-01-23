package cl.proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Administrator
 */
public class ProxyService implements InvocationHandler {

    private Object object;

    public ProxyService(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy begin start...");
        Object obj = method.invoke(object, args);
        System.out.println("proxy begin end...");
        return obj;
    }
}
