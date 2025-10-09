package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Category;
import com.example.demo.entity.Task;
import com.example.demo.entity.TimeLog;
import com.example.demo.form.TimeRegistForm;
import com.example.demo.service.CategoryService;
import com.example.demo.service.TimeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TimeRegistController {
	
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
		
		
		Task task = new Task();
		task.setCategoryId(form.getCategoryId());
		task.setStartTime(form.getStartTime());
		task.setEndTime(form.getEndTime());
		
		
		timeService.regist(task);
		
		List<TimeLog> list = timeService.findListAll();
		
		List<TimeLog> TimeLogList = timeService.findListAll();
		
		model.addAttribute("timeLogList", TimeLogList);
		
		model.addAttribute("categoryList", list);
		
		return "time-log";
	}
}
