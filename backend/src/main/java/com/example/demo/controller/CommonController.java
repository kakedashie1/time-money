package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.TimeLog;
import com.example.demo.service.TimeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CommonController {
	
	private final TimeService timeService;

	/*--- 完了後のリダイレクト先（タスク更新系） ---*/
	@GetMapping("/time-complete")
	private String completeTime(Model model) {
		
		
		
		List<TimeLog> list = timeService.findListAll();
		
		model.addAttribute("timeLogList", list);
		return "time-log";
		
	}

}
