package com.oil.service.system;

import java.util.List;


import com.oil.model.DataTablesResponseInfo;
import com.oil.model.Datadictionary;
import com.oil.model.ResponseInfo;
import com.oil.model.system.WareHouseInfo;

public interface WareHouseInfoService {

	//添加仓库信息
	ResponseInfo addWareHouseInfo(WareHouseInfo wareHouseInfo);
	
	//查询仓库信息数据
	DataTablesResponseInfo getWareHouseInfo();
	
	//修改仓库信息
	ResponseInfo updateWareHouseInfo(WareHouseInfo wareHouseInfo);
	
	//删除仓库信息
	ResponseInfo delWareHouseInfo(WareHouseInfo wareHouseInfo);
	
	//查询全部仓库信息数据
	List<Datadictionary> getLevel();
	
	//通过code查询全部仓库信息数据
	Datadictionary getLevelByid(String code);
	
	//通过仓库信息id 查询仓库信息
	WareHouseInfo getWareHouseInfoByid(int id);
	
	//根据仓库名称模糊查询 
	DataTablesResponseInfo findWareHouseInfoByName(String warehouseNumber);
	
	//添加大罐信息数据
	ResponseInfo addWareHouseInfoTank(WareHouseInfo wareHouseInfo);
	
	//通过code查询全部仓库分类信息数据
	Datadictionary getTankTypeById(String code);
	
	//修改大罐信息数据
	ResponseInfo updateWareHouseInfoTank(WareHouseInfo wareHouseInfo);
}
