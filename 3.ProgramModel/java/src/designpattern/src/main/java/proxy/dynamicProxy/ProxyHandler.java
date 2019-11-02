package proxy.dynamicProxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import proxy.dynamicProxy.annotation.After;
import proxy.dynamicProxy.annotation.Before;
import proxy.dynamicProxy.annotation.MyAspect;

/**
 *  Author      :   AlanDing
 *  Time        :   2019/11/2 下午2:49
 *  File        :   ProxyHandler.java
 *  Description :
 */

public class ProxyHandler implements InvocationHandler {
    private List<Method> beforeMethods = new ArrayList<>();
    private List<Method> afterMethods = new ArrayList<>();

    private RealSubject rsub = new RealSubject();

    ProxyHandler() {
        // 把rsub 换成bean自动注入，就可以模拟spring
        // 获得被 MyAspect 标注的注解类，并取得该类所有的方法，不包括继承的方法
        Class realsub = rsub.getClass().getAnnotation(MyAspect.class).value();
        Stream<Method> methods = Arrays.stream(realsub.getDeclaredMethods());

        // 遍历所有方法，将被Before和After分别标记的方法放到对应的列表里
        methods.forEach(m -> {
            Annotation[] annotations = m.getAnnotations();
            if (annotations.length != 0) {
                for (Annotation a : annotations) {
                    if (a instanceof Before) {
                        beforeMethods.add(m);
                    } else if (a instanceof After) {
                        afterMethods.add(m);
                    }
                }
            }
        });
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        for (Method m : beforeMethods) {
            m.setAccessible(true);
            m.invoke(rsub);
        }

        System.out.println("This is proxy!");

        for (Method m : afterMethods) {
            m.setAccessible(true);
            m.invoke(rsub);
        }
        return proxy;
    }
}
