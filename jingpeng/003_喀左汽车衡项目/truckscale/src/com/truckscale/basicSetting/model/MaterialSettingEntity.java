package com.truckscale.basicSetting.model;

public class MaterialSettingEntity {

	// 自增长ID
	private Integer Id;
	// 材料编号
	private String MaterielNumber;
	// 材料名称
	private String MaterielName;
	// 材料类别（数据字典） 0:原材料1:产成品 2:既是原材料又是产成品
	private String MaterielType;
	// 材料类别名称
	private String MaterielTypeName;
	// 规格型号
	private String MaterielModel;
	private String Remarks;
	private Integer IsDel;
	private String Creater;
	private String CreaterDate;
	private String Reviser;
	private String ReviserDate;
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getMaterielNumber() {
		return MaterielNumber;
	}
	public void setMaterielNumber(String materielNumber) {
		MaterielNumber = materielNumber;
	}
	public String getMaterielName() {
		return MaterielName;
	}
	public void setMaterielName(String materielName) {
		MaterielName = materielName;
	}
	public String getMaterielType() {
		return MaterielType;
	}
	public void setMaterielType(String materielType) {
		MaterielType = materielType;
	}
	public String getMaterielTypeName() {
		return MaterielTypeName;
	}
	public void setMaterielTypeName(String materielTypeName) {
		MaterielTypeName = materielTypeName;
	}
	public String getMaterielModel() {
		return MaterielModel;
	}
	public void setMaterielModel(String materielModel) {
		MaterielModel = materielModel;
	}
	public String getRemarks() {
		return Remarks;
	}
	public void setRemarks(String remarks) {
		Remarks = remarks;
	}
	public Integer getIsDel() {
		return IsDel;
	}
	public void setIsDel(Integer isDel) {
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
