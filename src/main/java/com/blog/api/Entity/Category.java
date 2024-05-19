package com.blog.api.Entity;

import java.util.HashSet;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="categories")
public class Category {
	
	
	@Override
	public String toString() {
		return "Category [id=" + id + ", categoryName=" + categoryName + ", posts=" + posts + "]";
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	
    @JsonIgnore
	public Set<Post> getPosts() {
		return posts;
	}

    @JsonIgnore
	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String categoryName;
	

	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	public Set<Post> posts =  new HashSet<>();


	public Category() {
		
	}


	public Category(Long id, String categoryName, Set<Post> posts) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.posts = posts;
	}

	
}
