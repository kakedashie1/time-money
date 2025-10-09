package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.TimeLog;
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
	
	
	
	
	@PostMapping("/time-log")
	private String showLogList(Model model) {
		
		List<TimeLog> list = timeService.findListAll();
		
		model.addAttribute("timeLogList", list);
		return "time-log";
	}
}
