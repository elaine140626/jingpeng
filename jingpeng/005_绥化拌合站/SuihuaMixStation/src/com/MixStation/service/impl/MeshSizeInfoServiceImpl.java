package com.MixStation.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MixStation.dao.MeshSizeInfoDao;
import com.MixStation.model.MeshSizeInfoEntity;
import com.MixStation.service.MeshSizeInfoService;
@Service
public class MeshSizeInfoServiceImpl implements MeshSizeInfoService {

	@Autowired
	private MeshSizeInfoDao meshSizeInfoDao;

	@Override
	public List<MeshSizeInfoEntity> getAllMeshSizeInfo(Map<String, Object> map) {
		return meshSizeInfoDao.getAllMeshSizeInfo(map);
	}

	@Override
	public int addMeshSizeInfo(MeshSizeInfoEntity meshSizeInfoEntity) {
		return meshSizeInfoDao.addMeshSizeInfo(meshSizeInfoEntity);
	}

	@Override
	public int updateMeshSizeInfo(MeshSizeInfoEntity meshSizeInfoEntity) {
		return meshSizeInfoDao.updateMeshSizeInfo(meshSizeInfoEntity);
	}

	@Override
	public int deleteMeshSizeInfo(Map<String, Object> map) {
		return meshSizeInfoDao.deleteMeshSizeInfo(map);
	}

	@Override
	public List<Map<String, Object>> getGradingInfo(Map<String, Object> map) {
		return meshSizeInfoDao.getGradingInfo(map);
	}
}
