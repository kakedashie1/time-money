package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.EditLog;
import com.example.demo.entity.Log;
import com.example.demo.entity.LogDetail;
import com.example.demo.entity.MaxDay;
import com.example.demo.entity.TimeLog;

@Mapper
public interface TimeRepository {

	
	List<TimeLog> selectListAll();
	
	void insert(@Param("log") Log log);
	
	LogDetail selectByLogId(@Param("logId") Integer logId);
	
	void update(@Param("log") EditLog log);
	
	void delete(@Param("logId") Integer logId);
	
	List<TimeLog> selectByNowDay(@Param("nowDay") LocalDate nowDay);
	
	MaxDay maxDay(@Param("nowDay") LocalDate nowDay);
	
	Log maxLogId(@Param("nowDay") LocalDate nowDay);
}
