package com.mixingStation.dao.meshInfo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mixingStation.model.asphalt.AsphaltGrading;
import com.mixingStation.model.meshInfo.MeshSizeDataAnalysis;
import com.mixingStation.model.meshInfo.MeshSizeInfo;

@Repository
public interface MeshSizeDao {

	List<MeshSizeInfo> queryMeshSizeList(MeshSizeInfo meshSize);

	int insertMeshSizeList(MeshSizeInfo meshSize);

	int updateMeshSizeList(MeshSizeInfo meshSize);

	int deleteMeshSizeList(MeshSizeInfo meshSize);

	int insertMeshModel();

	int insertMeshSizeDataAnalysis(MeshSizeDataAnalysis meshSizeDataAnalysis);

	List<MeshSizeDataAnalysis> queryMeshSizeDataList(int analysisId);

	int deleteGradationInfo(AsphaltGrading asphaltGrading);

}
