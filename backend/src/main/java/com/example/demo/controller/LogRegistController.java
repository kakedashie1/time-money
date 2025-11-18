package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Category;
import com.example.demo.entity.Log;
import com.example.demo.entity.MaxDay;
import com.example.demo.entity.TimeLog;
import com.example.demo.form.TimeRegistForm;
import com.example.demo.security.UserDetailsImpl;
import com.example.demo.service.CategoryService;
import com.example.demo.service.TimeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LogRegistController {
	
	private final TimeService timeService;
	
	private final CategoryService categoryService;
	
	
	
	
	
	
	@PostMapping("/time-show-regist")
	public String showRegist(@AuthenticationPrincipal UserDetailsImpl principal,@ModelAttribute TimeRegistForm form,Model model) {
		List<Category> list = categoryService.findAll();
		LocalDate toDay = form.getToDay();
		LocalDate nowDay = LocalDate.now();
		if (toDay.isAfter(nowDay)) {
			
			List<TimeLog> logList = timeService.findListAll(principal.getId());
			nowDay = LocalDate.now();
			
		    form.setToDay(nowDay);
			model.addAttribute("timeLogList", logList);
			model.addAttribute("timeRegistForm", form);
			return "time-log";
		}
		
		MaxDay maxDay = timeService.findByMaxDay(form.getToDay());
		if(maxDay != null) {
			form.setMaxDay(maxDay.getMaxDay());
		}
		
		
		model.addAttribute("categoryList", list);
		model.addAttribute("maxDay", maxDay);
		model.addAttribute("toDay", toDay);
		return "time-regist";
	}
	
	@PostMapping("/time-regist")
	public String regist( @AuthenticationPrincipal UserDetailsImpl principal,Model model ,@Validated TimeRegistForm form,BindingResult result) {
		LocalDate nowday = LocalDate.now();
		if (result.hasErrors()) {
			List<TimeLog> TimeLogList = timeService.findListAll(principal.getId());
			
			model.addAttribute("timeLogList", TimeLogList);
			
			List<Category> CategoryList = categoryService.findAll();
			model.addAttribute("categoryList", CategoryList);
			
			return "time-regist";
		}
		
		Category category = categoryService.findByCategoryId(form.getCategoryId());
		form.setCategoryName(category.getCategoryName());
	
		
		
		Log log = new Log();
		log.setCategoryId(form.getCategoryId());
		log.setStartTime(form.getStartTime());
		log.setEndTime(form.getEndTime());
		log.setUserId(principal.getId());
		
		
		timeService.regist(log);
		
		
		
		
		return "redirect:/top";
	}
}
