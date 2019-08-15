package com.mix.model;

import java.util.List;

/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public class Cement_TheoryProportion {

	/**
	 * 水泥理论配比表
	 */
	private static final long serialVersionUID = -59640957702588263L;
	
	private int i_id;//自增长ID
	
	private int i_org_Id;//组织机构ID
	
	private String  str_prop_Code;//配比编码
	
	private int i_product_Id;//产品Id
	
	private String  str_design_Strength;//设计强度（单位：MPa）
	
	private double d_water_Cement_Ratio;//水灰比
	
	private double d_sand_Ratio;//砂率
	
	private int i_slump_Low;//坍落度1（单位：mm）
	
	private int i_slump_High;//坍落度2
	
	private String str_remarks;//备注
	
	private String str_operator;//创建人
	
	private String  str_create_Date;//创建日期
	
	private String str_modifier;//修改人
	
	private String str_modify_Date;//修改日期
	
	private int i_valid_Flag;//有效标识
	
	private int i_upload;//上传标识
	
	private String material_Model;
	
	private String material_Name;
	
	private String operate;
	
	private int serialNumber;
	
	private String str_tld;
	
	public String getStr_create_Date() {
		return str_create_Date;
	}

	public void setStr_create_Date(String str_create_Date) {
		this.str_create_Date = str_create_Date;
	}

	private List<Cement_TheoPropDetailed> Cement_TheoPropDetaileds;
	
	public List<Cement_TheoPropDetailed> getCement_TheoPropDetaileds() {
		return Cement_TheoPropDetaileds;
	}

	public void setCement_TheoPropDetaileds(List<Cement_TheoPropDetailed> cement_TheoPropDetaileds) {
		Cement_TheoPropDetaileds = cement_TheoPropDetaileds;
	}

	public String getStr_tld() {
		return str_tld;
	}

	public void setStr_tld(String str_tld) {
		this.str_tld = str_tld;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
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

	public String getStr_prop_Code() {
		return str_prop_Code;
	}

	public void setStr_prop_Code(String str_prop_Code) {
		this.str_prop_Code = str_prop_Code;
	}

	public int getI_product_Id() {
		return i_product_Id;
	}

	public void setI_product_Id(int i_product_Id) {
		this.i_product_Id = i_product_Id;
	}

	public String getStr_design_Strength() {
		return str_design_Strength;
	}

	public void setStr_design_Strength(String str_design_Strength) {
		this.str_design_Strength = str_design_Strength;
	}

	public double getD_water_Cement_Ratio() {
		return d_water_Cement_Ratio;
	}

	public void setD_water_Cement_Ratio(double d_water_Cement_Ratio) {
		this.d_water_Cement_Ratio = d_water_Cement_Ratio;
	}

	public double getD_sand_Ratio() {
		return d_sand_Ratio;
	}

	public void setD_sand_Ratio(double d_sand_Ratio) {
		this.d_sand_Ratio = d_sand_Ratio;
	}

	public int getI_slump_Low() {
		return i_slump_Low;
	}

	public void setI_slump_Low(int i_slump_Low) {
		this.i_slump_Low = i_slump_Low;
	}

	public int getI_slump_High() {
		return i_slump_High;
	}

	public void setI_slump_High(int i_slump_High) {
		this.i_slump_High = i_slump_High;
	}

	public String getStr_remarks() {
		return str_remarks;
	}

	public void setStr_remarks(String str_remarks) {
		this.str_remarks = str_remarks;
	}

	public String getStr_operator() {
		return str_operator;
	}

	public void setStr_operator(String str_operator) {
		this.str_operator = str_operator;
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

	public String getMaterial_Model() {
		return material_Model;
	}

	public void setMaterial_Model(String material_Model) {
		this.material_Model = material_Model;
	}

	public String getMaterial_Name() {
		return material_Name;
	}

	public void setMaterial_Name(String material_Name) {
		this.material_Name = material_Name;
	}

	
	
}
