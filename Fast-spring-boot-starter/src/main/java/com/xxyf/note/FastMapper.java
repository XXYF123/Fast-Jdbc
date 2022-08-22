package com.xxyf.note;


import com.xxyf.mapper.FastSteater;
import com.xxyf.mapper.Mapper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER})
@Import(FastSteater.class)
@ComponentScan(basePackages = {"com.xxyf.mapper"}, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {Mapper.class}))
public @interface FastMapper {
    String[] value() default {};
}
