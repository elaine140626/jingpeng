package com.mixingStation.model.materialInfo;

public class MaterialInfo {

	// 自增长ID
	private int id;

	// 组织机构编码
	private int orgId;

	// 物料编号
	private String materialCode;

	// 物料名称id
	private int mateNameId;
	
	// 物料名称
	private String mateName;

	// 规格型号id
	private int mateModelId;

	// 规格型号
	private String mateModel;
		
	// 密度
	private double density;

	// 计量单位
	private String measureUnit;

	// 物料总类 0 沥青,1水泥
	private char materialType;

	// 区别物料类型 0:原材料 1:产成品 2:半成品
	private char materialMold;

	// 备注
	private String remarks;

	// 创建人
	private String operator;

	// 创建日期
	private String createDate;

	// 修改人
	private String modifier;

	// 修改日期
	private String modifDate;

	// 上传标识
	private boolean upload;

	// 删除标识 0已删除(无效用户) 1 有效用户
	private boolean validFlag;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrgId() {
		return orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public int getMateNameId() {
		return mateNameId;
	}

	public void setMateNameId(int mateNameId) {
		this.mateNameId = mateNameId;
	}

	public String getMateName() {
		return mateName;
	}

	public void setMateName(String mateName) {
		this.mateName = mateName;
	}

	public int getMateModelId() {
		return mateModelId;
	}

	public void setMateModelId(int mateModelId) {
		this.mateModelId = mateModelId;
	}

	public String getMateModel() {
		return mateModel;
	}

	public void setMateModel(String mateModel) {
		this.mateModel = mateModel;
	}

	public double getDensity() {
		return density;
	}

	public void setDensity(double density) {
		this.density = density;
	}

	public String getMeasureUnit() {
		return measureUnit;
	}

	public void setMeasureUnit(String measureUnit) {
		this.measureUnit = measureUnit;
	}

	public char getMaterialType() {
		return materialType;
	}

	public void setMaterialType(char materialType) {
		this.materialType = materialType;
	}

	public char getMaterialMold() {
		return materialMold;
	}

	public void setMaterialMold(char materialMold) {
		this.materialMold = materialMold;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getModifDate() {
		return modifDate;
	}

	public void setModifDate(String modifDate) {
		this.modifDate = modifDate;
	}

	public boolean isUpload() {
		return upload;
	}

	public void setUpload(boolean upload) {
		this.upload = upload;
	}

	public boolean isValidFlag() {
		return validFlag;
	}

	public void setValidFlag(boolean validFlag) {
		this.validFlag = validFlag;
	}




}
