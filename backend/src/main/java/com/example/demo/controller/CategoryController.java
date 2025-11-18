package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Category;
import com.example.demo.entity.LogDetail;
import com.example.demo.form.TimeDetailForm;
import com.example.demo.form.TimeRegistForm;
import com.example.demo.service.CategoryService;
import com.example.demo.service.TimeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryService categoryService;
	
	private final TimeService timeService;

	@PostMapping("/category-list")
	private String showCategory(Model model ,TimeDetailForm form,TimeRegistForm registForm) {

		List<Category> list = categoryService.findAll();

		LogDetail logDetail = timeService.findDetailByLogId(form.getLogId());
		
		registForm.setToDay(registForm.getToDay());
		registForm.setMaxDay(registForm.getMaxDay());

		model.addAttribute("logDetail", logDetail);

		model.addAttribute("categoryList", list);
		
		
		model.addAttribute("timeRegistForm", registForm);

		return "category";
	}
}
