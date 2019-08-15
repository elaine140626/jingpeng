package com.jingpeng.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jingpeng.model.AndroDTO;
import com.jingpeng.model.Asph_TargetProportion;
import com.jingpeng.model.Search;
import com.kdt.base.exception.DataAccessException;
import com.kdt.base.support.mybatis.impl.KDDaoSupport;

@Repository
public class AndroDao extends KDDaoSupport{
	
	 private final static String NAMESPACE = "andro";

	 
	public List<AndroDTO> getAsphalt_warning(HashMap<String, Object> map) throws DataAccessException{
		 return this.select(NAMESPACE+".getAsphalt_warning", map);
	}
	
	public List<AndroDTO> getAsphalt_production_statisticslist(HashMap<String, Object> map) throws DataAccessException{
		 
		 return this.select(NAMESPACE+".getAsphalt_production_statisticslist", map);
	}
	
	public List<AndroDTO> getAsphalt_production_statisticspage( AndroDTO androDTO) throws DataAccessException{
		 
		 return this.select(NAMESPACE+".getAsphalt_production_statisticspage", androDTO);
	}
	public List<AndroDTO> getAsphalt_production_statisticsechar(HashMap<String, Object> map) throws DataAccessException{
		 
		 return this.select(NAMESPACE+".getAsphalt_production_statisticsechar", map);
	}
	
	 
	public List<AndroDTO> getmaterial_consumption(HashMap<String, Object> map) throws DataAccessException{
		 return this.select(NAMESPACE+".getmaterial_consumption", map);
	 }
	 
	 public List<AndroDTO> getmaterial_consumption1(HashMap<String, Object> map) throws DataAccessException{
		 return this.select(NAMESPACE+".getmaterial_consumption1", map);
	 }
	 
	 public List<AndroDTO> getAsphalt_warningdetails(HashMap<String, Object> map) throws DataAccessException{
		 
		 return this.select(NAMESPACE+".getAsphalt_warningdetails", map);
	 }
	 
	 public List<AndroDTO> getDeviation_Asphalt(HashMap<String, Object> map) throws DataAccessException{
		 
		 return this.select(NAMESPACE+".getDeviation_Asphalt", map);
	 }
	 
	 public List<AndroDTO> getDeviation_Cement(Map<String, Object> map) throws DataAccessException{
		 
		 return this.select(NAMESPACE+".getDeviation_Cement", map);
	 }
	 public List<AndroDTO> getDeviation_Waterstability(HashMap<String, Object> map) throws DataAccessException{
		 
		 return this.select(NAMESPACE+".getDeviation_Waterstability", map);
	 }
	 
	 
	 public int addDeviation_Asphalt(AndroDTO androDTO) throws DataAccessException{
		 
		 return this.insert(NAMESPACE+".addDeviation_Asphalt", androDTO);
	 }
	 
	 public int addDeviation_Cement(AndroDTO androDTO) throws DataAccessException{
		 
		 return this.insert(NAMESPACE+".addDeviation_Cement", androDTO);
	 }

	public List<Map<String, Object>> getOrgId(List org_Id)throws DataAccessException{
		return this.select(NAMESPACE+".getOrgId", org_Id);
	}

	public List<Map<String, String>> getOrgName(List org_Id) throws DataAccessException{
		return this.select(NAMESPACE+".getOrgName", org_Id);
	}

	public List<Map<String, Object>> getSnOrgId(List org_Id) throws DataAccessException {
		return this.select(NAMESPACE+".getSnOrgId", org_Id);
	}

	public List<Map<String, Object>> getSwOrgId(List org_Id) throws DataAccessException {
		return this.select(NAMESPACE+".getSwOrgId", org_Id);
	}
	
	public List<AndroDTO> getsnorgid(List org_Id) throws DataAccessException {
		return this.select(NAMESPACE+".getsnorgid", org_Id);
	}
	
	public List<AndroDTO> getsworgid(List org_Id) throws DataAccessException {
		return  this.select(NAMESPACE+".getsworgid", org_Id);
	}
	
	public List<AndroDTO> getlqorgid(List org_Id) throws DataAccessException {
		return  this.select(NAMESPACE+".getlqorgid", org_Id);
	}
	
	
	 
	 
}
