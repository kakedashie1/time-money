package com.example.demo.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Log {

	
	private Integer logId;
	private String categoryName;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private Integer userId;
	private Integer categoryId;
}
