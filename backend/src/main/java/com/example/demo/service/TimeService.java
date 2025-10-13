package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Log;
import com.example.demo.entity.LogDetail;
import com.example.demo.entity.TimeLog;

public interface TimeService {

	List<TimeLog> findListAll();
	
	void regist(Log task);
	
	LogDetail findDetailByLogId(Integer logId);
	
	void edit(Log log);
	
	void remove(Integer logId);
}
