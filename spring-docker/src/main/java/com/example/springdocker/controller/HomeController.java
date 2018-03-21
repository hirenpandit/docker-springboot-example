package com.example.springdocker.controller;

import com.example.springdocker.entity.User;
import com.example.springdocker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

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
		return userService.saveUser(user);


	}

	
}
