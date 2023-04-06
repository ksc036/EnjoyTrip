package com.ssafy.sample.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.sample.model.UserDto;
import com.ssafy.sample.util.DBUtil;

public class UserDaoImpl implements UserDao {

	private static UserDao userDao = new UserDaoImpl();
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
			if (rs.next()) {
				userDto = new UserDto();
				userDto.setEmail(rs.getString("email"));
				userDto.setUsername(rs.getString("username"));
				userDto.setAddress(rs.getString("address"));
				userDto.setSido_code(rs.getInt("sido_code"));
				userDto.setGugun_code(rs.getInt("gugun_code"));

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
			sql.append("insert into user (username, nickname, email, password, sido_code, gugun_code, address) \n");
			sql.append("values (?, ?, ?, ?, ?, ?, ?)");
			pstmt = conn.prepareStatement(sql.toString());

			String username = (userDto.getUsername().length() != 0) ? userDto.getUsername() : "김싸피";
			String nickname = (userDto.getNickname().length() != 0) ? userDto.getNickname() : "싸피킴";
			String email = userDto.getEmail();
			String password = (userDto.getPassword().length() != 0) ? userDto.getPassword() : "1111";
			int sido_code = userDto.getSido_code();
			int gugun_code = userDto.getGugun_code();
			
			String address = (userDto.getAddress().length() != 0) ? userDto.getAddress() : "지옥";
			
			

			pstmt.setString(1, username);
			pstmt.setString(2, nickname);
			pstmt.setString(3, email);
			pstmt.setString(4, password);
			pstmt.setInt(5, sido_code);
			pstmt.setInt(6, gugun_code);
			pstmt.setString(7, address);
			pstmt.executeUpdate();

		} finally {
			dbUtil.close(rs, pstmt, conn);
		}

	}

	@Override
	public boolean idcheck(String email) throws SQLException {
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
			if (rs.next()) {
				return false;
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}

		return true;
	}

	@Override
	public Map<Integer, String> sido_map() throws SQLException {
		Map<Integer, String> map = new LinkedHashMap<Integer, String>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = dbUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from sido order by sido_code");
			while (rs.next()) {
				map.put(rs.getInt("sido_code"), rs.getString("sido_name"));
			}

		} finally {
			dbUtil.close(rs, stmt, conn);
		}
		return map;
	}

	@Override
	public Map<Integer, String> gugun_map(int sido_code) throws SQLException {
		Map<Integer, String> map = new LinkedHashMap<Integer, String>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from gugun \n");
			sql.append("where sido_code = ? \n"); 
			sql.append("order by gugun_code"); 
			
			
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, sido_code);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				map.put(rs.getInt("gugun_code"), rs.getString("gugun_name"));
			}
			
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return map;
	}

}
