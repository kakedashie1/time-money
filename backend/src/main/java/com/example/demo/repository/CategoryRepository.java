package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.Category;

@Mapper
public interface CategoryRepository {

	List<Category> selectAll();
	
	Category selectByCategoryId(@Param("categoryId") int categoryId);
}
