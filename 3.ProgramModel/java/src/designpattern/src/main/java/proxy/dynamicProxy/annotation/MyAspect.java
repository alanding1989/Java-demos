package proxy.dynamicProxy.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *  Author      :   AlanDing
 *  Time        :   2019/11/2 下午3:25
 *  File        :   before.java
 *  Description :
 */

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAspect {
    Class<?> value();
}

