package com.jingpeng.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jingpeng.dao.PlatformIndexDao;
import com.jingpeng.model.Core_User_Info;
import com.jingpeng.service.PlatformIndexService;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.exception.DataAccessException;

@Service
public class PlatformIndexServiceImpl implements PlatformIndexService{
	@Autowired
	private PlatformIndexDao platformIndexDao;
	
	public List<Map<String, String>> getOrgName(List<Integer> orgids) throws BusinessException {
		try {
			return platformIndexDao.getOrgName(orgids);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}
	
}
