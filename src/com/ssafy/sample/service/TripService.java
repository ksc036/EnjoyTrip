package com.ssafy.sample.service;

import java.util.List;

import com.ssafy.sample.model.BoardDto;
import com.ssafy.sample.model.TripDto;

public interface TripService {
	List<TripDto> loadLocationService() throws Exception;

	List<TripDto> loadRandomInfoService() throws Exception;

	TripDto viewDetailService(String content_id) throws Exception;

	List<TripDto> searchTripService(String keyword, String content_type_id, String sido_code) throws Exception;

	List<TripDto> searchNearByResService(String swLa, String swLo, String neLa, String neLo) throws Exception;

	List<TripDto> searchNearByHotPlaceService(String swLa, String swLo, String neLa, String neLo) throws Exception;

	List<TripDto> loadRandomInfoUseUserService(String sido_code, String gugun_code) throws Exception;

	List<TripDto> searchByKeyWordInMapService(String keyword)throws Exception;
}
