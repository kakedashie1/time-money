package com.example.demo.form;

import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class UserRegistForm {

	
	
	@Size(min=1, max=16, message="1文字から16文字で指定してください。")
	private String userId;
	
	@Size(min=1, max=16, message="1文字から16文字で指定してください。")
	private String password;
	
	
	
	private String role;

}
