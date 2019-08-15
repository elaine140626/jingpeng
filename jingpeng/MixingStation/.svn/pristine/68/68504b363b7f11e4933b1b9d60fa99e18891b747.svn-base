package com.mix.service.cement.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mix.dao.cement.CementConstructionPropDao;
import com.mix.dao.cement.Cement_TheoPropDetailedDao;
import com.mix.dao.cement.Cement_TheoryProportionDao;
import com.mix.model.Cement_ConsPropDetailed;
import com.mix.model.Cement_TheoPropDetailed;
import com.mix.model.Cement_TheoryProportion;
import com.mix.model.V_MaterialInfo;
import com.mix.service.cement.Cement_TheoryProportionService;
@Service
@Transactional
public class Cement_TheoryProportionServiceImpl implements Cement_TheoryProportionService {

	@Autowired
	private Cement_TheoryProportionDao cement_TheoryProportionDao;
	
	@Autowired
	private Cement_TheoPropDetailedDao cement_TheoPropDetailedDao ;
	
	@Autowired
	private CementConstructionPropDao cementConstructionPropDao;
	
	//查询水泥理论配比
	@Override
	public List<Cement_TheoryProportion> getCement_TheoryProportion(HashMap<String, Object> map) {
		return cement_TheoryProportionDao.getCement_TheoryProportion(map);
	}

	//查询水泥施工理论配比明细
	@Override
	public List<Cement_ConsPropDetailed> getCement_TheoryDetailByProporId(
			Cement_ConsPropDetailed cement_ConsPropDetailed) {
		return cement_TheoryProportionDao.getCement_TheoryDetailByProporId(cement_ConsPropDetailed);
	}

	//通过施工配比编号获取所有的物料id
	@Override
	public List<Map<String, Object>> getAllMaterials_id(Map<String, Object> map) {
		return cement_TheoryProportionDao.getAllMaterials_id(map);
	}

	//材料名称
	@Override
	public List<Map<String, Object>> getYclList(Cement_ConsPropDetailed cement_ConsPropDetailed) {
		return cement_TheoryProportionDao.getYclList(cement_ConsPropDetailed);
	}

	//材料类型
	@Override
	public List<Map<String, Object>> getYclModelList(Map<String, Object> map) {
		return cement_TheoryProportionDao.getYclModelList(map);
	}

	//通过id查询水泥理论配比
	@Override
	public List<Cement_TheoryProportion> getCement_TheoryProportionById(
			Cement_TheoryProportion cement_TheoryProportion) {
		return cement_TheoryProportionDao.getCement_TheoryProportionById(cement_TheoryProportion);
	}

	@Override
	public List<Cement_TheoPropDetailed> select_Asph_TargetPropDetailed(
			Cement_TheoPropDetailed cement_TheoPropDetailed) {
		return cement_TheoryProportionDao.select_Asph_TargetPropDetailed(cement_TheoPropDetailed);
	}

	//获取水泥配比编号
	@Override
	public List<Cement_TheoryProportion> getCementProportionCode(Cement_TheoryProportion cement_TheoryProportion) {
		return cement_TheoryProportionDao.getCementProportionCode(cement_TheoryProportion);
	}

	@SuppressWarnings("null")
	@Override
	public int addCement_TheoryProportion(Map<String, Object> map) {
			cement_TheoryProportionDao.addCement_TheoryProportion(map);
			int i_id = (int) map.get("i_id");
			List<Cement_TheoPropDetailed> list = (List<Cement_TheoPropDetailed>) map.get("list");
			for(int i = 0; i < list.size(); i++) {
				list.get(i).setI_theoProp_Id(i_id);
				list.get(i).setI_valid_Flag(1);
			}
			if(list != null) {
				cement_TheoPropDetailedDao.addCement_TheoPropDetailed(list);
			}else {
				System.out.println("不能为空");
			}
			return 0;
	}

	@Override
	public int updateCement_TheoryProportion(Map<String, Object> map) {
		List<Cement_TheoPropDetailed> list = (List<Cement_TheoPropDetailed>) map.get("list");
		cement_TheoryProportionDao.deletD(map);
		for(int i = 0; i < list.size(); i++) {
			list.get(i).setI_theoProp_Id(Integer.valueOf(map.get("i_id").toString()));
		}
		cement_TheoryProportionDao.updateCement_TheoryProportion(map);
		cement_TheoPropDetailedDao.addCement_TheoPropDetailed(list);
		return 0;
	}

	@Override
	public int deletCement_TheoryProportion(Cement_TheoryProportion cement_TheoryProportion) {
		Map<String,Object> map = new HashMap<String,Object>();
				map.put("i_id", cement_TheoryProportion.getI_id());
		cement_TheoryProportionDao.deletD(map);
		cement_TheoryProportionDao.deletCement_TheoryProportion(cement_TheoryProportion);
		return 0;
	}

	@Override
	public List<V_MaterialInfo> getRawMaterial(Map<String, Object> map) {
		return cement_TheoryProportionDao.getRawMaterial(map);
	}

	@Override
	public List<Cement_TheoryProportion> Find_Cement_TheoryProportionByMaterial_Code(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return cement_TheoryProportionDao.Find_Cement_TheoryProportionByMaterial_Code(map);
	}
}
