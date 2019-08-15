package com.truckscale.basicSetting.model;

import java.util.List;
import java.util.Map;

public class FeedCompanyEntity {
	
	//主键id
	private int id;
	
	//供料单位编号
	private String feedCompanyNumber;
	
	//供料单位名称
	private String feedCompanyName;
	
	//联系人
	private String contacts;
	
	//联系人电话
	private String telephone;
	
	//备注
	private String remarks;
	
	//删除标记
	private int isDel;
	
	//创建人
	private String creater;
	
	//创建时间
	private String createrDate;
	
	//修改人
	private String reviser;
	
	//修改时间
	private String reviserDate;
	
	private List<FeeddetailedcompanyEntity> feeddetailedcompanyEntityList;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFeedCompanyNumber() {
		return feedCompanyNumber;
	}

	public void setFeedCompanyNumber(String feedCompanyNumber) {
		this.feedCompanyNumber = feedCompanyNumber;
	}
	

	public String getFeedCompanyName() {
		return feedCompanyName;
	}

	public void setFeedCompanyName(String feedCompanyName) {
		this.feedCompanyName = feedCompanyName;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getIsDel() {
		return isDel;
	}

	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getCreaterDate() {
		return createrDate;
	}

	public void setCreaterDate(String createrDate) {
		this.createrDate = createrDate;
	}

	public String getReviser() {
		return reviser;
	}

	public void setReviser(String reviser) {
		this.reviser = reviser;
	}

	public String getReviserDate() {
		return reviserDate;
	}

	public void setReviserDate(String reviserDate) {
		this.reviserDate = reviserDate;
	}

	public List<FeeddetailedcompanyEntity> getFeeddetailedcompanyEntityList() {
		return feeddetailedcompanyEntityList;
	}

	public void setFeeddetailedcompanyEntityList(List<FeeddetailedcompanyEntity> feeddetailedcompanyEntityList) {
		this.feeddetailedcompanyEntityList = feeddetailedcompanyEntityList;
	}	
	
}
