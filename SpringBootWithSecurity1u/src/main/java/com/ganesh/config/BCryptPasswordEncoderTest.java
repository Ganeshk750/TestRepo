package com.ganesh.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderTest {
	
	public static void main(String[] args) {
		
		BCryptPasswordEncoder enCoder = new BCryptPasswordEncoder();
		String encodedPWD = enCoder.encode("admin");
		System.out.println(encodedPWD);
		
		//$2a$10$bcbMRbi2ZZg4kovPxeNSJeADFBqlD7TAgfv.92lTg/YNh9xnKU116
	}

}
