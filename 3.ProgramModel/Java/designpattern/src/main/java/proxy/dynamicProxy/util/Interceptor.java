package proxy.dynamicProxy.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;
import proxy.dynamicProxy.ProxyHandler;

/**
 *  @Author      :   AlanDing
 *  @Time        :   2019/11/25 下午10:42
 *  @File        :   Interceptor.java
 *  @Description :
 */

public interface Interceptor {
	// 实际方法拦截调用由子类实现
	Object intercept(Object proxy, Method method, Object[] args)
		throws InvocationTargetException, IllegalAccessException;

	// 封装生成代理对象方法
	default Object enhance(Object target) {
		return ProxyHandler.createProxy(target, this);
	}

	default void setProperties(Properties properties) {

	}
}
