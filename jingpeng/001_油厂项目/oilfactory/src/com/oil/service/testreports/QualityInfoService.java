package com.oil.service.testreports;

import java.util.List;
import java.util.Map;

public interface QualityInfoService {

	
	/**
	 * @since 查询质检管理列表 
	 * @return List<Map<String, Object>> 入库表和出库表中符合查询条件的数据
	 */
	List<Map<String, Object>> queryQualityInfo();

}
