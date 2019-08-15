package com.truckscale.weighingManagement.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.truckscale.weighingManagement.dao.WeighingQueryOtherOutDao;
import com.truckscale.weighingManagement.model.WeighingQueryOtherOutEntity;
import com.truckscale.weighingManagement.model.WeighingQueryOutEntity;
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
	public Map<String, Object> getDataList(Map<String, Object> map) {
		Map<String, Object> dataList = new HashMap<String, Object>();
		List<WeighingQueryOtherOutEntity> ClientList = weighingQueryOtherOutDao.ClientList(map);	
		dataList.put("ClientList", ClientList);
		List<WeighingQueryOtherOutEntity> PlateNumberList = weighingQueryOtherOutDao.PlateNumberList(map);
		dataList.put("PlateNumberList", PlateNumberList);
		List<WeighingQueryOtherOutEntity> DeliveryManList = weighingQueryOtherOutDao.DeliveryManList(map);	
		dataList.put("DeliveryManList", DeliveryManList);
		List<WeighingQueryOtherOutEntity> FleetNameList = weighingQueryOtherOutDao.FleetNameList(map);	
		dataList.put("FleetNameList", FleetNameList);
		List<WeighingQueryOtherOutEntity> MaterielNameList = weighingQueryOtherOutDao.MaterielNameList(map);	
		dataList.put("MaterielNameList", MaterielNameList);
		List<WeighingQueryOtherOutEntity> MaterielModelList = weighingQueryOtherOutDao.MaterielModelList(map);	
		dataList.put("MaterielModelList", MaterielModelList);
		return dataList;
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
