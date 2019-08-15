package com.mix.service.cement;

import java.util.List;

import com.mix.model.Cement_TheoPropDetailed;
import com.mix.model.Cement_TheoryProportion;

/**
 * 
 * @Title 水泥配比明细
 * @author ygt
 * @date 2018年10月8日
 */
public interface Cement_TheoPropDetailedService {
	
	//水泥配比明细
	public List<Cement_TheoPropDetailed> getCement_TheoPropDetailed(Cement_TheoPropDetailed cement_TheoPropDetailed);
	
	public List<Cement_TheoPropDetailed> getCement_TheoPropDetailedByTheoProp_ID(Cement_TheoryProportion cement_TheoryProportion);

	public int updateCement_TheoPropDetailed(Cement_TheoPropDetailed cement_TheoPropDetailed);
	
	public void deletCement_TheoPropDetailed(Cement_TheoPropDetailed cement_TheoPropDetailed);
	
}