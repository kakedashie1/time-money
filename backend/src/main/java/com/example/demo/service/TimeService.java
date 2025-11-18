package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.entity.Day;
import com.example.demo.entity.EditLog;
import com.example.demo.entity.Log;
import com.example.demo.entity.LogDetail;
import com.example.demo.entity.MaxDay;
import com.example.demo.entity.TimeLog;

public interface TimeService {

	List<TimeLog> findListAll(String username);
	
	void regist(Log log);
	
	LogDetail findDetailByLogId(Integer logId);
	
	void edit(EditLog log);
	
	void remove(Integer logId);
	
	List<TimeLog> findByNowDay(Day day);
	
	MaxDay findByMaxDay(LocalDate nowDay);
	
	Log maxLogId(LocalDate nowDay);
}
