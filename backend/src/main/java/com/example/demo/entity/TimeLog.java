package com.example.demo.entity;

import lombok.Data;
@Data
public class TimeLog {
	private Integer logId;
	private String categoryName;
	private String startTime;
	private String endTime;
	private Integer userId;
	private Integer categoryId;
	private String yearDate;
}
