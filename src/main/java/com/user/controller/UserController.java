package com.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.model.User;
import com.user.services.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	public UserService userService;

	@RequestMapping(value = "{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody User getUserInfo(@PathVariable("userId") long userId) {

		User user = userService.getUserInformation(userId);
		return user;
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody User createUserInfo(@RequestBody User user) {

		userService.createNewUser(user);
		return userService.getUserInformation(user.getUserId());
	}
}
