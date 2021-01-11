package com.lyb.mvc.anno;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Enumration {
    String[] options() default {};
    String value() default "";
}
