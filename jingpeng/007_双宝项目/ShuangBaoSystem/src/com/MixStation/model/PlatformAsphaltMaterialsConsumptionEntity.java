package com.MixStation.model;

public class PlatformAsphaltMaterialsConsumptionEntity {
	private int serialNumber;// 序号
	private String Id;
	private String Org_ID;
	private String Org_Name;// 拌合站名称
	private String Material;// 原材料名称
	private String MaterialConsumption;// 消耗量
	private String collect_Date;// 日期
	
	private int i_id;
	private int i_targ_Prop_Id;// 目标配比ID
	private String str_materials_Name;
	private int i_materials_Id;// 材料Id
	private double d_weight;// 材料用量
	private String str_material_Origin;// 材料产地
	private String str_manufacturer;// 生产厂家
	private int i_valid_Flag;// 用户是否有效：1有效，0无效
	private int i_upload;// 上传标识
	//private String Material;//产品名称-产品型号

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getOrg_ID() {
		return Org_ID;
	}

	public void setOrg_ID(String org_ID) {
		Org_ID = org_ID;
	}

	public String getOrg_Name() {
		return Org_Name;
	}

	public void setOrg_Name(String org_Name) {
		Org_Name = org_Name;
	}

	public String getMaterial() {
		return Material;
	}

	public void setMaterial(String material) {
		Material = material;
	}

	public String getMaterialConsumption() {
		return MaterialConsumption;
	}

	public void setMaterialConsumption(String materialConsumption) {
		MaterialConsumption = materialConsumption;
	}

	public String getCollect_Date() {
		return collect_Date;
	}

	public void setCollect_Date(String collect_Date) {
		this.collect_Date = collect_Date;
	}

	public int getI_id() {
		return i_id;
	}

	public void setI_id(int i_id) {
		this.i_id = i_id;
	}

	public int getI_targ_Prop_Id() {
		return i_targ_Prop_Id;
	}

	public void setI_targ_Prop_Id(int i_targ_Prop_Id) {
		this.i_targ_Prop_Id = i_targ_Prop_Id;
	}

	public String getStr_materials_Name() {
		return str_materials_Name;
	}

	public void setStr_materials_Name(String str_materials_Name) {
		this.str_materials_Name = str_materials_Name;
	}

	public int getI_materials_Id() {
		return i_materials_Id;
	}

	public void setI_materials_Id(int i_materials_Id) {
		this.i_materials_Id = i_materials_Id;
	}

	public double getD_weight() {
		return d_weight;
	}

	public void setD_weight(double d_weight) {
		this.d_weight = d_weight;
	}

	public String getStr_material_Origin() {
		return str_material_Origin;
	}

	public void setStr_material_Origin(String str_material_Origin) {
		this.str_material_Origin = str_material_Origin;
	}

	public String getStr_manufacturer() {
		return str_manufacturer;
	}

	public void setStr_manufacturer(String str_manufacturer) {
		this.str_manufacturer = str_manufacturer;
	}

	public int getI_valid_Flag() {
		return i_valid_Flag;
	}

	public void setI_valid_Flag(int i_valid_Flag) {
		this.i_valid_Flag = i_valid_Flag;
	}

	public int getI_upload() {
		return i_upload;
	}

	public void setI_upload(int i_upload) {
		this.i_upload = i_upload;
	}
	
}
