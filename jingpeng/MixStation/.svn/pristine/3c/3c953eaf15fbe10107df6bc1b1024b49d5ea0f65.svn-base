package com.jingpeng.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.jingpeng.dao.CementConstructionPropDao;
import com.jingpeng.model.Asph_TargetPropDetailed;
import com.jingpeng.model.Asph_TargetProportion;
import com.jingpeng.model.Cement_ConsPropDetailed;
import com.jingpeng.model.Cement_ConstructionProportion;
import com.jingpeng.model.Cement_TheoPropDetailed;
import com.jingpeng.model.Cement_TheoryProportion;
import com.jingpeng.service.CementConstructionPropService;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.exception.DataAccessException;

@Service
@Transactional
public class CementConstructionPropServiceImpl implements CementConstructionPropService{
	@Autowired
	private CementConstructionPropDao cementConstructionPropDao;
	
	public List<Cement_ConstructionProportion> getCementConstructionProp(Map<String, Object> map) throws BusinessException {
		try {
			return cementConstructionPropDao.getCementConstructionProp(map);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}

	public List<Cement_ConstructionProportion> getPropCode(String str_prop_Code) throws BusinessException {
		try {
			return cementConstructionPropDao.getPropCode(str_prop_Code);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}

	public List<Cement_ConstructionProportion> getMaterNameAndModel(Cement_ConstructionProportion cement_ConstructionProportion) throws BusinessException {
		try {
			return cementConstructionPropDao.getMaterNameAndModel(cement_ConstructionProportion);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}


	public int addCementConstructionPro(Map<String, Object> map) throws BusinessException {
		try {
			int i_id = cementConstructionPropDao.addCementConstructionPro(map);
			List<Cement_ConsPropDetailed> cement_ConsPropDetailedList = (List<Cement_ConsPropDetailed>) map.get("list");
			for(int i = 0; i < cement_ConsPropDetailedList.size(); i++) {
				cement_ConsPropDetailedList.get(i).setI_consProp_Id(i_id);
			}
			cementConstructionPropDao.addCementConsPropDetailed(cement_ConsPropDetailedList);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
		return 0;
	}

	public List<Cement_TheoryProportion> getTheoProp(int i_id) throws BusinessException {
		try {
			return cementConstructionPropDao.getTheoProp(i_id);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}

	public int addCementConsPropDetailed(List<Cement_ConsPropDetailed> cement_ConsPropDetailedList) throws BusinessException {
		try {
			return cementConstructionPropDao.addCementConsPropDetailed(cement_ConsPropDetailedList);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}

	public int updateCementConstructionPro(Map<String, Object> map) throws BusinessException {
		try {
			cementConstructionPropDao.delCementConsPropDetailed(map);
//			cementConstructionPropDao.updateCementConstructionPro(map);
			List<Cement_ConsPropDetailed> cement_ConsPropDetailedList = (List<Cement_ConsPropDetailed>) map.get("list");
			
			for(int i = 0; i < cement_ConsPropDetailedList.size(); i++) {
				cement_ConsPropDetailedList.get(i).setI_consProp_Id(Integer.parseInt(map.get("i_id").toString()));
			}
			cementConstructionPropDao.addSgpbXxList(cement_ConsPropDetailedList);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
		return 0;
	}

	public int delCementConstructionPro(Cement_ConstructionProportion cement_ConstructionProportion) throws BusinessException {
		try {
			return cementConstructionPropDao.delCementConstructionPro(cement_ConstructionProportion);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}

	public List<Cement_ConstructionProportion> getCementConstructionPropbypids(Map<String, Object> map)throws BusinessException {
		try {
			return cementConstructionPropDao.getCementConstructionPropbyPids(map);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}


	public List<Map<String, Object>> getgetTheoryProportionCode(Map<String, Object> map) throws BusinessException {
		try {
			return cementConstructionPropDao.getgetTheoryProportionCode(map);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}

	public List<Cement_ConstructionProportion> getC_ConstructionByid(Cement_ConstructionProportion cement_ConstructionProportion) throws BusinessException {
		try {
			return cementConstructionPropDao.getC_ConstructionByid(cement_ConstructionProportion);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}

	public List<Cement_ConstructionProportion> getCement_ConstructionByid(HashMap<String, Object> map)throws BusinessException {
		try {
			return cementConstructionPropDao.getCement_ConstructionByid(map);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}

	public List<Map<String, Object>> getCementConstructionPropbypid(Map<String, Object> map)throws BusinessException {
		try {
			return cementConstructionPropDao.getCementConstructionPropbyPid(map);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}
	
	public List<Map<String, Object>> getCementConstructionPropbypidList(Map<String, Object> map)throws BusinessException {
		try {
			return cementConstructionPropDao.getCementConstructionPropbypidList(map);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}
	
	public List<Cement_ConsPropDetailed> getC_ConstructionDeatlByid(Cement_ConsPropDetailed cement_ConsPropDetailed)throws BusinessException {
		try {
			return cementConstructionPropDao.getC_ConstructionDeatlByid(cement_ConsPropDetailed);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}

	public int addSgpbXx(Cement_ConstructionProportion cement_ConstructionProportion) throws BusinessException {
		try {
			int i_id =  cementConstructionPropDao.addSgpbXx(cement_ConstructionProportion);
			List<Cement_ConsPropDetailed> list =cement_ConstructionProportion.getCement_ConsPropDetailedList();
			if(list != null) {
				for(int i = 0; i < list.size(); i++) {
					list.get(i).setI_consProp_Id(i_id);
					list.get(i).setI_valid_Flag(1);
					
				}
				cementConstructionPropDao.addSgpbXxList(list);
			}
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
		return 0;
	}

	public int addSgpbXxList(List<Cement_ConsPropDetailed> list) throws BusinessException {
		try {
			return cementConstructionPropDao.addSgpbXxList(list);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
		
	}
	
	public List<Cement_ConstructionProportion> getMainById(Cement_ConstructionProportion cement_ConstructionProportion)throws BusinessException {
		try {
			return cementConstructionPropDao.getMainById(cement_ConstructionProportion);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}
	
	public List<Map<String, Object>> getMainByIdGrid(Cement_ConstructionProportion cement_ConstructionProportion) throws BusinessException {
		try {
			return cementConstructionPropDao.getMainByIdGrid(cement_ConstructionProportion);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	/*
	 * 理论配合比删除条件
	 * tongn
	 * 2018.6.28
	 */
	public List<Map<String, Object>> getCementConstructionProportionByTheoPropID(Map<String, Object> map)
			throws BusinessException {
		try {
			return cementConstructionPropDao.getCementConstructionProportionByTheoPropID(map);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	public List<Map<String, Object>> getTheory(Map<String, Object> map) throws BusinessException{
		try {
			return cementConstructionPropDao.getTheory(map);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	public List<Map<String, Object>> getTheoryList(Map<String, Object> map) throws BusinessException {
		try {
			return cementConstructionPropDao.getTheoryList(map);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	public List<Map<String, Object>> getSgpbNo(Map<String, Object> map) throws BusinessException {
		try {
			return cementConstructionPropDao.getSgpbNo(map);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}

	public List<Cement_ConsPropDetailed> select_Asph_TargetPropDetailed(
			Cement_ConsPropDetailed cement_ConsPropDetailed) throws DataAccessException {

		return cementConstructionPropDao.select_Asph_TargetPropDetailed(cement_ConsPropDetailed);
	}

}
