package com.MixStation.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.MixStation.model.MaterialProductEntity;

@Repository
public interface MaterialProductDao{

	List<MaterialProductEntity> getMaterialProduct(Map<String, Object> map);
	
}
