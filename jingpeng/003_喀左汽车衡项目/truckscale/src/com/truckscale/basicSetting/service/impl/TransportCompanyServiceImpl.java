package com.truckscale.basicSetting.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truckscale.basicSetting.dao.TransportCompanyDao;
import com.truckscale.basicSetting.model.CarInfo;
import com.truckscale.basicSetting.model.TransportCompany;
import com.truckscale.basicSetting.service.TransportCompanyService;
@Service
public class TransportCompanyServiceImpl implements TransportCompanyService {

	@Autowired
	private TransportCompanyDao transportCompanyDao;
	
	@Override
	public List<TransportCompany> getAllTransportCompany(Map<String, Object> map) {
		return transportCompanyDao.getAllTransportCompany(map);
	}

	@Override
	public int addTransportCompany(TransportCompany transportCompany) {
		return transportCompanyDao.addTransportCompany(transportCompany);
	}

	@Override
	public int updateTransportCompany(TransportCompany transportCompany) {
		return transportCompanyDao.updateTransportCompany(transportCompany);
	}

	@Override
	public int delTransportCompany(TransportCompany transportCompany) {
		return transportCompanyDao.delTransportCompany(transportCompany);
	}

	@Override
	public List<TransportCompany> getTransportCompany(Map<String, Object> map) {
		return transportCompanyDao.getTransportCompany(map);
	}

	@Override
	public List<CarInfo> getCarInfoList(Map<String, Object> map) {
		return transportCompanyDao.getCarInfoList(map);
	}

	@Override
	public String getGenerateCount(String type) {
		//查询最新的一条数据
		TransportCompany transportCompany = transportCompanyDao.getTransportCompanyCount();
		String result = "";
		Date now = new Date();
		//截取日期
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		String dateString = formatter.format(now);
		result += dateString + type;
		String transportCompanyCount = "";
		//判断数据是否存在如果不存在默认添加初始格式
		if(transportCompany != null) {
			if(transportCompany.getTransportCompanyNumber() != null && !"".equals(transportCompany.getTransportCompanyNumber())) {
				transportCompanyCount = transportCompany.getTransportCompanyNumber();
				//必须符合前缀的格式如果不是默认初始格式
				if(transportCompanyCount.length() > 11) {
					//从前缀英文简写截取编号
					int newEquipment = Integer.parseInt(transportCompanyCount.substring(6)) + 1;
					result += String.format("%0"+transportCompanyCount.substring(6).length()+"d", newEquipment);
				}else {
					result += "000001";	
				}
			}
		}else {
			result += "000001";	
		}
		return result;
	}

}
