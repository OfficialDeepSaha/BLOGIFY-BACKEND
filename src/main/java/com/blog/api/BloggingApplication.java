package com.blog.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.blog.api.Entity.User;
import com.blog.api.Repository.UserRepository;

@SpringBootApplication
public class BloggingApplication implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(BloggingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(userRepository.findAll().isEmpty() == true) {
			
			User user = new User();
			user.setName("SuperAdmin");
			user.setEmail("deepsaha01896@gmail.com");
			user.setPassword(bCryptPasswordEncoder.encode("deePs223@#"));
			user.setRole("ROLE_ADMIN");
			user.setAbout("I Am the Admin of this site.😎");
			
			userRepository.save(user);
			
		}
		
		
	}
	
	
	

}
