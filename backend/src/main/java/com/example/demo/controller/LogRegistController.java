package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Category;
import com.example.demo.entity.Log;
import com.example.demo.entity.TimeLog;
import com.example.demo.form.TimeRegistForm;
import com.example.demo.service.CategoryService;
import com.example.demo.service.TimeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LogRegistController {
	
	private final TimeService timeService;
	
	private final CategoryService categoryService;
	
	
	
	
	
	
	@PostMapping("/time-show-regist")
	public String showRegist(@ModelAttribute TimeRegistForm form,Model model) {
		List<Category> list = categoryService.findAll();
		model.addAttribute("categoryList", list);
		return "time-regist";
	}
	
	@PostMapping("/time-regist")
	public String regist(@Validated  Model model ,TimeRegistForm form,BindingResult result) {
		LocalDate nowday = LocalDate.now();
		if (result.hasErrors()) {
			List<TimeLog> TimeLogList = timeService.findListAll();
			
			model.addAttribute("timeLogList", TimeLogList);
			
			List<Category> CategoryList = categoryService.findAll();
			model.addAttribute("categoryList", CategoryList);
			
			return "time-regist";
		}
		
		Category category = categoryService.findByCategoryId(form.getCategoryId());
		form.setCategoryName(category.getCategoryName());
//		
		
		
		Log log = new Log();
		log.setCategoryId(form.getCategoryId());
		log.setStartTime(form.getStartTime());
		log.setEndTime(form.getEndTime());
		
		
		timeService.regist(log);
		
		List<TimeLog> list = timeService.findListAll();
		
		List<TimeLog> TimeLogList = timeService.findListAll();
		
		model.addAttribute("timeLogList", TimeLogList);
		model.addAttribute("today", nowday);
		model.addAttribute("categoryList", list);
		
		return "time-log";
	}
}
