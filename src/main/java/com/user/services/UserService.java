package com.user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.dao.UserMapper;
import com.user.model.User;

@Service
public class UserService {

	@Autowired
	public UserMapper userMapper;
	
	public User getUserInformation(long userId){		
		
		User user;
		user = userMapper.getUserDetails(userId);
		return user;
	}
}
