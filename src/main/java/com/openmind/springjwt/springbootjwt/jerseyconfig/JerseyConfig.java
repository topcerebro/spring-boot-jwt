package com.openmind.springjwt.springbootjwt.jerseyconfig;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.openmind.springjwt.springbootjwt.controllers.PetsInformationResource;
import com.openmind.springjwt.springbootjwt.controllers.UserControllerResource;

@Component
public class JerseyConfig extends ResourceConfig {

	
	public JerseyConfig() {
		register(UserControllerResource.class);
		register(PetsInformationResource.class);
	}
	
}
