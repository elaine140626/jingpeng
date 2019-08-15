package com.mix.service.asphalt.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mix.dao.asphalt.AsphProportionDao;
import com.mix.dao.asphalt.Asph_TargetProDao;
import com.mix.model.Asph_TargetPropDetailed;
import com.mix.model.Asph_TargetProportion;
import com.mix.model.Asphalt_Prod_Proportion;
import com.mix.model.V_MaterialInfo;
import com.mix.service.asphalt.Asph_TargetProService;

@Service
@Transactional
public class Asph_TargetProServiceImpl implements Asph_TargetProService{
	@Autowired
	private Asph_TargetProDao asph_TargetProDao;
	@Autowired
	AsphProportionDao  asphProportionDao;
	private Asph_TargetProportion asphTargetPro;
	
	public List<Asph_TargetProportion> getAsphTargetPro(Map<String, Object> map)  {
			return asph_TargetProDao.getV_Asph_TargetPro(map);
	}

	public List<V_MaterialInfo> getRawMaterial(Map map)  {
			return asph_TargetProDao.getRawMaterial(map);
	}

	public List<V_MaterialInfo> getMaterialModelBymateNameid(V_MaterialInfo v_MaterialInfo)  {
			return asph_TargetProDao.getMaterialModelBymateNameid(v_MaterialInfo);
	}
	
	public List getProportionCode(Asph_TargetProportion asphTargetPro)  {
			return asph_TargetProDao.getProportionCode(asphTargetPro);
	}

	public int addAsphTargetProD(List<Asph_TargetPropDetailed> list)  {
			return asph_TargetProDao.addAsphTargetProD(list);
	}

	public int addAsphTargetPro(Asph_TargetProportion asphTargetPro)  {
			asph_TargetProDao.addAsphTargetPro(asphTargetPro);
			List<Asph_TargetProportion> Pro = asph_TargetProDao.getAsphTargetProById(asphTargetPro);
			List<Asph_TargetPropDetailed> list = asphTargetPro.getAsph_TargetPropList();
			if(list != null) {
				for(int i = 0; i < list.size(); i++) {
					list.get(i).setI_targ_Prop_Id(Pro.get(0).getI_id());
					list.get(i).setI_valid_Flag(1);
				}
				asph_TargetProDao.addAsphTargetProD(list);
			}
			return 0;
	}

	public List<Map<String, Object>> getAsphTargetProD(Asph_TargetProportion asphTargetPro)  {
			return asph_TargetProDao.getAsphTargetProD(asphTargetPro);
	}

	public int updateAsphTargetPro(Asph_TargetProportion asphTargetPro)  {
			asph_TargetProDao.updateAsphTargetPro(asphTargetPro);
			List<Asph_TargetPropDetailed> list = asphTargetPro.getAsph_TargetPropList();
			if(list != null) {
				for(int i = 0; i < list.size(); i++) {
					list.get(i).setI_targ_Prop_Id(asphTargetPro.getI_id());
					list.get(i).setI_valid_Flag(1);
				}
				asph_TargetProDao.delAsph_TargetPropDetailed(asphTargetPro);
				asph_TargetProDao.addAsphTargetProD(list);
			}
			return 0;
	}

	public int delAsph_TargetPropDetailed(Asph_TargetProportion asphTargetPro)  {
			return asph_TargetProDao.delAsph_TargetPropDetailed(asphTargetPro);
	}

	public int delAsphTargetPro(Asph_TargetProportion asphTargetPro)  {
			return asph_TargetProDao.delAsphTargetPro(asphTargetPro);
	}

	public List<Asph_TargetProportion> getAsphTargetProById(Asph_TargetProportion asphTargetPro){
			return asph_TargetProDao.getAsphTargetProById(asphTargetPro);
	}
	public List<Asphalt_Prod_Proportion> getAsphalt_Prod_ProportionPropId(Asphalt_Prod_Proportion asphalt_Prod_Proportion){
		return asphProportionDao.getAsphalt_Prod_ProportionPropId(asphalt_Prod_Proportion);
	}



	public List<Asph_TargetProportion> select_Asph_TargetPropDetailed(Asph_TargetProportion asph_TargetPro)  {
			return  asph_TargetProDao.select_Asph_TargetPropDetailed(asph_TargetPro);
	}

	@Override
	public List<Asph_TargetProportion> Find_Asph_TargetProportionByMaterial_Code(
			Map<String, Object> map) {
		return asph_TargetProDao.Find_Asph_TargetProportionByMaterial_Code(map);
	}

}
