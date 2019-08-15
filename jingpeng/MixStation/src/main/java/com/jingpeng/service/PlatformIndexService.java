package com.jingpeng.service;

import java.util.List;
import java.util.Map;

import com.jingpeng.model.Core_User_Info;
import com.kdt.base.exception.BusinessException;

public interface PlatformIndexService {

	List<Map<String, String>> getOrgName(List<Integer> orgids) throws BusinessException;

}
