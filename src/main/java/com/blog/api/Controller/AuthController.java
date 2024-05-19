package com.blog.api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.api.Dto.HttpUtils;
import com.blog.api.Entity.User;
import com.blog.api.Repository.UserRepository;
import com.blog.api.Requests.LoginRequest;
import com.blog.api.Response.AuthResponse;
import com.blog.api.Services.CustomUserDetailService;
import com.blog.api.Services.UserService;
import com.blog.api.configuration.JwtTokenProvider;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private CustomUserDetailService customUserDetails;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpServletRequest request;
	
	//fetching IP
		
	@RequestMapping(
	        method = RequestMethod.GET,
	        value = "/user/ip",
	        produces = MediaType.TEXT_PLAIN_VALUE
	    )
	    @ResponseBody
	    public String getClientIPAddress() {
	        String ip = HttpUtils.getRequestIP(request);
	        return ip;
	    }	
	
	

	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUserHandler(@Valid @RequestBody User user) throws Exception {

		User isEmailExist = userRepository.findByEmail(user.getEmail());
		if (isEmailExist != null) {

			throw new Exception("Email Is Already Used With Another Account");
		}
		
		// Create new user
		User createdUser = new User();
		createdUser.setEmail(user.getEmail());
		createdUser.setName(user.getName());
		createdUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		createdUser.setImage("sample_image.jpg");
		createdUser.setRole("ROLE_CUSTOMER");
		createdUser.setIp(getClientIPAddress());
		System.out.print("IP Address Saved In DataBase");
		userRepository.save(createdUser);

		Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtTokenProvider.generateToken(authentication);

		AuthResponse authResponse = new AuthResponse();
		authResponse.setMessage("Signup Successfull !!");
		authResponse.setJwt(token);
		System.out.print("Your JWT Token is- "+token);
		userService.sendRegistrationSuccessMail(user.getName() ,user.getEmail());
		authResponse.setMessage("Email sent !!");

		return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);

	}

	
	
	
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest) {
		String username = loginRequest.getEmail();
		String password = loginRequest.getPassword();

		System.out.println(username + " ----- " + password);

		Authentication authentication = authenticate(username, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtTokenProvider.generateToken(authentication);
		AuthResponse authResponse = new AuthResponse();

		authResponse.setStatus(true);
		authResponse.setJwt(token);
		authResponse.setMessage("Signin Successfull !!");

		return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
	}

	private Authentication authenticate(String username, String password) {
		UserDetails userDetails = customUserDetails.loadUserByUsername(username);

		System.out.println("sign in userDetails - " + userDetails);

		if (userDetails == null) {
			System.out.println("sign in userDetails - null " + userDetails);
			throw new BadCredentialsException("Invalid username or password");
		}
		if (!bCryptPasswordEncoder.matches(password, userDetails.getPassword())) {
			System.out.println("sign in userDetails - password not match " + userDetails);
			throw new BadCredentialsException("Invalid username or password");
		}
		return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getUsername(), userDetails.getAuthorities());
	}
	
	
	
	
	
	
	
	
	
}
