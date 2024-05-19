package com.blog.api.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.api.Entity.Likes;
import com.blog.api.Entity.Post;
import com.blog.api.Entity.User;
import com.blog.api.Repository.LikeRepository;
import com.blog.api.Repository.PostRepository;
import com.blog.api.Repository.UserRepository;

@Service
public class LikeServiceImpl implements LikeService {

	@Autowired
	private LikeRepository likeRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public Likes addLike(Long userId, Long postId, Likes likes) throws NotFoundException {
		User user = userRepository.findById(userId).get();
		Post post = postRepository.findById(postId).get();

		Likes li = new Likes();
		li.setPost(post);
		li.setLikeText(likes.getLikeText() + 1);
		li.setUser(user);

		return likeRepository.save(li);

	}

	@Override
	public void removeLike(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Likes> getLikes(Long postId) {

		return likeRepository.findByPostId(postId);
	}

}
