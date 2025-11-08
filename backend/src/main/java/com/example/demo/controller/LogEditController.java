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
import com.example.demo.entity.EditLog;
import com.example.demo.entity.LogDetail;
import com.example.demo.entity.TimeLog;
import com.example.demo.form.TimeEditForm;
import com.example.demo.form.TimeRegistForm;
import com.example.demo.service.CategoryService;
import com.example.demo.service.TimeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LogEditController {

	private final TimeService timeService;

	private final CategoryService categoryService;

	@PostMapping("/time-show-edit")
	public String showEdit(@ModelAttribute TimeEditForm editForm, Model model) {
		List<Category> list = categoryService.findAll();
		LogDetail logDetail = timeService.findDetailByLogId(editForm.getLogId());
		model.addAttribute("logDetail", logDetail);
		model.addAttribute("categoryList", list); 
		return "time-edit";
	}

	@PostMapping("/time-edit")
	public String edit(Model model, @Validated TimeEditForm editForm, BindingResult result) {
		LocalDate nowDay = LocalDate.now();
		if (result.hasErrors()) {
			List<TimeLog> TimeLogList = timeService.findListAll();

			model.addAttribute("timeLogList", TimeLogList);

			List<Category> CategoryList = categoryService.findAll();
			model.addAttribute("categoryList", CategoryList);

			LogDetail logDetail = timeService.findDetailByLogId(editForm.getLogId());
			model.addAttribute("logDetail", logDetail);

			return "time-edit";
		}

		Category category = categoryService.findByCategoryId(editForm.getCategoryId());
		editForm.setCategoryName(category.getCategoryName());

		EditLog log = new EditLog();
		log.setLogId(editForm.getLogId());
		log.setCategoryId(editForm.getCategoryId());
		log.setStartTime(editForm.getStartTime());
		log.setEndTime(editForm.getEndTime());

		timeService.edit(log);

		List<TimeLog> list = timeService.findListAll();

		List<TimeLog> TimeLogList = timeService.findListAll();
		
		TimeRegistForm form = new TimeRegistForm();
	    form.setToDay(nowDay);

		model.addAttribute("timeLogList", TimeLogList);
		model.addAttribute("timeRegistForm", form);
		model.addAttribute("categoryList", list);

		return "time-log";
	}
}
