package com.example.demo.validation;



import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = CustomDateValidator.class)
@Target({ ElementType.TYPE })
@Retention(RUNTIME)
public @interface ValidDate {
    String message() default "開始時間は終了時間より前になるように設定してください。";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
    String startTimeStr();
    String endTimeStr();
}