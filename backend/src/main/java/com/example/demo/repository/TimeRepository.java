package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.TimeLog;

@Mapper
public interface TimeRepository {

	
	List<TimeLog> selectListAll();
}
