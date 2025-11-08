package com.example.demo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CategoryValidator implements ConstraintValidator<CategoryDate, Object> {

	private final CategoryService categoryService;

	private String categoryIdStr;

	private String message;
	
	private String category;

	@Override
	public void initialize(CategoryDate constraintAnnotation) {

		this.categoryIdStr = constraintAnnotation.categoryIdStr();
		this.message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {

		BeanWrapper beanWrapper = new BeanWrapperImpl(value);
		category = (String)beanWrapper.getPropertyValue(categoryIdStr);
		

		if (categoryIdStr != null) {
			Category categoryId = categoryService.findByCategoryId(category);

			if (categoryId == null) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(message)
						.addPropertyNode(categoryIdStr)
						.addConstraintViolation();
				return false;
			}

		}

		return true;

	}
}