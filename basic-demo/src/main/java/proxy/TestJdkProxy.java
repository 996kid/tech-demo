package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 996kid@gmail.com
 * @Description TestJdkProxy
 * @Date 2022/7/8 14:17
 */
public class TestJdkProxy implements InvocationHandler {


    public static void main(String[] args) {
        GrettingService grettingService = (GrettingService) Proxy.newProxyInstance(TestJdkProxy.class.getClassLoader(),
                 new Class[] {GrettingService.class},
                 new TestJdkProxy());
        grettingService.gretting("l3vi");
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("hello, " + args[0]);
        return null;
    }
}
