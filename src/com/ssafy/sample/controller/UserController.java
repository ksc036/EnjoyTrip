package com.ssafy.sample.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import com.ssafy.sample.model.UserDto;
import com.ssafy.sample.service.UserService;
import com.ssafy.sample.service.UserServiceImpl;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService userService;

	@Override
	public void init() throws ServletException {
		userService = UserServiceImpl.getInstance();
		super.init();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("application/x-json; charset=UTF-8");

		String action = request.getParameter("action");
		String path = "";
		if ("login".equals(action)) {
			response.getWriter().write(login(request, response));
			
		}else if ("gugun_map".equals(action)) {
			
			response.getWriter().write(gugun_map(request, response));
			
		} else if ("logout".equals(action)) {
			path = logout(request, response);
			redirect(request, response, path);
		} else if ("mvjoin".equals(action)) {
			path = mvjoin(request, response);
			forward(request, response, path);
		} else if ("join".equals(action)) {
			path = join(request, response);
			redirect(request, response, path);
		} else if ("idcheck".equals(action)) {
			response.getWriter().write(idcheck(request, response));
		} else {
			path = "/home.jsp";
			redirect(request, response, path);
		}
	}

	private String login(HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		try {
			UserDto userDto = userService.login(email, password);
			if (userDto != null) { //
				HttpSession session = request.getSession();
				session.setAttribute("userInfo", userDto);
				return "success";
			} else { // 로그인 실패
				return "fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}

	}
	
	private String gugun_map(HttpServletRequest request, HttpServletResponse response) {
		int sido_code = Integer.parseInt(request.getParameter("sido_code"));
		try {
			String str  = userService.gugun_map(sido_code);
			return str;
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}

	}
	
	private String idcheck(HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter("email");
		
		try {
			if(userService.idcheck(email)) 
				return "success";
			else
				return "fail";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	private String join(HttpServletRequest request, HttpServletResponse response) {

		UserDto userDto = new UserDto();
		userDto.setEmail(request.getParameter("join-email"));
		userDto.setPassword(request.getParameter("join-password"));
		userDto.setNickname(request.getParameter("nickname"));
		userDto.setUsername(request.getParameter("username"));
		userDto.setSido_code(Integer.parseInt(request.getParameter("sido_code")));
		userDto.setGugun_code(Integer.parseInt(request.getParameter("gugun_code")));
		userDto.setAddress(request.getParameter("address"));
		
		
		try {
			userService.join(userDto);
			return "/home.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return "/error/error.jsp";
		}

	}
	
	private String mvjoin(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			request.setAttribute("sido_map", userService.sido_map());
			return "/join.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return "/error/error.jsp";
		}

	} 	
	

	private String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "/home.jsp";
	}

	private void forward(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

	private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
		response.sendRedirect(request.getContextPath() + path);
	}

}
