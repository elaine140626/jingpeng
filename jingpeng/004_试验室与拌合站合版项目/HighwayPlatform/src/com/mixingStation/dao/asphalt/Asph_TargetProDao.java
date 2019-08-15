package com.mixingStation.dao.asphalt;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mixingStation.model.asphalt.Asph_TargetPropDetailed;
import com.mixingStation.model.asphalt.Asph_TargetProportion;


@Repository
public interface Asph_TargetProDao{
	
	List<Asph_TargetProportion> getAsphTargetPro(Map<String, Object> map) ;
	
	int addAsphTargetPro(Asph_TargetProportion asphTargetPro) ;

	int addAsphTargetProD(List<Asph_TargetPropDetailed> list) ;
	
	int updateAsphTargetPro(Asph_TargetProportion asphTargetPro) ;

	int delAsph_TargetPropDetailed(Asph_TargetProportion asphTargetPro) ;

	int delAsphTargetPro(Asph_TargetProportion asphTargetPro) ;
	
	int getAsphTargetProRepeat(Asph_TargetProportion asph_TargetPro) ;

	List<Asph_TargetProportion> getAsphTargetProById(Asph_TargetProportion asphTargetPro) ;
	
	List<Map<String, Object>> getAsphTargetProD(Asph_TargetProportion asphTargetPro) ;
	
}
