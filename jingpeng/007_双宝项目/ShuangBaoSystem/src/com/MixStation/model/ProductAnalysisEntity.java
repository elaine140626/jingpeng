package com.MixStation.model;

public class ProductAnalysisEntity {
	private String Product_ID; // 产品Id
	private String Material_Name;// 物料名称
	private String Material_Model;// 物料型号
	private String Collect_Time; // 采集时间按日
	private String Total_Weight; // 拌和总量

	public String getProduct_ID() {
		return Product_ID;
	}

	public void setProduct_ID(String product_ID) {
		Product_ID = product_ID;
	}

	public String getMaterial_Name() {
		return Material_Name;
	}

	public void setMaterial_Name(String material_Name) {
		Material_Name = material_Name;
	}

	public String getMaterial_Model() {
		return Material_Model;
	}

	public void setMaterial_Model(String material_Model) {
		Material_Model = material_Model;
	}

	public String getCollect_Time() {
		return Collect_Time;
	}

	public void setCollect_Time(String collect_Time) {
		Collect_Time = collect_Time;
	}

	public String getTotal_Weight() {
		return Total_Weight;
	}

	public void setTotal_Weight(String total_Weight) {
		Total_Weight = total_Weight;
	}

}
