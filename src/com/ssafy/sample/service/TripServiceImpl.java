package com.ssafy.sample.service;

import java.util.List;

import com.ssafy.sample.dao.TripDao;
import com.ssafy.sample.dao.TripDaoImpl;
import com.ssafy.sample.model.TripDto;

public class TripServiceImpl implements TripService {
	private TripDao tripDao;

	private TripServiceImpl() {
		tripDao = TripDaoImpl.getTripDao();
	}

	private static TripService tripSerivce = new TripServiceImpl();

	public static TripService getTripSerivce() {
		return tripSerivce;
	}

	@Override
	public List<TripDto> loadLocationService() throws Exception {
		return tripDao.loadLocationService();
	}

	@Override
	public List<TripDto> loadRandomInfoService() throws Exception {
		return tripDao.loadRandomInfoDao();
	}

	@Override
	public TripDto viewDetailService(String content_id) throws Exception {
		return tripDao.viewDetailDao(content_id);
	}

	@Override
	public List<TripDto> searchTripService(String keyword, String content_type_id, String sido_code) throws Exception {
		return tripDao.searchTripDao(keyword, content_type_id, sido_code);
	}

	@Override
	public List<TripDto> searchNearByResService(String swLa, String swLo, String neLa, String neLo) throws Exception {
		return tripDao.searchNearByResDao(swLa, swLo, neLa, neLo);
	}

	@Override
	public List<TripDto> searchNearByHotPlaceService(String swLa, String swLo, String neLa, String neLo)
			throws Exception {
		return tripDao.searchNearByHotPlaceDao(swLa, swLo, neLa, neLo);
	}

	@Override
	public List<TripDto> loadRandomInfoUseUserService(String sido_code, String gugun_code) throws Exception {
		return tripDao.loadRandomInfoUseUserDao(sido_code, gugun_code);
	}

	@Override
	public List<TripDto> searchByKeyWordInMapService(String keyword) throws Exception {
		return tripDao.searchByKeyWordInMapDao(keyword);
	}

}
