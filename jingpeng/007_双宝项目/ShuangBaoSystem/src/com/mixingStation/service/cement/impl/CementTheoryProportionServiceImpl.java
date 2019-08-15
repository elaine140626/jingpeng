package com.mixingStation.service.cement.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mixingStation.dao.cement.CementTheoryProportionDao;
import com.mixingStation.model.DataTablesResponseInfo;
import com.mixingStation.model.cement.CementConsPropDetailed;
import com.mixingStation.model.cement.CementTheoPropDetailed;
import com.mixingStation.model.cement.CementTheoryProportion;
import com.mixingStation.model.materialInfo.MaterialInfo;
import com.mixingStation.service.cement.CementTheoryProportionService;
@Service
public class CementTheoryProportionServiceImpl implements CementTheoryProportionService {

	@Autowired
	private CementTheoryProportionDao cementTheoryProportionDao; 
	
	@Override
	public DataTablesResponseInfo getAllCementTheoryProportion(Map<String, Object> map) {
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		List<CementTheoryProportion> allCementTheoryProportion = cementTheoryProportionDao.getAllCementTheoryProportion(map);
		dtri.setData(allCementTheoryProportion);
		return dtri;
	}

	@Override
	public List<MaterialInfo> getMaterialNameList(Map<String, Object> map) {
		return cementTheoryProportionDao.getMaterialNameList(map);
	}

	@Override
	public List<MaterialInfo> getMaterialModelList(Map<String, Object> map) {
		return cementTheoryProportionDao.getMaterialModelList(map);
	}

	@Override
	public int addCementTheoryProportion(CementTheoryProportion cementTheoryProportion,List<CementTheoPropDetailed> list) {
		int result = 0;
		//理论信息表插入
		result += cementTheoryProportionDao.addCementTheoryProportion(cementTheoryProportion);
		//取得刚插入的理论配比表的id
		int id = cementTheoryProportionDao.getCementTheoryProportionInfoId(cementTheoryProportion.getOperator());
		//理论配比明细表的插入
		if(list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setTheoPropId(id);
				result += cementTheoryProportionDao.addCementTheoPropDetailed(list.get(i));
			}
		}
		return result;
	}

	@Override
	public List<CementTheoryProportion> getCementTheoryProportionById(Map<String, Object> map) {
		return cementTheoryProportionDao.getCementTheoryProportionById(map);
	}

	@Override
	public List<CementTheoPropDetailed> getCementTheoPropDetailedById(Map<String, Object> map) {
		return cementTheoryProportionDao.getCementTheoPropDetailedById(map);
	}

	@Override
	public int updateCementTheoryProportion(CementTheoryProportion cementTheoryProportion,List<CementTheoPropDetailed> list) {
		int result = 0;
		//配比信息表的更新
		result += cementTheoryProportionDao.updateCementTheoryProportion(cementTheoryProportion);
		//明细删除
		result += cementTheoryProportionDao.deleteCementTheoPropDetailed(Integer.toString(cementTheoryProportion.getId()));
		//明细插入
		if(list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setTheoPropId(cementTheoryProportion.getId());
				result += cementTheoryProportionDao.addCementTheoPropDetailed(list.get(i));
			}
		}
		return result;
	}

	@Override
	public int deleteCementTheoryProportionById(Map<String, Object> map) {
		int result = 0;
		//删除理论配比
		result += cementTheoryProportionDao.deleteCementTheoryProportionById(map);
		//删除明细
		result += cementTheoryProportionDao.deleteCementTheoPropDetailedById(map);
		return result;
	}
	
	//通过施工配比编号获取所有的物料id
	@Override
	public List<Map<String, Object>> getAllMaterials_id(Map<String, Object> map) {
		return cementTheoryProportionDao.getAllMaterials_id(map);
	}

	//材料名称
	@Override
	public List<Map<String, Object>> getYclList(CementConsPropDetailed cement_ConsPropDetailed) {
		return cementTheoryProportionDao.getYclList(cement_ConsPropDetailed);
	}

	//材料类型
	@Override
	public List<Map<String, Object>> getYclModelList(Map<String, Object> map) {
		return cementTheoryProportionDao.getYclModelList(map);
	}
}
