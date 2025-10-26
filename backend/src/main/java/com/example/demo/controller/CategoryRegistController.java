package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Category;
import com.example.demo.entity.LogDetail;
import com.example.demo.form.CategoryRegistForm;
import com.example.demo.service.CategoryService;
import com.example.demo.service.TimeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CategoryRegistController {

	private final CategoryService categoryService;
	
	private final TimeService timeService;

	@PostMapping("/category-show-regist")
	public String showRegist(Model model,CategoryRegistForm form) {
		LogDetail logDetail = timeService.findDetailByLogId(form.getLogId());
		model.addAttribute("logDetail", logDetail);
		return "category-regist";
	}

	@PostMapping("/category-regist")
	public String regist(@Validated Model model, CategoryRegistForm form, BindingResult result) {

		if (result.hasErrors()) {

			return "category-regist";
		}

		Category category = new Category();
		category.setCategoryName(form.getCategoryName());
		categoryService.regist(category);
		
		LogDetail logDetail = timeService.findDetailByLogId(form.getLogId());
		
		List<Category> list = categoryService.findAll();
		
		model.addAttribute("logDetail", logDetail);
		model.addAttribute("categoryList", list);
		
		

		return "category";
	}
}
