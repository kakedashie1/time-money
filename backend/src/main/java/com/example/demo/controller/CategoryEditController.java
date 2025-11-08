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
import com.example.demo.service.CategoryService;
import com.example.demo.service.TimeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CategoryEditController {

	private final TimeService timeService;

	private final CategoryService categoryService;

	@PostMapping("/category-show-edit")
	public String showEdit(@ModelAttribute CategoryEditForm form, Model model) {
		Category category = categoryService.findByCategoryId(form.getCategoryId());
		LogDetail logDetail = timeService.findDetailByLogId(form.getLogId());
		model.addAttribute("logDetail", logDetail);
		model.addAttribute("category", category);
		return "category-edit";
	}

	@PostMapping("/category-edit")
	public String edit( Model model,@Validated CategoryEditForm form, BindingResult result) {
		
		if (result.hasErrors()) {
			Category category = categoryService.findByCategoryId(form.getCategoryId());
			LogDetail logDetail = timeService.findDetailByLogId(form.getLogId());
			model.addAttribute("logDetail", logDetail);
			model.addAttribute("category", category);

			return "category-edit";
		}

		

		Category category = new Category();
		category.setCategoryId(form.getCategoryId());
		category.setCategoryName(form.getCategoryName());

		categoryService.edit(category);



		List<Category> list = categoryService.findAll();

		LogDetail logDetail = timeService.findDetailByLogId(form.getLogId());

		model.addAttribute("logDetail", logDetail);

		model.addAttribute("categoryList", list);

		return "category";
	}
}
