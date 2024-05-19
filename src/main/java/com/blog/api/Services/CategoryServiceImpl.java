package com.blog.api.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.api.Entity.Category;
import com.blog.api.Repository.CategoryRepository;
import com.blog.api.Repository.PostRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private PostRepository postRepository;
	

	@Override
	public Category createCategory(Category category) {
		
		return categoryRepository.save(category);
	}

	
	
	
	
	
	
	
	
	
	
	
	
}
