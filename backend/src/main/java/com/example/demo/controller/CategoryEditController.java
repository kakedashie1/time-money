package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Category;
import com.example.demo.entity.LogDetail;
import com.example.demo.form.CategoryEditForm;
import com.example.demo.form.TimeRegistForm;
import com.example.demo.service.CategoryService;
import com.example.demo.service.TimeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CategoryEditController {

	private final TimeService timeService;

	private final CategoryService categoryService;

	@PostMapping("/category-show-edit")
	public String showEdit(@ModelAttribute CategoryEditForm form, Model model,TimeRegistForm registForm) {
		Category category = categoryService.findByCategoryId(form.getCategoryId());
		LogDetail logDetail = timeService.findDetailByLogId(form.getLogId());
		registForm.setToDay(registForm.getToDay());
		registForm.setMaxDay(registForm.getMaxDay());
		model.addAttribute("logDetail", logDetail);
		model.addAttribute("category", category);
		model.addAttribute("timeRegistForm", registForm);
		return "category-edit";
	}

	@PostMapping("/category-edit")
	public String edit( Model model,@Validated CategoryEditForm form, BindingResult result,TimeRegistForm registForm) {
		
		if (result.hasErrors()) {
			Category category = categoryService.findByCategoryId(form.getCategoryId());
			LogDetail logDetail = timeService.findDetailByLogId(form.getLogId());
			registForm.setToDay(registForm.getToDay());
			registForm.setMaxDay(registForm.getMaxDay());
			model.addAttribute("logDetail", logDetail);
			model.addAttribute("category", category);
			model.addAttribute("timeRegistForm", registForm);

			return "category-edit";
		}

		

		Category category = new Category();
		category.setCategoryId(form.getCategoryId());
		category.setCategoryName(form.getCategoryName());

		categoryService.edit(category);



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
