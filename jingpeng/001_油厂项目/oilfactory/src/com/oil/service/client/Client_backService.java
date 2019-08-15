package com.oil.service.client;

import java.util.List;
import java.util.Map;

import com.oil.model.client.Client_back;
import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;


public interface Client_backService {
	//获取回访记录客户ztree树
	List<Map<String,Object>> getClient_back_Tree();
	//新增回访记录
	ResponseInfo addClient_back(Client_back client_back);
	//客户评价下拉列表内容查询
	DataTablesResponseInfo getVisitContent();
	//客户名称下拉列表内容查询
	DataTablesResponseInfo getCustomerInfo();
	//查询用户信息ztree
	List<Map<String,Object>> getClient_back_List();
	//回访记录列表查询
	DataTablesResponseInfo getClient_back();
	//获取客户名称
	DataTablesResponseInfo getUserInfoByCode();
	//根据id查询回访记录
	DataTablesResponseInfo getClient_backById(int id);
	//修改回访记录
	ResponseInfo updateClient_back(Client_back client_back);
	//删除回访记录
	ResponseInfo delClient_back(int id);
	//根据客户名称查询客户信息
	DataTablesResponseInfo findCustomerinfoByName(String customerName);
	//客户菜单根据父子节点查询
	DataTablesResponseInfo findByNameAndId(Map<String, Object> map);
}
