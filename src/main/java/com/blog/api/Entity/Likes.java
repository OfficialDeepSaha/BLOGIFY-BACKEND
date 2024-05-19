package com.blog.api.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "likes")
public class Likes {

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getLikeText() {
		return likeText;
	}

	public void setLikeText(int likeText) {
		this.likeText = likeText;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int likeText;

	@ManyToOne
	private Post post;

	@ManyToOne
	private User user;

	public Likes() {
		
	}

	public Likes(Long id, int likeText, Post post, User user) {
		super();
		this.id = id;
		this.likeText = likeText;
		this.post = post;
		this.user = user;
	}

	
	
	
	
	
}
