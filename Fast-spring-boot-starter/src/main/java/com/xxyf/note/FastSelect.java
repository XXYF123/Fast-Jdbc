package com.xxyf.note;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FastSelect {
    String value();

    String[] param() default {};

    String[] key() default {};

    String[] Value() default {};
}
