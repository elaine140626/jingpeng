package com.jingpeng.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jingpeng.dao.OrganizationInfoDao;
import com.jingpeng.model.OrganizationInfo;
import com.jingpeng.model.UserInfo;
import com.jingpeng.service.OrganizationInfoService;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.exception.DataAccessException;

@Service
@Transactional
public class OrganizationInfoServiceImpl implements OrganizationInfoService{
	
	@Autowired
	private OrganizationInfoDao organizationInfoDao;

	public int getTest() throws BusinessException {
						
		try {
			
			List<Map<String, Object>> list = organizationInfoDao.getTest();
			
			int a = (Integer) list.get(0).get("a");
			
			return a;
			
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}		
		
	}


	public List<OrganizationInfo> getOrgInfo(Map<String, Object> param) throws BusinessException {
	try {
			
			List<OrganizationInfo> list = organizationInfoDao.getOrgInfo(param);
						
			return list;
			
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}	
	}




}
