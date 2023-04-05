package com.ssafy.sample.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.sample.model.BoardDto;
import com.ssafy.sample.model.UserDto;
import com.ssafy.sample.service.BoardService;
import com.ssafy.sample.service.BoardServiceImpl;
import com.ssafy.sample.util.PageNavigation;
import com.ssafy.sample.util.ParameterCheck;

@WebServlet("/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private int pgno;
	private String key;
	private String word;
	private String queryString;

	private BoardService boardService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		boardService = BoardServiceImpl.getInstance();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		pgno = ParameterCheck.notNumberToOne(request.getParameter("pgno"));
		key = ParameterCheck.nullToBlank(request.getParameter("key"));
		word = ParameterCheck.nullToBlank(request.getParameter("word"));
		queryString = "?pgno=" + pgno + "&key=" + key + "&word=" + URLEncoder.encode(word, "utf-8");

		String path = "";
		if ("list".equals(action)) {
			path = list(request, response);
			forward(request, response, path);
		} else if ("view".equals(action)) {
			path = view(request, response);
			forward(request, response, path);
		} else if ("mvwrite".equals(action)) {
			path = "/write.jsp";
			redirect(request, response, path);
		} else if ("write".equals(action)) {
			path = write(request, response);
			forward(request, response, path);
		} else if ("mvmodify".equals(action)) {
			path = mvModify(request, response);
			forward(request, response, path);
		} else if ("modify".equals(action)) {
			path = modify(request, response);
			forward(request, response, path);
		} else if ("delete".equals(action)) {
			path = delete(request, response);
			redirect(request, response, path);
		} else {
			redirect(request, response, path);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

	private void forward(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

	private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
		response.sendRedirect(request.getContextPath() + path);
	}

	private String list(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		UserDto userDto = (UserDto) session.getAttribute("userInfo");
		
			try {
				Map<String, String> map = new HashMap<String, String>();
				map.put("pgno", pgno + "");
				map.put("key", key);
				map.put("word", word);

				List<BoardDto> list = boardService.list(map);
				request.setAttribute("articles", list);

				PageNavigation pageNavigation = boardService.makePageNavigation(map);
				request.setAttribute("navigation", pageNavigation);

				return "/board.jsp" + queryString;
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "글목록 출력 중 문제 발생!!!");
				return "/error/error.jsp";
			}
	
	}

	private String view(HttpServletRequest request, HttpServletResponse response) {

		
			int articleNo = Integer.parseInt(request.getParameter("articleno"));
			try {
				BoardDto boardDto = boardService.getArticle(articleNo);
				boardService.updateHit(articleNo);
				request.setAttribute("article", boardDto);

				return "/view.jsp" + queryString;
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "글내용 출력 중 문제 발생!!!");
				return "/error/error.jsp";
			}

	}
	
	private String write(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		UserDto userDto = (UserDto) session.getAttribute("userInfo");
		if (userDto != null) {
			BoardDto boardDto = new BoardDto();
			boardDto.setEmail(userDto.getEmail());
			boardDto.setTitle(request.getParameter("title"));
			boardDto.setContent(request.getParameter("content"));
			try {
				boardService.write(boardDto);
				return "/board?action=list";
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "글작성 중 문제 발생!!!");
				return "/error/error.jsp";
			}
		} else
			return "/user/login.jsp";
	}
	
	

	private String mvModify(HttpServletRequest request, HttpServletResponse response) {
		int articleNo = Integer.parseInt(request.getParameter("articleno"));
		try {
			BoardDto boardDto = boardService.getArticle(articleNo);
			boardService.updateHit(articleNo);
			request.setAttribute("article", boardDto);

			return "/modify.jsp" + queryString;
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "글 수정 중 문제 발생!!!");
			return "/error/error.jsp";
		}
		
		
		
	}

	private String modify(HttpServletRequest request, HttpServletResponse response) {
		
		int articleNo = Integer.parseInt(request.getParameter("articleno"));
		BoardDto boardDto = new BoardDto();
		boardDto.setTitle(request.getParameter("title"));
		boardDto.setContent(request.getParameter("content"));
		boardDto.setArticleNo(Integer.parseInt(request.getParameter("articleno")));
		try {
			boardService.modify(boardDto);
			return "/board" + queryString +"&action=view&acticleno=" + articleNo ;
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "수정 중 문제 발생!!!");
			return "/error/error.jsp" ;
		}
		
		
		
		
		

	}

	private String delete(HttpServletRequest request, HttpServletResponse response) {
		int articleNo = Integer.parseInt(request.getParameter("articleno"));
		
		try {
			boardService.delete(articleNo);
			return "/board" + queryString +"&action=list"   ;
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "삭제 중 문제 발생!!!");
			return "/error/error.jsp" ;
		}
	}

}
