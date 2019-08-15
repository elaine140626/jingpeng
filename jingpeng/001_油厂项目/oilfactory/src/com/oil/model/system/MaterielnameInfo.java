package com.oil.model.system;

public class MaterielnameInfo {
	private int Id		;
	private String MaterielName; //物料名称
	private int IsDel;//删除标记
	private String Creater	;//创建人	
	private String CreaterDate	;//创建时间
	private String Reviser	;//注册人
	private String ReviserDate	;//注册时间
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getMaterielName() {
		return MaterielName;
	}
	public void setMaterielName(String materielName) {
		MaterielName = materielName;
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
