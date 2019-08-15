package com.jingpeng.service;

import java.util.List;
import java.util.Map;

import com.jingpeng.model.Asph_TargetPropDetailed;
import com.jingpeng.model.Asph_TargetProportion;
import com.jingpeng.model.Asphalt_Prod_Proportion;
import com.jingpeng.model.V_MaterialInfo;
import com.kdt.base.exception.BusinessException;

public interface Asph_TargetProService {
	List<Asph_TargetProportion> getAsphTargetPro(Map<String, Object> map) throws BusinessException;

	List<V_MaterialInfo> getRawMaterial(Map map) throws BusinessException;

	List<V_MaterialInfo> getMaterialModelBymateNameid(V_MaterialInfo v_MaterialInfo) throws BusinessException;

	List getProportionCode(Asph_TargetProportion asphTargetPro) throws BusinessException;

	int addAsphTargetPro(Asph_TargetProportion asphTargetPro) throws BusinessException;

	int addAsphTargetProD(List<Asph_TargetPropDetailed> list) throws BusinessException;

	List<Map<String, Object>> getAsphTargetProD(Asph_TargetProportion asphTargetPro) throws BusinessException;

	int updateAsphTargetPro(Asph_TargetProportion asphTargetPro) throws BusinessException;

	int delAsph_TargetPropDetailed(Asph_TargetProportion asphTargetPro) throws BusinessException;

	int delAsphTargetPro(Asph_TargetProportion asphTargetPro) throws BusinessException;
	
	List<Asph_TargetProportion> select_Asph_TargetPropDetailed(Asph_TargetProportion asph_TargetPro) throws BusinessException;

	List<Asph_TargetProportion> getAsphTargetProById(Asph_TargetProportion asphTargetPro) throws BusinessException;
	
	/*
	 * 根据Targ_PropID 查找
	 * tongn
	 * 2018.6.27
	 */
	List<Asphalt_Prod_Proportion> getAsphalt_Prod_ProportionPropId(Asphalt_Prod_Proportion asphalt_Prod_Proportion) throws Exception;
}
