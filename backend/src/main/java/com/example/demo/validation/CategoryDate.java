package com.example.demo.validation;



import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = CategoryValidator.class)
@Target({ ElementType.TYPE })
@Retention(RUNTIME)
public @interface CategoryDate {
    String message() default "正しいカテゴリー名を入力してください。";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
    String categoryIdStr();
    
}