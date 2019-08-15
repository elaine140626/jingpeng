package com.oil.service.system;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.system.SalesCompany;

public interface SalesCompanyService {
	//查询所有的销售公司
	DataTablesResponseInfo getSalesCompanyList(Map<String,Object> map);
	
	//添加销售公司
	ResponseInfo addSalesCompany(HttpServletRequest request, Map<String, Object> map) throws IOException ;
	
	//删除销售公司
	ResponseInfo delSalesCompany(HttpServletRequest request, Map<String, Object> map)throws IOException;
	
	//修改销售公司
	ResponseInfo updateSalesCompany(HttpServletRequest request, Map<String, Object> map) throws IOException ;
	
	// 根据id查
	SalesCompany getSalesCompanyInfo(Map<String, Object> map);
	
	//销售公司名称
	List<Map<String,Object>> getSalesCompanyName();
	
	//删除的校验查询
	List<Map<String,Object>> getAllSalesCompany(Map<String,Object> map);
}
