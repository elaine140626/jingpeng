package com.oil.service.system.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oil.dao.system.ProviderDao;
import com.oil.model.system.Provider;
import com.oil.service.system.ProviderService;
@Service
public class ProviderServiceImpl implements ProviderService{

	@Autowired
	private ProviderDao providerDao;
	//供应商页查询所有
	@Override
	public List<Provider> getProvider() {
		return providerDao.getProvider();
	}
	//根据供应商名称模糊查询
	@Override
	public List<Provider> findProvider(String SupplierName) {
		return providerDao.findProvider(SupplierName);
	}
	//修改功能查询供应商信息
	@Override
	public Provider findProviderByid(int id) {
		return providerDao.findProviderByid(id);
	}
	@Override 	//添加供应商信息
	public int addProvider(Provider provider) {
		return providerDao.addProvider(provider);
	}
	//修改供应商信息
	@Override
	public int updateProvider(Provider provider) {
		// TODO Auto-generated method stub
		return providerDao.updateProvider(provider);
	}
	//删除供应商信息
	@Override
	public int delProviderById(int id) {
		// TODO Auto-generated method stub
		return providerDao.delProviderById(id);
	}
	//删除校验查询
	@Override
	public List<Map<String, Object>> getAllStoragemeasure(Map<String, Object> map) {
		return providerDao.getAllStoragemeasure(map);
	}

}
