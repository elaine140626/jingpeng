package com.truckscale.basicSetting.service;

import java.util.HashMap;
import java.util.List;

import com.truckscale.basicSetting.model.ReceiveUnitEntity;

public interface ReceiveUnitService {

	List<ReceiveUnitEntity> getReceiveUnitList(HashMap<String, Object> map);

	int insertReceiveUnit(ReceiveUnitEntity receiveUnitEntity);

	int updateReceiveUnit(ReceiveUnitEntity receiveUnitEntity);

	int deleteReceiveUnit(ReceiveUnitEntity receiveUnitEntity);

	String getGenerateNumber(String type);

}
