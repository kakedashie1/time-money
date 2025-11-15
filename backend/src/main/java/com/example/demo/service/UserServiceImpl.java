package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository repository;

	@Override
	@Transactional
	public void regist(User user) {

		String hashed = "{noop}" + user.getPassword();

		// パスワードの再設定
		user.setPassword(hashed);

		repository.insert(user);

	}

}