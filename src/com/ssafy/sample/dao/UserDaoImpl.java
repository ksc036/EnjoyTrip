package com.ssafy.sample.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssafy.sample.model.UserDto;
import com.ssafy.sample.util.DBUtil;

public class UserDaoImpl implements UserDao {
	
	private static UserDao userDao =  new UserDaoImpl();
	private DBUtil dbUtil = DBUtil.getInstance();

	private UserDaoImpl() {
		super();
	}
	
	public static UserDao getInstance() {
		return userDao;
	}

	@Override
	public UserDto login(String email, String password) throws SQLException {
		UserDto userDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * \n");
			sql.append("from user \n");
			sql.append("where email = ? and password = ? \n");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				userDto = new UserDto();
				userDto.setEmail(rs.getString("email"));
				userDto.setUsername(rs.getString("username"));
				userDto.setAddress(rs.getString("address"));

			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return userDto;
	}
		
		
		
		
	@Override
	public void join(UserDto userDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into user (username, nickname, email, password, address, address2) \n");
			sql.append("values (?, ?, ?, ?, ?, ?)");
			pstmt = conn.prepareStatement(sql.toString());
			
			String username = (userDto.getUsername().length() !=0) ? userDto.getUsername() : "김싸피";
			String nickname = (userDto.getNickname().length() !=0) ? userDto.getNickname() : "싸피킴";
			String email = userDto.getEmail();
			String password = (userDto.getPassword().length() !=0) ? userDto.getPassword() : "1111";
			String address = (userDto.getAddress().length() !=0) ? userDto.getAddress() : "서울";
			String address2 = (userDto.getAddress2().length() !=0) ? userDto.getAddress2() : "107동";
			
			
			
			pstmt.setString(1, username);
			pstmt.setString(2, nickname);
			pstmt.setString(3, email);
			pstmt.setString(4, password);
			pstmt.setString(5, address);
			pstmt.setString(6, address2);
			pstmt.executeUpdate();
			

			
		}finally {
			dbUtil.close(rs,pstmt,conn);
		}
		
		
	}

	@Override
	public boolean idcheck(String email) throws SQLException {
		UserDto userDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select email  \n");
			sql.append("from user \n");
			sql.append("where email = ? \n");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return false;
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		
		return true;
	}
	
	
	
	
	
	
}
