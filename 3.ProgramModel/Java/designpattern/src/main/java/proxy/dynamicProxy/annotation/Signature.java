package proxy.dynamicProxy.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *  @Author      :   AlanDing
 *  @Time        :   2019/11/25 下午4:25
 *  @File        :   Signature.java
 *  @Description :
 */

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Signature {
}
