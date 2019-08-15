package com.mix.service.cement.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mix.dao.cement.CementConstructionPropDao;
import com.mix.model.Cement_ConsPropDetailed;
import com.mix.model.Cement_ConstructionProportion;
import com.mix.model.Cement_TheoryProportion;
import com.mix.service.cement.CementConstructionPropService;
@Service
@Transactional
public class CementConstructionPropServiceImpl implements CementConstructionPropService {

	@Autowired
	private CementConstructionPropDao cementConstructionPropDao;
	
	public List<Cement_ConstructionProportion> getCementConstructionProp(Map<String, Object> map)  {
			return cementConstructionPropDao.getCementConstructionProp(map);
	}

	public List<Cement_ConstructionProportion> getPropCode(String str_prop_Code)  {
			return cementConstructionPropDao.getPropCode(str_prop_Code);
	}

	public List<Cement_ConstructionProportion> getMaterNameAndModel(Cement_ConstructionProportion cement_ConstructionProportion)  {
			return cementConstructionPropDao.getMaterNameAndModel(cement_ConstructionProportion);
	}


	public int addCementConstructionPro(Map<String, Object> map)  {
			int i_id = cementConstructionPropDao.addCementConstructionPro(map);
			List<Cement_ConsPropDetailed> cement_ConsPropDetailedList = (List<Cement_ConsPropDetailed>) map.get("list");
			for(int i = 0; i < cement_ConsPropDetailedList.size(); i++) {
				cement_ConsPropDetailedList.get(i).setI_consProp_Id(i_id);
			}
			cementConstructionPropDao.addCementConsPropDetailed(cement_ConsPropDetailedList);
		return 0;
	}

	public List<Cement_TheoryProportion> getTheoProp(int i_id)  {
			return cementConstructionPropDao.getTheoProp(i_id);
	}

	public int addCementConsPropDetailed(List<Cement_ConsPropDetailed> cement_ConsPropDetailedList)  {
			return cementConstructionPropDao.addCementConsPropDetailed(cement_ConsPropDetailedList);
	}

	public int updateCementConstructionPro(Map<String, Object> map)  {
			cementConstructionPropDao.delCementConsPropDetailed(map);
			List<Cement_ConsPropDetailed> cement_ConsPropDetailedList = (List<Cement_ConsPropDetailed>) map.get("list");
			
			for(int i = 0; i < cement_ConsPropDetailedList.size(); i++) {
				cement_ConsPropDetailedList.get(i).setI_consProp_Id(Integer.parseInt(map.get("i_id").toString()));
			}
			cementConstructionPropDao.addSgpbXxList(cement_ConsPropDetailedList);
		return 0;
	}

	public int delCementConstructionPro(Cement_ConstructionProportion cement_ConstructionProportion)  {
			return cementConstructionPropDao.delCementConstructionPro(cement_ConstructionProportion);
	}

	public List<Cement_ConstructionProportion> getCementConstructionPropbypids(Map<String, Object> map) {
			return cementConstructionPropDao.getCementConstructionPropbyPids(map);
	}


	public List<Map<String, Object>> getgetTheoryProportionCode(Map<String, Object> map)  {
			return cementConstructionPropDao.getgetTheoryProportionCode(map);
	}

	public List<Cement_ConstructionProportion> getC_ConstructionByid(Cement_ConstructionProportion cement_ConstructionProportion)  {
			return cementConstructionPropDao.getC_ConstructionByid(cement_ConstructionProportion);
	}

	public List<Cement_ConstructionProportion> getCement_ConstructionByid(HashMap<String, Object> map) {
			return cementConstructionPropDao.getCement_ConstructionByid(map);
	}

	public List<Map<String, Object>> getCementConstructionPropbypid(Map<String, Object> map) {
			return cementConstructionPropDao.getCementConstructionPropbyPid(map);
	}
	
	public List<Map<String, Object>> getCementConstructionPropbypidList(Map<String, Object> map) {
			return cementConstructionPropDao.getCementConstructionPropbypidList(map);
	}
	
	public List<Cement_ConsPropDetailed> getC_ConstructionDeatlByid(Cement_ConsPropDetailed cement_ConsPropDetailed) {
			return cementConstructionPropDao.getC_ConstructionDeatlByid(cement_ConsPropDetailed);
	}

	public int addSgpbXx(Cement_ConstructionProportion cement_ConstructionProportion)  {
			cementConstructionPropDao.addSgpbXx(cement_ConstructionProportion);
			int i_id = cement_ConstructionProportion.getI_id();
			List<Cement_ConsPropDetailed> list =cement_ConstructionProportion.getCement_ConsPropDetailedList();
			if(list != null) {
				for(int i = 0; i < list.size(); i++) {
					list.get(i).setI_consProp_Id(i_id);
					list.get(i).setI_valid_Flag(1);
					
				}
				cementConstructionPropDao.addSgpbXxList(list);
			}
		return 0;
	}

	public int addSgpbXxList(List<Cement_ConsPropDetailed> list)  {
			return cementConstructionPropDao.addSgpbXxList(list);
	}
	
	public List<Cement_ConstructionProportion> getMainById(Cement_ConstructionProportion cement_ConstructionProportion) {
			return cementConstructionPropDao.getMainById(cement_ConstructionProportion);
	}
	
	public List<Map<String, Object>> getMainByIdGrid(Cement_ConstructionProportion cement_ConstructionProportion)  {
			return cementConstructionPropDao.getMainByIdGrid(cement_ConstructionProportion);
	}
	public List<Map<String, Object>> getCementConstructionProportionByTheoPropID(Map<String, Object> map){
			return cementConstructionPropDao.getCementConstructionProportionByTheoPropID(map);
	}
	public List<Map<String, Object>> getCementConstructionProportionByID(Map<String, Object> map){
		return cementConstructionPropDao.getCementConstructionProportionByID(map);
}
	public List<Map<String, Object>> getTheory(Map<String, Object> map) {
			return cementConstructionPropDao.getTheory(map);
	}

	public List<Map<String, Object>> getTheoryList(Map<String, Object> map)  {
			return cementConstructionPropDao.getTheoryList(map);
	}

	public List<Map<String, Object>> getSgpbNo(Map<String, Object> map)  {
			return cementConstructionPropDao.getSgpbNo(map);
	}

	public List<Cement_ConsPropDetailed> select_Asph_TargetPropDetailed(
			Cement_ConsPropDetailed cement_ConsPropDetailed) {
		return cementConstructionPropDao.select_Asph_TargetPropDetailed(cement_ConsPropDetailed);
	}
}
