package com.example.demo.form;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.validation.CategoryDate;
import com.example.demo.validation.ValidDate;

import lombok.Data;

@Data
@ValidDate(startTimeStr = "startTime", endTimeStr = "endTime")
@CategoryDate(categoryIdStr = "categoryId")
public class TimeEditForm {
	
	private Integer logId;
	
	private LocalDate maxDay;
	
	
	private String categoryId;
	
//	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
//	@NotNull(message = "日時を入力してください。")
	private LocalDateTime startTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@NotNull(message = "日時を入力してください。")
	private LocalDateTime endTime;
	
	@Size(max=10, message="10文字以内で入力してください。")
	private String categoryName;
}
