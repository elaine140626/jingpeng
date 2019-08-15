package com.jingpeng.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jingpeng.model.Asph_TargetProportion;
import com.jingpeng.model.Asphalt_GradDetailed;
import com.jingpeng.model.Asphalt_Grading;
import com.jingpeng.model.Asphalt_GradingModel;
import com.kdt.base.exception.DataAccessException;
import com.kdt.base.support.mybatis.impl.KDDaoSupport;

@Repository
public class Asphalt_GradingDao extends KDDaoSupport{
	
private final static String NAMESPACE = "asphalt_GradingModel";
	
	public List<Asphalt_GradingModel> getasphalt_Gradings (Map<String, Object> map) throws DataAccessException {
		
		return select(NAMESPACE+".gets", map);
	}
	
	public List<Asphalt_GradingModel> getAsphalt_GradingById (Asphalt_GradingModel asphalt_GradingModel) throws DataAccessException {
		
		return select(NAMESPACE+".getAsphalt_GradingById", asphalt_GradingModel);
	}
	
	
	public List<Asphalt_GradingModel> getAsphalt_GradDetailedById (Asphalt_GradingModel asphalt_GradingModel) throws DataAccessException {
		
		return select(NAMESPACE+".getAsphalt_GradDetailedById", asphalt_GradingModel);
	}
	
	public  List<Asphalt_GradingModel> getAsphalt_GradCode(Asphalt_GradingModel asphalt_GradingModel)throws DataAccessException {

		return select(NAMESPACE+".getAsphalt_GradCode", asphalt_GradingModel);
	}
	
	public List<Asphalt_Grading> getAsphalt_Gradingcode(Asphalt_Grading asphalt_Grading)throws DataAccessException{
		return this.select(NAMESPACE+".getAsphalt_Gradingcode", asphalt_Grading);
		
	}
	
	public int addAsphalt_Grading(Asphalt_Grading asphalt_Grading)throws DataAccessException{
		this.insert(NAMESPACE+".addAsphalt_Grading", asphalt_Grading);
		return asphalt_Grading.getI_id();
	}
	
	public int addAsphalt_GradDetailed(List<Asphalt_GradDetailed> list)throws DataAccessException{
		
		return this.insert(NAMESPACE+".addAsphalt_GradDetailed", list);
	}
	
	public int updateAsphalt_GradDetailed(Asphalt_Grading asphalt_Grading) throws DataAccessException {
		
		return this.update(NAMESPACE+".updateAsphalt_GradDetailed", asphalt_Grading);
	}
	
	
	public int deletAsphalt_Grading(Asphalt_Grading asphalt_Grading) throws DataAccessException {
		
		return this.update(NAMESPACE+".deletAsphalt_Grading", asphalt_Grading);
	}

	public int deletAsphalt_GradDetailed(Asphalt_Grading asphalt_Grading) throws DataAccessException{
		return this.update(NAMESPACE+".deletAsphalt_GradDetailed", asphalt_Grading);
	}

	public List<Asphalt_Grading> select_Asph_TargetPropDetailed(Asphalt_Grading asphalt_Grading) throws DataAccessException {
		return this.select(NAMESPACE+".select_Asph_TargetPropDetailed", asphalt_Grading);
	}
	
}
