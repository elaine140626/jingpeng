package com.oil.dao.system;

import java.util.List;
import java.util.Map;

import com.oil.model.system.SalesCompany;

public interface SalesCompanyDao {
	//查询所有的销售公司
	List<SalesCompany> getSalesCompanyList(Map<String,Object> map);
	
	//添加销售公司
	int addSalesCompany(SalesCompany param);
	
	//删除销售公司
	int delSalesCompany(Map<String,Object> map);
	
	//修改销售公司
	int updateSalesCompany(SalesCompany parm);
	
	//获取销售公司明称
	List<Map<String,Object>> getSalesCompanyName();
	
	//删除的校验查询
	List<Map<String,Object>> getAllSalesCompany(Map<String,Object> map);
 }
