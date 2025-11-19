package com.example.demo.form;

import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class LoginForm {

	
	@Size(min=1, max=16, message="1文字から16文字で指定してください。")
	private String username;
	
	@Size(min=1, message="入力してください。")
	private String password;
}
