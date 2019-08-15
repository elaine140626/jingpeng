package com.mix.model;

public class MaterName_Info{

	/**
	 * 原材料名称表
	 */
	private static final long serialVersionUID = -6856314222092706921L;
	
	private int i_id; //自增长ID
	
	private int materialName_Id;//
	
	private int materialModel_Id;//
	
	private String str_material_Name; //物料名称
	
	private String str_operator; //创建人
	
	private String str_create_Date; //创建日期
	
	private int i_valid_Flag; //有效标识
	
	private int i_upload; //上传标识

	public int getMaterialName_Id() {
		return materialName_Id;
	}

	public void setMaterialName_Id(int materialName_Id) {
		this.materialName_Id = materialName_Id;
	}

	public int getMaterialModel_Id() {
		return materialModel_Id;
	}

	public void setMaterialModel_Id(int materialModel_Id) {
		this.materialModel_Id = materialModel_Id;
	}

	public int getI_id() {
		return i_id;
	}

	public void setI_id(int i_id) {
		this.i_id = i_id;
	}

	public String getStr_material_Name() {
		return str_material_Name;
	}

	public void setStr_material_Name(String str_material_Name) {
		this.str_material_Name = str_material_Name;
	}

	public String getStr_operator() {
		return str_operator;
	}

	public void setStr_operator(String str_operator) {
		this.str_operator = str_operator;
	}

	public String getStr_create_Date() {
		return str_create_Date;
	}

	public void setStr_create_Date(String str_create_Date) {
		this.str_create_Date = str_create_Date;
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
