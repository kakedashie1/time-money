package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Category;
import com.example.demo.entity.Day;
import com.example.demo.entity.Log;
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
public class LogController {

	private final TimeService timeService;
	
	private final CategoryService categoryService;

	private LocalDate nowDay = LocalDate.now();

	//	@GetMapping("/login")
	//	private String login() {
	//		return "login";
	//	}

	@GetMapping("/")
	private String showListSelection(@AuthenticationPrincipal UserDetailsImpl principal, Model model) {
		// HTMLテンプレート名で return
		List<TimeLog> list = timeService.findListAll(principal.getId());
		List<Category> categoryList = categoryService.findAll();
		nowDay = LocalDate.now();
		TimeRegistForm form = new TimeRegistForm();
		form.setToDay(nowDay);
		form.setUserId(principal.getUsername());
		CategoryRegistForm registForm = new CategoryRegistForm();
		CategoryEditForm editForm = new CategoryEditForm();
		Category category = new Category();
		model.addAttribute("timeLogList", list);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("timeRegistForm", form);
		model.addAttribute("categoryRegistForm", registForm);
		model.addAttribute("categoryEditForm", editForm);
		model.addAttribute("category", category);
		return "time-log";
	}

	@GetMapping("/top")
	private String top(@AuthenticationPrincipal UserDetailsImpl principal, Model model) {
		// HTMLテンプレート名で return
		
		List<TimeLog> list = timeService.findListAll(principal.getId());
		List<Category> categoryList = categoryService.findAll();
		nowDay = LocalDate.now();
		TimeRegistForm form = new TimeRegistForm();
		form.setToDay(nowDay);
		form.setUserId(principal.getUsername());
		model.addAttribute("timeLogList", list);
		model.addAttribute("timeRegistForm", form);
		model.addAttribute("categoryList", categoryList);
		return "time-log";
	}

	@PostMapping("/time-log")
	private String showLogList(@AuthenticationPrincipal UserDetailsImpl principal, Model model) {
		nowDay = LocalDate.now();
		List<TimeLog> list = timeService.findListAll(principal.getId());
		List<Category> categoryList = categoryService.findAll();
		TimeRegistForm form = new TimeRegistForm();
		form.setToDay(nowDay);
		form.setUserId(principal.getUsername());
		model.addAttribute("timeLogList", list);
		model.addAttribute("timeRegistForm", form);
		model.addAttribute("categoryList", categoryList);
		return "time-log";
	}

	@PostMapping("/log-detail")
	private String logDetail(@AuthenticationPrincipal UserDetailsImpl principal, Model model, TimeRegistForm form) {

		String correctId;

		form.setToDay(form.getToDay());
		form.setUserId(principal.getUsername());

		LocalDate toDay = LocalDate.now();

		LogDetail logDetail = timeService.findDetailByLogId(form.getLogId());

		Log maxLogId = timeService.maxLogId(nowDay);

		Integer maxId = maxLogId.getLogId();

		Integer logId = logDetail.getLogId();

		if (maxId.equals(logId) && toDay.isEqual(nowDay)) {

			correctId = "true";
			model.addAttribute("correctId", correctId);
		}

		model.addAttribute("logDetail", logDetail);
		model.addAttribute("timeRegistForm", form);

		return "log-detail";
	}

	@PostMapping("/next-day")
	private String nextDay(@AuthenticationPrincipal UserDetailsImpl principal, Model model) {
		nowDay = nowDay.plusDays(1);
		Day day = new Day();
		

		day.setNowDay(nowDay);
		day.setUserId(principal.getId());
		List<TimeLog> nextDay = timeService.findByNowDay(day);
		List<Category> categoryList = categoryService.findAll();
		TimeRegistForm form = new TimeRegistForm();
		form.setToDay(nowDay);
		form.setUserId(principal.getUsername());
		model.addAttribute("timeLogList", nextDay);
		model.addAttribute("timeRegistForm", form);
		model.addAttribute("categoryList", categoryList);

		return "time-log";
	}

	@PostMapping("/prev-day")
	private String prevDay(@AuthenticationPrincipal UserDetailsImpl principal, Model model) {
		nowDay = nowDay.plusDays(-1);
		Day day = new Day();

		day.setNowDay(nowDay);
		day.setUserId(principal.getId());
		List<TimeLog> prevDay = timeService.findByNowDay(day);
		List<Category> categoryList = categoryService.findAll();
		TimeRegistForm form = new TimeRegistForm();
		form.setToDay(nowDay);
		form.setUserId(principal.getUsername());
		model.addAttribute("timeLogList", prevDay);
		model.addAttribute("timeRegistForm", form);

		return "time-log";
	}
}
