package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Category;
import com.example.demo.entity.LogDetail;
import com.example.demo.entity.TimeLog;
import com.example.demo.form.CategoryEditForm;
import com.example.demo.form.CategoryRegistForm;
import com.example.demo.form.TimeRegistForm;
import com.example.demo.security.UserDetailsImpl;
import com.example.demo.service.CategoryService;
import com.example.demo.service.TimeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CategoryEditController {

	private final TimeService timeService;

	private final CategoryService categoryService;

	@PostMapping("/category-show-edit")
	public String showEdit(@ModelAttribute CategoryEditForm form, Model model,TimeRegistForm registForm, @AuthenticationPrincipal UserDetailsImpl principal) {
		Category category = categoryService.findByCategoryId(form.getCategoryId());
		LogDetail logDetail = timeService.findDetailByLogId(form.getLogId());
		registForm.setToDay(registForm.getToDay());
		registForm.setMaxDay(registForm.getMaxDay());
		List<TimeLog> TimeLogList = timeService.findListAll(principal.getId());
		
		CategoryRegistForm categoryRegistForm = new CategoryRegistForm();
		List<Category> CategoryList = categoryService.findAll();
		model.addAttribute("timeLogList", TimeLogList);
		model.addAttribute("logDetail", logDetail);
		model.addAttribute("category", category);
		model.addAttribute("timeRegistForm", registForm);
		
		model.addAttribute("categoryRegistForm",categoryRegistForm);
		model.addAttribute("categoryList", CategoryList);
		model.addAttribute("categoryEditMode", "categoryEditMode");
		return "time-log";
	}

	@PostMapping("/category-edit")
	public String edit(Model model, @Validated CategoryEditForm form, BindingResult result, TimeRegistForm registForm,@AuthenticationPrincipal UserDetailsImpl principal) {

		if (result.hasErrors()) {
			Category category = categoryService.findByCategoryId(form.getCategoryId());
			LogDetail logDetail = timeService.findDetailByLogId(form.getLogId());
			registForm.setToDay(registForm.getToDay());
			registForm.setMaxDay(registForm.getMaxDay());
			List<TimeLog> TimeLogList = timeService.findListAll(principal.getId());
			
			CategoryRegistForm categoryRegistForm = new CategoryRegistForm();
			List<Category> CategoryList = categoryService.findAll();
			model.addAttribute("logDetail", logDetail);
			model.addAttribute("category", category);
			model.addAttribute("timeRegistForm", registForm);
			
			model.addAttribute("categoryRegistForm",categoryRegistForm);
			model.addAttribute("categoryList", CategoryList);
			model.addAttribute("categoryEditMode", "categoryEditMode");
			model.addAttribute("timeLogList", TimeLogList);
			return "time-log";
		}

		Category category = new Category();
		category.setCategoryId(form.getCategoryId());
		category.setCategoryName(form.getCategoryName());

		categoryService.edit(category);

		List<Category> list = categoryService.findAll();

		LogDetail logDetail = timeService.findDetailByLogId(form.getLogId());

		registForm.setToDay(registForm.getToDay());
		registForm.setMaxDay(registForm.getMaxDay());
		LocalDate nowDay = LocalDate.now();
		registForm.setToDay(nowDay);
		
		List<TimeLog> TimeLogList = timeService.findListAll(principal.getId());
		CategoryEditForm editForm = new CategoryEditForm();
		CategoryRegistForm categoryRegistForm = new CategoryRegistForm();
		List<Category> CategoryList = categoryService.findAll();
		Category categoryForm = new Category();
		model.addAttribute("logDetail", logDetail);

		model.addAttribute("categoryList", list);

		model.addAttribute("timeRegistForm", registForm);
		model.addAttribute("timeLogList", TimeLogList);
		model.addAttribute("categoryEditForm", editForm);
		model.addAttribute("categoryRegistForm",categoryRegistForm);
		model.addAttribute("categoryList", CategoryList);
		model.addAttribute("category", categoryForm);
		model.addAttribute("categoryEditMode", null);
		model.addAttribute("categoryEditedMode", "categoryEdited");

		return "time-log";
	}
}
