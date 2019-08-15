package com.jingpeng.service;

import java.util.List;
import java.util.Map;

import com.jingpeng.model.Asph_TargetProportion;
import com.jingpeng.model.Asphalt_GradDetailed;
import com.jingpeng.model.Asphalt_Grading;
import com.jingpeng.model.Asphalt_GradingModel;
import com.kdt.base.exception.BusinessException;

public interface Asphalt_GradingService {
	
	public List<Asphalt_GradingModel> getAsphalt_Gradings(Map<String, Object> map) throws BusinessException;
	
	public List<Asphalt_GradingModel> getAsphalt_GradingById(Asphalt_GradingModel asphalt_GradingModel) throws BusinessException;

	public List<Asphalt_GradingModel> getAsphalt_GradDetailedById(Asphalt_GradingModel asphalt_GradingModel) throws BusinessException;

	public int addAsphalt_Grad(Asphalt_Grading asphalt_Grading)throws BusinessException;

	public List<Asphalt_Grading> getAsphalt_Gradingcode (Asphalt_Grading asphalt_Grading)throws BusinessException;
	
	public int updateAsphalt_GradDetailed(Asphalt_Grading asphalt_Grading)throws BusinessException;

	public int deletAsphalt_Grading(Asphalt_Grading asphalt_Grading)throws BusinessException;

	public List<Asphalt_Grading> select_Asph_TargetPropDetailed(Asphalt_Grading asphalt_Grading) throws BusinessException;

}
