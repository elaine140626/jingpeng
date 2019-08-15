package com.mix.service.cement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mix.model.Cement_ConsPropDetailed;
import com.mix.model.Cement_TheoPropDetailed;
import com.mix.model.Cement_TheoryProportion;
import com.mix.model.V_MaterialInfo;

/**
 * 
 * @Title 水泥理论比例
 * @author ygt
 * @date 2018年10月8日
 */
public interface Cement_TheoryProportionService {
	//查询水泥的理论配比信息
	public List<Cement_TheoryProportion> getCement_TheoryProportion(HashMap<String, Object> map);

	//水泥施工配比明细
	public List<Cement_ConsPropDetailed> getCement_TheoryDetailByProporId(Cement_ConsPropDetailed cement_ConsPropDetailed);

	//通过施工配比编号获取所有的物料id
	public List<Map<String, Object>>  getAllMaterials_id(Map<String, Object> map);

	//材料名称
	public List<Map<String, Object>>  getYclList(Cement_ConsPropDetailed cement_ConsPropDetailed);

	//材料类型
	public List<Map<String, Object>>  getYclModelList(Map<String, Object> map);

	//通过id查询水泥理论配比
	public List<Cement_TheoryProportion> getCement_TheoryProportionById(Cement_TheoryProportion cement_TheoryProportion);

	public List<Cement_TheoPropDetailed> select_Asph_TargetPropDetailed(Cement_TheoPropDetailed cement_TheoPropDetailed);
	
	//获取水泥配比编号
	public List<Cement_TheoryProportion> getCementProportionCode(Cement_TheoryProportion cement_TheoryProportion);
	
	//添加 水泥配比
	public int addCement_TheoryProportion(Map<String, Object> map);
	
	//更新 水泥理论配比
	public int updateCement_TheoryProportion(Map<String, Object> map);
	
	//删除水泥理论配比
	public int  deletCement_TheoryProportion(Cement_TheoryProportion cement_TheoryProportion);
	
	//查询原材料
	public List<V_MaterialInfo> getRawMaterial(Map<String, Object> map);
	
	public List<Cement_TheoryProportion> Find_Cement_TheoryProportionByMaterial_Code(Map<String, Object> map);
}

