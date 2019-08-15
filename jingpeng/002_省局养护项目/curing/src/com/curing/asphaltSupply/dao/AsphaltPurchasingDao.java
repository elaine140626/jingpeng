package com.curing.asphaltSupply.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.curing.asphaltSupply.model.AsphaltPurchasingEntity;
@Repository
public interface AsphaltPurchasingDao {

	// 获取沥青采购的数据
	List<AsphaltPurchasingEntity> getAsphaltPurchasing(Map<String, Object> map);
	
	// 新增沥青采购
	int insertAsphaltPurchasing(AsphaltPurchasingEntity asphaltPurchasingEntity);
	
	// 更新沥青采购
	int updateAsphaltPurchasing(AsphaltPurchasingEntity asphaltPurchasingEntity);
}
