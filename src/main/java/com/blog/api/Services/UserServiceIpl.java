package com.blog.api.Services;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blog.api.Entity.User;
import com.blog.api.Repository.UserRepository;
import com.blog.api.configuration.JwtTokenProvider;
import com.cloudinary.Cloudinary;

import jakarta.mail.internet.MimeMessage;

@Service
public class UserServiceIpl implements UserService {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private Cloudinary cloudinary;

	@Autowired
	private JavaMailSender jSender;

	@Override
	public User findUserProfileByJwt(String jwt) throws Exception {
		System.out.println("user service");
		String email = jwtTokenProvider.getEmailFromJwtToken(jwt);

		System.out.println("email" + email);

		User user = userRepository.findByEmail(email);

		if (user == null) {
			throw new Exception("user not exist with email " + email);
		}
		System.out.println("email user" + user.getEmail());
		return user;
	}

	@Override
	public User updateUserDetails(User user, Long id) {

		User updatedUser = userRepository.findById(id).get();
		updatedUser.setName(user.getName());
		updatedUser.setEmail(user.getEmail());
		updatedUser.setAbout(user.getAbout());
		return userRepository.save(updatedUser);
	}

	@Override
	public User getUserById(Long id) {

		return userRepository.findById(id).get();
	}

	@Override
	public User uploadFile(MultipartFile multipartFile, Long id) throws IOException {
		User uploadUserImage = userRepository.findById(id).get();
		uploadUserImage.setImage(cloudinary.uploader()
				.upload(multipartFile.getBytes(), Map.of("public_id", UUID.randomUUID().toString())).get("url")
				.toString());
		return userRepository.save(uploadUserImage);
	}

	@Override
	public void sendRegistrationSuccessMail(String name , String to) throws Exception {

		MimeMessage message = jSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setSubject("Registration Success👍");
		helper.setText("<html><body>" +
			            "<h2 style='color:#333;'>Message From BLOGIFY</h2>" +
			            "<p>Hello,</p>"+ name +
			            "<p><strong></strong></p>" +
			            "<p>" + "Thank You For Registration Our Site" + "</p>" +
			            "</body></html>" , true);
		helper.setTo(to);
		helper.setFrom("Blogify", "BLOGIFY");

		jSender.send(message);

	}

	@Override
	public void ComplainOrQueryMail(String from, String name, String message) throws Exception {

		MimeMessage mesg = jSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mesg, true);
		helper.setSubject("Message From Customer-> ");
		helper.setText(
				
				"<html><body>" +
			            "<h2 style='color:#333;'>Message From Customer</h2>" +
			            "<p><strong>Customer Name:</strong> " + name + "</p>" +
			            "<p><strong>Customer Email:</strong> " + from + "</p>" +
			            "<p><strong>Message:</strong></p>" +
			            "<p>" + message + "</p>" +
			            "</body></html>" , true
				
				
				
				);
		helper.setTo("owner.blogify@gmail.com");
		helper.setFrom(from , "Customer Mail");

		jSender.send(mesg);

	}

}
