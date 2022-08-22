package com.xxyf.note;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author 小小怡飞
 * @Date 2022/7/31 12:26
 * @Version JDK 8
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FastInsert {
    String value();

    String[] param() default {};
}
