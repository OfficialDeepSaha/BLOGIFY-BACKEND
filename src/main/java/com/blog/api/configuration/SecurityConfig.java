package com.blog.api.configuration;

import java.util.Arrays;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.blog.api.Services.CustomUserDetailService;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CustomUserDetailService customUserDetails() {

		return new CustomUserDetailService();
	}
	
	

	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeHttpRequests(Authorize -> Authorize
				.requestMatchers("/api/**").authenticated()
				.anyRequest().permitAll()
				)
		.addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
		.csrf().disable()
		.cors().configurationSource(new CorsConfigurationSource() {
					
					@Override
					public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
						
						CorsConfiguration cfg = new CorsConfiguration();
						
						cfg.setAllowedOrigins(Arrays.asList(
								
								"http://localhost:5173"
								
								
							)
						);
						
						cfg.setAllowedMethods(Collections.singletonList("*"));
						cfg.setAllowCredentials(true);
						cfg.setAllowedHeaders(Collections.singletonList("*"));
						cfg.setExposedHeaders(Arrays.asList("Authorization"));
						cfg.setMaxAge(3600L);
						return cfg;
						
					}
				})
		.and()
		.httpBasic()
		.and()
		.formLogin();
		
		return http.build();
		
	}
	
	
	
	
	
	
	
	
	
	

	public DaoAuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
		daoAuthenticationProvider.setUserDetailsService(customUserDetails());
		return daoAuthenticationProvider;

	}

	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {

		return authenticationConfiguration.getAuthenticationManager();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	

}
