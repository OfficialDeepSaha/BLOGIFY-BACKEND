package com.blog.api.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.api.Entity.Likes;
import com.blog.api.Repository.LikeRepository;
import com.blog.api.Services.LikeService;

@RestController
public class LikeController {
	
	
	@Autowired
	private LikeService likeService;
	
	@Autowired
	private LikeRepository likeRepository;
	
	@PostMapping("/addlike/{userId}/{postId}")
	public ResponseEntity<Likes> addLike(@PathVariable Long userId ,@PathVariable Long postId ,Likes likes)throws NotFoundException {
		
	Likes likei	= likeService.addLike(userId, postId, likes);
	
	return new ResponseEntity<Likes>(likei , HttpStatus.CREATED);
		
		
	}
	
	
	
	
	
	@GetMapping("/getall/likes/{postId}")
	public ResponseEntity<List<Likes>> getLikes(@PathVariable Long postId) {
		 Long totalLikes =  likeRepository.count();
		 
		 System.out.print("Total Likes:- " +totalLikes + "");
		 System.out.println("");
		
		return new ResponseEntity<List<Likes>> (likeService.getLikes(postId)  ,HttpStatus.ACCEPTED);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	

}
