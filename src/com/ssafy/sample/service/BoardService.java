package com.ssafy.sample.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.sample.model.BoardDto;
import com.ssafy.sample.util.PageNavigation;

public interface BoardService {
	
	void write(BoardDto boardDto) throws Exception;
	List<BoardDto> list(Map<String, String> map) throws Exception;
	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	BoardDto getArticle(int articleNo) throws Exception;
	void updateHit(int articleNo) throws Exception;
	
	void modify(BoardDto boardDto) throws Exception;
	void delete(int articleNo) throws Exception;

}
