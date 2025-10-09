package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.TimeLog;
import com.example.demo.repository.TimeRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class TimeServiceImpl implements TimeService {

	private final TimeRepository timeRepository;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<TimeLog> findListAll() {
		// TODO 自動生成されたメソッド・スタブ
		
		List<TimeLog> list = timeRepository.selectListAll();
		return list;
	}

}
