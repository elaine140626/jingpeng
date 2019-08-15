package com.jingpeng.model;


import com.kdt.base.support.model.ModelSupport;

public class Scattergram_Info extends ModelSupport{

	/**
	 * 站区设备分布信息
	 */
	private static final long serialVersionUID = -2005983011861694938L;
	
	private int i_id; //自增长ID
	
	private int i_organization_Info_Id; //组织机构ID/拌和站ID
	
	private String str_location; //位置编号
	
	private int i_reserveTank_Info_Id; //油罐ID
	
	private int i_machine_Info_Id; //拌和机ID

	public int getI_id() {
		return i_id;
	}

	public void setI_id(int i_id) {
		this.i_id = i_id;
	}

	public int getI_organization_Info_Id() {
		return i_organization_Info_Id;
	}

	public void setI_organization_Info_Id(int i_organization_Info_Id) {
		this.i_organization_Info_Id = i_organization_Info_Id;
	}

	public String getStr_location() {
		return str_location;
	}

	public void setStr_location(String str_location) {
		this.str_location = str_location;
	}

	public int getI_reserveTank_Info_Id() {
		return i_reserveTank_Info_Id;
	}

	public void setI_reserveTank_Info_Id(int i_reserveTank_Info_Id) {
		this.i_reserveTank_Info_Id = i_reserveTank_Info_Id;
	}

	public int getI_machine_Info_Id() {
		return i_machine_Info_Id;
	}

	public void setI_machine_Info_Id(int i_machine_Info_Id) {
		this.i_machine_Info_Id = i_machine_Info_Id;
	}

}
