package com.oil.model.repertory;

public class TaskCheckingEntity {
	private Integer Id;
	private String MaterielName; // 物料名称(产品名称)
	private String MaterielModel; // 规格型号(产品型号)
	private String PlanNumber; // 编号
	private Double PlanWeight; // 计划生产量
	private String Batch; // 生产批次
	private String RawMaterialName; // 原材料名称
	private String RawMaterialModel; // 原材料型号
	private Double PrimeNumber; // 库存量
	private Double RatioWeight; // 含量
	private Double MaterielWeight; // 重量
	private String Unit; // 单位
	private String IsSatisfy; // 是否满足生产

	public String getUnit() {
		return Unit;
	}

	public void setUnit(String unit) {
		Unit = unit;
	}

	public String getRawMaterialModel() {
		return RawMaterialModel;
	}

	public void setRawMaterialModel(String rawMaterialModel) {
		RawMaterialModel = rawMaterialModel;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
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

	public String getPlanNumber() {
		return PlanNumber;
	}

	public void setPlanNumber(String planNumber) {
		PlanNumber = planNumber;
	}

	public Double getPlanWeight() {
		return PlanWeight;
	}

	public void setPlanWeight(Double planWeight) {
		PlanWeight = planWeight;
	}

	public String getBatch() {
		return Batch;
	}

	public void setBatch(String batch) {
		Batch = batch;
	}

	public String getRawMaterialName() {
		return RawMaterialName;
	}

	public void setRawMaterialName(String rawMaterialName) {
		RawMaterialName = rawMaterialName;
	}

	public Double getPrimeNumber() {
		return PrimeNumber;
	}

	public void setPrimeNumber(Double primeNumber) {
		PrimeNumber = primeNumber;
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

	public String getIsSatisfy() {
		return IsSatisfy;
	}

	public void setIsSatisfy(String isSatisfy) {
		IsSatisfy = isSatisfy;
	}
}
