package com.example.demo.validation;

import java.time.LocalDateTime;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

@Component
public class CustomMaxDateValidator implements ConstraintValidator<ValidMaxDate, Object> {

	private String maxDayStr;

	private String startTimeStr;

	private String message;
	
	

	@Override
	public void initialize(ValidMaxDate constraintAnnotation) {
		this.maxDayStr = constraintAnnotation.maxDayStr();
		this.startTimeStr = constraintAnnotation.startTimeStr();
		this.message = constraintAnnotation.message();
	}

	@Override
	    public boolean isValid(Object value, ConstraintValidatorContext context) {
	    	
	    	
	    	 BeanWrapper beanWrapper = new BeanWrapperImpl(value);
	         LocalDateTime maxDay = (LocalDateTime) beanWrapper.getPropertyValue(maxDayStr);
	         LocalDateTime startTime = (LocalDateTime) beanWrapper.getPropertyValue(startTimeStr);
	         
	         if (maxDay == null || startTime == null) {
	        	 
	        	 return true;
	         }
	         
//	         if (startTime.equals(endTime)) {
//	        	 context.disableDefaultConstraintViolation();
//	             context.buildConstraintViolationWithTemplate(message)
//	                 .addPropertyNode(startTimeStr)
//	                 .addConstraintViolation();
//	        	 return false;
//	         }
	         if (maxDay.isAfter(startTime)) {
	             // エラーメッセージをデフォルトのものから変更する場合
	             context.disableDefaultConstraintViolation();
	             context.buildConstraintViolationWithTemplate(message)
	                 .addPropertyNode(maxDayStr)
	                 .addConstraintViolation();
	             return false;
	         }
	        	 return true;
	         
	}
}