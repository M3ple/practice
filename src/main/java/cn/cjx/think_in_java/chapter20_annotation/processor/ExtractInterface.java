//: annotations/ExtractInterface.java
// APT-based annotation processing.
package cn.cjx.think_in_java.chapter20_annotation.processor;
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface ExtractInterface {
  public String value();
} ///:~
