//: annotations/database/DBTable.java
package cn.cjx.think_in_java.chapter20_annotation.processor;
import java.lang.annotation.*;

@Target(ElementType.TYPE) // Applies to classes only
@Retention(RetentionPolicy.RUNTIME)
public @interface DBTable {
  public String name() default "";
} ///:~
