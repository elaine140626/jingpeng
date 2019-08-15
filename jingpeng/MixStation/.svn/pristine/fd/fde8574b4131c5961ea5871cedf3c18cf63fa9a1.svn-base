package com.jingpeng.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jingpeng.dao.Organization_InfoDao;
import com.jingpeng.model.Organization_Info;
import com.jingpeng.service.Organization_InfoService;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.exception.DataAccessException;

@Service
public class Organization_InfoServiceImpl implements Organization_InfoService {

	@Autowired
	private Organization_InfoDao organization_InfoDao;

	public List<Organization_Info> getorganization_Infos(Organization_Info organization_Info) throws BusinessException {
		try {
			return organization_InfoDao.getOrganization_Info(organization_Info);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}

	}

	public List<Organization_Info> getAsphaltmixingstation(List<Integer> orgids)
			throws BusinessException {
		try {
			return organization_InfoDao.getAsphaltmixingstation(orgids);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}

	public List<Organization_Info> getCementmixingstation(List<Integer> orgids)
			throws BusinessException {
		try {
			return organization_InfoDao.getCementmixingstation(orgids);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}

	public List<Organization_Info> getWaterstabilitymixingstation(List<Integer> orgids)
			throws BusinessException {
		try {
			return organization_InfoDao.getWaterstabilitymixingstation(orgids);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}
}