package com.mix.dao.asphalt;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mix.model.Asph_TargetPropDetailed;
import com.mix.model.Asph_TargetProportion;
import com.mix.model.Asphalt_Prod_Proportion;
import com.mix.model.V_MaterialInfo;

@Repository
public interface Asph_TargetProDao{
	List<Asph_TargetProportion> getAsphTargetPro(Map<String, Object> map) ;
	
	List<Asph_TargetProportion> getV_Asph_TargetPro(Map<String, Object> map) ;

	List<V_MaterialInfo> getRawMaterial(Map map) ;

	List<V_MaterialInfo> getMaterialModelBymateNameid(V_MaterialInfo v_MaterialInfo) ;

	List getProportionCode(Asph_TargetProportion asphTargetPro) ;

	int addAsphTargetPro(Asph_TargetProportion asphTargetPro) ;

	int addAsphTargetProD(List<Asph_TargetPropDetailed> list) ;

	List<Map<String, Object>> getAsphTargetProD(Asph_TargetProportion asphTargetPro) ;

	int updateAsphTargetPro(Asph_TargetProportion asphTargetPro) ;

	int delAsph_TargetPropDetailed(Asph_TargetProportion asphTargetPro) ;

	int delAsphTargetPro(Asph_TargetProportion asphTargetPro) ;
	
	List<Asph_TargetProportion> select_Asph_TargetPropDetailed(Asph_TargetProportion asph_TargetPro) ;

	List<Asph_TargetProportion> getAsphTargetProById(Asph_TargetProportion asphTargetPro) ;
	
	List<Asphalt_Prod_Proportion> getAsphalt_Prod_ProportionPropId(Asphalt_Prod_Proportion asphalt_Prod_Proportion);
	
	List<Asph_TargetProportion> Find_Asph_TargetProportionByMaterial_Code(Map<String, Object> map);
}
