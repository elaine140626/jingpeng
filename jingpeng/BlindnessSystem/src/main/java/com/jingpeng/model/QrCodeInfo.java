package com.jingpeng.model;

import com.kdt.base.support.model.ModelSupport;

public class QrCodeInfo extends ModelSupport{

private static final long serialVersionUID = -7574269001354682643L;
	
	private  Integer id; 
	private  String Prefix; 
	private  String StartCode; 
	private  String endCode;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPrefix() {
		return Prefix;
	}
	public void setPrefix(String prefix) {
		Prefix = prefix;
	}
	public String getStartCode() {
		return StartCode;
	}
	public void setStartCode(String startCode) {
		StartCode = startCode;
	}
	public String getEndCode() {
		return endCode;
	}
	public void setEndCode(String endCode) {
		this.endCode = endCode;
	}
	
	
}
