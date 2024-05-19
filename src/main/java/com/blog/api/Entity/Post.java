package com.blog.api.Entity;

import java.time.LocalDate;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.JoinColumn;

import com.blog.api.Dto.PostStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "posts")
public class Post {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "post_title", length = 2000, nullable = false)
	private String title;

	@Column(length = 5000000, nullable = false)
	private String shortInfo;

	@Column(length = 2000000000)
	private String content;

	private String imageName;

	private LocalDate date;

	private String tags;

	private PostStatus postStatus;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
	private Set<Likes> liked = new HashSet<>();

	public Post() {

	}

	public Post(Long id, String title, String shortInfo, String content, String imageName, LocalDate date, String tags,
			PostStatus postStatus, Category category, User user, Set<Likes> liked) {
		super();
		this.id = id;
		this.title = title;
		this.shortInfo = shortInfo;
		this.content = content;
		this.imageName = imageName;
		this.date = date;
		this.tags = tags;
		this.postStatus = postStatus;
		this.category = category;
		this.user = user;
		this.liked = liked;
	}

	
	
	
	
	
	
	
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShortInfo() {
		return shortInfo;
	}

	public void setShortInfo(String shortInfo) {
		this.shortInfo = shortInfo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public PostStatus getPostStatus() {
		return postStatus;
	}

	public void setPostStatus(PostStatus postStatus) {
		this.postStatus = postStatus;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@JsonIgnore
	public Set<Likes> getLiked() {
		return liked;
	}

	@JsonIgnore
	public void setLiked(Set<Likes> liked) {
		this.liked = liked;
	}
	
	
	

}
