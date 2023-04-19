package proxy.cglib;


import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class DaoProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object object, Method method, Object[] objects, MethodProxy proxy) throws Throwable {
        if (method.getName().equals("update")) {
            System.out.println("Before Method Invoke");
            proxy.invokeSuper(object, objects);
            System.out.println("After Method Invoke");
        }

        return object;
    }
    
}