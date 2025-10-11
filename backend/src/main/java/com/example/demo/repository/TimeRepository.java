package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.LogDetail;
import com.example.demo.entity.Task;
import com.example.demo.entity.TimeLog;

@Mapper
public interface TimeRepository {

	
	List<TimeLog> selectListAll();
	
	void insert(@Param("task") Task task);
	
	LogDetail selectByLogId(@Param("logId") Integer logId);
}
