package com.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.user.model.User;

@Service
public class UserMapper implements RowMapper<User> {

	@Autowired
	private JdbcTemplate jdbTemplate;

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {

		User user = new User();
		user.setUserId(rs.getLong("user_id"));
		user.setUserName(rs.getString("user_name"));
		user.setFirstName(rs.getString("first_name"));
		user.setLastName(rs.getString("last_name"));
		user.setEmail(rs.getString("user_email"));
		user.setUserRole(rs.getString("user_role"));
		return user;
	}

	public User getUserDetails(long userId) {

		String sql = "select user_id, user_name, first_name, last_name, user_email, user_role from loginadm01.t_user where user_id=?";
		User user = jdbTemplate.queryForObject(sql, new Object[] { userId }, new UserMapper());
		return user;
	}
}
