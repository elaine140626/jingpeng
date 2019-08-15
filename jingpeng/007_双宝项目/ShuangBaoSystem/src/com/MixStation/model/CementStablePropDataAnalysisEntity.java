package com.MixStation.model;

public class CementStablePropDataAnalysisEntity {
	private int serialNumber;
	private String id;
	private String material_Name;//原材料名称
	private String cons_Prop_Weight;//施工配比值
	private String collection_Weight;//采集重量
	private String actual_Proportion;//实际占比
	public String getCons_Prop_Weight() {
		return cons_Prop_Weight;
	}
	public void setCons_Prop_Weight(String cons_Prop_Weight) {
		this.cons_Prop_Weight = cons_Prop_Weight;
	}
	public String getCollection_Weight() {
		return collection_Weight;
	}
	public void setCollection_Weight(String collection_Weight) {
		this.collection_Weight = collection_Weight;
	}
	public String getActual_Proportion() {
		return actual_Proportion;
	}
	public void setActual_Proportion(String actual_Proportion) {
		this.actual_Proportion = actual_Proportion;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getMaterial_Name() {
		return material_Name;
	}
	public void setMaterial_Name(String material_Name) {
		this.material_Name = material_Name;
	}

}
