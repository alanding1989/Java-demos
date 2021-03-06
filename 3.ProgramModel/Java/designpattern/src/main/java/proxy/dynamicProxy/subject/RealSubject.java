package proxy.dynamicProxy.subject;

import proxy.dynamicProxy.annotation.After;
import proxy.dynamicProxy.annotation.Before;
import proxy.dynamicProxy.annotation.MyAspect;
import proxy.dynamicProxy.annotation.Signature;

/**
 *  Author      :   AlanDing
 *  Time        :   2019/11/2 下午2:19
 *  File        :   RealSubject.java
 *  Description :
 */

@MyAspect(RealSubject.class)
public class RealSubject implements Subject {

	@Before
	@Override
	public void showTheName() {
		System.out.println("This is real subject!");
	}

	@After
	@Override
	@Signature
	public void fuckTheProxy() {
		System.out.println("I`m fucking the dynamicProxy!");
	}

}
