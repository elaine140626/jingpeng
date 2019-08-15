package com.mix.model;

public class Organization_Info{

	/**
	 * 机构树信息
	 */
	private static final long serialVersionUID = -2682139025687880836L;
	
	private int i_id; //自增长ID
	
	private int i_parent_Id; //父结点ID
	
	private String str_org_Name; //机构名称
	
	private String str_org_Code; //机构编码
	
	private String str_org_Type; //机构类型
	
	private String str_org_Address; //机构地址
	
	private String str_section_code; //合同段编码
	
	private String str_projOrg_code; //所属项目部码
	
	private String str_contact; //联系人
	
	private String str_tel; //联系电话
	
	private double d_latitude; //纬度
	
	private double d_longitude; //经度
	
	private String str_remark; //备注
	
	private int i_amoun; //拌和机数量
	
	private String  str_unit; //所属单位
	
	private String str_super_Unit; //上级单位
	
	private String str_data_Source_Vendor; //数据来源
	
	private int i_proportion_Type; //配比分析类型
	
	private String str_operator; //创建人
	
	private String str_create_Date; //创建日期
	
	private String str_modifier; //修改人
	
	private String str_modify_Date; //修改日期
	
	private int i_valid_Flag; //有效标识
	
	private int i_upload; //上传标识
	
	private String  ParentName;
	
	private int Id;
	
	private String  Org_Name;

	public String getParentName() {
		return ParentName;
	}

	public void setParentName(String parentName) {
		ParentName = parentName;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getOrg_Name() {
		return Org_Name;
	}

	public void setOrg_Name(String org_Name) {
		Org_Name = org_Name;
	}

	public int getI_id() {
		return i_id;
	}

	public void setI_id(int i_id) {
		this.i_id = i_id;
	}

	public String getStr_org_Name() {
		return str_org_Name;
	}

	public void setStr_org_Name(String str_org_Name) {
		this.str_org_Name = str_org_Name;
	}

	public String getStr_org_Code() {
		return str_org_Code;
	}

	public void setStr_org_Code(String str_org_Code) {
		this.str_org_Code = str_org_Code;
	}

	public String getStr_org_Type() {
		return str_org_Type;
	}

	public void setStr_org_Type(String str_org_Type) {
		this.str_org_Type = str_org_Type;
	}

	public String getStr_org_Address() {
		return str_org_Address;
	}

	public void setStr_org_Address(String str_org_Address) {
		this.str_org_Address = str_org_Address;
	}

	public String getStr_section_code() {
		return str_section_code;
	}

	public void setStr_section_code(String str_section_code) {
		this.str_section_code = str_section_code;
	}

	public String getStr_projOrg_code() {
		return str_projOrg_code;
	}

	public void setStr_projOrg_code(String str_projOrg_code) {
		this.str_projOrg_code = str_projOrg_code;
	}

	public String getStr_contact() {
		return str_contact;
	}

	public void setStr_contact(String str_contact) {
		this.str_contact = str_contact;
	}

	public String getStr_tel() {
		return str_tel;
	}

	public void setStr_tel(String str_tel) {
		this.str_tel = str_tel;
	}

	public double getD_latitude() {
		return d_latitude;
	}

	public void setD_latitude(double d_latitude) {
		this.d_latitude = d_latitude;
	}

	public double getD_longitude() {
		return d_longitude;
	}

	public void setD_longitude(double d_longitude) {
		this.d_longitude = d_longitude;
	}

	public String getStr_remark() {
		return str_remark;
	}

	public void setStr_remark(String str_remark) {
		this.str_remark = str_remark;
	}

	public int getI_amoun() {
		return i_amoun;
	}

	public void setI_amoun(int i_amoun) {
		this.i_amoun = i_amoun;
	}

	public String getStr_unit() {
		return str_unit;
	}

	public void setStr_unit(String str_unit) {
		this.str_unit = str_unit;
	}

	public String getStr_super_Unit() {
		return str_super_Unit;
	}

	public void setStr_super_Unit(String str_super_Unit) {
		this.str_super_Unit = str_super_Unit;
	}

	public String getStr_data_Source_Vendor() {
		return str_data_Source_Vendor;
	}

	public void setStr_data_Source_Vendor(String str_data_Source_Vendor) {
		this.str_data_Source_Vendor = str_data_Source_Vendor;
	}

	public int getI_proportion_Type() {
		return i_proportion_Type;
	}

	public void setI_proportion_Type(int i_proportion_Type) {
		this.i_proportion_Type = i_proportion_Type;
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

	public int getI_parent_Id() {
		return i_parent_Id;
	}

	public void setI_parent_Id(int i_parent_Id) {
		this.i_parent_Id = i_parent_Id;
	}

}
