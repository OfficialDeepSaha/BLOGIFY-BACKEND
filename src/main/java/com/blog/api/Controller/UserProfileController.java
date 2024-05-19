package com.blog.api.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.api.Dto.UserDTO;
import com.blog.api.Entity.User;
import com.blog.api.Services.UserService;

@RestController
@RequestMapping("/profile")
public class UserProfileController {
	
	@Autowired
	private UserService userService;
	
	
	
	
	@PostMapping("/update/{userId}")
	public ResponseEntity<User> updateUserDetailsById(@RequestBody User user, @PathVariable("userId") Long id ) {

		User updatedUser = userService.updateUserDetails(user, id );
		
		return new ResponseEntity<User>(updatedUser , HttpStatus.CREATED);

	}
	
	@GetMapping("/getdetails/{userId}")
	public User getUserDetailsById(@PathVariable("userId") Long id) {
		
		return userService.getUserById(id);
	}
	
	
	@PostMapping("/upload/image/{userId}")
	public ResponseEntity<User> uploadImageForPost(@RequestParam("image") MultipartFile file, @PathVariable Long userId)
			throws IOException {

		User upload = userService.uploadFile(file, userId);

		return new ResponseEntity<User>(upload, HttpStatus.CREATED);

	}
	
	@PostMapping("/send/query")
	public void sendQueryMail(@RequestBody UserDTO user) throws Exception{
		
		 userService.ComplainOrQueryMail(user.getEmail(), user.getName(), user.getMessage());
		 System.out.print("Mail Sent to the ADMIN !!");
	}
	
	

}
