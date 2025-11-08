package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Category;

public interface CategoryService {

	List<Category> findAll();

	Category findByCategoryId(String categoryId);

	void edit(Category category);

	void remove(Integer categoryId);
	
	void regist(Category category);
}
