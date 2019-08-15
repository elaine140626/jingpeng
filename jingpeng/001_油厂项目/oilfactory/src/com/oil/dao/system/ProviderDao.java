package com.oil.dao.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.oil.model.system.Provider;

public interface ProviderDao {
	
	List<Provider> getProvider();//供应商页查询所有
	
	List<Provider> findProvider(@Param("SupplierName") String SupplierName);//根据供应商名称模糊查询
	
	Provider findProviderByid(@Param("id") int id);//修改功能查询供应商信息
	
	int addProvider(Provider provider);	//添加供应商信息
	
	int updateProvider(Provider Provider);//修改供应商信息
	
	int delProviderById(@Param("id") int id);//删除供应商信息
	
	List<Map<String,Object>> getAllStoragemeasure(Map<String,Object> map);//删除校验查询
}
