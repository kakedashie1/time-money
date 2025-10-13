package com.example.demo.form;

import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class TimeEditForm {
	
	private Integer logId;
	
	private Integer categoryId;
	
	private String startTime;
	
	
	private String endTime;
	
	@Size(max=10, message="10文字以内で入力してください。")
	private String categoryName;
}
