package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
public class CategoryRegistController {

	private final CategoryService categoryService;

	private final TimeService timeService;

	@PostMapping("/category-show-regist")
	public String showRegist(Model model, CategoryRegistForm form, TimeRegistForm registForm) {
		LogDetail logDetail = timeService.findDetailByLogId(form.getLogId());
		registForm.setToDay(registForm.getToDay());
		registForm.setMaxDay(registForm.getMaxDay());
		model.addAttribute("logDetail", logDetail);
		model.addAttribute("timeRegistForm", registForm);
		return "category-regist";
	}

	@PostMapping("/category-regist")
	public String regist(Model model, @Validated CategoryRegistForm form, BindingResult result,
			TimeRegistForm registForm, @AuthenticationPrincipal UserDetailsImpl principal) {

		if (result.hasErrors()) {
			List<TimeLog> TimeLogList = timeService.findListAll(principal.getId());

			model.addAttribute("timeLogList", TimeLogList);
			CategoryEditForm editForm = new CategoryEditForm();
			Category categoryEdit = new Category();
			List<Category> CategoryList = categoryService.findAll();
			model.addAttribute("categoryList", CategoryList);
			model.addAttribute("timeRegistForm", registForm);
			model.addAttribute("categoryErrorMessage", "error");
			model.addAttribute("categoryEditForm", editForm);
			model.addAttribute("category", categoryEdit);

			return "time-log";
		}

		Category category = new Category();
		category.setCategoryName(form.getCategoryName());
		categoryService.regist(category);

		LogDetail logDetail = timeService.findDetailByLogId(form.getLogId());

		List<Category> list = categoryService.findAll();

		registForm.setToDay(registForm.getToDay());
		registForm.setMaxDay(registForm.getMaxDay());
		LocalDate nowDay = LocalDate.now();
		registForm.setToDay(nowDay);
		List<TimeLog> timeLogList = timeService.findListAll(principal.getId());
		CategoryEditForm editForm = new CategoryEditForm();
		Category categoryEdit = new Category();
		model.addAttribute("timeLogList", timeLogList);
		model.addAttribute("logDetail", logDetail);
		model.addAttribute("categoryList", list);
		model.addAttribute("timeRegistForm", registForm);
		model.addAttribute("categoryEditForm", editForm);
		model.addAttribute("category", categoryEdit);

		return "time-log";
	}
}
