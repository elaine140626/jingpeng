package com.mixingStation.service.asphalt;

import java.util.List;
import java.util.Map;

import com.mixingStation.model.asphalt.Asph_TargetPropDetailed;
import com.mixingStation.model.asphalt.Asph_TargetProportion;

public interface Asph_TargetProService {
	/**
	 * 查询目标配合比列表
	 */
	List<Asph_TargetProportion> getAsphTargetPro(Map<String, Object> map) ;
	/**
	 * 添加目标配合比信息
	 */
	int addAsphTargetPro(Asph_TargetProportion asphTargetPro) ;
	/**
	 * 添加目标配合比详细信息
	 */
	int addAsphTargetProD(List<Asph_TargetPropDetailed> list) ;
	/**
	 * 修改目标配合比信息
	 */
	int updateAsphTargetPro(Asph_TargetProportion asphTargetPro) ;
	/**
	 * 删除目标配合比信息
	 */
	int delAsphTargetPro(Asph_TargetProportion asphTargetPro) ;
	/**
	 * 删除目标配合比详细信息
	 */
	int delAsph_TargetPropDetailed(Asph_TargetProportion asphTargetPro) ;
	
	int getAsphTargetProRepeat(Asph_TargetProportion asph_TargetPro) ;
	
	List<Asph_TargetProportion> getAsphTargetProById(Asph_TargetProportion asphTargetPro) ;
	
	List<Map<String, Object>> getAsphTargetProD(Asph_TargetProportion asphTargetPro) ;
}
