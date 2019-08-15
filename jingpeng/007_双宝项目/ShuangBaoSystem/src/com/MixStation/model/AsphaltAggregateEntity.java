package com.MixStation.model;

public class AsphaltAggregateEntity {
	private String Id;
	private String Material_Model;
	private String Material_Name;
	private String Collect_Time;// 采集时间
	private String avgAsphalt;// 平均值

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getMaterial_Model() {
		return Material_Model;
	}

	public void setMaterial_Model(String material_Model) {
		Material_Model = material_Model;
	}

	public String getMaterial_Name() {
		return Material_Name;
	}

	public void setMaterial_Name(String material_Name) {
		Material_Name = material_Name;
	}

	public String getCollect_Time() {
		return Collect_Time;
	}

	public void setCollect_Time(String collect_Time) {
		Collect_Time = collect_Time;
	}

	public String getAvgAsphalt() {
		return avgAsphalt;
	}

	public void setAvgAsphalt(String avgAsphalt) {
		this.avgAsphalt = avgAsphalt;
	}

}
