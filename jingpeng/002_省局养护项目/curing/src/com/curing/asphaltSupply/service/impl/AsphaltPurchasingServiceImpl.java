package com.curing.asphaltSupply.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curing.asphaltSupply.dao.AsphaltPurchasingDao;
import com.curing.asphaltSupply.model.AsphaltPurchasingEntity;
import com.curing.asphaltSupply.service.AsphaltPurchasingService;

@Service
public class AsphaltPurchasingServiceImpl implements AsphaltPurchasingService{

	@Autowired
	AsphaltPurchasingDao asphaltPurchasingDao;
	
	// 获取沥青采购数据
	public List<AsphaltPurchasingEntity> getAsphaltPurchasing(Map<String, Object> map) {
		return asphaltPurchasingDao.getAsphaltPurchasing(map);
	}

	// 新增沥青采购
	public int insertAsphaltPurchasing(AsphaltPurchasingEntity asphaltPurchasingEntity) {
		return asphaltPurchasingDao.insertAsphaltPurchasing(asphaltPurchasingEntity);
	}

	// 更新沥青采购
	public int updateAsphaltPurchasing(AsphaltPurchasingEntity asphaltPurchasingEntity) {
		return asphaltPurchasingDao.updateAsphaltPurchasing(asphaltPurchasingEntity);
	}
}
