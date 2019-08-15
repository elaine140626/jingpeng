package com.oil.service.system.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oil.dao.system.SerialNumberDao;
import com.oil.model.system.Prefix;
import com.oil.service.system.SerialNumberService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
@Transactional
public class SerialNumberServiceImpl implements SerialNumberService {

	@Autowired
	private SerialNumberDao serialNumberDao;

	@Override
	public int adSerialNumber(Map<String, Object> param) {
		int res = 0;
		JSONArray jsonArray=JSONArray.fromObject(param.get("prefixList"));	
		for (int i = 0; i < jsonArray.size(); i++) {
			Prefix prefix = new Prefix();
			JSONObject job = jsonArray.getJSONObject(i);
			prefix.setDistinguish(job.get("distinguish").toString());
			prefix.setAllPrefix(job.get("allPrefix").toString());
			prefix.setYear(job.get("year").toString());
			prefix.setMonth(job.get("month").toString());
			prefix.setDay(job.get("day").toString());
			prefix.setHour(job.get("hour").toString());
			prefix.setBranch(job.get("branch").toString());
			prefix.setSecond(job.get("second").toString());
			prefix.setCreater(param.get("creater").toString());
			if (job.get("flowNumber")!= null) {
				prefix.setFlowNumber(job.get("flowNumber").toString());	
			}
//			prefix.setCreater(job.get("creater").toString());
			res += serialNumberDao.adSerialNumber(prefix);
		}
		return res;
	}

	@Override
	public int updateSerialNumber(Map<String, Object> param) {
		int res = 0;
		JSONArray jsonArray=JSONArray.fromObject(param.get("prefixList"));	
		for (int i = 0; i < jsonArray.size(); i++) {
			Prefix prefix = new Prefix();
			JSONObject job = jsonArray.getJSONObject(i);
			prefix.setId(Integer.parseInt(job.get("id").toString()));
			prefix.setDistinguish(job.get("distinguish").toString());
			prefix.setAllPrefix(job.get("allPrefix").toString());
			prefix.setYear(job.get("year").toString());
			prefix.setMonth(job.get("month").toString());
			prefix.setDay(job.get("day").toString());
			prefix.setHour(job.get("hour").toString());
			prefix.setBranch(job.get("branch").toString());
			prefix.setSecond(job.get("second").toString());
			prefix.setReviser(param.get("creater").toString());
			if (job.get("flowNumber")!= null) {
				prefix.setFlowNumber(job.get("flowNumber").toString());	
			}
			res += serialNumberDao.updateSerialNumber(prefix);
		}
		return res; 
	}


	public List<Prefix> getAllPrefix() {
		return serialNumberDao.getAllPrefix();
	}

	public Prefix getContractInfoPrefix(String types) {
		Prefix prefix = serialNumberDao.getContractInfoPrefix(types);
		String prefixs = "";
		Date now = new Date(); 
		if (prefix.getAllPrefix() != null && !"".equals(prefix.getAllPrefix())) {
			prefixs += prefix.getAllPrefix();
		}
		if(!"1".equals(prefix.getYear())) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
			String dateString = formatter.format(now);
			prefixs += dateString;
		}
		if(!"1".equals(prefix.getMonth())) {
			SimpleDateFormat formatter = new SimpleDateFormat("MM");
			prefixs += formatter.format(now);
		}
		if(!"1".equals(prefix.getDay())) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd");
			prefixs += formatter.format(now);
		}
		if(!"1".equals(prefix.getHour())) {
			SimpleDateFormat formatter = new SimpleDateFormat("HH");
			prefixs += formatter.format(now);
		}
		if(!"1".equals(prefix.getBranch())) {
			SimpleDateFormat formatter = new SimpleDateFormat("mm");
			prefixs += formatter.format(now);
		}
		if(!"1".equals(prefix.getSecond())) {
			SimpleDateFormat formatter = new SimpleDateFormat("ss");
			prefixs += formatter.format(now);
		}
		if(prefix.getFlowNumber() != null && !"".equals(prefix.getFlowNumber())) {
			prefixs += prefix.getFlowNumber();
		}
		prefix.setPrefixs(prefixs);
		return prefix;
	}

	@Override
	public int updateContractInfoPrefix(String types) {
		int res = 0;
		Prefix prefix = serialNumberDao.getContractInfoPrefix(types);
		if (prefix.getFlowNumber()!= null & !"".equals(prefix.getFlowNumber())) {
			int newEquipment = Integer.parseInt(prefix.getFlowNumber()) + 1;
			prefix.setFlowNumber(String.format("%0"+prefix.getFlowNumber().length()+"d",newEquipment));
		}
		res += serialNumberDao.updateSerialNumber(prefix);
		return res;
	}

}
