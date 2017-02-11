package com.user.constoller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.user.model.User;

@Controller
public class UserController {

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
}
