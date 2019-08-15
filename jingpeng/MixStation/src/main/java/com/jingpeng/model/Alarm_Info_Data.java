package com.jingpeng.model;

import com.kdt.base.support.model.ModelSupport;

/**
 * 预警信息
 * 
 * @author Administrator
 *
 */
public class Alarm_Info_Data extends ModelSupport {
	private static final long serialVersionUID = -3814902990455049592L;
	private int i_id;
	private String str_alarm_Info;// 预警信息
	private String str_remarks;// 备注
	private String str_operator;// 创建人
	private String str_create_Date;// 创建日期
	private String str_modifier;// 修改人
	private String str_modify_Date;// 修改日期
	private int i_valid_Flag;// 用户是否有效：1有效，0无效
	private int i_upload;// 上传标识
	private String str_column_10;//留用字段
	public int getI_id() {
		return i_id;
	}
	public void setI_id(int i_id) {
		this.i_id = i_id;
	}
	public String getStr_alarm_Info() {
		return str_alarm_Info;
	}
	public void setStr_alarm_Info(String str_alarm_Info) {
		this.str_alarm_Info = str_alarm_Info;
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
	public String getStr_column_10() {
		return str_column_10;
	}
	public void setStr_column_10(String str_column_10) {
		this.str_column_10 = str_column_10;
	}
	
}
