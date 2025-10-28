package com.example.demo.form;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TimeRegistForm {

	private Integer logId;
	
	private Integer categoryId;

	@DateTimeFormat(pattern = "yyyy-MM-dd-HH-mm")
	@NotNull(message = "日時を入力してください。")
	private LocalDateTime startTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd-HH-mm")
	@NotNull(message = "日時を入力してください。")
	private LocalDateTime endTime;

	@Size(max = 10, message = "10文字以内で入力してください。")
	private String categoryName;
	
	
}
