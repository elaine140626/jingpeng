package com.jingpeng.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jingpeng.dao.Asphalt_ProductionDataDao;
import com.jingpeng.model.AsphaltGrad_DataAnalysis;
import com.jingpeng.model.AsphaltProp_DataAnalysis;
import com.jingpeng.model.Asphalt_ProductionDTO;
import com.jingpeng.service.Asphalt_ProductionDataService;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.exception.DataAccessException;

@Service
public class Asphalt_ProductionDataServiceImpl implements Asphalt_ProductionDataService{
	@Autowired
	private Asphalt_ProductionDataDao asphalt_ProductionDataDao;

	public List<Asphalt_ProductionDTO> getAsphaltProductionData(Map<String, Object> map) throws BusinessException {
		try {
			return asphalt_ProductionDataDao.getAsphaltProductionData(map);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}

	public List<AsphaltProp_DataAnalysis> getAsphaltPropDataAnalysis(Asphalt_ProductionDTO asphalt_ProductionDTO) throws BusinessException {
		try {
			return asphalt_ProductionDataDao.getAsphaltPropDataAnalysis(asphalt_ProductionDTO);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}

	public List<AsphaltGrad_DataAnalysis> asphaltGradDataAnalysis(Asphalt_ProductionDTO asphalt_ProductionDTO) throws BusinessException {
		try {
			return asphalt_ProductionDataDao.asphaltGradDataAnalysis(asphalt_ProductionDTO);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}

	public List<Asphalt_ProductionDTO> getAsphaltProductionDataByID(Asphalt_ProductionDTO asphalt_ProductionDTO)
			throws BusinessException {
		try {
			return asphalt_ProductionDataDao.getAsphaltProductionDataByID(asphalt_ProductionDTO);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}

	public List<Map<String, Object>> getMaterialConsumption(Asphalt_ProductionDTO asphalt_ProductionDTO)
			throws BusinessException {
		try {
			return asphalt_ProductionDataDao.getMaterialConsumption(asphalt_ProductionDTO);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}

	public List<Map<String, String>> getAsphaltProductionDataNew(Map<String, Object> map) throws BusinessException {
		try {
			return asphalt_ProductionDataDao.getAsphaltProductionDataNew(map);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}
	
	
	
}
