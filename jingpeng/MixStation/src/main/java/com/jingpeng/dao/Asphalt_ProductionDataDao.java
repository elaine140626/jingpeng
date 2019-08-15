package com.jingpeng.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jingpeng.model.AsphaltGrad_DataAnalysis;
import com.jingpeng.model.AsphaltProp_DataAnalysis;
import com.jingpeng.model.Asphalt_ProductionDTO;
import com.kdt.base.exception.DataAccessException;
import com.kdt.base.support.mybatis.impl.KDDaoSupport;

@Repository
public class Asphalt_ProductionDataDao extends KDDaoSupport{
	private final static String NAMESPACE = "asphalt_ProductionData";

	public List<Asphalt_ProductionDTO> getAsphaltProductionData(Map<String, Object> map) throws DataAccessException {
		return this.select(NAMESPACE+".getAsphaltProductionData", map);
	}

	public List<AsphaltProp_DataAnalysis> getAsphaltPropDataAnalysis(Asphalt_ProductionDTO asphalt_ProductionDTO) throws DataAccessException {
		return this.select(NAMESPACE+".getAsphaltPropDataAnalysis", asphalt_ProductionDTO);
	}

	public List<AsphaltGrad_DataAnalysis> asphaltGradDataAnalysis(Asphalt_ProductionDTO asphalt_ProductionDTO) throws DataAccessException {
		return this.select(NAMESPACE+".asphaltGradDataAnalysis", asphalt_ProductionDTO);
	}

	public List<Asphalt_ProductionDTO> getAsphaltProductionDataByID(Asphalt_ProductionDTO asphalt_ProductionDTO) throws DataAccessException {
		return this.select(NAMESPACE+".getAsphaltProductionDataByID", asphalt_ProductionDTO);
	}

	public List<Map<String, Object>> getMaterialConsumption(Asphalt_ProductionDTO asphalt_ProductionDTO) throws DataAccessException {
		return this.select(NAMESPACE+".getMaterialConsumption", asphalt_ProductionDTO);
	}

	public List<Map<String, String>> getAsphaltProductionDataNew(Map<String, Object> map)throws DataAccessException  {
		// TODO Auto-generated method stub
		return this.select(NAMESPACE+".getAsphaltProductionDataNew", map);
	}
	
	
	
}
