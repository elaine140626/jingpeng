package com.oil.service.transportation.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oil.dao.transportation.TransportationExportmeasureDao;
import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.service.transportation.TransportationExportmeasureService;

@Service
@Transactional
public class TransportationExportmeasureServiceImpl implements TransportationExportmeasureService {

	@Autowired
	private TransportationExportmeasureDao transportationExportmeasureDao;
	
	
	@Override
	public DataTablesResponseInfo getExportMeasurePlate() {
		// TODO Auto-generated method stub
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		info.setData(transportationExportmeasureDao.getExportMeasurePlate());
		return info;
	}


	@Override
	public DataTablesResponseInfo getExportmeasureByPlateNumber(Map<String, Object> map) {
		// TODO Auto-generated method stub
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<Map<String,Object>> ExOrStList  = transportationExportmeasureDao.getExportmeasureByPlateNumber(map);
		Map<String,Object> SerialIdMap = new HashMap<>();
		for (int i = 0; i < ExOrStList.size(); i++) {
			List<Map<String,Object>> exIsHaveNoweighoutwarehouse = new ArrayList<>();
			SerialIdMap.put("SerialId", ExOrStList.get(i).get("SerialId"));
			exIsHaveNoweighoutwarehouse = transportationExportmeasureDao.exIsHaveNoweighoutwarehouse(SerialIdMap);
			if(exIsHaveNoweighoutwarehouse.size()>0 && Integer.parseInt(ExOrStList.get(i).get("outType").toString()) == 0) {
				ExOrStList.get(i).put("outType", 99);
			}
		}
		info.setData(transportationExportmeasureDao.getExportmeasureByPlateNumber(map));
		return info;
	}


	@Override
	public DataTablesResponseInfo getSaleType() {
		// TODO Auto-generated method stub
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		info.setData(transportationExportmeasureDao.getSaleType());
		return info;
	}


	@Override
	public DataTablesResponseInfo getCustomertransportsById(Map<String, Object> map) {
		// TODO Auto-generated method stub
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		if(map.get("kid")!=null&&map.get("kid")!="") {
			map.put("kid", Integer.parseInt(map.get("kid").toString()));
		}
		List<Map<String,Object>> transportsList = transportationExportmeasureDao.getCustomertransportsById(map);
		info.setData(transportsList);
		return info;
	}


	@Override
	public ResponseInfo addTranSportList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

}
