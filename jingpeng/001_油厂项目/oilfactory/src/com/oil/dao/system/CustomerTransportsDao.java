package com.oil.dao.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.oil.model.Datadictionary;
import com.oil.model.Userinfo;
import com.oil.model.client.Client_back;
import com.oil.model.sales.Customerinfo;
import com.oil.model.system.Startaddress;
import com.oil.model.system.Transports;

public interface CustomerTransportsDao {
	//客户止运地ztree
	List<Map<String,Object>> getTransports_Tree();
	//添加客户止运地
	int addTransports(Transports transports);
	//修改客户止运地
	int updateTransports(Transports transports);
	//获取全部客户止运地信息
	List<Transports> getTransports(Map<String, Object> map);
	//删除客户止运地
	int delTransports(@Param("id") int id);
	//根据客户名称模糊查询
	List<Transports> findCustomerinfoByName(@Param("customerName") String customerName);
	//客户菜单根据父子节点查询
	List<Transports> findByNameAndId(Map<String, Object> map);
	//修改功能查询方法
	Transports getTransportsById(@Param("id") int id);
	//获取没存入客户止运地的客户信息
	List<Customerinfo> getCustomerInfo();
	//根据客户Id查询客户止运地信息
	List<Map<String,Object>> getTrchangeInfo(@Param("id") int id);
	//查询全部回访形式信息数据
	List<Datadictionary> getVisitContent();
	//查询全部销售员信息数据
	List<Userinfo> getUserInfoByCode();
	//获取客户信息数据
	List<Map<String,Object>> getClient_back_List();
	//通过id获取客户信息数据
	Customerinfo getCustomerinfoById(@Param("id") int id);
	//通过Id查询回访信息数据
	Datadictionary getVisitContentByCode(@Param("code") String code);
	//删除校验信息查询
	List<Map<String,Object>> getAllTransports(Map<String,Object> map);
	//获取全部起运地信息
	List<Startaddress> getStartTransports(Map<String, Object> map);
	//新增起运地信息
	int addStartTransports(Startaddress startaddress);
	//修改起运地信息
	int updateStartTransports(Startaddress startaddress);
	//删除起运地信息
	int delStartTransports(@Param("id") int id);
	//根据起运地信息模糊查询
	List<Startaddress> findStartCustomerinfoByName(@Param("customerName") String customerName);
	//根据起运地信息id查询有无关联的来料加工
	Startaddress getStartTransportsById(@Param("id") int id);
	
	
}
