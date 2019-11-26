package proxy.dynamicProxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import proxy.dynamicProxy.util.Interceptor;

/**
 *  @Author      :  AlanDing
 *  @Time        :  2019/11/26 上午12:28
 *  @File        :  MyInterceptor.java
 *  @Description :
 */

public class MyInterceptor implements Interceptor {

	@Override
	public Object intercept(Object target, Method method, Object[] args)
		throws InvocationTargetException, IllegalAccessException {

		// 拦截方法，进行额外逻辑处理
		if (method.getName().equals("fuckTheProxy")) {
			System.out.println("代码增强成功！\n");

			return method.invoke(target, args);
		}

		// 调用原始方法
		return method.invoke(target, args);
	}

}
