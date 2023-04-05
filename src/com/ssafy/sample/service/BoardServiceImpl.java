package com.ssafy.sample.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.sample.dao.BoardDao;
import com.ssafy.sample.dao.BoardDaoImpl;
import com.ssafy.sample.model.BoardDto;
import com.ssafy.sample.util.DBUtil;
import com.ssafy.sample.util.PageNavigation;
import com.ssafy.sample.util.SizeConstant;


public class BoardServiceImpl implements BoardService {

	private BoardDao boardDao = BoardDaoImpl.getInstance();
	private static BoardService boardService = new BoardServiceImpl(); 
	private BoardServiceImpl() {
		super();
	}
	
	public static BoardService getInstance() {
		return boardService;
	}
	
	
	@Override
	public void write(BoardDto boardDto) throws Exception {
		boardDao.write(boardDto);

	}

	@Override
	public List<BoardDto> list(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		String key = map.get("key");
		param.put("key", key.isEmpty() ? "" : key);
		param.put("word", map.get("word").isEmpty() ? "" : map.get("word"));
		int pgno = Integer.parseInt(map.get("pgno"));
		int start = pgno * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
		param.put("start", start);
		param.put("listsize", SizeConstant.LIST_SIZE);
		return boardDao.list(param);
	}

	@Override
	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();

		int naviSize = SizeConstant.NAVIGATION_SIZE;
		int sizePerPage = SizeConstant.LIST_SIZE;
		int currentPage = Integer.parseInt(map.get("pgno"));

		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		Map<String, Object> param = new HashMap<String, Object>();
		String key = map.get("key");
//		if ("userid".equals(key))
//			key = "user_id";
		param.put("key", key.isEmpty() ? "" : key);
		param.put("word", map.get("word").isEmpty() ? "" : map.get("word"));
		int totalCount = boardDao.getTotalArticleCount(param);
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();

		return pageNavigation;
	}

	@Override
	public BoardDto getArticle(int articleNo) throws Exception {
		return boardDao.getArticle(articleNo);
	}

	@Override
	public void updateHit(int articleNo) throws Exception {
		boardDao.updateHit(articleNo);

	}

	@Override
	public void modify(BoardDto boardDto) throws Exception {
		boardDao.modify(boardDto);

	}

	@Override
	public void delete(int articleNo) throws Exception {
		boardDao.delete(articleNo);
	}

}
