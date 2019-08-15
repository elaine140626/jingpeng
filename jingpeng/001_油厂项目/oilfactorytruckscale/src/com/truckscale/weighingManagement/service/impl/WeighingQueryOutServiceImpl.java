package com.truckscale.weighingManagement.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.truckscale.weighingManagement.dao.WeighingQueryOutDao;
import com.truckscale.weighingManagement.model.NoweighEntity;
import com.truckscale.weighingManagement.model.Testreport;
import com.truckscale.weighingManagement.model.Testreportsaledetailed;
import com.truckscale.weighingManagement.model.WeighingQueryOutEntity;
import com.truckscale.weighingManagement.model.WeighingQueryOutPrint;
import com.truckscale.weighingManagement.service.WeighingQueryOutService;



@Service
@Transactional
public class WeighingQueryOutServiceImpl implements WeighingQueryOutService{

	@Autowired
	private WeighingQueryOutDao weighingQueryOutDao;
	
	@Override
	public List<WeighingQueryOutEntity> getWeighingQueryOut(Map<String, Object> param) {
		return weighingQueryOutDao.getWeighingQueryOut(param);
	}
	@Override
	public Map<String, Object> getDataList(Map<String, Object> map) {
		Map<String, Object> dataList = new HashMap<String, Object>();
		List<WeighingQueryOutEntity> ClientList = weighingQueryOutDao.ClientList(map);	
		dataList.put("ClientList", ClientList);
		List<WeighingQueryOutEntity> PlateNumberList = weighingQueryOutDao.PlateNumberList(map);
		dataList.put("PlateNumberList", PlateNumberList);
		List<WeighingQueryOutEntity> CompanyNameList = weighingQueryOutDao.CompanyNameList(map);	
		dataList.put("CompanyNameList", CompanyNameList);
		List<WeighingQueryOutEntity> Driver1List = weighingQueryOutDao.Driver1List(map);	
		dataList.put("Driver1List", Driver1List);
		List<WeighingQueryOutEntity> FleetNameList = weighingQueryOutDao.FleetNameList(map);	
		dataList.put("FleetNameList", FleetNameList);
		List<WeighingQueryOutEntity> MaterielNameList = weighingQueryOutDao.MaterielNameList(map);	
		dataList.put("MaterielNameList", MaterielNameList);
		List<WeighingQueryOutEntity> MaterielModelList = weighingQueryOutDao.MaterielModelList(map);	
		dataList.put("MaterielModelList", MaterielModelList);
		return dataList;
	}
	
	@Override
	public int deleteWeighingQueryOut(Map<String, Object> param) {
		return weighingQueryOutDao.deleteWeighingQueryOut(param);
	}

	@Override
	public int updateWeighingQueryOut(WeighingQueryOutEntity param) {
		return weighingQueryOutDao.updateWeighingQueryOut(param);
	}

	@Override
	public List<WeighingQueryOutPrint> getWeighingQueryOutPrintInfo(Map<String, Object> param) {
		return weighingQueryOutDao.getWeighingQueryOutPrintInfo(param);
	}

	@Override
	public List<NoweighEntity> getNoweighEntityPrintInfo(Map<String, Object> param) {
		return weighingQueryOutDao.getNoweighEntityPrintInfo(param);
	}

	@Override
	public List<Testreport> getTestreportInfo(Map<String, Object> map) {
		return weighingQueryOutDao.getTestreportInfo(map);
	}

	@Override
	public List<Testreportsaledetailed> getTestreportsaledetailedInfo(HashMap<String, Object> map) {
		return weighingQueryOutDao.getTestreportsaledetailedInfo(map);
	}

	@Override
	public List<Map<String, Object>> getCachetCompany(Map<String, Object> map) {
		return weighingQueryOutDao.getCachetCompany(map);
	}

	@Override
	public List<Map<String, Object>> getCachetPersonnel(Map<String, Object> map) {
		return weighingQueryOutDao.getCachetPersonnel(map);
	}

}
