package com.MixStation.model;

public class MaterialProductEntity {
	private int Id;
	private int Product_ID;//物料名称/型号ID
	private int Org_Id;
	private String Material_Model;
	private String Material_Name;
	private int MaterialModel_Id;
	private int Material_Name_Id;
	private int Valid_Flag;
	private int Material_Type;
	private int Material_Mold;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getProduct_ID() {
		return Product_ID;
	}
	public void setProduct_ID(int product_ID) {
		Product_ID = product_ID;
	}
	
	public int getOrg_Id() {
		return Org_Id;
	}
	public void setOrg_Id(int Org_Id) {
		this.Org_Id = Org_Id;
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
	public int getMaterialModel_Id() {
		return MaterialModel_Id;
	}
	public void setMaterialModel_Id(int materialModel_Id) {
		MaterialModel_Id = materialModel_Id;
	}
	public int getMaterial_Name_Id() {
		return Material_Name_Id;
	}
	public void setMaterial_Name_Id(int material_Name_Id) {
		Material_Name_Id = material_Name_Id;
	}
	public int getValid_Flag() {
		return Valid_Flag;
	}
	public void setValid_Flag(int valid_Flag) {
		Valid_Flag = valid_Flag;
	}
	public int getMaterial_Type() {
		return Material_Type;
	}
	public void setMaterial_Type(int material_Type) {
		Material_Type = material_Type;
	}
	public int getMaterial_Mold() {
		return Material_Mold;
	}
	public void setMaterial_Mold(int material_Mold) {
		Material_Mold = material_Mold;
	}
	
	
	
}
