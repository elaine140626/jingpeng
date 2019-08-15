package com.mix.dao.cement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mix.model.Core_User_Info;
import com.mix.model.Equipment_Info;
import com.mix.model.Organization_Info;
import com.mix.model.User_Info;
import com.mix.model.V_MaterialInfo;

/**
 * 
 * @Title 共用的接口
 * @author ygt
 * @date 2018年9月30日
 */
public interface CommonCementDao {
	//获取用户的组织机构
	public List<Map<String, Object>> getCUserOrgId(Core_User_Info user);
	public List<Map<String, Object>> getUserOrgId(User_Info user);
	
	//获取客户端树tree
	public List<Organization_Info> getOrgTree(Map<String, String> map);
	
	//查询产品名称
	public List<V_MaterialInfo> getMaterialNames(HashMap<String, Object> map);

	//查询产品型号
	public List<V_MaterialInfo> getMaterialModels(HashMap<String, Object> map);
	public List<V_MaterialInfo> getMaterialModel(V_MaterialInfo v_MaterialInfo);

	//获取设备信息
	public List<Equipment_Info> getEquipmentInfo(HashMap<String, Object> map);
}
