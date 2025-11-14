package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.TimeLog;
import com.example.demo.form.TimeRegistForm;
import com.example.demo.service.CategoryService;
import com.example.demo.service.TimeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CommonController {

	
	
	private final TimeService timeService;
	
	private final CategoryService categoryService;
	/*--- 完了後のリダイレクト先（タスク更新系） ---*/
	@GetMapping("/time-complete")
	private String completeTime(Model model,@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDate toDay) {
		
		
		List<TimeLog> list = timeService.findListAll();

		List<TimeLog> TimeLogList = timeService.findByNowDay(toDay);
		
		TimeRegistForm form = new TimeRegistForm();

		form.setToDay(toDay);

		model.addAttribute("timeLogList", TimeLogList);
		model.addAttribute("timeRegistForm", form);
		model.addAttribute("categoryList", list);

		return "time-log";

	}

}
