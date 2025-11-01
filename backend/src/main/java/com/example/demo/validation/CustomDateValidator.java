package com.example.demo.validation;

import java.time.LocalDateTime;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

@Component
public class CustomDateValidator implements ConstraintValidator<ValidDate, Object> {

	private String startTimeStr;

	private String endTimeStr;

	private String message;

	@Override
	public void initialize(ValidDate constraintAnnotation) {
		this.startTimeStr = constraintAnnotation.startTimeStr();
		this.endTimeStr = constraintAnnotation.endTimeStr();
		this.message = constraintAnnotation.message();
	}

	@Override
	    public boolean isValid(Object value, ConstraintValidatorContext context) {
	    	
	    	
	    	 BeanWrapper beanWrapper = new BeanWrapperImpl(value);
	         LocalDateTime startTime = (LocalDateTime) beanWrapper.getPropertyValue(startTimeStr);
	         LocalDateTime endTime = (LocalDateTime) beanWrapper.getPropertyValue(endTimeStr);
	         
	         if (startTime == null || endTime == null) {
	        	 
	        	 return true;
	         }
	         
	         if (startTime.isAfter(endTime)) {
	             // エラーメッセージをデフォルトのものから変更する場合
	             context.disableDefaultConstraintViolation();
	             context.buildConstraintViolationWithTemplate(message)
	                 .addPropertyNode(startTimeStr)
	                 .addConstraintViolation();
	             return false;
	         }
	        	 return true;
	         
	}
}