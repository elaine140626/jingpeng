package com.mixingStation.service.asphalt.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mixingStation.dao.asphalt.Asph_TargetProDao;
import com.mixingStation.model.asphalt.Asph_TargetPropDetailed;
import com.mixingStation.model.asphalt.Asph_TargetProportion;
import com.mixingStation.service.asphalt.Asph_TargetProService;

@Service
@Transactional
public class Asph_TargetProServiceImpl implements Asph_TargetProService{
	@Autowired
	private Asph_TargetProDao asph_TargetProDao;
	/**
	 * 查询目标配合比列表
	 */
	@Override
	public List<Asph_TargetProportion> getAsphTargetPro(Map<String, Object> map)  {
			return asph_TargetProDao.getAsphTargetPro(map);
	}
	/**
	 * 添加目标配合比信息
	 */
	public int addAsphTargetPro(Asph_TargetProportion asphTargetPro)  {
			asph_TargetProDao.addAsphTargetPro(asphTargetPro);
			Map<String,Object> map  = new HashMap<String,Object>();
			map.put("proportion_Code", asphTargetPro.getProportion_Code());
			List<Asph_TargetProportion> Pro= asph_TargetProDao.getAsphTargetPro(map);
			List<Asph_TargetPropDetailed> list = asphTargetPro.getAsph_TargetPropList();
			if(list != null) {
				for(int i = 0; i < list.size(); i++) {
					list.get(i).setI_targ_Prop_Id(Pro.get(0).getId());
					list.get(i).setI_valid_Flag(1);
				}
				asph_TargetProDao.addAsphTargetProD(list);
			}
			return 0;
	}
	/**
	 * 添加目标配合比详细信息
	 */
	public int addAsphTargetProD(List<Asph_TargetPropDetailed> list)  {
		return asph_TargetProDao.addAsphTargetProD(list);
	}
	/**
	 * 修改目标配合比信息
	 */
	public int updateAsphTargetPro(Asph_TargetProportion asphTargetPro)  {
		asph_TargetProDao.updateAsphTargetPro(asphTargetPro);
		List<Asph_TargetPropDetailed> list = asphTargetPro.getAsph_TargetPropList();
		Map<String,Object> map  = new HashMap<String,Object>();
		map.put("proportion_Code", asphTargetPro.getProportion_Code());
		List<Asph_TargetProportion> Pro= asph_TargetProDao.getAsphTargetPro(map);
		if(list != null) {
			for(int i = 0; i < list.size(); i++) {
				list.get(i).setI_targ_Prop_Id(Pro.get(0).getId());
				list.get(i).setI_valid_Flag(1);
			}
			asph_TargetProDao.delAsph_TargetPropDetailed(asphTargetPro);
			asph_TargetProDao.addAsphTargetProD(list);
		}
		return 0;
	}
	/**
	 * 删除目标配合比信息
	 */
	public int delAsph_TargetPropDetailed(Asph_TargetProportion asphTargetPro)  {
			return asph_TargetProDao.delAsph_TargetPropDetailed(asphTargetPro);
	}
	/**
	 * 删除目标配合比详细信息
	 */
	public int delAsphTargetPro(Asph_TargetProportion asphTargetPro)  {
			return asph_TargetProDao.delAsphTargetPro(asphTargetPro);
	}
	public int getAsphTargetProRepeat(Asph_TargetProportion asph_TargetPro)  {
		return  asph_TargetProDao.getAsphTargetProRepeat(asph_TargetPro);
}
	public List<Asph_TargetProportion> getAsphTargetProById(Asph_TargetProportion asphTargetPro){
		return asph_TargetProDao.getAsphTargetProById(asphTargetPro);
	}
	public List<Map<String, Object>> getAsphTargetProD(Asph_TargetProportion asphTargetPro)  {
		return asph_TargetProDao.getAsphTargetProD(asphTargetPro);
}
}
