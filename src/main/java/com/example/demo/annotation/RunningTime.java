package com.example.demo.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


// 궁금하면 구글링!
@Target({ ElementType.TYPE, ElementType.METHOD}) // 어노테이션 적용대상
@Retention(RetentionPolicy.RUNTIME) // 어노테이션 유지기간
public @interface RunningTime {


}
