package com.jingpeng.model;

import com.kdt.base.support.model.ModelSupport;

public class Tab extends ModelSupport {
	private static final long serialVersionUID = -915377189761073052L;

	private int Id;
	private int Org_ID;
	private String Org_Name;
	private double TotalWeight;
	private int Total;
	private int Eligibility;
	private String Collect_Time;
	private String type;
	
	public String getCollect_Time() {
		return Collect_Time;
	}

	public void setCollect_Time(String collect_Time) {
		Collect_Time = collect_Time;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getOrg_ID() {
		return Org_ID;
	}

	public void setOrg_ID(int org_ID) {
		Org_ID = org_ID;
	}

	public String getOrg_Name() {
		return Org_Name;
	}

	public void setOrg_Name(String org_Name) {
		Org_Name = org_Name;
	}

	public double getTotalWeight() {
		return TotalWeight;
	}

	public void setTotalWeight(double totalWeight) {
		TotalWeight = totalWeight;
	}

	public int getTotal() {
		return Total;
	}

	public void setTotal(int total) {
		Total = total;
	}

	public int getEligibility() {
		return Eligibility;
	}

	public void setEligibility(int eligibility) {
		Eligibility = eligibility;
	}

}