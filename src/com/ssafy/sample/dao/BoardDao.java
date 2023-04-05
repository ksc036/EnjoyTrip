package com.ssafy.sample.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.sample.model.BoardDto;


public interface BoardDao {

	void write(BoardDto boardDto) throws SQLException;
	List<BoardDto> list(Map<String, Object> param) throws SQLException;
	int getTotalArticleCount(Map<String, Object> param) throws SQLException;
	BoardDto getArticle(int articleNo) throws SQLException;
	void updateHit(int articleNo) throws SQLException;

	
	void modify(BoardDto boardDto) throws SQLException;
	void delete(int articleNo) throws SQLException;
	
	
	
}
