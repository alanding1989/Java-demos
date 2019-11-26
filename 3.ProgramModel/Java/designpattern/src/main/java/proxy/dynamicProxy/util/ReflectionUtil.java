package proxy.dynamicProxy.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 *  @Author      :  AlanDing
 *  @Time        :  2019/11/25 下午8:06
 *  @File        :  proxy.dynamicProxy.util.ReflectionUtil.java
 *  @Description :
 */

public class ReflectionUtil {

	public static void processAnnotatedMethod(Object target,
											  BiConsumer<Method, Annotation> biConsumer) {
		Arrays.stream(target.getClass().getDeclaredMethods()).forEach(method -> {
			Annotation[] annotations = method.getAnnotations();
			if (annotations.length > 0){
				for (Annotation annotation : annotations) {
					biConsumer.accept(method, annotation);
				}
			}
		});
	}
}
