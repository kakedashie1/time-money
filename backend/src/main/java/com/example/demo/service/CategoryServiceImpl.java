package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	
	private final CategoryRepository categoryRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Category> findAll() {
		// TODO 自動生成されたメソッド・スタブ
		
		List<Category> list = categoryRepository.selectAll();
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public Category findByCategoryId(int categoryId) {
		// TODO 自動生成されたメソッド・スタブ
		
		Category category = categoryRepository.selectByCategoryId(categoryId);
		
		return category;
	}

}
