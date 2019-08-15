package com.oil.service.system;

import java.util.List;
import java.util.Map;

import com.oil.model.client.Client_back;
import com.oil.model.system.Startaddress;
import com.oil.model.system.Transports;
import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;


public interface CustomerTransportsService {
	//客户止运地ztree
	List<Map<String,Object>> getTransports_Tree();
	//添加客户止运地
	ResponseInfo addTransports(Transports transports);
	//修改客户止运地
	ResponseInfo updateTransports(Transports transports);
	//获取全部止运地信息
	DataTablesResponseInfo getTransports(Map<String, Object> map);
	//删除客户止运地
	ResponseInfo delTransports(int id);
	//根据客户名称模糊查询
	DataTablesResponseInfo findCustomerinfoByName(String customerName);
	//客户菜单根据父子节点查询
	DataTablesResponseInfo findByNameAndId(Map<String, Object> map);
	//修改功能查询方法
	DataTablesResponseInfo getTransportsById(int id);
	//获取没存入客户止运地的客户信息
	DataTablesResponseInfo getCustomerInfo();
	//根据客户Id查询客户止运地信息
	List<Map<String, Object>> getTrchangeInfo(int id);
	//查询全部回访形式信息数据 
	DataTablesResponseInfo getVisitContent();
	//获取客户信息数据
	List<Map<String,Object>> getClient_back_List();
	//查询全部销售员信息数据
	DataTablesResponseInfo getUserInfoByCode();
	//删除校验信息查询
	List<Map<String,Object>> getAllTransports(Map<String,Object> map);
	//获取全部起运地信息
	DataTablesResponseInfo getStartTransports(Map<String,Object> map);
	//新增起运地信息
	ResponseInfo addStartTransports(Startaddress startaddress);
	//修改起运地信息
	ResponseInfo updateStartTransports(Startaddress startaddress);
	//删除起运地信息
	ResponseInfo delStartTransports(int id);
	//根据起运地信息模糊查询
	DataTablesResponseInfo findStartCustomerinfoByName(String customerName);
	//根据起运地信息id查询有无关联的来料加工
	DataTablesResponseInfo getStartTransportsById(int id);
}
