package proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Objects;

/**
 *  Author      :   AlanDing
 *  Time        :   2019/11/2 下午2:16
 *  File        :   ProxyClient.java
 *  Description :
 */

public class ProxyClient {
    public static void main(String[] args) {
        // JDK动态代理只能代理接口方法，无法代理类
        Subject proxy = (Subject) createProxy(RealSubject.class, new ProxyHandler(),
                                              Subject.class);
        proxy.showTheName();
    }

    private static Object createProxy(Class<?> clazz, InvocationHandler handler, Class<?>... infs) {
        Objects.requireNonNull(infs);
        Objects.requireNonNull(handler);
        ClassLoader classLoader = clazz.getClassLoader();
        return Proxy.newProxyInstance(classLoader, infs, handler);
    }
};

