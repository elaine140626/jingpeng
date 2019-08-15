package com.oil.service.client;



import java.util.List;
import java.util.Map;

import com.oil.model.client.Client_back;
import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;

public interface ClientVisitService {
	//新增拜访记录
	ResponseInfo addClientVisit(Client_back client_back);
	//客户评价下拉列表内容查询
	DataTablesResponseInfo getVisitContent();
	//客户名称下拉列表内容查询
	DataTablesResponseInfo getCustomerInfo();
	//获取拜访记录数据
	DataTablesResponseInfo getClientVisit();
	//根据id查询拜访记录
	DataTablesResponseInfo getClientVisitById(int id);
	//修改拜访记录
	ResponseInfo updateClientVisit(Client_back client_back);
	//删除拜访记录
	ResponseInfo delClientVisit(int id);
	//根据客户名称查询客户信息
	DataTablesResponseInfo findCustomerinfoByName(String customerName);
	//拜访记录列表查询
	List<Map<String,Object>> getClient_back();
	//客户菜单根据父子节点查询
	DataTablesResponseInfo findByNameAndId(Map<String, Object> map);
	
}
