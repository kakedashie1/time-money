package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.TimeLog;
import com.example.demo.form.LogRemoveForm;
import com.example.demo.service.TimeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LogRemoveController {

	private final TimeService timeService;

	/*--- タスク削除リクエスト（タスク詳細画面より） ---*/
	@PostMapping("/log-remove")
	public String remove(
			@ModelAttribute LogRemoveForm form,
			Model model) {
		LocalDate nowday = LocalDate.now();
		timeService.remove(form.getLogId());
		// タスク削除確認画面に遷移する
		List<TimeLog> list = timeService.findListAll();

		List<TimeLog> TimeLogList = timeService.findListAll();

		model.addAttribute("timeLogList", TimeLogList);
		model.addAttribute("today", nowday);
		model.addAttribute("categoryList", list);

		return "time-log";
	}

}
