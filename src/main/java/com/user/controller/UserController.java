package com.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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

	/**
	 * GET registration form
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/registration", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String registration(Model model) {
		model.addAttribute("userForm", new User());
		return "registration";
	}

	/**
	 * Create user form
	 * 
	 * @param userForm
	 * @return
	 */
	@RequestMapping(value = "/registration", method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView registerUser(@ModelAttribute("user") User userForm) {
		System.out.println(userForm);
		userService.createNewUser(userForm);
		return new ModelAndView("redirect:/user/" + userForm.getUserName());
	}

	/**
	 * Get user by id
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/id/{userId}", method = RequestMethod.GET)
	public ModelAndView getUserInfo(@PathVariable("userId") long userId) {
		User user = userService.getUserInformation(userId);
		return new ModelAndView("user", "user", user);
	}

	/**
	 * Get user by user name
	 * 
	 * @param userName
	 * @return
	 */

	@RequestMapping(value = "/{userName}", method = RequestMethod.GET)
	public ModelAndView getUserByUserName(@PathVariable("userName") String userName) {
		User user = userService.getUserByUsername(userName);
		return new ModelAndView("user", "user", user);
	}

	@RequestMapping(value = "/json/createuser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody void createUserInfo(@RequestBody User user) {
		userService.createNewUser(user);
	}

	@RequestMapping(value = "/json/{userName:.+}", method = RequestMethod.GET)
	public @ResponseBody User getUserInfoByUserName(@PathVariable("userName") String userName) {
		User user = userService.getUserByUsername(userName);
		return user;
	}
}
