package com.eastwood.design.pattern.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName: DynamicProxy
 * @Description: TODO
 * @Author: yyh
 * @Date: 2023/4/19 14:05
 */
public class DynamicProxy implements InvocationHandler {

    private Object proxiedObject;

    public DynamicProxy(Object proxiedObject) {
        this.proxiedObject = proxiedObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("begin.");
        // 调用被代理的类的方法
        method.invoke(proxiedObject, args);
        System.out.println("end.");
        return null;
    }

    public static void main(String[] args) {
        Simple simpleProxy = (Simple) Proxy.newProxyInstance(
                DynamicProxy.class.getClassLoader(),
                SimpleImpl.class.getInterfaces(),
                new DynamicProxy(new SimpleImpl()));
        simpleProxy.doThings();
    }
}
