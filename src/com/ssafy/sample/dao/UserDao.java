package com.ssafy.sample.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.ssafy.sample.model.UserDto;
public interface UserDao {
	UserDto login(String email, String password) throws SQLException;
	void join(UserDto userDto) throws SQLException;
	boolean idcheck(String email) throws SQLException; 
	Map<Integer, String> sido_map() throws SQLException;
	Map<Integer, String> gugun_map(int sido_code) throws SQLException;
}
