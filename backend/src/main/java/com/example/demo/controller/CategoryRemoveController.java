package com.example.demo.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Category;
import com.example.demo.entity.LogDetail;
import com.example.demo.entity.TimeLog;
import com.example.demo.form.CategoryEditForm;
import com.example.demo.form.CategoryRegistForm;
import com.example.demo.form.CategoryRemoveForm;
import com.example.demo.form.TimeRegistForm;
import com.example.demo.security.UserDetailsImpl;
import com.example.demo.service.CategoryService;
import com.example.demo.service.TimeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CategoryRemoveController {

	private final CategoryService categoryService;
	
	private final TimeService timeService;

	/*--- タスク削除リクエスト（タスク詳細画面より） ---*/
	@PostMapping("/category-remove")
	public String remove(@AuthenticationPrincipal UserDetailsImpl principal,
			@ModelAttribute CategoryRemoveForm form,
			Model model,TimeRegistForm registForm) {
		
		categoryService.remove(form.getCategoryId());
		// タスク削除確認画面に遷移する
		List<Category> list = categoryService.findAll();

		LogDetail logDetail = timeService.findDetailByLogId(form.getLogId());
		CategoryRegistForm categoryRegistForm = new CategoryRegistForm();
		registForm.setToDay(registForm.getToDay());
		registForm.setMaxDay(registForm.getMaxDay());
		CategoryEditForm editForm = new CategoryEditForm();
		Category categoryEdit = new Category();

		List<TimeLog> timeLogList = timeService.findListAll(principal.getId());
		model.addAttribute("timeLogList", timeLogList);
		model.addAttribute("categoryList", list);
		model.addAttribute("categoryRegistForm", categoryRegistForm);
		model.addAttribute("timeRegistForm", registForm);
		model.addAttribute("categoryEditForm", editForm);
		model.addAttribute("category", categoryEdit);

		return "time-log";
	}
}
