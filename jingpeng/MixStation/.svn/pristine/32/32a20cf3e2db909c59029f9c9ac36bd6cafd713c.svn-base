package com.jingpeng.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jingpeng.dao.AndroDao;
import com.jingpeng.model.AndroDTO;
import com.jingpeng.model.Search;
import com.jingpeng.service.AndroService;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.exception.DataAccessException;

@Service
public class AndroServiceImpl implements AndroService{
	
	@Autowired
	private AndroDao androDao;

	public List<AndroDTO> getAsphalt_warning(HashMap<String, Object> map) throws BusinessException {
		
		try {
			return androDao.getAsphalt_warning(map);
		} catch (DataAccessException e) {
			throw new BusinessException();
		}
	}

	public List<AndroDTO> getAsphalt_production_statisticslist(HashMap<String, Object> map) throws BusinessException {
		try {
			return androDao.getAsphalt_production_statisticslist(map);
		} catch (DataAccessException e) {
			throw new BusinessException();
		}
	}

	public List<AndroDTO> getAsphalt_production_statisticspage(AndroDTO androDTO) throws BusinessException {
		try {
			return androDao.getAsphalt_production_statisticspage(androDTO);
		} catch (DataAccessException e) {
			throw new BusinessException();
		}
	}
	
	
	public List<AndroDTO> getAsphalt_production_statisticsechar(HashMap<String, Object> map) throws BusinessException {
		try {
			return androDao.getAsphalt_production_statisticsechar(map);
		} catch (DataAccessException e) {
			throw new BusinessException();
		}
	}

	public List<AndroDTO> getmaterial_consumption(HashMap<String, Object> map) throws BusinessException {
		try {
			return androDao.getmaterial_consumption(map);
		} catch (DataAccessException e) {
			throw new BusinessException();
		}
	}

	public List<AndroDTO> getmaterial_consumption1(HashMap<String, Object> map) throws BusinessException {
		try {
			return androDao.getmaterial_consumption1(map);
		} catch (DataAccessException e) {
			throw new BusinessException();
		}
	}

	public List<AndroDTO> getAsphalt_warningdetails(HashMap<String, Object> map) throws BusinessException {
		try {
			return androDao.getAsphalt_warningdetails(map);
		} catch (DataAccessException e) {
			throw new BusinessException();
		}
	}

	public List<AndroDTO> getDeviation_Asphalt(HashMap<String, Object> map) throws BusinessException {
		try {
			return androDao.getDeviation_Asphalt(map);
		} catch (DataAccessException e) {
			throw new BusinessException();
		}
	}
	
	public List<AndroDTO> getDeviation_Cement(Map<String, Object> map) throws BusinessException {
		try {
			return androDao.getDeviation_Cement(map);
		} catch (DataAccessException e) {
			throw new BusinessException();
		}
		
	}
	public List<AndroDTO> getDeviation_Waterstability(HashMap<String, Object> map) throws BusinessException {
		try {
			return androDao.getDeviation_Waterstability(map);
		} catch (DataAccessException e) {
			throw new BusinessException();
		}
	}

	@Transactional
	public void addgetDeviation_Asphalt(AndroDTO androDTO) throws BusinessException {
		try {
			androDao.addDeviation_Asphalt(androDTO);
		} catch (DataAccessException e) {
			throw new BusinessException();
		}
		
	}

	
	@Transactional
	public void addgetDeviation_Cement(AndroDTO androDTO) throws BusinessException {
		try {
			androDao.addDeviation_Cement(androDTO);
		} catch (DataAccessException e) {
			throw new BusinessException();
		}
	}

	public List<Map<String, Object>> getOrgId(List org_Id) throws BusinessException {
		try {
			return androDao.getOrgId(org_Id);
		} catch (DataAccessException e) {
			throw new BusinessException();
		}
	}

	public List<Map<String, String>> getOrgName(List org_Id) throws BusinessException  {
		try {
			return androDao.getOrgName(org_Id);
		} catch (DataAccessException e) {
			throw new BusinessException();
		}
	}

	public List<Map<String, Object>> getSnOrgId(List org_Id) throws BusinessException {
		try {
			return androDao.getSnOrgId(org_Id);
		} catch (DataAccessException e) {
			throw new BusinessException();
		}
	}

	public List<Map<String, Object>> getSwOrgId(List org_Id) throws BusinessException {
		try {
			return androDao.getSwOrgId(org_Id);
		} catch (DataAccessException e) {
			throw new BusinessException();
		}
	}
	
	public List<AndroDTO> getsnorgId(List org_Id) throws BusinessException {
		try {
			return androDao.getsnorgid(org_Id);
		} catch (DataAccessException e) {
			throw new BusinessException();
		}
	}
	
	public List<AndroDTO> getsworgId(List org_Id) throws BusinessException {
		try {
			return androDao.getsworgid(org_Id);
		} catch (DataAccessException e) {
			throw new BusinessException();
		}
	}


	public void addgetDeviation_Waterstability(AndroDTO androDTO) throws BusinessException {
		
	}

	public List<AndroDTO> getlqorgId(List org_Id) throws BusinessException {
		try {
			return androDao.getlqorgid(org_Id);
		} catch (DataAccessException e) {
			throw new BusinessException();
		}
	}

	


}
