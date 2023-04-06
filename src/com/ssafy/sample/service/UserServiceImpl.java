package com.ssafy.sample.service;

import java.util.Map;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;

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

	@Override
	public Map<Integer, String> sido_map() throws Exception {
		return userDao.sido_map();
	}
	
	@Override
	public String gugun_map(int sido_code) throws Exception {
		JSONObject jsonObject = new JSONObject(userDao.gugun_map( sido_code));
		return jsonObject.toJSONString();
	}

}
