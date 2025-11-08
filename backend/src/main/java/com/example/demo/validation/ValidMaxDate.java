package com.example.demo.validation;



import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = CustomMaxDateValidator.class)
@Target({ ElementType.TYPE })
@Retention(RUNTIME)
public @interface ValidMaxDate {
    String message() default "開始時間は前回のログの終了時間より後になるように時刻を設定してください。";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
    String maxDayStr();
    String startTimeStr();
}