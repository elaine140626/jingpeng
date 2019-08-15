package com.truckscale.common.service;

import java.util.List;
import java.util.Map;

public interface InitialInfoService {
	// 数据字典取得
	List<Map<String,Object>> getDataDictionary(Map<String, Object> map);

}
