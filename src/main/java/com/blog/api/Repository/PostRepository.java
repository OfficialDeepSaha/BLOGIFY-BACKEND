package com.blog.api.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.blog.api.Entity.Category;
import com.blog.api.Entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findByUserId(Long userId);

	@Query(value = "SELECT * FROM posts ORDER BY id DESC LIMIT 3", nativeQuery = true)
	List<Post> findLatestPost();

	 Long findByCategory(Category category);

	Long countPostsByCategoryId(Long categoryId);

	
	
	

	
}
