package com.example.demo.validation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = {UnusedNameValidator.class})
@Target({FIELD})
@Retention(RUNTIME)
public @interface UnusedName {
	
    String message() default "この名前は既に登録されています";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
    @Target({FIELD})
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        UnusedName[] value();
    }
	
}
