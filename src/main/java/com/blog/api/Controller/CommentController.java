package com.blog.api.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.api.Entity.Comment;
import com.blog.api.Repository.CommentRepository;
import com.blog.api.Services.CommentService;

@RestController
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private CommentRepository commentRepository;
	
	
	
	@PostMapping("/comment/{userId}/{id}")
	 public ResponseEntity<Comment> addCommentToPost(@PathVariable Long userId  ,@PathVariable("id") Long postId, @RequestBody Comment comment) throws NotFoundException {
        Comment userComment = commentService.addCommentToPost(userId , postId, comment);
        return new ResponseEntity<Comment>(userComment , HttpStatus.CREATED);
    }
	
	
	@GetMapping("/getallcomments/{postId}")
	public ResponseEntity<List<Comment>> getComments(@PathVariable Long postId) {
		commentRepository.count();
		
		return new ResponseEntity<List<Comment>> (commentService.getComments(postId)  ,HttpStatus.ACCEPTED);
		
	}
	
	
	

}
