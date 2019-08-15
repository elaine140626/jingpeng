package com.truckscale.basicSetting.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truckscale.basicSetting.dao.ReceiveUnitDao;
import com.truckscale.basicSetting.model.ReceiveUnitEntity;
import com.truckscale.basicSetting.service.ReceiveUnitService;
@Service
public class ReceiveUnitServiceImpl implements ReceiveUnitService {

	@Autowired
	ReceiveUnitDao receiveUnitDao;
	
	@Override
	public List<ReceiveUnitEntity> getReceiveUnitList(HashMap<String, Object> map) {
		return receiveUnitDao.getReceiveUnitList(map);
	}

	@Override
	public int insertReceiveUnit(ReceiveUnitEntity receiveUnitEntity) {
		HashMap<String, Object> parma = new HashMap<String, Object>();
		parma.put("Purpose", receiveUnitEntity.getPurpose());
		for (int i = 0; i < receiveUnitDao.getReceiveUnitList1(parma).size(); i++) {
			if(receiveUnitDao.getReceiveUnitList1(parma).get(i).getReceiveUnitName().equals(receiveUnitEntity.getReceiveUnitName())) {
				return 2;
			}
		}
		return receiveUnitDao.insertReceiveUnit(receiveUnitEntity);
	}

	@Override
	public int updateReceiveUnit(ReceiveUnitEntity receiveUnitEntity) {
		HashMap<String, Object> parma = new HashMap<String, Object>();
		parma.put("ids", receiveUnitEntity.getId());
		for (int i = 0; i < receiveUnitDao.getReceiveUnitList(parma).size(); i++) {
			if(receiveUnitDao.getReceiveUnitList(parma).get(i).getReceiveUnitName().equals(receiveUnitEntity.getReceiveUnitName())) {
				return 2;
			}
		}
		return receiveUnitDao.updateReceiveUnit(receiveUnitEntity);
	}

	@Override
	public int deleteReceiveUnit(ReceiveUnitEntity receiveUnitEntity) {
		int result = 0;
		Map<String,Object> map = receiveUnitDao.getExportmeasureList(receiveUnitEntity);
		if(map != null ) {
			return 2;
		}
		result += receiveUnitDao.deleteReceiveUnit(receiveUnitEntity);
		return result;
	}

	@Override
	public String getGenerateNumber(String type) {
		//查询最新的一条数据
		ReceiveUnitEntity receiveUnitEntity = receiveUnitDao.getReceiveUnitListNumber();
		String result = "";
		Date now = new Date();
		//截取年份
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		String dateString = formatter.format(now);
		result += dateString + type;
		String receiveUnitNumber="";
		//判断数据是否存在如果不存在默认添加初始格式
		if(receiveUnitEntity != null) {
			if (receiveUnitEntity.getReceiveUnitNumber()!= null && !"".equals(receiveUnitEntity.getReceiveUnitNumber())) {
				receiveUnitNumber = receiveUnitEntity.getReceiveUnitNumber();
				//必须符合前缀的格式如果不是默认添加初始格式
				if(receiveUnitNumber.length() > 11) {
					//从前缀英文简写截取编号
					int newEquipment = Integer.parseInt(receiveUnitNumber.substring(6)) + 1;
					result += String.format("%0"+receiveUnitNumber.substring(6).length()+"d",newEquipment);
				}else {
					result += "000001";
				}
			}
		}else {
			result += "000001";
		}
		return result;
	}

}
