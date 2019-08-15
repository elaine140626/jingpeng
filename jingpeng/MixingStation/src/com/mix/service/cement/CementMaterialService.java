package com.mix.service.cement;

import java.util.List;
import java.util.Map;

import com.mix.model.V_MaterialInfo;

/**
 * 
 * @Title 水泥物料
 * @author ygt
 * @date 2018年9月30日
 */
public interface CementMaterialService {
	//获取物料列表
	public List<V_MaterialInfo> getMaterialInfo(V_MaterialInfo v_MaterialInfo);
	
	//主键获取物料信息
	public List<V_MaterialInfo> getMaterialById(V_MaterialInfo v_MaterialInfo);
	
	//验证插入物料的名称和型号不能重复
	public List<V_MaterialInfo> getMaterialbyNameOrModel (V_MaterialInfo v_MaterialInfo);
	
	//更新物料
	public int updateMaterial(V_MaterialInfo v_MaterialInfo);
	
	//通过物料编号查询物料信息
	public List<V_MaterialInfo> getMaterialbyCode(V_MaterialInfo v_MaterialInfo);
	
	//水泥原材料可以删除条件
	public List<Map<String, Object>> iscementMaterialMayMaterialDel1(Map<String, Object> map);
	
	//水泥原材料可以删除条件
	public List<Map<String, Object>> iscementMaterialMayMaterialDel2(Map<String, Object> map);
	
	//水泥删除物料
	public int deletMaterial(V_MaterialInfo v_MaterialInfo);
	
	//添加原材料
	public void addMater(V_MaterialInfo v_MaterialInfo);
}
