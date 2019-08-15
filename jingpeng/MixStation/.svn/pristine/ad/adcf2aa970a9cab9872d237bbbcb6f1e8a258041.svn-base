package com.jingpeng.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jingpeng.dao.Cement_TheoPropDetailedDao;
import com.jingpeng.dao.Cement_TheoryProportionDao;
import com.jingpeng.model.Cement_ConsPropDetailed;
import com.jingpeng.model.Cement_TheoPropDetailed;
import com.jingpeng.model.Cement_TheoryProportion;
import com.jingpeng.model.V_MaterialInfo;
import com.jingpeng.service.Cement_TheoryProportionService;
import com.jingpeng.service.impl.Cement_TheoryProportionServiceImpl;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.exception.DataAccessException;

@Service
@Transactional
public class Cement_TheoryProportionServiceImpl implements Cement_TheoryProportionService {

	@Autowired
	private Cement_TheoryProportionDao  cement_TheoryProportionDao;
	
	@Autowired
	private Cement_TheoPropDetailedDao cement_TheoPropDetailedDao ;
	
	public List<Cement_TheoryProportion> getCement_TheoryProportion( HashMap<String, Object>  map) throws BusinessException {
		
		try {
			return cement_TheoryProportionDao.getCement_TheoryProportion(map);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
		
	}
	

	public List<Cement_TheoryProportion> getCement_TheoryProportionById(Cement_TheoryProportion cement_TheoryProportion)
			throws BusinessException {
		try {
			return cement_TheoryProportionDao.getCement_TheoryProportionById(cement_TheoryProportion);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}
	
	public int addCement_TheoryProportion(Map<String, Object> map) throws BusinessException {
		try {
			int i_id = cement_TheoryProportionDao.addCement_TheoryProportion(map);
			//map.put("i_materials_Id", (Integer)map.get("list").get);
			List<Cement_TheoPropDetailed> list = (List<Cement_TheoPropDetailed>) map.get("list");
			for(int i = 0; i < list.size(); i++) {
				list.get(i).setI_theoProp_Id(i_id);
				list.get(i).setI_valid_Flag(1);
				//list.get(i).setI_materials_Id(Integer.parseInt(list.get(i).getStr_material_Name()));
			}
			if(list != null) {
				cement_TheoPropDetailedDao.addCement_TheoPropDetailed(list);
			}else {
				System.out.println("不能为空");
			}
			return 0;
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}

	public int updateCement_TheoryProportion(Map<String, Object> map) throws BusinessException {
		
		try {
			List<Cement_TheoPropDetailed> list = (List<Cement_TheoPropDetailed>) map.get("list");
			cement_TheoryProportionDao.deletD(map);
			for(int i = 0; i < list.size(); i++) {
				list.get(i).setI_theoProp_Id(Integer.valueOf(map.get("i_id").toString()));
			}
			cement_TheoryProportionDao.updateCement_TheoryProportion(map);
			cement_TheoPropDetailedDao.addCement_TheoPropDetailed(list);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
		return 0;
	}

	public int deletCement_TheoryProportion(Cement_TheoryProportion cement_TheoryProportion) throws BusinessException {
		try {
			return cement_TheoryProportionDao.deletCement_TheoryProportion(cement_TheoryProportion);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}

	public List<Cement_TheoryProportion> getCementProportionCode(Cement_TheoryProportion cement_TheoryProportion)throws BusinessException {
		
		try {
			return cement_TheoryProportionDao.getCementProportionCode(cement_TheoryProportion);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}


	public List<V_MaterialInfo> getRawMaterial(Map<String, Object> map) throws BusinessException {
		try {
			return cement_TheoryProportionDao.getRawMaterial(map);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}


	public List<Cement_ConsPropDetailed> getCement_TheoryDetailByProporId(
			Cement_ConsPropDetailed cement_ConsPropDetailed) throws BusinessException {
		try {
			return cement_TheoryProportionDao.getCement_TheoryDetailByProporId(cement_ConsPropDetailed);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}


	public List<Map<String, Object>> getYclList(Cement_ConsPropDetailed cement_ConsPropDetailed)
			throws BusinessException {
		try {
			return cement_TheoryProportionDao.getYclList(cement_ConsPropDetailed);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}

	public List<Map<String, Object>> getAllMaterials_id(Map<String, Object> map)throws BusinessException {
		try {
			return cement_TheoryProportionDao.getAllMaterials_id(map);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}

	public List<Map<String, Object>> getYclModelList(Map<String, Object> map) throws BusinessException {
		try {
			return cement_TheoryProportionDao.getYclModelList(map);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}


	public List<Cement_TheoPropDetailed> select_Asph_TargetPropDetailed(Cement_TheoPropDetailed cement_TheoPropDetailed)
			throws BusinessException {
		try {
			return cement_TheoryProportionDao.select_Asph_TargetPropDetailed(cement_TheoPropDetailed);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}

}
