package com.truckscale.basicSetting.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.truckscale.basicSetting.model.ReceiveUnitEntity;
@Repository
public interface ReceiveUnitDao {

	List<ReceiveUnitEntity> getReceiveUnitList(HashMap<String, Object> map);

	int insertReceiveUnit(ReceiveUnitEntity receiveUnitEntity);

	int updateReceiveUnit(ReceiveUnitEntity receiveUnitEntity);

	int deleteReceiveUnit(ReceiveUnitEntity receiveUnitEntity);

	ReceiveUnitEntity getReceiveUnitListNumber();

	Map<String, Object> getExportmeasureList(ReceiveUnitEntity receiveUnitEntity);
	List<ReceiveUnitEntity> getReceiveUnitList1(HashMap<String, Object> map);
	
}
