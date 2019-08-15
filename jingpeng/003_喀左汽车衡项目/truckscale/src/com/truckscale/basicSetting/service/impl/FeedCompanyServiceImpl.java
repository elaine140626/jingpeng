package com.truckscale.basicSetting.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truckscale.basicSetting.dao.FeedCompanyDao;
import com.truckscale.basicSetting.model.FeedCompanyEntity;
import com.truckscale.basicSetting.model.FeeddetailedcompanyEntity;
import com.truckscale.basicSetting.service.FeedCompanyService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Service
public class FeedCompanyServiceImpl implements FeedCompanyService {
	@Autowired
	FeedCompanyDao feedCompanyDao;

	@Override
	public List<FeedCompanyEntity> getFeedCompanyList(HashMap<String, Object> map) {
		return feedCompanyDao.getFeedCompanyList(map);
	}

	@Override
	public FeedCompanyEntity getFeedCompanyById(HashMap<String, Object> map) {
		List<FeedCompanyEntity> feedCompanyList = feedCompanyDao.getFeedCompanyList(map);
		FeeddetailedcompanyEntity feeddetailedcompanyEntity = new FeeddetailedcompanyEntity();
		feeddetailedcompanyEntity.setFeedCompanyId(feedCompanyList.get(0).getId());
		feedCompanyList.get(0).setFeeddetailedcompanyEntityList(feedCompanyDao.getFeeddetailedcompanyList(feeddetailedcompanyEntity));
		return feedCompanyList.get(0);
	}
	
	@Override
	public int insertFeedCompany(Map<String, Object> map) {
		int result = 0;
		HashMap<String, Object> parma = new HashMap<String, Object>();
		parma.put("ids", map.get("id").toString());
		List<FeedCompanyEntity> feedCompanyEntityList = feedCompanyDao.getFeedCompanyList(parma);
		for (int i = 0; i < feedCompanyEntityList.size(); i++) {
			if(feedCompanyEntityList.get(i).getFeedCompanyName().equals(map.get("feedCompanyName").toString())) {
				return 2;
			}	
		}
		if(Integer.parseInt(map.get("id").toString()) == 0) {
			result += feedCompanyDao.insertFeedCompany(map);
		}else {
			result += feedCompanyDao.updateFeedCompanyMap(map);
		}
		JSONArray jsonArray=JSONArray.fromObject(map.get("feeddetailedcompanyList"));
		if(jsonArray.size()>0){
			for(int i=1;i<jsonArray.size();i++){
				FeeddetailedcompanyEntity feeddetailedcompanyEntity = new FeeddetailedcompanyEntity();
				JSONObject job = jsonArray.getJSONObject(i);
					feeddetailedcompanyEntity.setFeedCompanyId(Integer.parseInt(map.get("id").toString()));
				if (!"".equals(job.get("materielNameId").toString())) {
					feeddetailedcompanyEntity.setMaterielNameId(Integer.parseInt(job.get("materielNameId").toString()));
				}
				if (!"".equals(job.get("unitPrice").toString())) {
					feeddetailedcompanyEntity.setUnitPrice(Double.valueOf(job.get("unitPrice").toString()));
				}
				if (!"".equals(job.get("materielPlace").toString())) {
					feeddetailedcompanyEntity.setMaterielPlace(job.get("materielPlace").toString());
				}
				if (!"".equals(job.get("manufactor").toString())) {
					feeddetailedcompanyEntity.setManufactor(job.get("manufactor").toString());
				}
				if (!"".equals(job.get("remark").toString())) {
					feeddetailedcompanyEntity.setRemarks(job.get("remark").toString());
				}
				if (!"".equals(job.get("feeddetailedcompanyId").toString())) {
					feeddetailedcompanyEntity.setFeeddetailedcompanyId(Integer.parseInt(job.get("feeddetailedcompanyId").toString()));
					if (Integer.parseInt(job.get("feeddetailedcompanyId").toString()) == 0) {
						 feedCompanyDao.insertFeeddetailedcompany(feeddetailedcompanyEntity);
					}else {
						 feedCompanyDao.updateFeeddetailedcompany(feeddetailedcompanyEntity);
					}
				}
			}
		}
		
		return result;
	}

	@Override
	public int updateFeedCompany(FeedCompanyEntity feedCompanyEntity) {
		return feedCompanyDao.updateFeedCompany(feedCompanyEntity);
	}

	@Override
	public int deleteFeedCompany(FeedCompanyEntity feedCompanyEntity) {
		int result = 0;
		Map<String,Object> map = feedCompanyDao.getExportmeasureList(feedCompanyEntity);
		if(map != null ) {
			return 2;
		}
		result += feedCompanyDao.deleteFeedCompany(feedCompanyEntity);
		return result;
	}

	@Override
	public int deleteFeeddetailedcompany(Map<String, Object> param) {
		return feedCompanyDao.deleteFeeddetailedcompany(param);
	}

	@Override
	public String getGenerateNumber(String type) {
		//查询最新的一条数据
		FeedCompanyEntity feedCompanyEntity = feedCompanyDao.getFeedCompanyNumber();
		String result = "";
		Date now = new Date();
		//截取年份
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		String dateString = formatter.format(now);
		result += dateString + type;
		String feedCompanyNumber = "";
		//判断数据是否存在如果不存在默认添加初始格式
		if(feedCompanyEntity != null) {
			if (feedCompanyEntity.getFeedCompanyNumber()!= null && !"".equals(feedCompanyEntity.getFeedCompanyNumber())) {
				feedCompanyNumber = feedCompanyEntity.getFeedCompanyNumber();
				//必须符合前缀的格式如果不是默认添加初始格式
				if(feedCompanyNumber.length() > 11) {
					//从前缀英文简写截取编号
					int newEquipment = Integer.parseInt(feedCompanyNumber.substring(6)) + 1;
					result += String.format("%0"+feedCompanyNumber.substring(6).length()+"d",newEquipment);
				}else {
					result += "000001";	
				}
			}
		}else {
			result += "000001";	
		}
		return result;
	}

}
