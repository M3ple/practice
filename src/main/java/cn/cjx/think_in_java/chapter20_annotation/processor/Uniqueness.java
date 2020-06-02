//: annotations/database/Uniqueness.java
// Sample of nested annotations
package cn.cjx.think_in_java.chapter20_annotation.processor;

public @interface Uniqueness {
  Constraints constraints()
    default @Constraints(unique=true);
} ///:~
