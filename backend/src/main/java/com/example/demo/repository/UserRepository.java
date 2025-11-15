package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.User;

@Mapper
public interface UserRepository {

	void insert(@Param("user") User user);

	User selectByUserId(@Param("userId") String userId);
}
