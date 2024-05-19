package com.blog.api.Cloud;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

@Configuration
public class CloudinaryCloud {
	
	private final String CLOUD_NAME = "dwa1sm1f2";
    private final String API_KEY = "764819777455659";
    private final String API_SECRET = "1DXYXSbygsyiPzUr7bke6aObGBc";
    
    @Bean
    public Cloudinary cloudinary(){
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name",CLOUD_NAME);
        config.put("api_key",API_KEY);
        config.put("api_secret",API_SECRET);

        return new Cloudinary(config);
    }

}
