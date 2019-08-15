package com.mix.service.cement.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mix.dao.cement.CommonCementDao;
import com.mix.model.Core_User_Info;
import com.mix.model.Equipment_Info;
import com.mix.model.Organization_Info;
import com.mix.model.User_Info;
import com.mix.model.V_MaterialInfo;
import com.mix.service.cement.CommonCementService;
@Service
@Transactional
public class CommonCementServiceImpl implements CommonCementService {
	@Autowired
	private CommonCementDao commonCementDao;
	
	@Override
	public String getCUserOrgId(Core_User_Info user) {
		List<Map<String, Object>> list = commonCementDao.getCUserOrgId(user);
		String str_power_Org_Id = list.get(0).get("str_power_Org_Id").toString();
		return str_power_Org_Id;
	}

	@Override
	public int[] getUserOrgId(User_Info user) {
		List<Map<String, Object>> list = commonCementDao.getUserOrgId(user);
		int[] orgs = new int[list.size()];
		for(int i = 0; i < list.size(); i++) {
			orgs[i] = Integer.parseInt(list.get(i).get("i_id").toString());
		}
		return orgs;
	}

	@Override
	public List<Organization_Info> getOrgTree(Map<String, String> map) {
		return commonCementDao.getOrgTree(map);
	}

	@Override
	public List<V_MaterialInfo> getMaterialNames(HashMap<String, Object> map) {
		return commonCementDao.getMaterialNames(map);
	}

	@Override
	public List<V_MaterialInfo> getMaterialModels(HashMap<String, Object> map) {
		return commonCementDao.getMaterialModels(map);
	}

	@Override
	public List<V_MaterialInfo> getMaterialModel(V_MaterialInfo v_MaterialInfo) {
		return commonCementDao.getMaterialModel(v_MaterialInfo);
	}

	@Override
	public List<Equipment_Info> getEquipmentInfo(HashMap<String, Object> map) {
		return commonCementDao.getEquipmentInfo(map);
	}

}
