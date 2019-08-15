package com.jingpeng.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jingpeng.dao.CommonDao;
import com.jingpeng.model.Asph_TargetProportion;
import com.jingpeng.model.Asphalt_Grading;
import com.jingpeng.model.Asphalt_Prod_Proportion;
import com.jingpeng.model.Cement_ConsPropDetailed;
import com.jingpeng.model.Cement_ConstructionProportion;
import com.jingpeng.model.Core_User_Info;
import com.jingpeng.model.Equipment_Info;
import com.jingpeng.model.Organization_Info;
import com.jingpeng.model.User_Info;
import com.jingpeng.model.V_MaterialInfo;
import com.jingpeng.service.CommonService;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.exception.DataAccessException;

@Service
@Transactional
public class CommonServiceImpl implements CommonService{
	@Autowired
	private CommonDao commonDao;
	
	public List<V_MaterialInfo> getMaterialName(V_MaterialInfo v_MaterialInfo) throws BusinessException {
		try {
			return commonDao.getMaterialName(v_MaterialInfo);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}
	
	public List<V_MaterialInfo> getMaterialModel(V_MaterialInfo v_MaterialInfo) throws BusinessException {
		try {
			return commonDao.getMaterialModel(v_MaterialInfo);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}
	
	public List<Asph_TargetProportion> getAsph_TargetProCode() throws BusinessException {
		try {
			return commonDao.getAsph_TargetProCode();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}
	
	public List<Asph_TargetProportion> getAsph_TargetProCodeById(V_MaterialInfo v_MaterialInfo) throws BusinessException {
		try {
			return commonDao.getAsph_TargetProCodeById(v_MaterialInfo);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}

	public List<Cement_ConsPropDetailed> getCement_ConsPropDetailedById(Cement_ConsPropDetailed cement_ConsPropDetailed)
			throws BusinessException {
		try {
			return commonDao.getCement_ConsPropDetailedById(cement_ConsPropDetailed);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}

	public List<Cement_ConstructionProportion> getCement_ConstructionProportionById(Cement_ConstructionProportion cement_ConstructionProportion) throws BusinessException {
		try {
			return commonDao.getCement_ConstructionProportionById(cement_ConstructionProportion);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}

	public List<Equipment_Info> getEquipmentInfo(HashMap<String, Object> map) throws BusinessException {
		try {
			return commonDao.getEquipmentInfo(map);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}

	public List<Asph_TargetProportion> getGradeCodeById(V_MaterialInfo v_MaterialInfo) throws BusinessException {
		try {
			return commonDao.getGradeCodeById(v_MaterialInfo);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}

	public List<Asphalt_Prod_Proportion> getProportionCode(Asphalt_Prod_Proportion asphalt_Prod_Proportion)
			throws BusinessException {
		try {
			return commonDao.getProportionCode(asphalt_Prod_Proportion);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}

	public List<Asphalt_Grading> getGradeCode(Asphalt_Prod_Proportion asphalt_Prod_Proportion)
			throws BusinessException {
		try {
			return commonDao.getGradeCode(asphalt_Prod_Proportion);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}

	public List<Organization_Info> getOrgTree(Map<String, String> map) throws BusinessException {
		try {
			return commonDao.getOrgTree(map);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}
	
	public int[] getUserOrgId(User_Info user) throws BusinessException {
		try {
			List<Map<String, Object>> list = commonDao.getUserOrgId(user);
			int[] orgs = new int[list.size()];
			for(int i = 0; i < list.size(); i++) {
				orgs[i] = Integer.parseInt(list.get(i).get("i_id").toString());
			}
			return orgs;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}

	public List<V_MaterialInfo> getMaterialNames(HashMap<String, Object> map) throws BusinessException {
		try {
			return commonDao.getMaterialNames(map);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}

	public List<V_MaterialInfo> getMaterialModels(HashMap<String, Object> map) throws BusinessException {
		try {
			return commonDao.getMaterialModels(map);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}

	public String getCUserOrgId(Core_User_Info user) throws BusinessException {
		try {
			List<Map<String, Object>> list = commonDao.getCUserOrgId(user);
			String str_power_Org_Id = list.get(0).get("str_power_Org_Id").toString();
			return str_power_Org_Id;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}

	public List<V_MaterialInfo> getMaterialModelIdbyNameAndCode(V_MaterialInfo v_MaterialInfo) throws BusinessException {
		try {
			 return commonDao.getMaterialModelIdbyNameAndCode(v_MaterialInfo);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
		
	}
}
