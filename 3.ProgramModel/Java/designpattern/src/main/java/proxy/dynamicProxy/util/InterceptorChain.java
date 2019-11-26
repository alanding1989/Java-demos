package proxy.dynamicProxy.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *  @Author      :  AlanDing
 *  @Time        :  2019/11/25 下午10:48
 *  @File        :  InterceptorChain.java
 *  @Description :
 */

public class InterceptorChain {
	private List<Interceptor> interceptors = new ArrayList<>();

	public Object createProxy(Object target) {
		for (Interceptor interceptor : interceptors) {
			// 在这一步就需要决定具体要对哪些对象生成代理对象。
			// 本来应该直接调用 ProxyHandler.createProxy(target, interceptor)生成代理对象。
			// 但考虑到具体的拦截器实现可能想自己决定要拦截哪些对象(其实要拦截的对象已经在注解中指定了)。
			// 所以将其封装并抽取到 Interceptor 接口中，Interceptor 可以重写，也可以使用接口默认实现。
			// target = ProxyHandler.createProxy(target, interceptor);

			target = interceptor.enhance(target);
		}
		return target;
	}

	public void addInterceptors(Interceptor interceptor) {
		interceptors.add(interceptor);
	}

	public List<Interceptor> getInterceptors() {
		return Collections.unmodifiableList(interceptors);
	}
}
