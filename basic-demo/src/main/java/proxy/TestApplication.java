package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName proxy.TestApplication
 * @Description proxy.TestApplication
 * @Author 996kid
 * @Date 2020/5/12 11:12
 */
public class TestApplication implements InvocationHandler {

    private Object target;

    public Object getProxyInstance(Object object) {
        this.target = object;
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),
                object.getClass().getInterfaces(),
                this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object object = method.invoke(target, args);
        System.out.println("after");
        return object;
    }


    public static void main(String[] args) {
        SpeakerImpl speaker = new SpeakerImpl();
        Speaker speakerEnhance = (Speaker) new TestApplication().getProxyInstance(speaker);
        speakerEnhance.announce();
    }
}

class SpeakerImpl implements Speaker {

    @Override
    public void announce() {
        System.out.println("SpeakerImpl");
    }
}