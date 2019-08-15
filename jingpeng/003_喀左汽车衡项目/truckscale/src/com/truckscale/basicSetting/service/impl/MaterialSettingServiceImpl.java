package com.truckscale.basicSetting.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truckscale.basicSetting.dao.MaterialSettingDao;
import com.truckscale.basicSetting.model.FeedCompanyEntity;
import com.truckscale.basicSetting.model.MaterialSettingEntity;
import com.truckscale.basicSetting.service.MaterialSettingService;
@Service
public class MaterialSettingServiceImpl implements MaterialSettingService{

	@Autowired
	MaterialSettingDao materialSettingDao;
	
	// 获取材料名称List信息
	public List<MaterialSettingEntity> getMaterialSettingList(Map<String, Object> map) {
		return materialSettingDao.getMaterialSettingList(map);
	}
	
	// 判断是否存在相同材料名称和编号
	public List<MaterialSettingEntity> getCount(MaterialSettingEntity param) {
		return materialSettingDao.getCount(param);
	}

	// 新增材料名称表
	public int insertMaterialSetting(MaterialSettingEntity param) {
		return materialSettingDao.insertMaterialSetting(param);
	}

	// 更新材料名称表
	public int updateMaterialSetting(MaterialSettingEntity param) {
		return materialSettingDao.updateMaterialSetting(param);
	}

	// 删除材料名称表
	public int deleteMaterialSetting(MaterialSettingEntity param) {
		int result = 0;
		Map<String,Object> map = materialSettingDao.getFeeddetailedcompany(param);
		if(map != null ) {
			return 2;
		}
		result += materialSettingDao.deleteMaterialSetting(param);
		return result;
	}

	@Override
	public String getGenerateNumber(String type) {
		//查询最新的一条数据
		MaterialSettingEntity materialSettingEntity = materialSettingDao.getMaterialSettingNumber();
		String result = "";
		Date now = new Date();
		//截取年份
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		String dateString = formatter.format(now);
		result += dateString + type;
		String materielNumber = "";
		//判断数据是否存在如果不存在默认添加初始格式
		if(materialSettingEntity != null) {
			if (materialSettingEntity.getMaterielNumber()!= null && !"".equals(materialSettingEntity.getMaterielNumber())) {
				materielNumber = materialSettingEntity.getMaterielNumber();
				//必须符合前缀的格式如果不是默认添加初始格式
				if(materielNumber.length() > 11) {
					//从前缀英文简写截取编号
					int newEquipment = Integer.parseInt(materielNumber.substring(6)) + 1;
					result += String.format("%0"+materielNumber.substring(6).length()+"d",newEquipment);
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
