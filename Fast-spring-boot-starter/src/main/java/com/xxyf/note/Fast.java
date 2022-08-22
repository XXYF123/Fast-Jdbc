package com.xxyf.note;

import java.lang.annotation.*;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER})
public @interface Fast {
}
