package com.oil.service.system;

import java.util.List;
import java.util.Map;

import com.oil.model.system.Provider;

public interface ProviderService {
	
	List<Provider> getProvider();	//供应商页查询所有
	
	List<Provider> findProvider(String SupplierName);//根据供应商名称模糊查询
	
	Provider findProviderByid(int id);//修改功能查询供应商信息
	
	int addProvider(Provider provider);	//添加供应商信息
	
	int updateProvider(Provider provider);//修改供应商信息
	
	int delProviderById(int id);//删除供应商信息
	
	List<Map<String,Object>> getAllStoragemeasure(Map<String,Object> map);//删除校验查询
}
