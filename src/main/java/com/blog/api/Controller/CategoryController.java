package com.blog.api.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.api.Entity.Category;
import com.blog.api.Repository.CategoryRepository;
import com.blog.api.Services.CategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@PostMapping("/create/category")
	public ResponseEntity<Category> createCategory(@RequestBody Category category){
		
     return new ResponseEntity<Category>(categoryService.createCategory(category) , HttpStatus.CREATED);	
		
		
	}
	
	
	
	
//	@GetMapping("/{categoryId}/post-count")
//    public ResponseEntity<Long> getPostCountByCategoryId(@PathVariable Long categoryId) {
//        long postCount = categoryService.countPostsByCategoryId(categoryId);
//        return ResponseEntity.ok(postCount);
//    }
	
	
	
	
	
	
	
	
	
	@GetMapping("/getall/categories")
	public ResponseEntity<List<Category>> getCategory(){
		
		return new ResponseEntity<List<Category>>(categoryRepository.findAll()  ,HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
