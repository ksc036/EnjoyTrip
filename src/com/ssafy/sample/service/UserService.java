package com.ssafy.sample.service;

import java.util.Map;

import com.ssafy.sample.model.UserDto;

public interface UserService {
	UserDto login(String email, String password) throws Exception;
	void join(UserDto userDto) throws Exception;
	boolean idcheck(String email) throws Exception;
	Map<Integer, String> sido_map() throws Exception;
	String gugun_map(int sido_code) throws Exception;

	

}
