package com.MixStation.service;

import java.util.List;
import java.util.Map;

import com.MixStation.model.MaterialProductEntity;

public interface MaterialProductService {

	List<MaterialProductEntity> getMaterialProduct(Map<String, Object> map);


}
