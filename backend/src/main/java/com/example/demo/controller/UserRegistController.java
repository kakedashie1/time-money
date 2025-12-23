package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.User;
import com.example.demo.form.UserRegistForm;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserRegistController {

	private final UserService service;

	/*--- ユーザ登録画面表示 ---*/
	@GetMapping("/show-user-regist-form")
	public String showUserRegistForm(@ModelAttribute UserRegistForm form) {
		return "user-regist";
	}

	/*--- ユーザ登録リクエスト（登録画面より） ---*/
	@PostMapping("/regist-user")
	public String registUser(
			@Validated UserRegistForm form,
			BindingResult result,RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "/user-regist";
		}
		
		User user = new User();
		user.setUserId(form.getUserId());
		user.setPassword(form.getPassword());
		user.setRole(form.getRole());
		
		if(user.getRole() == null) {
			service.userRegist(user);
			
			
		}else {
			
			service.regist(user);
		}


		redirectAttributes.addFlashAttribute("msg", "(ユーザ登録)");


		return "redirect:/login";
	}



}
