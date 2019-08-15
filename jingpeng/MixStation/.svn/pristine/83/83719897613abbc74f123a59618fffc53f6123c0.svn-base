package com.jingpeng.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jingpeng.dao.AppDao;
import com.jingpeng.model.V_MaterialInfo;
import com.jingpeng.service.AppService;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.exception.DataAccessException;

@Service
@Transactional
public class AppServiceImpl implements AppService{
	@Autowired
	private AppDao appDao;

	
	public List<V_MaterialInfo> getAppMaterialName(Map<String, Object> map) throws BusinessException {
		try {
			return appDao.getAppMaterialName(map);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}


	public List<V_MaterialInfo> getAppMaterialModel(Map<String, Object> map) throws BusinessException {
		try {
			return appDao.getAppMaterialModel(map);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}


	public List<Map<String, Object>> getAppOrgId(Map<String, Object> map) throws BusinessException {
		try {
			return appDao.getAppOrgId(map);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
