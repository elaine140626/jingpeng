package com.oil.dao.client;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.oil.model.Datadictionary;
import com.oil.model.Userinfo;
import com.oil.model.client.Client_back;
import com.oil.model.sales.Customerinfo;

public interface Client_backDao {
	//获取回访记录客户ztree树
	List<Map<String,Object>> getClient_back_Tree();
	//新增回访记录
	int addClient_back(Client_back client_back);
	//客户评价下拉列表内容查询
	List<Datadictionary> getVisitContent();
	//客户名称下拉列表内容查询
	List<Customerinfo> getCustomerInfo();
	//查询用户信息ztree
	List<Userinfo> getUserInfoByCode();
	//回访记录列表查询
	List<Client_back> getClient_back();
	//查询用户信息ztree
	List<Map<String,Object>> getClient_back_List();
	//根据id查询回访记录
	Customerinfo getCustomerinfoById(@Param("id") int id);
	//通过Id查询回访信息数据 
	Datadictionary getVisitContentByCode(@Param("code") String code);
	//通过ID查询全部回访信息数据
	Client_back getClient_backById(@Param("id") int id);
	//修改回访记录
	int updateClient_back(Client_back client_back);
	//删除回访记录
	int delClient_back(@Param("id") int id);
	//根据客户名称查询客户信息
	List<Client_back> findCustomerinfoByName(@Param("customerName") String customerName);
	//客户菜单根据父子节点查询
	List<Client_back> findByNameAndId(Map<String, Object> map);
}
