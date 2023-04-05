package com.ssafy.sample.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.sample.model.BoardDto;
import com.ssafy.sample.model.TripDto;

public interface TripDao {
	List<TripDto> loadLocationService() throws SQLException;
	List<TripDto> loadRandomInfoDao() throws SQLException;
	TripDto viewDetailDao(String content_id) throws SQLException;
	List<TripDto> searchTripDao(String keyword, String content_type_id, String sido_code) throws SQLException;
	List<TripDto> searchNearByResDao(String swLa, String swLo, String neLa, String neLo) throws SQLException;
}
