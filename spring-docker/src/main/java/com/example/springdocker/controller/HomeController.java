package com.example.springdocker.controller;

import com.example.springdocker.entity.User;
import com.example.springdocker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class HomeController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private UserService userService;

	@GetMapping("/hello")
	public String hello(){
		return "hello, world!";
	}

	@GetMapping("/listUser")
	public List<User> listUser(){
		return userService.getUsers();
	}

	@GetMapping("/addUser")
	public User addUser(@RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname) {
		User user = new User();
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setUsername("hiren");
		user.setPassword(new BCryptPasswordEncoder().encode("pass"));
		return userService.saveUser(user);


	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> login(@RequestHeader HttpHeaders reqHeaders, @RequestBody String body){
		ResponseEntity<String> response;
		HttpEntity entity = new HttpEntity(body, reqHeaders);
		response = restTemplate.exchange("http://localhost:8080/spring-docker-service/oauth/token", HttpMethod.POST, entity, String.class);
		return response;
	}

	
}
