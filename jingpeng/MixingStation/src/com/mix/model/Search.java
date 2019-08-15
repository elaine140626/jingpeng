package com.mix.model;

import java.util.List;
import java.util.Map;

public class Search{
	private static final long serialVersionUID = -1L;
	private String org_Ids;
	private String start_Time;
	private String end_Time;
	private String UserName;
	private List<Map<String,String>> list;
	public String getOrg_Ids() {
		return org_Ids;
	}
	public void setOrg_Ids(String org_Ids) {
		this.org_Ids = org_Ids;
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
