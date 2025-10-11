package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.LogDetail;
import com.example.demo.entity.TimeLog;
import com.example.demo.form.TimeDetailForm;
import com.example.demo.service.TimeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LogController {
	
	private final TimeService timeService;
	
	
	@GetMapping("/login")
	private String login() {
		return "login";
	}
	
	@GetMapping("/top")
	private String showListSelection(Model model) {
		// HTMLテンプレート名で return
		List<TimeLog> list = timeService.findListAll();
		
		model.addAttribute("timeLogList", list);
		return "time-log";
	}
	
	
	
	
	@PostMapping("/time-log")
	private String showLogList(Model model) {
		
		List<TimeLog> list = timeService.findListAll();
		
		model.addAttribute("timeLogList", list);
		return "time-log";
	}
	
	@PostMapping("/log-detail")
	private String logDetail(Model model, TimeDetailForm form) {
		
		LogDetail logDetail = timeService.findDetailByLogId(form.getLogId());
		
		model.addAttribute("logDetail", logDetail);
		
		return "log-detail";
	}
}
