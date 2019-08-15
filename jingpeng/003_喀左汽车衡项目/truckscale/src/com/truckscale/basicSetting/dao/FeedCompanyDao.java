package com.truckscale.basicSetting.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.truckscale.basicSetting.model.FeedCompanyEntity;
import com.truckscale.basicSetting.model.FeeddetailedcompanyEntity;

@Repository
public interface FeedCompanyDao {

	List<FeedCompanyEntity> getFeedCompanyList(HashMap<String, Object> map);

	int insertFeedCompany(Map<String, Object> map);

	int updateFeedCompany(FeedCompanyEntity feedCompanyEntity);

	int deleteFeedCompany(FeedCompanyEntity feedCompanyEntity);

	int insertFeeddetailedcompany(FeeddetailedcompanyEntity feeddetailedcompanyEntity);

	int updateFeeddetailedcompany(FeeddetailedcompanyEntity feeddetailedcompanyEntity);

	int updateFeedCompanyMap(Map<String, Object> map);

	List<FeeddetailedcompanyEntity> getFeeddetailedcompanyList(FeeddetailedcompanyEntity feeddetailedcompanyEntity);

	int deleteFeeddetailedcompany(Map<String, Object> param);

	FeedCompanyEntity getFeedCompanyNumber();

	int deleteFeeddetailedcompanyById(@Param("feedCompanyId") int feedCompanyId);

	Map<String,Object> getExportmeasureList(FeedCompanyEntity feedCompanyEntity);

}
