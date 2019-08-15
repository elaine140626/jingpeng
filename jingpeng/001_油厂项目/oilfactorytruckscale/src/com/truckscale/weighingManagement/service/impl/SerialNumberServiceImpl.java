package com.truckscale.weighingManagement.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truckscale.weighingManagement.dao.SerialNumberDao;
import com.truckscale.weighingManagement.service.SerialNumberService;

@Service
public class SerialNumberServiceImpl implements SerialNumberService{

	@Autowired
	SerialNumberDao serialNumberDao;
	
	@Override
	public String getSerialNumber() {
		
		// 流水号
		String serialNumber = "";
		String data = serialNumberDao.getSerialNumber();
		Date now = new Date();
		
		if(data != null && !("").equals(data)) {
			SimpleDateFormat year = new SimpleDateFormat("yyyy");
			String dateString = year.format(now);
			serialNumber += dateString;
			SimpleDateFormat month = new SimpleDateFormat("MM");
			serialNumber += month.format(now);
			SimpleDateFormat day = new SimpleDateFormat("dd");
			serialNumber += day.format(now);
			serialNumber += String.format("%03d", Integer.parseInt(data)+1);       
		}else {
			SimpleDateFormat year = new SimpleDateFormat("yyyy");
			String dateString = year.format(now);
			serialNumber += dateString;
			SimpleDateFormat month = new SimpleDateFormat("MM");
			serialNumber += month.format(now);
			SimpleDateFormat day = new SimpleDateFormat("dd");
			serialNumber += day.format(now);
			serialNumber += "001";
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("number", serialNumber);
		int result = serialNumberDao.insertSerialNumber(map);
		if(result > 0) {
			return serialNumber;
		}else {
			return "";
		}
	}

}
