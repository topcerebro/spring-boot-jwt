package com.openmind.springjwt.springbootjwt.security.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public final class SecurityUtils {
	/**
	* Privents instantiation of this class.
	*/
	private SecurityUtils (){
	}

	@Bean
	public static BCryptPasswordEncoder bcrypPasswordEncoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
		return encoder;
	}
	
}
