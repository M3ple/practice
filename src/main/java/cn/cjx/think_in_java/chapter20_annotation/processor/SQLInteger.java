//: annotations/database/SQLInteger.java
package cn.cjx.think_in_java.chapter20_annotation.processor;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLInteger {
  String name() default "";
  Constraints constraints() default @Constraints;
} ///:~
