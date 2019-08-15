package com.truckscale.weighingManagement.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.truckscale.basicSetting.model.CarInfo;
import com.truckscale.basicSetting.service.VehicleSettingService;
import com.truckscale.weighingManagement.dao.WeighingQueryDao;
import com.truckscale.weighingManagement.model.WeighingQueryEntity;
import com.truckscale.weighingManagement.service.WeighingQueryService;


@Service
@Transactional
public class WeighingQueryServiceImpl implements WeighingQueryService{

	@Autowired
	private WeighingQueryDao weighingQueryDao;
	@Autowired
	private VehicleSettingService vehicleSettingService;
	
	@Override
	public List<WeighingQueryEntity> getWeighingQuery(Map<String, Object> param) {
		return weighingQueryDao.getWeighingQuery(param);
	}
	@Override
	public List<WeighingQueryEntity> getWeighingQuerySum(Map<String, Object> param) {
		return weighingQueryDao.getWeighingQuerySum(param);
	}
	
	@Override
	public Map<String, Object> getDataList(Map<String, Object> map) {
		Map<String, Object> dataList = new HashMap<String, Object>();
		// 供料单位
		List<WeighingQueryEntity> feedCompanyList = weighingQueryDao.feedCompanyList(map);	
		dataList.put("feedCompanyList", feedCompanyList);
		// 收料单位
		List<WeighingQueryEntity> receiveUnitList = weighingQueryDao.receiveUnitList(map);	
		dataList.put("receiveUnitList", receiveUnitList);
		// 材料名称
		List<WeighingQueryEntity> materielNameList = weighingQueryDao.materielNameList(map);	
		dataList.put("materielNameList", materielNameList);
		// 材料型号
		List<WeighingQueryEntity> materielModelList = weighingQueryDao.materielModelList(map);	
		dataList.put("materielModelList", materielModelList);
		// 司机姓名
		List<WeighingQueryEntity> driverNameList = weighingQueryDao.driverNameList(map);	
		dataList.put("driverNameList", driverNameList);
		// 车主姓名
		List<WeighingQueryEntity> carDriverNameList = weighingQueryDao.carDriverNameList(map);	
		dataList.put("carDriverNameList", carDriverNameList);
		// 车牌号码
		List<WeighingQueryEntity> carNumberList = weighingQueryDao.carNumberList(map);	
		dataList.put("carNumberList", carNumberList);
		return dataList;
	}

	@Override
	public Map<String, Object> getDataList_add(Map<String, Object> map) {
		Map<String, Object> dataList = new HashMap<String, Object>();
		// 供料单位
		List<WeighingQueryEntity> feedCompanyList = weighingQueryDao.feedCompanyList_add(map);	
		dataList.put("feedCompanyList", feedCompanyList);
		// 收料单位
		List<WeighingQueryEntity> receiveUnitList = weighingQueryDao.receiveUnitList_add(map);	
		dataList.put("receiveUnitList", receiveUnitList);
		// 材料名称
		List<WeighingQueryEntity> materielNameList = weighingQueryDao.materielNameList_add(map);	
		dataList.put("materielNameList", materielNameList);
		// 材料型号
		List<WeighingQueryEntity> materielModelList = weighingQueryDao.materielModelList_add(map);	
		dataList.put("materielModelList", materielModelList);
		return dataList;
	}
	
	@Override
	public Map<String, Object> getFeedcompanyList(Map<String, Object> map) {
		Map<String, Object> dataList = new HashMap<String, Object>();
		// 材料名称
		List<WeighingQueryEntity> materielNameList = weighingQueryDao.getMaterielNameList(map);	
		dataList.put("materielNameList", materielNameList);
		// 规格型号
		List<WeighingQueryEntity> materielModelList = weighingQueryDao.getMaterielModelList(map);	
		dataList.put("materielModelList", materielModelList);
		return dataList;
	}
	
	@Override
	public Map<String, Object> getMaterielNameList(Map<String, Object> map) {
		Map<String, Object> dataList = new HashMap<String, Object>();
		// 供料单位
		List<WeighingQueryEntity> feedcompanyList = weighingQueryDao.getFeedcompanyList(map);	
		dataList.put("feedcompanyList", feedcompanyList);
		// 规格型号
		List<WeighingQueryEntity> materielModelList = weighingQueryDao.getMaterielModelList(map);	
		dataList.put("materielModelList", materielModelList);
		return dataList;
	}
	
	@Override
	public Map<String, Object> getMaterielModelList(Map<String, Object> map) {
		Map<String, Object> dataList = new HashMap<String, Object>();
		// 供料单位
		List<WeighingQueryEntity> feedcompanyList = weighingQueryDao.getFeedcompanyList(map);	
		dataList.put("feedcompanyList", feedcompanyList);
		// 材料名称
		List<WeighingQueryEntity> materielNameList = weighingQueryDao.getMaterielNameList(map);	
		dataList.put("materielNameList", materielNameList);
		return dataList;
	}
	
	@Override
	public int deleteWeighingQuery(Map<String, Object> param) {
		return weighingQueryDao.deleteWeighingQuery(param);
	}

	@Override
	public int updateWeighingQuery(WeighingQueryEntity param) {
		return weighingQueryDao.updateWeighingQuery(param);
	}
	
	@Override
	public List<Map<String, Object>> getPrintInfo(Map<String, Object> map) {
		return weighingQueryDao.getPrintInfo(map);
	}

