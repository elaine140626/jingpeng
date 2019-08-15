package com.MixStation.service;

import java.util.List;
import java.util.Map;

import com.MixStation.model.MeshSizeInfoEntity;

public interface MeshSizeInfoService {
	
	//查询所有筛孔信息
	List<MeshSizeInfoEntity> getAllMeshSizeInfo(Map<String,Object> map);
	
	//添加筛孔信息
	int addMeshSizeInfo(MeshSizeInfoEntity meshSizeInfoEntity);
	
	//修改筛孔信息
	int updateMeshSizeInfo(MeshSizeInfoEntity meshSizeInfoEntity);
	
	//删除筛孔信息
	int deleteMeshSizeInfo(Map<String,Object> map);
	
	//删除校验
	List<Map<String,Object>> getGradingInfo(Map<String,Object> map);
}
