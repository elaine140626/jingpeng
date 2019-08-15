package com.jingpeng.model;

import java.util.List;
import java.util.Map;

import com.kdt.base.support.model.ModelSupport;

public class Search extends ModelSupport{
	private static final long serialVersionUID = -1L;
	private String orgId;
	private String start_Time;
	private String end_Time;
	private String UserName;
	private List<Map<String,String>> list;
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getStart_Time() {
		return start_Time;
	}
	public void setStart_Time(String start_Time) {
		this.start_Time = start_Time;
	}
	public String getEnd_Time() {
		return end_Time;
	}
	public void setEnd_Time(String end_Time) {
		this.end_Time = end_Time;
	}
	public List<Map<String,String>> getList() {
		return list;
	}
	public void setList(List<Map<String,String>> list) {
		this.list = list;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	
}
