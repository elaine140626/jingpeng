package com.jingpeng.model;

import com.kdt.base.support.model.ModelSupport;

/**
 * 材料视图 Material_Info、MaterModel_Info、MaterName_Info关联表
 * 
 * @author Administrator
 *
 */
public class V_MaterialInfo extends ModelSupport {
	private static final long serialVersionUID = -8103233938172961499L;
	private int i_id;
	private int i_org_Id;
	private String str_material_Code;
	private int i_mateName_Id;
	private int i_mateModel_Id;
	private double d_density;
	private String str_measure_Unit;
	private String str_material_Type;
	private String str_operator;
	private String str_create_Date;
	private String str_modifier;
	private String str_modify_Date;
	private int i_valid_Flag;
	private int i_upload;
	private String str_remarks;
	private String str_material_Model;//规格型号
	private String str_material_Name;
	private String str_material_Mold;//类型
	private String operate;
	private String nameAndModel;
	private int serialNumber;
	
	public String getStr_material_Mold() {
		return str_material_Mold;
	}

	public void setStr_material_Mold(String str_material_Mold) {
		this.str_material_Mold = str_material_Mold;
	}

	public int getI_id() {
		return i_id;
	}

	public void setI_id(int i_id) {
		this.i_id = i_id;
	}

	public int getI_org_Id() {
		return i_org_Id;
	}

	public void setI_org_Id(int i_org_Id) {
		this.i_org_Id = i_org_Id;
	}

	public String getStr_material_Code() {
		return str_material_Code;
	}

	public void setStr_material_Code(String str_material_Code) {
		this.str_material_Code = str_material_Code;
	}

	public int getI_mateName_Id() {
		return i_mateName_Id;
	}

	public void setI_mateName_Id(int i_mateName_Id) {
		this.i_mateName_Id = i_mateName_Id;
	}

	public int getI_mateModel_Id() {
		return i_mateModel_Id;
	}

	public void setI_mateModel_Id(int i_mateModel_Id) {
		this.i_mateModel_Id = i_mateModel_Id;
	}

	public double getD_density() {
		return d_density;
	}

	public void setD_density(double d_density) {
		this.d_density = d_density;
	}

	public String getStr_measure_Unit() {
		return str_measure_Unit;
	}

	public void setStr_measure_Unit(String str_measure_Unit) {
		this.str_measure_Unit = str_measure_Unit;
	}

	public String getStr_material_Type() {
		return str_material_Type;
	}

	public void setStr_material_Type(String str_material_Type) {
		this.str_material_Type = str_material_Type;
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

	public String getStr_modifier() {
		return str_modifier;
	}

	public void setStr_modifier(String str_modifier) {
		this.str_modifier = str_modifier;
	}

	public String getStr_modify_Date() {
		return str_modify_Date;
	}

	public void setStr_modify_Date(String str_modify_Date) {
		this.str_modify_Date = str_modify_Date;
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

	public String getStr_remarks() {
		return str_remarks;
	}

	public void setStr_remarks(String str_remarks) {
		this.str_remarks = str_remarks;
	}

	public String getStr_material_Model() {
		return str_material_Model;
	}

	public void setStr_material_Model(String str_material_Model) {
		this.str_material_Model = str_material_Model;
	}

	public String getStr_material_Name() {
		return str_material_Name;
	}

	public void setStr_material_Name(String str_material_Name) {
		this.str_material_Name = str_material_Name;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String getNameAndModel() {
		return nameAndModel;
	}

	public void setNameAndModel(String nameAndModel) {
		this.nameAndModel = nameAndModel;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}


}
