package com.jingpeng.model;

import com.kdt.base.support.model.ModelSupport;

public class EngineeringProject extends ModelSupport{

	/**
	 * 工程项目表
	 */
	private static final long serialVersionUID = -3535297320755719594L;
	
	private int i_id;//自增长ID
	
	private int i_org_Id;//组织机构ID
	
	private String str_project_Name;//项目名称
	
	private String str_project_Code;//项目编号
	
	private String str_project_Address;//项目地点
	
	private String str_remark;//备注
	
	private String str_operator;//创建人
	
	private String str_create_Date; //创建日期
	
	private String  str_modifier;//修改人
	
	private String  str_modify_Date;//修改日期
	
	private int i_valid_Flag;//有效标识
	
	private int i_upload;//上传标识

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

	public String getStr_project_Name() {
		return str_project_Name;
	}

	public void setStr_project_Name(String str_project_Name) {
		this.str_project_Name = str_project_Name;
	}

	public String getStr_project_Code() {
		return str_project_Code;
	}

	public void setStr_project_Code(String str_project_Code) {
		this.str_project_Code = str_project_Code;
	}

	public String getStr_project_Address() {
		return str_project_Address;
	}

	public void setStr_project_Address(String str_project_Address) {
		this.str_project_Address = str_project_Address;
	}

	public String getStr_remark() {
		return str_remark;
	}

	public void setStr_remark(String str_remark) {
		this.str_remark = str_remark;
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
	
}
