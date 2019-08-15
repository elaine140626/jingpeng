package com.mixingStation.service.meshInfo;

import java.util.List;
import java.util.Map;

import com.mixingStation.model.meshInfo.MeshSizeDataAnalysis;
import com.mixingStation.model.meshInfo.MeshSizeInfo;

public interface MeshSizeService {

	List<MeshSizeInfo> queryMeshSizeList(MeshSizeInfo meshSize);

	int insertMeshSizeList(MeshSizeInfo meshSize) throws Exception;

	int updateMeshSizeList(MeshSizeInfo meshSize) throws Exception;

	int deleteMeshSizeList(MeshSizeInfo meshSize) throws Exception;

	List<Map<String, Object>> queryMeshSizeDataList(MeshSizeDataAnalysis meshSizeDataAnalysis);

}
