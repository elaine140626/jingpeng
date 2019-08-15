package com.oil.service.client;

import java.util.List;
import java.util.Map;

import com.oil.model.Arrears;
import com.oil.model.CityInfo;
import com.oil.model.DataTablesResponseInfo;
import com.oil.model.Datadictionary;
import com.oil.model.ResponseInfo;
import com.oil.model.sales.Customerinfo;
import com.oil.model.system.Provinceinfo;

public interface ClientService {

	//获取客户信息数据
	List<Customerinfo> getCustomerInfoList(Map<String, Object> map);
	//添加客户信息数据
	int addCustomerinfo(Customerinfo customerinfo);
	//根据客户名称模糊查询
	List<Customerinfo> findCustomerinfo(String customername);
	//修改功能查询客户信息
	Customerinfo findCustomerinfoById(int uuid);
	//修改客户信息
	int updateCustomerinfo(Customerinfo customerinfo);
	//删除客户信息
	ResponseInfo delCustomerinfoById(int uuid);
	//查询数据字典等级
	List<Datadictionary> getDatadictionaryList();
	//查询所有的省份
	List<Provinceinfo> getProvinceinfoList();
	//查询省下所有的市
	List<CityInfo> getCityInfoList(int ProvinceId);
	//添加欠款明细
	int addArrears(Arrears arrears);
//	int delArrears(int customerId);
	//修改欠款明细(逻辑删)
	int updateArrearsInfo(int id);
	//修改欠款明细(物理删)
	int updateArrears(int customerId);
	//通过id查询所在的市
	CityInfo getCityInfo(int id);
	//通过市查询所在的省
	Provinceinfo getProvinceinfo(int provinceId);
	//通过客户id查询欠款明细
	List<Arrears> getArrears(int customerId);
	//根据客户父子级查询客户菜单
	DataTablesResponseInfo findByNameAndId(Map<String, Object> map);
	//查询用户信息ztree
	List<Map<String,Object>> getClient_back();
}
