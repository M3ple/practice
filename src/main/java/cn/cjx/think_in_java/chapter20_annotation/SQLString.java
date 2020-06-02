package cn.cjx.think_in_java.chapter20_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @功能描述:
 * @使用对象:xx系统
 * @接口版本:
 * @创建日期: 2019/5/27 13:42
 * @创建人:陈俊旋
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLString {
    String name() default "";
}
