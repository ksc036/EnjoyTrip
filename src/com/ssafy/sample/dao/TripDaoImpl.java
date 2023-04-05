package com.ssafy.sample.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ssafy.sample.model.BoardDto;
import com.ssafy.sample.model.TripDto;
import com.ssafy.sample.util.DBUtil;

public class TripDaoImpl implements TripDao {

	private DBUtil dbUtil;

	private TripDaoImpl() {
		dbUtil = DBUtil.getInstance();
	}

	private static TripDao tripDao = new TripDaoImpl();

	public static TripDao getTripDao() {
		return tripDao;
	}

	@Override
	public List<TripDto> loadLocationService() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TripDto> list = new ArrayList<>();

		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder("select * from sido ");
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				TripDto tripDto = new TripDto();
				tripDto.setAreacode(rs.getString("sido_code"));
				tripDto.setName(rs.getString("sido_name"));
				list.add(tripDto);
			}
		} finally {
			dbUtil.close(pstmt, rs, conn);
		}
		return list;
	}

	@Override
	public List<TripDto> loadRandomInfoDao() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TripDto> list = new ArrayList<>();

		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder(
					"select * from attraction_info  where CHAR_LENGTH(first_image)>1 order BY RAND() LIMIT 32 ");
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				TripDto tripDto = new TripDto();
				tripDto.setLatitude(rs.getString("latitude"));
				tripDto.setLongitude(rs.getString("longitude"));

				tripDto.setContentid(rs.getString("content_id"));
				tripDto.setContenttypeid(rs.getString("content_type_id"));
				tripDto.setTitle(rs.getString("title"));
				tripDto.setAddr1(rs.getString("addr1"));
				tripDto.setAddr2(rs.getString("addr2"));
				tripDto.setTel(rs.getString("tel"));
				tripDto.setFirstimage(rs.getString("first_image"));
				tripDto.setFirstimage2(rs.getString("first_image2"));
				list.add(tripDto);
			}
		} finally {
			dbUtil.close(pstmt, rs, conn);
		}
		return list;
	}

	@Override
	public TripDto viewDetailDao(String content_id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		TripDto tripDto = new TripDto();
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder(
					"select info.content_id content_id, info.content_type_id content_type_id, info.title title, info.addr1 addr1, info.addr2 addr2, info.first_image first_image, info.first_image2 first_image2,\r\n"
							+ "des.overview description, info.content_type_id content_type_id, info.tel tel\r\n"
							+ "from attraction_info info,  attraction_description des \r\n"
							+ "where info.content_id = des.content_id \r\n" + "and  \r\n" + "des.content_id = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, Integer.parseInt(content_id));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				tripDto.setDescription(rs.getString("description"));
				tripDto.setContentid(rs.getString("content_id"));
				tripDto.setContenttypeid(rs.getString("content_type_id"));
				tripDto.setTitle(rs.getString("title"));
				tripDto.setAddr1(rs.getString("addr1"));
				tripDto.setAddr2(rs.getString("addr2"));
				tripDto.setTel(rs.getString("tel"));
				tripDto.setFirstimage(rs.getString("first_image"));
				tripDto.setFirstimage2(rs.getString("first_image2"));
			}
		} finally {
			dbUtil.close(pstmt, rs, conn);
		}
		return tripDto;

	}

	@Override
	public List<TripDto> searchTripDao(String keyword, String content_type_id, String sido_code) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TripDto> list = new ArrayList<>();

		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder(
					"select info.latitude latitude, info.longitude longitude, info.content_id content_id, info.content_type_id content_type_id, info.title title, info.addr1 addr1, info.addr2 addr2, info.first_image first_image, info.first_image2 first_image2, \r\n"
							+ "des.overview description, info.content_type_id content_type_id, info.tel tel \r\n"
							+ "from attraction_info info,  attraction_description des \r\n"
							+ "where info.content_id = des.content_id and content_type_id = ? and sido_code= ? and (title like ?  or des.overview like ?) order by readcount limit 40 ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, content_type_id);
			pstmt.setString(2, sido_code);
			pstmt.setString(3, "%" + keyword + "%");
			pstmt.setString(4, "%" + keyword + "%");

			rs = pstmt.executeQuery();
			while (rs.next()) {
				TripDto tripDto = new TripDto();
				tripDto.setLatitude(rs.getString("latitude"));
				tripDto.setLongitude(rs.getString("longitude"));
				tripDto.setContentid(rs.getString("content_id"));
				tripDto.setContenttypeid(rs.getString("content_type_id"));
				tripDto.setTitle(rs.getString("title"));
				tripDto.setAddr1(rs.getString("addr1"));
				tripDto.setAddr2(rs.getString("addr2"));
				tripDto.setTel(rs.getString("tel"));
				tripDto.setFirstimage(rs.getString("first_image"));
				tripDto.setFirstimage2(rs.getString("first_image2"));
				list.add(tripDto);
			}
		} finally {
			dbUtil.close(pstmt, rs, conn);
		}
		return list;
	}

	@Override
	public List<TripDto> searchNearByResDao(String swLa, String swLo, String neLa, String neLo) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TripDto> list = new ArrayList<>();

		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder(
					" select * from attraction_info where longitude between ? and ? and  latitude between ? and ? and content_type_id = 39");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, swLo);
			pstmt.setString(2, neLo);
			pstmt.setString(3, swLa);
			pstmt.setString(4, neLa);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				TripDto tripDto = new TripDto();
				tripDto.setLatitude(rs.getString("latitude"));
				tripDto.setLongitude(rs.getString("longitude"));
				tripDto.setContentid(rs.getString("content_id"));
				tripDto.setContenttypeid(rs.getString("content_type_id"));
				tripDto.setTitle(rs.getString("title"));
				tripDto.setAddr1(rs.getString("addr1"));
				tripDto.setAddr2(rs.getString("addr2"));
				tripDto.setTel(rs.getString("tel"));
				tripDto.setFirstimage(rs.getString("first_image"));
				tripDto.setFirstimage2(rs.getString("first_image2"));
				list.add(tripDto);
			}
		} finally {
			dbUtil.close(pstmt, rs, conn);
		}
		return list;
	}

	@Override
	public List<TripDto> searchNearByHotPlaceDao(String swLa, String swLo, String neLa, String neLo)
			throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TripDto> list = new ArrayList<>();

		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder(
					" select * from attraction_info where longitude between ? and ? and  latitude between ? and ? order by readcount limit 10 ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, swLo);
			pstmt.setString(2, neLo);
			pstmt.setString(3, swLa);
			pstmt.setString(4, neLa);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				TripDto tripDto = new TripDto();
				tripDto.setLatitude(rs.getString("latitude"));
				tripDto.setLongitude(rs.getString("longitude"));
				tripDto.setContentid(rs.getString("content_id"));
				tripDto.setContenttypeid(rs.getString("content_type_id"));
				tripDto.setTitle(rs.getString("title"));
				tripDto.setAddr1(rs.getString("addr1"));
				tripDto.setAddr2(rs.getString("addr2"));
				tripDto.setTel(rs.getString("tel"));
				tripDto.setFirstimage(rs.getString("first_image"));
				tripDto.setFirstimage2(rs.getString("first_image2"));
				list.add(tripDto);
			}
		} finally {
			dbUtil.close(pstmt, rs, conn);
		}
		return list;
	}
}
