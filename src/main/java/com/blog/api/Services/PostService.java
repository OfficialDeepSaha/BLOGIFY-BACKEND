package com.blog.api.Services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.blog.api.Entity.Category;
import com.blog.api.Entity.Post;

public interface PostService {
	
	public Post createPost(Post post , Long userId , Long categoryId );

	public List<Post> getallPosts();
	
	public Post uploadFile(MultipartFile multipartFile , Long id) throws IOException;
	
	public List<Post> getPostsByUserId(Long userId);
	
	public Post statusChangeByPostId(Long id);
	
	public Post updatePostById(Post post ,Long postId , Long categoryId);
	
	public List<Post> getLatestPost();
	
	public Long countPostsByCategoryId(Long categoryId);
	
	 
	
	
	
	      
	
}
