package com.blog.api.Services;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.blog.api.Entity.Comment;

public interface CommentService {
	public Comment addCommentToPost(Long userId ,Long postId, Comment commentText) throws NotFoundException;
	
	public List<Comment> getComments(Long postId);

}
