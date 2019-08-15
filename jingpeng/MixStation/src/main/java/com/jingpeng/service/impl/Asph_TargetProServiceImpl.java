package com.jingpeng.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jingpeng.dao.AsphProportionDao;
import com.jingpeng.dao.Asph_TargetProDao;
import com.jingpeng.model.Asph_TargetPropDetailed;
import com.jingpeng.model.Asph_TargetProportion;
import com.jingpeng.model.Asphalt_Prod_Proportion;
import com.jingpeng.model.V_MaterialInfo;
import com.jingpeng.service.Asph_TargetProService;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.exception.DataAccessException;

@Service
@Transactional
public class Asph_TargetProServiceImpl implements Asph_TargetProService{
	@Autowired
	private Asph_TargetProDao asph_TargetProDao;
	@Autowired
	AsphProportionDao  asphProportionDao;
	private Asph_TargetProportion asphTargetPro;
	
	public List<Asph_TargetProportion> getAsphTargetPro(Map<String, Object> map) throws BusinessException {
		try {
			return asph_TargetProDao.getV_Asph_TargetPro(map);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	public List<V_MaterialInfo> getRawMaterial(Map map) throws BusinessException {
		try {
			return asph_TargetProDao.getRawMaterial(map);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	public List<V_MaterialInfo> getMaterialModelBymateNameid(V_MaterialInfo v_MaterialInfo) throws BusinessException {
		try {
			return asph_TargetProDao.getMaterialModelBymateNameid(v_MaterialInfo);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}
	
	public List getProportionCode(Asph_TargetProportion asphTargetPro)  throws BusinessException{
		try {
			return asph_TargetProDao.getProportionCode(asphTargetPro);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	public int addAsphTargetProD(List<Asph_TargetPropDetailed> list) throws BusinessException {
		try {
			return asph_TargetProDao.addAsphTargetProD(list);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
		
	}

	public int addAsphTargetPro(Asph_TargetProportion asphTargetPro) throws BusinessException {
		try {
			int i_id =  asph_TargetProDao.addAsphTargetPro(asphTargetPro);
			List<Asph_TargetPropDetailed> list = asphTargetPro.getAsph_TargetPropList();
			if(list != null) {
				for(int i = 0; i < list.size(); i++) {
					list.get(i).setI_targ_Prop_Id(i_id);
					list.get(i).setI_valid_Flag(1);
				}
				asph_TargetProDao.addAsphTargetProD(list);
			}
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
		return 0;
	}

	public List<Map<String, Object>> getAsphTargetProD(Asph_TargetProportion asphTargetPro) throws BusinessException {
		try {
			return asph_TargetProDao.getAsphTargetProD(asphTargetPro);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	public int updateAsphTargetPro(Asph_TargetProportion asphTargetPro) throws BusinessException {
		try {
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
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
		return 0;
	}

	public int delAsph_TargetPropDetailed(Asph_TargetProportion asphTargetPro) throws BusinessException {
		try {
			return asph_TargetProDao.delAsph_TargetPropDetailed(asphTargetPro);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	public int delAsphTargetPro(Asph_TargetProportion asphTargetPro) throws BusinessException {
		try {
			return asph_TargetProDao.delAsphTargetPro(asphTargetPro);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	public List<Asph_TargetProportion> getAsphTargetProById(Asph_TargetProportion asphTargetPro)
			throws BusinessException {
		try {
			return asph_TargetProDao.getAsphTargetProById(asphTargetPro);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	
	/*
	 * 根据Targ_PropID 查找
	 * tongn
	 * 2018.6.27
	 */
	public List<Asphalt_Prod_Proportion> getAsphalt_Prod_ProportionPropId(Asphalt_Prod_Proportion asphalt_Prod_Proportion) throws Exception {
		
		return asphProportionDao.getAsphalt_Prod_ProportionPropId(asphalt_Prod_Proportion);
	}



	public List<Asph_TargetProportion> select_Asph_TargetPropDetailed(Asph_TargetProportion asph_TargetPro) throws BusinessException {
		// TODO Auto-generated method stub
		try {
			return  asph_TargetProDao.select_Asph_TargetPropDetailed(asph_TargetPro);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
