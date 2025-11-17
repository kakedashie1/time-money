package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.EditLog;
import com.example.demo.entity.Log;
import com.example.demo.entity.LogDetail;
import com.example.demo.entity.MaxDay;
import com.example.demo.entity.TimeLog;
import com.example.demo.repository.TimeRepository;
import com.example.demo.security.UserDetailsImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TimeServiceImpl implements TimeService {

	private final TimeRepository timeRepository;

	@Override
	@Transactional(readOnly = true)
	public List<TimeLog> findListAll() {
		// TODO 自動生成されたメソッド・スタブ

		UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext()
				.getAuthentication()
				.getPrincipal();

		List<TimeLog> list = timeRepository.selectListAll();
		return list;
	}

	@Override
	@Transactional
	public void regist(Log log) {
		// TODO 自動生成されたメソッド・スタブ

		timeRepository.insert(log);
	}

	@Override
	@Transactional(readOnly = true)
	public LogDetail findDetailByLogId(Integer logId) {
		// TODO 自動生成されたメソッド・スタブ

		LogDetail logDetail = timeRepository.selectByLogId(logId);

		return logDetail;
	}

	@Override
	@Transactional
	public void edit(EditLog log) {
		// TODO 自動生成されたメソッド・スタブ

		timeRepository.update(log);

	}

	@Override
	public void remove(Integer logId) {
		// TODO 自動生成されたメソッド・スタブ

		timeRepository.delete(logId);

	}

	@Override
	public List<TimeLog> findByNowDay(LocalDate nowDay) {
		// TODO 自動生成されたメソッド・スタブ

		List<TimeLog> changeDay = timeRepository.selectByNowDay(nowDay);
		return changeDay;
	}

	@Override
	public MaxDay findByMaxDay(LocalDate nowDay) {
		// TODO 自動生成されたメソッド・スタブ

		MaxDay maxDay = timeRepository.maxDay(nowDay);
		return maxDay;
	}

	@Override
	public Log maxLogId(LocalDate nowDay) {
		// TODO 自動生成されたメソッド・スタブ

		Log maxLogId = timeRepository.maxLogId(nowDay);
		return maxLogId;
	}

}
