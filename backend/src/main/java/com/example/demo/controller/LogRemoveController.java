package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Category;
import com.example.demo.entity.Day;
import com.example.demo.entity.TimeLog;
import com.example.demo.form.CategoryEditForm;
import com.example.demo.form.CategoryRegistForm;
import com.example.demo.form.LogRemoveForm;
import com.example.demo.form.TimeRegistForm;
import com.example.demo.security.UserDetailsImpl;
import com.example.demo.service.TimeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LogRemoveController {

	private final TimeService timeService;

	/*--- タスク削除リクエスト（タスク詳細画面より） ---*/
	@PostMapping("/log-remove")
	public String remove(@AuthenticationPrincipal UserDetailsImpl principal,
			@ModelAttribute LogRemoveForm removeForm, TimeRegistForm form,
			Model model) {
		LocalDate nowDay = LocalDate.now();
		timeService.remove(removeForm.getLogId());
		// タスク削除確認画面に遷移する

		Day day = new Day();

		day.setNowDay(nowDay);
		day.setUserId(principal.getId());
		List<TimeLog> list = timeService.findListAll(principal.getId());
		CategoryRegistForm categoryRegistForm = new CategoryRegistForm();
		CategoryEditForm categoryEditForm = new CategoryEditForm();
		Category categoryEdit = new Category();
		List<TimeLog> TimeLogList = timeService.findByNowDay(day);
		form.setToDay(nowDay);
		model.addAttribute("timeLogList", TimeLogList);
		model.addAttribute("today", nowDay);
		model.addAttribute("categoryList", list);
		model.addAttribute("timeRegistForm", form);
		model.addAttribute("categoryRegistForm",categoryRegistForm);
		model.addAttribute("categoryEditForm", categoryEditForm);
		model.addAttribute("category", categoryEdit);
		return "time-log";
	}

}
