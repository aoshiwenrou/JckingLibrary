package com.jcking.lib.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Jcking
 * @time 2019/3/3 16:47
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE})
public @interface EFModuleAnnotation {
    String moduleName();

    String delegateName();
}
