/**
 * 
 */
package com.truckscale.basicSetting.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.truckscale.basicSetting.model.FeedCompanyEntity;

/**
 * @author Administrator
 *
 */
public interface FeedCompanyService {

	List<FeedCompanyEntity> getFeedCompanyList(HashMap<String, Object> map);

	int insertFeedCompany(Map<String, Object> map);

	int updateFeedCompany(FeedCompanyEntity feedCompanyEntity);

	int deleteFeedCompany(FeedCompanyEntity feedCompanyEntity);

	FeedCompanyEntity getFeedCompanyById(HashMap<String, Object> map);

	int deleteFeeddetailedcompany(Map<String, Object> param);

	String getGenerateNumber(String type);

}
