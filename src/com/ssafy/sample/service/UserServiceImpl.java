package com.ssafy.sample.service;

import java.sql.SQLException;
import java.util.regex.Pattern;

import com.ssafy.sample.dao.UserDao;
import com.ssafy.sample.dao.UserDaoImpl;
import com.ssafy.sample.model.UserDto;

public class UserServiceImpl implements UserService {

	public static UserService userService = new UserServiceImpl();
	private UserDao userDao;

	private UserServiceImpl() {
		userDao = UserDaoImpl.getInstance();
	}

	public static UserService getInstance() {
		return userService;
	}

	@Override
	public UserDto login(String email, String password) throws Exception {
		return userDao.login(email, password);
	}
	


	
	@Override
	public void join(UserDto userDto) throws Exception {
		userDao.join(userDto);
	}
	
	
	
	@Override
	public boolean idcheck(String email) throws Exception {

		if(userDao.idcheck(email)&&(Pattern.matches("[a-z0-9]+@[a-z]+\\.[a-z]{2,3}", email))) {
			return userDao.idcheck(email);
		}else {
			return false ;
		}

	}

}
