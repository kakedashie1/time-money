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
import com.example.demo.entity.LogDetail;
import com.example.demo.entity.TimeLog;
import com.example.demo.form.TimeEditForm;
import com.example.demo.service.CategoryService;
import com.example.demo.service.TimeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LogEditController {
	
	private final TimeService timeService;
	
	private final CategoryService categoryService;
	
	
	
	
	
	
	@PostMapping("/time-show-edit")
	public String showEdit(@ModelAttribute TimeEditForm form,Model model) {
		List<Category> list = categoryService.findAll();
		LogDetail logDetail = timeService.findDetailByLogId(form.getLogId());
		model.addAttribute("logDetail", logDetail);
		model.addAttribute("categoryList", list);
		return "time-edit";
	}
	
	@PostMapping("/time-edit")
	public String edit(@Validated  Model model ,TimeEditForm form,BindingResult result) {
		LocalDate nowday = LocalDate.now();
		if (result.hasErrors()) {
			List<TimeLog> TimeLogList = timeService.findListAll();
			
			model.addAttribute("timeLogList", TimeLogList);
			
			List<Category> CategoryList = categoryService.findAll();
			model.addAttribute("categoryList", CategoryList);
			
			return "time-edit";
		}
		
		Category category = categoryService.findByCategoryId(form.getCategoryId());
		form.setCategoryName(category.getCategoryName());
		
		
		
		Log log = new Log();
		log.setLogId(form.getLogId());
		log.setCategoryId(form.getCategoryId());
		log.setStartTime(form.getStartTime());
		log.setEndTime(form.getEndTime());
		
		
		timeService.edit(log);
		
		List<TimeLog> list = timeService.findListAll();
		
		List<TimeLog> TimeLogList = timeService.findListAll();
		
		model.addAttribute("timeLogList", TimeLogList);
		model.addAttribute("today", nowday);
		model.addAttribute("categoryList", list);
		
		return "time-log";
	}
}
