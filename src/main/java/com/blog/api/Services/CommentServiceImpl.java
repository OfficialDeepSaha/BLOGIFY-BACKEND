package com.blog.api.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.blog.api.Entity.Comment;
import com.blog.api.Entity.Post;
import com.blog.api.Entity.User;
import com.blog.api.Repository.CommentRepository;
import com.blog.api.Repository.PostRepository;
import com.blog.api.Repository.UserRepository;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private CommentRepository commentRepository;

	public Comment addCommentToPost(Long userId ,Long postId, Comment commentText) throws NotFoundException {
		Optional<Post> optionalPost = postRepository.findById(postId);
		 User user = userRepository.findById(userId).get();
		if (optionalPost.isPresent()) {
			Post post = optionalPost.get();
			Comment comment = new Comment();
			comment.setComment(commentText.getComment());
			comment.setPost(post);
			comment.setUser(user);
			return commentRepository.save(comment);
		} else {
			throw new NotFoundException();
		}
	}

	@Override
	public List<Comment> getComments(Long postId) {
	    // Retrieve comments associated with the post
	    return commentRepository.findByPostId(postId);
	}

}
