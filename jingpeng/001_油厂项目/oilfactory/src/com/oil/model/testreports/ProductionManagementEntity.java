package com.oil.model.testreports;

public class ProductionManagementEntity {
	private Integer Id;
	private Integer PlanMeasureId; // 计划调度id
	private Integer MaterielId; // 原料id
	private String MaterielName; // 物料名称
	private String MaterielModel; // 规格型号
	private String Unit; // 物料单位
	private Double RatioWeight; // 含量(%)
	private Double MaterielWeight; // 理论消耗重量
	private Double ActualWeight; // 实际消耗重量
	private String Remarks; // 备注
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

	public Integer getPlanMeasureId() {
		return PlanMeasureId;
	}

	public void setPlanMeasureId(Integer planMeasureId) {
		PlanMeasureId = planMeasureId;
	}

	public Integer getMaterielId() {
		return MaterielId;
	}

	public void setMaterielId(Integer materielId) {
		MaterielId = materielId;
	}

	public String getMaterielName() {
		return MaterielName;
	}

	public void setMaterielName(String materielName) {
		MaterielName = materielName;
	}

	public String getMaterielModel() {
		return MaterielModel;
	}

	public void setMaterielModel(String materielModel) {
		MaterielModel = materielModel;
	}

	public String getUnit() {
		return Unit;
	}

	public void setUnit(String unit) {
		Unit = unit;
	}

	public Double getRatioWeight() {
		return RatioWeight;
	}

	public void setRatioWeight(Double ratioWeight) {
		RatioWeight = ratioWeight;
	}

	public Double getMaterielWeight() {
		return MaterielWeight;
	}

	public void setMaterielWeight(Double materielWeight) {
		MaterielWeight = materielWeight;
	}

	public Double getActualWeight() {
		return ActualWeight;
	}

	public void setActualWeight(Double actualWeight) {
		ActualWeight = actualWeight;
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
