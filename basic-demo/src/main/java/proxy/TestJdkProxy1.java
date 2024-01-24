package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestJdkProxy1 implements InvocationHandler {

    private final Object target;

    public TestJdkProxy1(Object target) {
        this.target = target;
        System.out.println("TestJdkProxy1 constructor");
    }

    public static void main(String[] args) {
        NormalClass target = new NormalClass();
        Class<?>[] interfaces = NormalClass.class.getInterfaces();
        Normal normal = (Normal) Proxy.newProxyInstance(TestJdkProxy1.class.getClassLoader(),
                interfaces,
                new TestJdkProxy1(target));
        normal.a();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before invoke");
        return method.invoke(target, args);
    }
}
