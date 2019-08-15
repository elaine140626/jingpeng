package com.mixingStation.service.cement.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Printer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mchange.v1.lang.BooleanUtils;
import com.mixingStation.dao.cement.CementProductionFiguresDao;
import com.mixingStation.model.DataTablesResponseInfo;
import com.mixingStation.model.cement.CementProductionFigures;
import com.mixingStation.service.cement.CementProductionFiguresService;

@Service
@Transactional
public class CementProductionFiguresServiceImpl implements CementProductionFiguresService {

	@Autowired
	private CementProductionFiguresDao cementProductionFiguresDao;

	// 获取list
	@Override
	public DataTablesResponseInfo getCementProductionStatisticsList(Map<String, Object> map) {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<CementProductionFigures> list = cementProductionFiguresDao.getCementProductionStatisticsList(map);
		info.setData(list);
		return info;
	}
	// 获取柱状图数据
	@Override
	public Map<String, Integer> getCementProductionData(Map<String, Object> map) {
		//总盘数
		int total = 0;
		//合格盘数
		int qualified = 0;
		//不合格盘数
		int unqualified = 0;
		Map<String,Integer> result = new HashMap<String,Integer>();
		List<CementProductionFigures> list = cementProductionFiguresDao.getCementProductionStatisticsList(map);
		
		if(list != null && list.size()>0) {
			  //算出合格数
			  for(CementProductionFigures CementProductionFigures : list) {
				  total = total+CementProductionFigures.getTotalNumber();
				  qualified = qualified + CementProductionFigures.getQualifiedNumber();
				  unqualified = unqualified + CementProductionFigures.getUnqualifiedNumber();
			  }
			}
			result.put("total", total);
			result.put("qualified", qualified);
			result.put("unqualified", unqualified);
		return result;
	}

	
}
