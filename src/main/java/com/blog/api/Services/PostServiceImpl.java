package com.blog.api.Services;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blog.api.Dto.PostStatus;
import com.blog.api.Entity.Category;
import com.blog.api.Entity.Post;
import com.blog.api.Entity.User;
import com.blog.api.Repository.CategoryRepository;
import com.blog.api.Repository.PostRepository;
import com.blog.api.Repository.UserRepository;
import com.cloudinary.Cloudinary;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private Cloudinary cloudinary;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Post createPost(Post post, Long userId , Long categoryId) {

		User user = userRepository.findById(userId).get();

		Post createPost = new Post();
		Category category= categoryRepository.findById(categoryId).get();

		createPost.setTitle(post.getTitle());
		createPost.setShortInfo(post.getShortInfo());
		createPost.setDate(LocalDate.now());
		createPost.setContent(post.getContent());
		createPost.setTags(post.getTags());
		createPost.setCategory(category);
		createPost.setUser(user);
		createPost.setPostStatus(PostStatus.PENDING);

		return postRepository.save(createPost);
	}

	@Override
	public List<Post> getallPosts() {

		return postRepository.findAll();
	}

	@Override
	public Post uploadFile(MultipartFile multipartFile, Long id) throws IOException {
		
		  Post post = postRepository.findById(id).get();
		  post.setImageName(cloudinary.uploader()
                .upload(multipartFile.getBytes(),
                        Map.of("public_id", UUID.randomUUID().toString()))
                .get("url")
                .toString());
		
	
		return postRepository.save(post);
	}

	
	public List<Post> getPostsByUserId(Long userId) {
        return postRepository.findByUserId(userId);
    }

	@Override
	public Post statusChangeByPostId(Long id) {
		Post post = postRepository.findById(id).get();
		
		post.setPostStatus(PostStatus.APPROVED);
		
		return postRepository.save(post);
	}

	@Override
	public Post updatePostById(Post post, Long postId ,Long categoryId) {
		  Post updatedPost = postRepository.findById(postId).get();
		  Category category =  categoryRepository.findById(categoryId).get();
		  updatedPost.setTitle(post.getTitle());
		  updatedPost.setTags(post.getTags());
		  updatedPost.setShortInfo(post.getShortInfo());
		  updatedPost.setDate(LocalDate.now());
		  updatedPost.setContent(post.getContent());
		  updatedPost.setCategory(category);;
		  updatedPost.setPostStatus(PostStatus.PENDING);
		return postRepository.save(updatedPost);
	}

	@Override
	public List<Post> getLatestPost() {
		
		return postRepository.findLatestPost();
	}
	
	
	public Long countPostsByCategoryId(Long categoryId) {
	    return postRepository.countPostsByCategoryId(categoryId);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
