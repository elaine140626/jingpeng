package com.curing.systemInfo.model;

public class DataDictionarySystemInfoListEntity {
	private String Id; // id
	private String Type; 
	private String Code; 
	private String Content; 
	private String ContentExplain; 
	private int IsDel;
	private String Creater;
	private String CreaterDate;
	private String Reviser;
	private String ReviserDate;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	
	public String getContentExplain() {
		return ContentExplain;
	}
	public void setContentExplain(String contentExplain) {
		ContentExplain = contentExplain;
	}
	public int getIsDel() {
		return IsDel;
	}
	public void setIsDel(int isDel) {
		IsDel = isDel;
	}
	public String getCreater() {
		return Creater;
	}
	public void setCreater(String creater) {
		Creater = creater;
	}
	public String getCreaterDate() {
		return CreaterDate;
	}
	public void setCreaterDate(String createrDate) {
		CreaterDate = createrDate;
	}
	public String getReviser() {
		return Reviser;
	}
	public void setReviser(String reviser) {
		Reviser = reviser;
	}
	public String getReviserDate() {
		return ReviserDate;
	}
	public void setReviserDate(String reviserDate) {
		ReviserDate = reviserDate;
	}
	
	
}
