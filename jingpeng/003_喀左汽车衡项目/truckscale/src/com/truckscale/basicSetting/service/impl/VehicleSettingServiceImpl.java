package com.truckscale.basicSetting.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truckscale.basicSetting.dao.VehicleSettingDao;
import com.truckscale.basicSetting.model.CarInfo;
import com.truckscale.basicSetting.model.TransportCompany;
import com.truckscale.basicSetting.service.VehicleSettingService;
@Service
public class VehicleSettingServiceImpl implements VehicleSettingService {

	@Autowired
	private VehicleSettingDao vehicleSettingDao;
	
	@Override
	public List<CarInfo> getAllCarInfo(Map<String, Object> map) {
		return vehicleSettingDao.getAllCarInfo(map);
	}

	@Override
	public List<TransportCompany> getTransportCompanyList(Map<String, Object> map) {
		return vehicleSettingDao.getTransportCompanyList(map);
	}

	@Override
	public List<CarInfo> getCarInfo(Map<String, Object> map) {
		return vehicleSettingDao.getCarInfo(map);
	}

	@Override
	public int addCarInfo(CarInfo carInfo) {
		return vehicleSettingDao.addCarInfo(carInfo);
	}

	@Override
	public int updateCarInfo(CarInfo carInfo) {
		return vehicleSettingDao.updateCarInfo(carInfo);
	}

	@Override
	public int deleteCarInfo(CarInfo carInfo) {
		return vehicleSettingDao.deleteCarInfo(carInfo);
	}

	@Override
	public String getCarCount(String type) {
		//查询最新一条数据
		CarInfo carInfo = vehicleSettingDao.getCarInfoCount();
		String result = "";
		Date now = new Date();
		//截取年份
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String dateString = sdf.format(now);
		result += dateString + type;
		String carInfoCount = "";
		//判断编号是否存在如果不存在默认添加初始格式
		if(carInfo != null) {
			if(carInfo.getCarNumber() != null && !"".equals(carInfo.getCarNumber())) {
				carInfoCount = carInfo.getCarNumber();
				//必须符合前缀的格式如果不是默认添加初始格式
				if(carInfoCount.length() > 11) {
					//从前缀的英文简写截取编号
					int newEquipment = Integer.parseInt(carInfoCount.substring(6)) + 1;
					result += String.format("%0"+carInfoCount.substring(6).length()+"d", newEquipment);
				}else {
					result += "000001";
				}
			}
		}else {
			result += "000001";
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> getWeighingQueryEntityInfo(Map<String, Object> map) {
		return vehicleSettingDao.getWeighingQueryEntityInfo(map);
	}

}
