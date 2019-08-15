package com.oil.dao.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.oil.model.Datadictionary;
import com.oil.model.system.WareHouseInfo;

public interface WareHouseInfoDao {

	//添加仓库信息
	int addWareHouseInfo(WareHouseInfo wareHouseInfo);
	
	//查询仓库信息数据
	List<WareHouseInfo> getWareHouseInfo();
	
	//修改仓库信息
	int updateWareHouseInfo(WareHouseInfo wareHouseInfo);
	
	//删除仓库信息
	int delWareHouseInfo(WareHouseInfo wareHouseInfo);
	
	//查询全部仓库信息数据
	List<Datadictionary> getLevel();
	
	//通过code查询全部仓库信息数据
	Datadictionary getLevelByid(@Param("code") String code);
	
	//通过仓库信息id 查询仓库信息
	WareHouseInfo getWareHouseInfoByid(@Param("id") int id);
	
	//根据仓库名称模糊查询 
	List<WareHouseInfo> findWareHouseInfoByName(@Param("warehouseName") String warehouseName);
	
	//添加大罐信息数据
	int addWareHouseInfoTank(WareHouseInfo wareHouseInfo);
	
	//查询全部仓库分类信息数据
	Datadictionary getTankType();
	
	//通过code查询全部仓库分类信息数据
	Datadictionary getTankTypeById(@Param("code") String code);
	
	//修改大罐信息数据
	int updateWareHouseInfoTank(WareHouseInfo wareHouseInfo);
	
}
