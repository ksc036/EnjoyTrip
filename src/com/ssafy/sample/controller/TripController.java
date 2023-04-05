package com.ssafy.sample.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ssafy.sample.model.TripDto;
import com.ssafy.sample.service.TripService;
import com.ssafy.sample.service.TripServiceImpl;

import jdk.nashorn.api.scripting.JSObject;

/**
 * Servlet implementation class TripController
 */
@WebServlet("/trip")
public class TripController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TripService tripService;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		tripService = TripServiceImpl.getTripSerivce();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		String path = "";
		System.out.println(action);
		if ("loadLocation".equals(action)) {
			out.print(loadLocation(request, response));
		} else if ("loadRandomInfo".equals(action)) {
			out.print(loadRandomInfo(request, response));
		} else if ("viewDetail".equals(action)) {
			out.print(viewDetail(request, response));
		} else if ("search".equals(action)) {
			out.print(searchTrip(request,response));
		}else if ("goMap".equals(action)) {
			path = goMap(request,response);
			forward(request, response, path);
		} else if ("searchNearByRes".equals(action)) {
			out.print(searchNearByRes(request, response));
		}else if ("searchNearByHotPlace".equals(action)) {
			out.print(searchNearByHotPlace(request, response));
		}else {
			redirect(request, response, path);
		}
	}

	private String searchNearByHotPlace(HttpServletRequest request, HttpServletResponse response) {
		String swLa = request.getParameter("swLa");
		String swLo = request.getParameter("swLo");
		String neLa = request.getParameter("neLa");
		String neLo = request.getParameter("neLo");
		
		JSONObject items = new JSONObject();
		JSONArray jsonarr = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		try {
			List<TripDto> lst = tripService.searchNearByHotPlaceService(swLa,swLo,neLa,neLo);
			for (TripDto tripDto : lst) {
				jsonObj = new JSONObject();
				jsonObj.put("latitude",tripDto.getLatitude());
				jsonObj.put("longitude",tripDto.getLongitude());
				jsonObj.put("firstimage", tripDto.getFirstimage());
				jsonObj.put("firstimage2", tripDto.getFirstimage2());
				jsonObj.put("contentid", tripDto.getContentid());
				jsonObj.put("title", tripDto.getTitle());
				jsonObj.put("tel", tripDto.getTel());
				jsonarr.add(jsonObj);
			}
			items.put("items", jsonarr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return items.toString();
	}

	private String searchNearByRes(HttpServletRequest request, HttpServletResponse response) {
		String swLa = request.getParameter("swLa");
		String swLo = request.getParameter("swLo");
		String neLa = request.getParameter("neLa");
		String neLo = request.getParameter("neLo");
		
		JSONObject items = new JSONObject();
		JSONArray jsonarr = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		try {
			List<TripDto> lst = tripService.searchNearByResService(swLa,swLo,neLa,neLo);
			for (TripDto tripDto : lst) {
				jsonObj = new JSONObject();
				jsonObj.put("latitude",tripDto.getLatitude());
				jsonObj.put("longitude",tripDto.getLongitude());
				jsonObj.put("firstimage", tripDto.getFirstimage());
				jsonObj.put("firstimage2", tripDto.getFirstimage2());
				jsonObj.put("contentid", tripDto.getContentid());
				jsonObj.put("title", tripDto.getTitle());
				jsonObj.put("tel", tripDto.getTel());
				jsonarr.add(jsonObj);
			}
			items.put("items", jsonarr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return items.toString();
	}

	private String goMap(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("latitude", request.getParameter("latitude"));
		request.setAttribute("longitude", request.getParameter("longitude"));
		request.setAttribute("title", request.getParameter("title"));
		return "/map.jsp";
	}

	private String searchTrip(HttpServletRequest request, HttpServletResponse response) {
		String content_type_id = request.getParameter("content_type_id");
		String sido_code = request.getParameter("sido_code");
		String keyword = request.getParameter("keyword");
		
		JSONObject items = new JSONObject();
		JSONArray jsonarr = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		
		try {
			List<TripDto> lst = tripService.searchTripService(keyword,content_type_id,sido_code);
			for (TripDto tripDto : lst) {
				jsonObj = new JSONObject();
				jsonObj.put("latitude",tripDto.getLatitude());
				jsonObj.put("longitude",tripDto.getLongitude());
				jsonObj.put("firstimage", tripDto.getFirstimage());
				jsonObj.put("firstimage2", tripDto.getFirstimage2());
				jsonObj.put("contentid", tripDto.getContentid());
				jsonObj.put("title", tripDto.getTitle());
				jsonObj.put("tel", tripDto.getTel());
				jsonarr.add(jsonObj);
			}
			items.put("items", jsonarr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return items.toString();
	}

	private String viewDetail(HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObj = new JSONObject();
		try {
			TripDto tripDto = tripService.viewDetailService(request.getParameter("content_id"));
			jsonObj = new JSONObject();
			jsonObj.put("firstimage", tripDto.getFirstimage());
			jsonObj.put("firstimage2", tripDto.getFirstimage2());
			jsonObj.put("contentid", tripDto.getContentid());
			jsonObj.put("title", tripDto.getTitle());
			jsonObj.put("tel", tripDto.getTel());
			jsonObj.put("description", tripDto.getDescription());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObj.toString();
	}

	private String loadRandomInfo(HttpServletRequest request, HttpServletResponse response) {
		JSONObject items = new JSONObject();
		JSONArray jsonarr = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		try {
			List<TripDto> lst = tripService.loadRandomInfoService();
			for (TripDto tripDto : lst) {
				jsonObj = new JSONObject();
				jsonObj.put("latitude",tripDto.getLatitude());
				jsonObj.put("longitude",tripDto.getLongitude());
				jsonObj.put("firstimage", tripDto.getFirstimage());
				jsonObj.put("firstimage2", tripDto.getFirstimage2());
				jsonObj.put("contentid", tripDto.getContentid());
				jsonObj.put("title", tripDto.getTitle());
				jsonObj.put("tel", tripDto.getTel());
				jsonarr.add(jsonObj);
			}
			items.put("items", jsonarr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return items.toString();
	}

	private String loadLocation(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject items = new JSONObject();
		JSONArray jsonarr = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		try {
			List<TripDto> lst = tripService.loadLocationService();
			for (int i = 0; i < lst.size(); i++) {
				jsonObj = new JSONObject();
				jsonObj.put("name", lst.get(i).getName());
				jsonObj.put("code", lst.get(i).getAreacode());
				jsonarr.add(jsonObj);
			}
			items.put("items", jsonarr);
		} catch (Exception e) {
			System.out.println("치명적에러가 발생하였습니다.");
			response.sendRedirect("/project/home.jsp");
			e.printStackTrace();
		}
		return items.toString();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
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

}
