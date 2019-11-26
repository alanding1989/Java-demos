package proxy.dynamicProxy;

import proxy.dynamicProxy.subject.RealSubject;
import proxy.dynamicProxy.subject.Subject;
import proxy.dynamicProxy.util.InterceptorChain;

/**
 *  Author      :   AlanDing
 *  Time        :   2019/11/2 下午2:16
 *  File        :   ProxyClient.java
 *  Description :
 */

// JDK动态代理只能代理接口方法，无法代理类
public class ProxyClient {
	private static InterceptorChain interceptorChain = new InterceptorChain();

	static {
		// 修改系统参数之后可以看到com.sun.proxy文件夹下生成了$Proxy.class的代理文件
		// System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

		// 初始化环境及配置
		interceptorChain.addInterceptors(new MyInterceptor());
	}

	public static void main(String[] args) {

		newSubject().fuckTheProxy();

	}


	// 封装具体拦截流程，返回一个被拦截实例
	private static Subject newSubject() {
		RealSubject target = new RealSubject();

		return (Subject) interceptorChain.createProxy(target);
	}
};

