package com.oil.dao.client;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.oil.model.Datadictionary;
import com.oil.model.client.Client_back;
import com.oil.model.sales.Customerinfo;

public interface ClientVisitDao {
	//新增拜访记录
	int addClientVisit(Client_back client_back);
	//客户评价下拉列表内容查询
	List<Datadictionary> getVisitContent();
	//客户名称下拉列表内容查询
	List<Customerinfo> getCustomerInfo();
	//获取拜访记录数据
	List<Client_back> getClientVisit();
	//根据id查询客户信息
	Customerinfo getCustomerinfoById(@Param("id") int id);
	
	Datadictionary getVisitContentByCode(@Param("code") String code);
	//根据id查询拜访记录
	Client_back getClientVisitById(@Param("id") int id);
	//修改拜访记录
	int updateClientVisit(Client_back client_back);
	//删除拜访记录
	int delClientVisit(@Param("id") int id);
	//根据客户名称查询客户信息
	List<Client_back> findCustomerinfoByName(@Param("customerName") String customerName);
	//拜访记录列表查询
	List<Map<String,Object>> getClient_back();
	//客户菜单根据父子节点查询
	List<Client_back> findByNameAndId(Map<String, Object> map);
}
