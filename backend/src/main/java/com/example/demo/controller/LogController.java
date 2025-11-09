package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Log;
import com.example.demo.entity.LogDetail;
import com.example.demo.entity.TimeLog;
import com.example.demo.form.TimeDetailForm;
import com.example.demo.form.TimeRegistForm;
import com.example.demo.service.TimeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LogController {

	private final TimeService timeService;

	private LocalDate nowDay = LocalDate.now();

	@GetMapping("/login")
	private String login() {
		return "login";
	}

	@GetMapping("/top")
	private String showListSelection(Model model) {
		// HTMLテンプレート名で return
		List<TimeLog> list = timeService.findListAll();
		nowDay = LocalDate.now();
		TimeRegistForm form = new TimeRegistForm();
		form.setToDay(nowDay);
		model.addAttribute("timeLogList", list);
		model.addAttribute("timeRegistForm", form);
		return "time-log";
	}

	@PostMapping("/time-log")
	private String showLogList(Model model) {
		nowDay = LocalDate.now();
		List<TimeLog> list = timeService.findListAll();
		TimeRegistForm form = new TimeRegistForm();
		form.setToDay(nowDay);
		model.addAttribute("timeLogList", list);
		model.addAttribute("timeRegistForm", form);
		return "time-log";
	}

	@PostMapping("/log-detail")
	private String logDetail(Model model, TimeDetailForm form) {

		String correctId;

		LogDetail logDetail = timeService.findDetailByLogId(form.getLogId());

		Log maxLogId = timeService.maxLogId(nowDay);

		Integer maxId = maxLogId.getLogId();

		Integer logId = logDetail.getLogId();

		if (maxId == logId) {

			correctId = "true";
			model.addAttribute("correctId",correctId );
		}

		model.addAttribute("logDetail", logDetail);
		

		return "log-detail";
	}

	@PostMapping("/next-day")
	private String nextDay(Model model) {
		nowDay = nowDay.plusDays(1);
		List<TimeLog> nextDay = timeService.findByNowDay(nowDay);
		TimeRegistForm form = new TimeRegistForm();
		form.setToDay(nowDay);
		model.addAttribute("timeLogList", nextDay);
		model.addAttribute("timeRegistForm", form);

		return "time-log";
	}

	@PostMapping("/prev-day")
	private String prevDay(Model model) {
		nowDay = nowDay.plusDays(-1);
		List<TimeLog> prevDay = timeService.findByNowDay(nowDay);
		TimeRegistForm form = new TimeRegistForm();
		form.setToDay(nowDay);
		model.addAttribute("timeLogList", prevDay);
		model.addAttribute("timeRegistForm", form);

		return "time-log";
	}
}
