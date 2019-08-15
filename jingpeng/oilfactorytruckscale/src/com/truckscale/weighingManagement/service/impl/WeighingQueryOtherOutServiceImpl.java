package com.truckscale.weighingManagement.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.truckscale.weighingManagement.dao.WeighingQueryOtherOutDao;
import com.truckscale.weighingManagement.model.WeighingQueryOtherOutEntity;
import com.truckscale.weighingManagement.service.WeighingQueryOtherOutService;


@Service
@Transactional
public class WeighingQueryOtherOutServiceImpl implements WeighingQueryOtherOutService{

	@Autowired
	private WeighingQueryOtherOutDao weighingQueryOtherOutDao;
	
	@Override
	public List<WeighingQueryOtherOutEntity> getWeighingQueryOtherOut(Map<String, Object> param) {
		return weighingQueryOtherOutDao.getWeighingQueryOtherOut(param);
	}

	@Override
	public int deleteWeighingQueryOtherOut(Map<String, Object> param) {
		return weighingQueryOtherOutDao.deleteWeighingQueryOtherOut(param);
	}

	@Override
	public int updateWeighingQueryOtherOut(WeighingQueryOtherOutEntity param) {
		return weighingQueryOtherOutDao.updateWeighingQueryOtherOut(param);
	}
	
	@Override
	public int insertWeighingQueryOtherOut(WeighingQueryOtherOutEntity param) {
		return weighingQueryOtherOutDao.insertWeighingQueryOtherOut(param);
	}

	@Override
	public int deleteElectronicsName(Map<String, Object> param) {
		return weighingQueryOtherOutDao.deleteElectronicsName(param);
	}

	@Override
	public List<WeighingQueryOtherOutEntity> getWeighingQueryOtherInfo(Map<String, Object> param) {
		return weighingQueryOtherOutDao.getWeighingQueryOtherInfo(param);
	}

}