	@Override
	public List<Map<String, Object>> getOutFeedCompanyInfo(Map<String, Object> map) {
		return weighingQueryDao.getOutFeedCompanyInfo(map);
	}

	@Override
	public List<Map<String, Object>> getOutReceiveUnitInfo(Map<String, Object> map) {
		return weighingQueryDao.getOutReceiveUnitInfo(map);
	}

	@Override
	public int addExportMeasureOut(WeighingQueryEntity WeighingQueryEntity) {
		int res = 0;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("plateNumber", WeighingQueryEntity.getPlateNumber());
		List<CarInfo> onlyCarInfo = weighingQueryDao.getOnlyCarInfo(map);
		if(onlyCarInfo != null && onlyCarInfo.size() > 0) {
			//获取一个车辆信息
			WeighingQueryEntity.setCarId(onlyCarInfo.get(0).getId());
		}else {
			CarInfo car = new CarInfo();
			//车辆编号
			String carCount = vehicleSettingService.getCarCount("CA");
			car.setCarNumber(carCount);
			//车牌号码
			car.setPlateNumber(WeighingQueryEntity.getPlateNumber());
			//司机姓名
			car.setDriverName(WeighingQueryEntity.getDriverName());
			//皮重
			car.setTareWeight(WeighingQueryEntity.getTareWeight());
			//添加车辆信息
			res = vehicleSettingService.addCarInfo(car);
			//获取新添加的车辆id
			Map<String,Object> map1 = new HashMap<String,Object>();
			map1.put("plateNumber", WeighingQueryEntity.getPlateNumber());
			List<CarInfo> onlyCarInfo1 = weighingQueryDao.getOnlyCarInfo(map1);
			//获取一个车辆信息
			WeighingQueryEntity.setCarId(onlyCarInfo1.get(0).getId());
		}
		//添加出库单
		res = weighingQueryDao.addExportMeasureOut(WeighingQueryEntity);
		return res;
	}

	@Override
	public String getWeighingInfoCount(String type) {
		//查询最新一条数据
		WeighingQueryEntity weighingInfo = weighingQueryDao.getWeighingInfoCount();
		String result = "";
		Date now = new Date();
		//截取年份
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String dateString = sdf.format(now);
		result += dateString + type;
		String weighingInfoCount = "";
		//判断编号是否存在如果不存在默认添加初始格式
		if(weighingInfo != null) {
			if(weighingInfo.getSerialId() != null && !"".equals(weighingInfo.getSerialId())) {
				weighingInfoCount = weighingInfo.getSerialId();
				//必须符合前缀的格式如果不是默认添加初始格式
				if(weighingInfoCount.length() > 11) {
					//从前缀的英文简写截取编号
					int newEquipment = Integer.parseInt(weighingInfoCount.substring(6)) + 1;
					result += String.format("%0"+weighingInfoCount.substring(6).length()+"d", newEquipment);
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
	public int addExportMeasureIn(WeighingQueryEntity WeighingQueryEntity) {
		int res = 0;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("plateNumber", WeighingQueryEntity.getPlateNumber());
		List<CarInfo> onlyCarInfo = weighingQueryDao.getOnlyCarInfo(map);
		if(onlyCarInfo != null && onlyCarInfo.size() > 0) {
			//获取一个车辆信息
			WeighingQueryEntity.setCarId(onlyCarInfo.get(0).getId());
		}else {
			CarInfo car = new CarInfo();
			//车辆编号
			String carCount = vehicleSettingService.getCarCount("CA");
			car.setCarNumber(carCount);
			//车牌号码
			car.setPlateNumber(WeighingQueryEntity.getPlateNumber());
			//司机姓名
			car.setDriverName(WeighingQueryEntity.getDriverName());
			//皮重
			car.setTareWeight(WeighingQueryEntity.getTareWeight());
			//添加车辆信息
			res = vehicleSettingService.addCarInfo(car);
			//获取新添加的车辆id
			Map<String,Object> map1 = new HashMap<String,Object>();
			map1.put("plateNumber", WeighingQueryEntity.getPlateNumber());
			List<CarInfo> onlyCarInfo1 = weighingQueryDao.getOnlyCarInfo(map1);
			//获取一个车辆信息
			WeighingQueryEntity.setCarId(onlyCarInfo1.get(0).getId());
		}
		//添加出库单
		res = weighingQueryDao.addExportMeasureIn(WeighingQueryEntity);
		return res;
	}

	@Override
	public String getInWeighingInfoCount(String type) {
		//查询最新一条数据
		WeighingQueryEntity weighingInfo = weighingQueryDao.getInWeighingInfoCount();
		String result = "";
		Date now = new Date();
		//截取年份
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String dateString = sdf.format(now);
		result += dateString + type;
		String weighingInfoCount = "";
		//判断编号是否存在如果不存在默认添加初始格式
		if(weighingInfo != null) {
			if(weighingInfo.getSerialId() != null && !"".equals(weighingInfo.getSerialId())) {
				weighingInfoCount = weighingInfo.getSerialId();
				//必须符合前缀的格式如果不是默认添加初始格式
				if(weighingInfoCount.length() > 11) {
					//从前缀的英文简写截取编号
					int newEquipment = Integer.parseInt(weighingInfoCount.substring(6)) + 1;
					result += String.format("%0"+weighingInfoCount.substring(6).length()+"d", newEquipment);
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
	public List<Map<String, Object>> getInFeedCompanyInfo(Map<String, Object> map) {
		return weighingQueryDao.getInFeedCompanyInfo(map);
	}
	
}
