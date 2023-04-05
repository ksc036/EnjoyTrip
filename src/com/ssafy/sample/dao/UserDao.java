package com.ssafy.sample.dao;

import java.sql.SQLException;

import com.ssafy.sample.model.UserDto;

public interface UserDao {
	UserDto login(String email, String password) throws SQLException;
	void join(UserDto userDto) throws SQLException;
	boolean idcheck(String email) throws SQLException; 
}
