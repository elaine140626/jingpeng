package com.jingpeng.model;

import com.kdt.base.support.model.ModelSupport;

public class Equipment_Connected extends ModelSupport{

	/**
	 * 设备实时连通信息表
	 */
	private static final long serialVersionUID = -244104501763780685L;
	
	private int i_id; //自增长ID
	
	private int i_org_Id;//组织机构ID
	
	private int  i_machine_Id;//拌和机ID
	
	private String  send_No;//发送设备编号
	
	private int i_equipment_Type;//设备类型
	
	private int i_is_OnLine;//是否在线
	
	private String  str_lastLink_Date;//最后连接时间
	
	private String str_operator;//创建人
	
	private String  str_create_Date;//创建日期
	
	private int i_valid_Flag;//有效标识
	
	private String  str_remark;//备注

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

	public int getI_machine_Id() {
		return i_machine_Id;
	}

	public void setI_machine_Id(int i_machine_Id) {
		this.i_machine_Id = i_machine_Id;
	}

	public String getSend_No() {
		return send_No;
	}

	public void setSend_No(String send_No) {
		this.send_No = send_No;
	}

	public int getI_equipment_Type() {
		return i_equipment_Type;
	}

	public void setI_equipment_Type(int i_equipment_Type) {
		this.i_equipment_Type = i_equipment_Type;
	}

	public int getI_is_OnLine() {
		return i_is_OnLine;
	}

	public void setI_is_OnLine(int i_is_OnLine) {
		this.i_is_OnLine = i_is_OnLine;
	}

	public String getStr_lastLink_Date() {
		return str_lastLink_Date;
	}

	public void setStr_lastLink_Date(String str_lastLink_Date) {
		this.str_lastLink_Date = str_lastLink_Date;
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

	public String getStr_remark() {
		return str_remark;
	}

	public void setStr_remark(String str_remark) {
		this.str_remark = str_remark;
	}

}
