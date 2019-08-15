package com.oil.model.dispath;

public class MaterielinfoEntity {
	// 物料id
	private int id;
	// 物料名称id
	private int materielNameId;
	// 规格型号id
	private int materielModelId;
	// 物料名称
	private String materielName;
	// 规格型号
	private String materielModel;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMaterielNameId() {
		return materielNameId;
	}
	public void setMaterielNameId(int materielNameId) {
		this.materielNameId = materielNameId;
	}
	public int getMaterielModelId() {
		return materielModelId;
	}
	public void setMaterielModelId(int materielModelId) {
		this.materielModelId = materielModelId;
	}
	public String getMaterielName() {
		return materielName;
	}
	public void setMaterielName(String materielName) {
		this.materielName = materielName;
	}
	public String getMaterielModel() {
		return materielModel;
	}
	public void setMaterielModel(String materielModel) {
		this.materielModel = materielModel;
	}
}
