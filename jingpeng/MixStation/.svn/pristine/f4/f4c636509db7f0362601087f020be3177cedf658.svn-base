package com.jingpeng.service;

import java.util.List;
import java.util.Map;

import com.jingpeng.model.Asph_TargetProportion;
import com.jingpeng.model.Asphalt_Prod_Proportion;
import com.kdt.base.exception.BusinessException;

public interface AsphProportionService {

public List<Asph_TargetProportion> getAsph_TargetProportion (Asph_TargetProportion asph_TargetProportion) throws BusinessException;
	
	public List<Asphalt_Prod_Proportion> getAsphalt_Prod_ProportionCode(Asphalt_Prod_Proportion asphalt_Prod_Proportion) throws BusinessException;
	
	public List<Asphalt_Prod_Proportion> getAsphalt_Prod_ProportionById(Map<String, Object> map) throws BusinessException;

	public List<Asph_TargetProportion> getAsph_TargetProportionCode(Asph_TargetProportion asph_TargetProportion)throws BusinessException;
	
	public List<Asphalt_Prod_Proportion> getAsphalt_Prod_Proportion (Map<String, Object> map) throws BusinessException;

	public int addAsphalt_Prod_Proportion(Asphalt_Prod_Proportion asphalt_Prod_Proportion)throws BusinessException;
	
	public int addAsph_TargetProportion(Asph_TargetProportion asph_TargetProportion)throws BusinessException;

	public int  updateAsphalt_Prod_Proportion(Asphalt_Prod_Proportion asphalt_Prod_Proportion)throws BusinessException;
	
	public void deletAsphalt_Prod_Proportion(Asphalt_Prod_Proportion asphalt_Prod_Proportion)throws BusinessException;

	public List<Map> getProduction_Plan(Asphalt_Prod_Proportion asphalt_Prod_Proportion)throws BusinessException;
	
	public List<Asphalt_Prod_Proportion> getAsphalt_Prod_ProportionByGradId(Map<String, Object> map)throws BusinessException;

	public List<Asph_TargetProportion> select_Asph_TargetPropDetailed(Asphalt_Prod_Proportion asphalt_Prod_Proportion) throws BusinessException;

}
