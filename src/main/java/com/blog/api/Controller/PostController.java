package com.blog.api.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.api.Entity.Category;
import com.blog.api.Entity.Post;
import com.blog.api.Repository.PostRepository;
import com.blog.api.Response.ApiResponse;
import com.blog.api.Services.PostService;

@RestController
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private PostRepository postRepository;

	@GetMapping("/getAll")
	public ResponseEntity<List<Post>> getallPosts() {

		return new ResponseEntity<List<Post>>(postService.getallPosts(), HttpStatus.OK);

	}

	@PostMapping("/user/{userId}/{categoryId}")
	public ResponseEntity<Post> savePosts(@RequestBody Post post, @PathVariable Long userId,
			@PathVariable Long categoryId) {

		Post createdPost = postService.createPost(post, userId, categoryId);

		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("Image Uploaded");
		apiResponse.setMessage("Post Successfully Created👍");
		apiResponse.setStatus(true);

		return new ResponseEntity<Post>(createdPost, HttpStatus.CREATED);

	}

	@PostMapping("/upload/image/{postId}")
	public ResponseEntity<Post> uploadImageForPost(@RequestParam("image") MultipartFile file, @PathVariable Long postId)
			throws IOException {

		Post upload = postService.uploadFile(file, postId);

		return new ResponseEntity<Post>(upload, HttpStatus.CREATED);

	}

	// getPosts using user id

	@GetMapping("/get/{userId}")
	public List<Post> getPostsByUserId(@PathVariable Long userId) {
		return postService.getPostsByUserId(userId);
	}

	@PostMapping("/update/{postId}/{categoryId}")
	public ResponseEntity<Post> updatePostById(@RequestBody Post post, @PathVariable Long postId,
			@PathVariable Long categoryId) throws Exception {

		return new ResponseEntity<Post>(postService.updatePostById(post, postId, categoryId), HttpStatus.CREATED);

	}

	@DeleteMapping("/delete/{postId}")
	public ResponseEntity<Post> deleteUserPostById(@PathVariable Long postId) throws Exception {
		postRepository.deleteById(postId);
		return new ResponseEntity<Post>(HttpStatus.ACCEPTED);
	}

	@PostMapping("/status/{postId}")
	public ResponseEntity<Post> changeStatus(@PathVariable Long postId) {

		postService.statusChangeByPostId(postId);

		return new ResponseEntity<Post>(HttpStatus.CREATED);

	}

	@GetMapping("/getpost/{postId}")
	public Post getPostsById(@PathVariable Long postId) {

		return postRepository.findById(postId).get();

	}

	@GetMapping("/latest-posts")
	public ResponseEntity<List<Post>> getLatestPost() {
		return new ResponseEntity<List<Post>>(postService.getLatestPost(), HttpStatus.OK);
	}

	@GetMapping("/{categoryId}/post-count")
	public ResponseEntity<Long> getPostCountByCategoryId(@PathVariable Long categoryId) {
		Long postCount = postService.countPostsByCategoryId(categoryId);
		System.out.print("Total Post Count:- " + postCount);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("Post Count Seccessful!!");
		return new ResponseEntity<>(postCount, HttpStatus.OK);
	}

}
