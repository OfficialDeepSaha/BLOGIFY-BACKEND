package com.blog.api.Services;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.blog.api.Entity.Comment;
import com.blog.api.Entity.Likes;

public interface LikeService {
	
	public Likes addLike(Long userId ,Long postId ,Likes likes) throws NotFoundException;
	
	public void removeLike(Long id);
	
	
	
	public List<Likes> getLikes(Long postId);

}
