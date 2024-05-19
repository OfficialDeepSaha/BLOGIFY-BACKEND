package com.blog.api.Services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.blog.api.Entity.User;

public interface UserService {
	
	public User findUserProfileByJwt(String jwt) throws Exception;
	
	User updateUserDetails(User user , Long id );
	
	User getUserById(Long id);
	
	public User uploadFile(MultipartFile multipartFile , Long id) throws IOException;
	
	public void sendRegistrationSuccessMail(String name ,String to) throws Exception;
	
	public void ComplainOrQueryMail(String from , String name , String message) throws Exception;
	

}
