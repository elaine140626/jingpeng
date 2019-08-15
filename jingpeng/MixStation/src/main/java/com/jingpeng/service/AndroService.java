package com.jingpeng.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jingpeng.model.AndroDTO;
import com.jingpeng.model.Search;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.exception.DataAccessException;

public interface AndroService {
	
	 List<AndroDTO> getAsphalt_warning (HashMap<String, Object> map)throws BusinessException;
	
	 List<AndroDTO> getAsphalt_production_statisticslist(HashMap<String, Object> map)throws BusinessException;
	
	 List<AndroDTO>  getAsphalt_production_statisticspage(AndroDTO androDTO)throws BusinessException;
	 
	 List<AndroDTO>  getAsphalt_production_statisticsechar(HashMap<String, Object> map)throws BusinessException;
	
	 List<AndroDTO> getmaterial_consumption(HashMap<String, Object> map)throws BusinessException;
	
	 List<AndroDTO> getmaterial_consumption1(HashMap<String, Object> map)throws BusinessException;
	
	 List<AndroDTO> getAsphalt_warningdetails(HashMap<String, Object> map)throws BusinessException;
	 
	 List<AndroDTO> getDeviation_Asphalt(HashMap<String, Object> map)throws BusinessException;
	 
	 List<AndroDTO> getDeviation_Cement(Map<String, Object> map)throws BusinessException;
	 
	 List<AndroDTO> getDeviation_Waterstability(HashMap<String, Object> map)throws BusinessException;
	 
	 void addgetDeviation_Asphalt(AndroDTO androDTO)throws BusinessException;
	 
	 void addgetDeviation_Cement(AndroDTO androDTO)throws BusinessException;
	 
	 void addgetDeviation_Waterstability(AndroDTO androDTO)throws BusinessException;

	List<Map<String, Object>> getOrgId(List org_Id)throws BusinessException;

	List<Map<String, String>> getOrgName(List org_Id)throws BusinessException;

	List<Map<String, Object>> getSnOrgId(List org_Id)throws BusinessException;
	
	List<Map<String, Object>> getSwOrgId(List org_Id)throws BusinessException;
	
	List<AndroDTO> getlqorgId(List org_Id)throws BusinessException;
	
	List<AndroDTO> getsnorgId(List org_Id)throws BusinessException;
	
	List<AndroDTO> getsworgId(List org_Id)throws BusinessException;
	 
}
