package com.mixingStation.service.cement.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mixingStation.dao.cement.CementConstructionProportionDao;
import com.mixingStation.model.cement.CementConsPropDetailed;
import com.mixingStation.model.cement.CementConstructionProportion;
import com.mixingStation.model.cement.CementTheoPropDetailed;
import com.mixingStation.model.cement.CementTheoryProportion;
import com.mixingStation.model.materialInfo.MaterialInfo;
import com.mixingStation.service.cement.CementConstructionProportionService;
@Service
public class CementConstructionProportionServiceImpl implements CementConstructionProportionService {

	@Autowired
	private CementConstructionProportionDao cementConstructionProportionDao;
	
	@Override
	public List<CementConstructionProportion> getAllCementConstructionProportion(Map<String, Object> map) {
		return cementConstructionProportionDao.getAllCementConstructionProportion(map);
	}

	@Override
	public List<MaterialInfo> getMaterialNameList(Map<String, Object> map) {
		return cementConstructionProportionDao.getMaterialNameList(map);
	}

	@Override
	public List<MaterialInfo> getMaterialModelList(Map<String, Object> map) {
		return cementConstructionProportionDao.getMaterialModelList(map);
	}

	@Override
	public List<CementTheoryProportion> getCementTheoryProportion(Map<String, Object> map) {
		return cementConstructionProportionDao.getCementTheoryProportion(map);
	}

	@Override
	public List<CementTheoPropDetailed> getCementTheoPropDetailedById(Map<String, Object> map) {
		return cementConstructionProportionDao.getCementTheoPropDetailedById(map);
	}

	@Override
	public int addCementConstructionProportion(CementConstructionProportion cementConstructionProportion,List<CementConsPropDetailed> list) {
		int result = 0;
		//理论信息表插入
		result += cementConstructionProportionDao.addCementConstructionProportion(cementConstructionProportion);
		//取得刚插入的理论配比表的id
		int id = cementConstructionProportionDao.getCementConstructionProportionInfoId(cementConstructionProportion.getOperator());
		//理论配比明细表的插入
		if(list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setConsPropId(id);
				result += cementConstructionProportionDao.addCementConsPropDetailed(list.get(i));
			}
		}
		return result;
	}

	@Override
	public List<CementConstructionProportion> getCementConstructionProportionById(Map<String, Object> map) {
		return cementConstructionProportionDao.getCementConstructionProportionById(map);
	}

	@Override
	public List<CementConsPropDetailed> getCementConsPropDetailedById(Map<String, Object> map) {
		return cementConstructionProportionDao.getCementConsPropDetailedById(map);
	}

	@Override
	public int updateCementConstructionProportion(CementConstructionProportion cementConstructionProportion,List<CementConsPropDetailed> list) {
		int result = 0;
		//配比信息表的更新
		result += cementConstructionProportionDao.updateCementConstructionProportion(cementConstructionProportion);
		//明细删除
		result += cementConstructionProportionDao.deleteCementConsPropDetailed(Integer.toString(cementConstructionProportion.getId()));
		//明细插入
		if(list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setConsPropId(cementConstructionProportion.getId());
				result += cementConstructionProportionDao.addCementConsPropDetailed(list.get(i));
			}
		}
		return result;
	}

	@Override
	public int deleteCementConstructionProportionById(Map<String, Object> map) {
		int result = 0;
		//删除施工配比
		result += cementConstructionProportionDao.deleteCementConstructionProportionById(map);
		//删除明细
		result += cementConstructionProportionDao.deleteCementConsPropDetailedById(map);
		return result;
	}

}
