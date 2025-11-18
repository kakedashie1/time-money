package com.example.demo.entity;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Log {

	
	private Integer logId;
	private String categoryName;
	private LocalDateTime startTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime endTime;
	private String userId;
	private String categoryId;
}
