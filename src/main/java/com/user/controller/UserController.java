package com.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.user.model.User;
import com.user.services.UserService;

@Controller
@RequestMapping(value = "user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/hello", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String hello(Model model,
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		model.addAttribute("userForm", new User());
		model.addAttribute("name", name);
		return "hello";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String registration(Model model) {
		model.addAttribute("userForm", new User());
		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
		model.addAttribute("userForm", new User());
		return "registration";
	}

	@RequestMapping(value = "{userId}", method = RequestMethod.GET)
	public ModelAndView getUserInfo(@PathVariable("userId") long userId) {
		User user = userService.getUserInformation(userId);
		return new ModelAndView("user","user",user);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody void createUserInfo(@RequestBody User user) {

		userService.createNewUser(user);
	}
	
	@RequestMapping(value = "/json/{userName}", method = RequestMethod.GET)
	public @ResponseBody User getUserInfoByUserName(@PathVariable("userName") String userName) {
		User user = userService.getUserByUsername(userName);
		return user;
	}
}
