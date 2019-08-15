package com.jingpeng.model;

import com.kdt.base.support.model.ModelSupport;

public class Correct_History extends ModelSupport{

	/**
	 * 生产数据修正历史记录：记录系统生产数据被修正的痕迹。
	 */
	private static final long serialVersionUID = -8146223980758123423L;
	
	private int i_id;//自增长ID
	
	private int i_org_Id;//组织机构ID
	
	private String str_reviser;//修正人

	private String str_correct_Time;//修正时间
	
	private String str_correct_Reason;//修正原因
	
	private int i_correct_ProdID;//修正使用生产配合比
	
	private int i_correct_GradId;//修正使用级配
	
	private int i_is_New_Dev;//是否使用是新偏差修正
	
	private String str_correct_Begin_Time;//修正开始时间
	
	private String str_correct_End_Time;//修正结束时间
	
	private String str_remarks;//备注
	
	private String str_operator;//创建人
	
	private String str_create_Date;//创建日期
	
	private String str_modifier;//修改人
	
	private String str_modify_Date;//修改日期
	
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

	public String getStr_reviser() {
		return str_reviser;
	}

	public void setStr_reviser(String str_reviser) {
		this.str_reviser = str_reviser;
	}

	public String getStr_correct_Time() {
		return str_correct_Time;
	}

	public void setStr_correct_Time(String str_correct_Time) {
		this.str_correct_Time = str_correct_Time;
	}

	public String getStr_correct_Reason() {
		return str_correct_Reason;
	}

	public void setStr_correct_Reason(String str_correct_Reason) {
		this.str_correct_Reason = str_correct_Reason;
	}

	public int getI_correct_ProdID() {
		return i_correct_ProdID;
	}

	public void setI_correct_ProdID(int i_correct_ProdID) {
		this.i_correct_ProdID = i_correct_ProdID;
	}

	public int getI_correct_GradId() {
		return i_correct_GradId;
	}

	public void setI_correct_GradId(int i_correct_GradId) {
		this.i_correct_GradId = i_correct_GradId;
	}

	public int getI_is_New_Dev() {
		return i_is_New_Dev;
	}

	public void setI_is_New_Dev(int i_is_New_Dev) {
		this.i_is_New_Dev = i_is_New_Dev;
	}

	public String getStr_correct_Begin_Time() {
		return str_correct_Begin_Time;
	}

	public void setStr_correct_Begin_Time(String str_correct_Begin_Time) {
		this.str_correct_Begin_Time = str_correct_Begin_Time;
	}

	public String getStr_correct_End_Time() {
		return str_correct_End_Time;
	}

	public void setStr_correct_End_Time(String str_correct_End_Time) {
		this.str_correct_End_Time = str_correct_End_Time;
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
