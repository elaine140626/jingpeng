package com.truckscale.basicSetting.dao;

import java.util.List;
import java.util.Map;

import com.truckscale.basicSetting.model.CarInfo;
import com.truckscale.basicSetting.model.TransportCompany;

/**
 * 
 * @Title TransportCompanyDao(运输单位Dao)
 * @author Administrator
 * @date 2019年4月9日
 */
public interface TransportCompanyDao {
	
	//查询所有运输单位
	List<TransportCompany> getAllTransportCompany(Map<String,Object> map);
	
	//运输单位去重
	List<TransportCompany> getTransportCompany(Map<String,Object> map);
	
	//添加运输单位
	int addTransportCompany(TransportCompany transportCompany);
	
	//修改运输单位
	int updateTransportCompany(TransportCompany transportCompany);
	
	//删除运输单位
	int delTransportCompany(TransportCompany transportCompany);
	
	//判断运输单是否被关联
	List<CarInfo> getCarInfoList(Map<String,Object> map);
	
	//运输单位编号自增
	TransportCompany getTransportCompanyCount();
}
