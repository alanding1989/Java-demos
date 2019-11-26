package proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import proxy.dynamicProxy.annotation.After;
import proxy.dynamicProxy.annotation.Before;
import proxy.dynamicProxy.annotation.Signature;
import proxy.dynamicProxy.util.Interceptor;
import proxy.dynamicProxy.util.ReflectionUtil;

/**
 *  Author      :   AlanDing
 *  Time        :   2019/11/2 下午2:49
 *  File        :   ProxyHandler.java
 *  Description :
 */

public class ProxyHandler implements InvocationHandler {
	private Object target;
	private final Interceptor interceptor;

	private List<Method> beforeMethods = new ArrayList<>();
	private List<Method> afterMethods = new ArrayList<>();


	private ProxyHandler(Object target, Interceptor interceptor) {
		this.target = target;
		this.interceptor = interceptor;

		processAspect();
	}

	private void processAspect() {
		// 遍历目标对象当前类所有方法，对特定注解标记的方法进行处理
		ReflectionUtil.processAnnotatedMethod(target, (method, annotation) -> {
			if (annotation instanceof Before) {
				beforeMethods.add(method);
			}
			if (annotation instanceof After) {
				afterMethods.add(method);
			}
		});
	}

	// 简单的工厂模式
	public static Object createProxy(Object target, Interceptor interceptor) {
		Class<?> type = target.getClass();
		Class<?>[] interfaces = type.getInterfaces();
		// 编写框架代码时，需要下面的代码
		// Map<Class<?>, List<Method>> signatureMap = getSignatureMap(target);
		// Class<?>[] interfaces = getAllInterfaces(type, signatureMap);

		return Proxy.newProxyInstance(
			type.getClassLoader(),
			interfaces,
			new ProxyHandler(target, interceptor));
	}


	private static Map<Class<?>, List<Method>> getSignatureMap(Object target) {
		Class<?> targetClass = target.getClass();
		Map<Class<?>, List<Method>> signatureMap = new HashMap<>();

		ReflectionUtil.processAnnotatedMethod(target, (method, annotation) -> {
			if (annotation instanceof Signature) {
				List<Method> methods = signatureMap.computeIfAbsent(targetClass,
																	k -> new ArrayList<>());
				methods.add(method);

				// 如果无法标记源码，只能在@Signature注解里指定要拦截的类方法，就用下面这段代码
				// try {
				// 	Method m = targetClass.getMethod(method.getName(), method.getParameterTypes());
				// 	methods.add(m);
				// } catch (NoSuchMethodException e) {
				// 	e.printStackTrace();
				// }
			}
		});

		return signatureMap;
	}


	private static Class<?>[]
	getAllInterfaces(Class<?> type, Map<Class<?>, List<Method>> signatureMap) {
		// 当无法对源码进行注释标记时(写框架时)，只能对拦截器指定需要拦截的接口方法，就需要这个方法来
		// 遍历需要被拦截的对象，可能这个接口方法不是这个对象的类实现的，所以要递归找父接口
		Set<Class<?>> interfaces = new HashSet<>();
		while (type != null) {
			for (Class<?> interf : type.getInterfaces()) {
				if (signatureMap.containsKey(interf)) {
					interfaces.add(interf);
				}
			}
			type = type.getSuperclass();
		}

		return interfaces.toArray(new Class<?>[0]);
	}


	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// 流程到这，需要被拦截对象的代理对象已经生成，也就是说无法选择拦截哪些对象了。
		// 这里只能对需要拦截的方法进行判断，筛选，或者筛选的流程交给具体的拦截器实现。

		// 实际的拦截目标对象方法的逻辑交给具体的拦截器去实现，所以这就是一个扩展点。
		// 因为组合优于继承，所以需要在被类中设置一个拦截器对象的字段属性，而不是继承接口。
		// 既然有字段，那就要在构造当前ProxyHandler的时候注入字段属性。
		// 因为构造ProxyHandler的时候传入与之对应的拦截器，所以是一一对应的关系。
		return interceptor.intercept(target, method, args);

		// for (Method m : beforeMethods) {
		// 	m.setAccessible(true);
		// 	System.out.println("This is a proxy!  beforeMethods =========");
		// 	m.invoke(target, args);
		// }
		//
		// System.out.println("\n---------------------------------\n");
		//
		// for (Method m : afterMethods) {
		// 	m.setAccessible(true);
		//
		// 	m.invoke(target, args);
		// 	System.out.println("This is a proxy! afterMethods =========");
		// }
		//
	}
}
