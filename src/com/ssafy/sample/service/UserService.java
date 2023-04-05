package com.ssafy.sample.service;

import java.sql.SQLException;

import com.ssafy.sample.model.UserDto;

public interface UserService {
	UserDto login(String email, String password) throws Exception;
	void join(UserDto userDto) throws Exception;
	boolean idcheck(String email) throws Exception;

	

}
