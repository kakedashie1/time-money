package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.validation.ValidDate;

import lombok.Data;

@Data
@ValidDate(startTimeStr = "startTime", endTimeStr = "endTime")
public class TimeEditForm {
	
	private Integer logId;
	
	private Integer categoryId;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd_HH:mm")
	@NotBlank(message = "開始時間を入力してください。")
	private String startTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd_HH:mm")
	@NotBlank(message = "終了時間を入力してください。")
	private String endTime;
	
	@Size(max=10, message="10文字以内で入力してください。")
	private String categoryName;
}
