package com.openmind.springjwt.springbootjwt.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.openmind.springjwt.domain.User;
import com.openmind.springjwt.domain.Users;
import com.openmind.springjwt.springbootjwt.jpa.repository.UserRepository;

import ch.qos.logback.core.status.Status;

@Component
@Path("users")
public class UserControllerResource {

	@Autowired(required = true)
	private UserRepository userRepository;

	@Autowired
	@Qualifier("bcrypPasswordEncoder")
	private BCryptPasswordEncoder encoder;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response showAllUsers() {

		try {

			final List<User> allUsers = new ArrayList<User>();

			Iterable<com.openmind.springjwt.springbootjwt.jpa.entities.User> users = userRepository.findAll();

			users.forEach((entity) -> {

				User user = new User();
				user.setId(entity.getId());
				user.setUserName(entity.getName());
				user.setPassword(entity.getPassword());
				user.setAuthorities(entity.getAuthorities());
				allUsers.add(user);
				
			});
			
			Users allSystemUsers =  new Users();
			allSystemUsers.setUsers(allUsers);
			return Response.ok(allSystemUsers).build();
		} catch (Exception e) {
			return Response.serverError().status(Status.ERROR).build();
		}

	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response createUser(User user) {

		com.openmind.springjwt.springbootjwt.jpa.entities.User entity = new com.openmind.springjwt.springbootjwt.jpa.entities.User();
		entity.setName(user.getUserName());
		entity.setPassword(encoder.encode(user.getPassword()));
		entity.setAuthorities(user.getAuthorities());
		try {

			com.openmind.springjwt.springbootjwt.jpa.entities.User newUser = userRepository.save(entity);

			user.setId(newUser.getId());
			user.setUserName(newUser.getName());
			user.setPassword("");
			user.setAuthorities(newUser.getAuthorities());

			return Response.ok(user).build();

		} catch (RuntimeException e) {
			return Response.status(Status.ERROR).build();
		}

	}

}
