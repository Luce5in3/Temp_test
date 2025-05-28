package Dynamic_proxies;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Hander implements InvocationHandler {
    private Object target;

    public Hander(Object target) {
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before invoking method"+method.getName());
        Object result = method.invoke(target, args);
        System.out.println("After invoking method"+method.getName());
        return result;
    }
}
