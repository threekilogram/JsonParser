package com.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wuxio 2018-05-14:16:45
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface GenerateParser {
}
