package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Category;
import com.example.demo.entity.LogDetail;
import com.example.demo.form.CategoryRemoveForm;
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
	public String remove(
			@ModelAttribute CategoryRemoveForm form,
			Model model) {
		
		categoryService.remove(form.getCategoryId());
		// タスク削除確認画面に遷移する
		List<Category> list = categoryService.findAll();

		LogDetail logDetail = timeService.findDetailByLogId(form.getLogId());

		model.addAttribute("logDetail", logDetail);

		model.addAttribute("categoryList", list);

		return "category";
	}
}
