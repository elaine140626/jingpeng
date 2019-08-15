package com.oil.dao.screenDisplay;

import java.util.List;

import com.oil.model.dispath.NextProductionPlanEntity;
import com.oil.model.testreports.Exportmeasure;

public interface QualityDao {

	//查询待出厂检测数据
	List<Exportmeasure> getAllBeforeQuality();
	
	//待下发生产工艺通知单
	List<NextProductionPlanEntity> getAllPlanQuality();
}
