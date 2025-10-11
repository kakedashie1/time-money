package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.LogDetail;
import com.example.demo.entity.Task;
import com.example.demo.entity.TimeLog;

public interface TimeService {

	List<TimeLog> findListAll();
	
	void regist(Task task);
	
	LogDetail findDetailByLogId(Integer logId);
}
