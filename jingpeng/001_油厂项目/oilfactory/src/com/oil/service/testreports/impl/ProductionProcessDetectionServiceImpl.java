package com.oil.service.testreports.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oil.dao.dispath.NextProductionPlanDao;
import com.oil.dao.testreports.ProductionProcessDetectionDao;
import com.oil.model.dispath.NextProductionPlanEntity;
import com.oil.service.testreports.ProductionProcessDetectionService;

@Service
@Transactional
public class ProductionProcessDetectionServiceImpl implements ProductionProcessDetectionService{

	@Autowired
	private ProductionProcessDetectionDao productionProcessDetectionDao;
	@Autowired
	private NextProductionPlanDao nextProductionPlanDao;
	
	// 计划调度表获取(生产过程检测)
	public List<NextProductionPlanEntity>  getPlanMeasure(Map<String,Object> map){
		return productionProcessDetectionDao.getPlanMeasure(map);
	}
	
	// 生产过程检测确认合格
	public int updateProductionProcessDetection(NextProductionPlanEntity nextProductionPlanEntity) {
		int res = 0;
		// 生产过程检测
		res += nextProductionPlanDao.updateProductionPlan(nextProductionPlanEntity);
		// 质检不合格需要调整的场合
		if (nextProductionPlanEntity.getIsQualified() == 1 && nextProductionPlanEntity.getIsAdjust() == 0) {
			// 质检不合格需要调整的场合，回到生产工艺通知单
			res += productionProcessDetectionDao.updateState(nextProductionPlanEntity);
		}		
		return res;
	}
}
