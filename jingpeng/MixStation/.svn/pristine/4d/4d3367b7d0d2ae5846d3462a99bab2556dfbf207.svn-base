package com.jingpeng.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import com.jingpeng.model.Asph_TargetPropDetailed;
import com.jingpeng.model.Asph_TargetProportion;
import com.jingpeng.model.Cement_ConsPropDetailed;
import com.jingpeng.model.Cement_ConstructionProportion;
import com.jingpeng.model.Cement_TheoPropDetailed;
import com.jingpeng.model.Cement_TheoryProportion;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.exception.DataAccessException;

public interface CementConstructionPropService {
	
	List<Cement_ConstructionProportion> getMainById(Cement_ConstructionProportion cement_ConstructionProportion) throws BusinessException;
	List<Map<String, Object>> getMainByIdGrid(Cement_ConstructionProportion Cement_ConstructionProportion) throws BusinessException;
	
	List<Cement_ConsPropDetailed> getC_ConstructionDeatlByid(Cement_ConsPropDetailed cement_ConsPropDetailed) throws BusinessException;
	
	//List<Cement_ConsPropDetailed> getC_ConstructionDeatlByid(Cement_ConstructionProportion cement_ConstructionProportion) throws BusinessException;

	List<Cement_ConstructionProportion> getCementConstructionProp(Map<String, Object> map) throws BusinessException;

	List<Cement_ConstructionProportion> getPropCode(String str_prop_Code) throws BusinessException;

	List<Cement_ConstructionProportion> getMaterNameAndModel(Cement_ConstructionProportion cement_ConstructionProportion) throws BusinessException;

	List<Map<String, Object>> getCementConstructionPropbypid(Map<String, Object> map) throws BusinessException;
	
	List<Map<String, Object>> getSgpbNo(Map<String, Object> map) throws BusinessException;
	
	List<Map<String, Object>> getCementConstructionPropbypidList(Map<String, Object> map) throws BusinessException;
	
	List<Cement_ConstructionProportion> getCementConstructionPropbypids(Map<String, Object> map) throws BusinessException;

	
	//为防止方法间的冲突，查询理论配合比主表信息
	List<Map<String, Object>> getTheory(Map<String, Object> map) throws BusinessException;
	//理论配合比明细
	List<Map<String, Object>> getTheoryList(Map<String, Object> map) throws BusinessException;
	
	int addSgpbXx(Cement_ConstructionProportion cement_ConstructionProportion) throws BusinessException;
	int addSgpbXxList(List<Cement_ConsPropDetailed> list) throws BusinessException;
	
	List<Cement_ConstructionProportion> getC_ConstructionByid (Cement_ConstructionProportion cement_ConstructionProportion) throws BusinessException;
	
	List<Cement_ConstructionProportion> getCement_ConstructionByid (HashMap<String, Object> map) throws BusinessException;

	int addCementConstructionPro(Map<String, Object> map) throws BusinessException;

	List<Cement_TheoryProportion> getTheoProp(int i_id) throws BusinessException;

	int addCementConsPropDetailed(List<Cement_ConsPropDetailed> cement_ConsPropDetailedList) throws BusinessException;

	int updateCementConstructionPro(Map<String, Object> map) throws BusinessException;

	

	int delCementConstructionPro(Cement_ConstructionProportion cement_ConstructionProportion) throws BusinessException;

	List<Map<String, Object>> getgetTheoryProportionCode(Map<String, Object> map) throws BusinessException;
	
	/*
	 * 理论配合比删除条件
	 * tongn
	 * 2018.6.28
	 */
	List<Map<String, Object>> getCementConstructionProportionByTheoPropID(Map<String, Object> map) throws BusinessException;
	
	List<Cement_ConsPropDetailed> select_Asph_TargetPropDetailed(Cement_ConsPropDetailed cement_ConsPropDetailed) throws DataAccessException;
	

}
