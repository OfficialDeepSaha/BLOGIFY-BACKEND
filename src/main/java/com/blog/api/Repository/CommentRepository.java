package com.blog.api.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.api.Entity.Comment;
import com.blog.api.Entity.Post;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	Comment save(Post post);
	
	List<Comment> findByPostId(Long postId);


}
