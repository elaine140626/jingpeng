package com.jingpeng.model;

import java.util.Date;

import com.kdt.base.support.model.ModelSupport;

/*
 * 盲样信息表
 */
public class BlindSampleInfo extends ModelSupport{

	private static final long serialVersionUID = -7574269001354682643L;
	
	private  Integer id; //自增长ID
	private  Integer org_Id; //管理处机构Id
	private  String unitCode; //单位编号
	private  String constructionUnit; //施工单位
	private  String engineeringName; //工程名称
	private  String sampeCode; //样品编号
	private  String purpose;//工程部位
	private  String testName;//试验名称
	private  String testState;//试验状态
	private  String designStrength;//设计强度等级
	private  String moldDate;//试件成型日期
	private  String quantity;//取样数量
	private  String placeOrigin;//产地
	private  String materialName;//材料名称
	private  String sPileNumber;//取样桩号
	private  String samplingDate;//取样日期
	private  String samplingPerson;//取样人
	private  String remarks;//备注
	private  String qRCode;	//二维码
	private  Integer isQualifiedSamp; //试样是否合格
	private  String operator; //创建人
	private  String createDate;//创建日期11
	private  String modifier; //修改人
	private  Date modifyDate; //修改日期
	private  Integer isValidData;//有效标识
	private String Org_Name;//管理层机构名称
	private String entrystatus;//录入状态
	private String targetDate; //	试验完成日期
	
	public String getTestState() {
		return testState;
	}
	public void setTestState(String testState) {
		this.testState = testState;
	}
	public Integer getId() {
		return id;
	}
	public String getMoldDate() {
		return moldDate;
	}
	public void setMoldDate(String moldDate) {
		this.moldDate = moldDate;
	}
	public String getSamplingDate() {
		return samplingDate;
	}
	public void setSamplingDate(String samplingDate) {
		this.samplingDate = samplingDate;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrgId() {
		return org_Id;
	}
	public void setOrgId(Integer org_Id) {
		this.org_Id = org_Id;
	}
	public String getUnitCode() {
		return unitCode;
	}
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	public String getConstructionUnit() {
		return constructionUnit;
	}
	public void setConstructionUnit(String constructionUnit) {
		this.constructionUnit = constructionUnit;
	}
	public String getEngineeringName() {
		return engineeringName;
	}
	public void setEngineeringName(String engineeringName) {
		this.engineeringName = engineeringName;
	}
	public String getSampeCode() {
		return sampeCode;
	}
	public void setSampeCode(String sampeCode) {
		this.sampeCode = sampeCode;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getDesignStrength() {
		return designStrength;
	}
	public void setDesignStrength(String designStrength) {
		this.designStrength = designStrength;
	}

	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getPlaceOrigin() {
		return placeOrigin;
	}
	public void setPlaceOrigin(String placeOrigin) {
		this.placeOrigin = placeOrigin;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getsPileNumber() {
		return sPileNumber;
	}
	public void setsPileNumber(String sPileNumber) {
		this.sPileNumber = sPileNumber;
	}

	public String getSamplingPerson() {
		return samplingPerson;
	}
	public void setSamplingPerson(String samplingPerson) {
		this.samplingPerson = samplingPerson;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getqRCode() {
		return qRCode;
	}
	public void setqRCode(String qRCode) {
		this.qRCode = qRCode;
	}
	public Integer getIsQualifiedSamp() {
		return isQualifiedSamp;
	}
	public void setIsQualifiedSamp(Integer isQualifiedSamp) {
		this.isQualifiedSamp = isQualifiedSamp;
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
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public Integer getIsValidData() {
		return isValidData;
	}
	public void setIsValidData(Integer isValidData) {
		this.isValidData = isValidData;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getEntrystatus() {
		return entrystatus;
	}
	public void setEntrystatus(String entrystatus) {
		this.entrystatus = entrystatus;
	}
	public String getOrg_Name() {
		return Org_Name;
	}
	public void setOrg_Name(String org_Name) {
		Org_Name = org_Name;
	}
	public String getTargetDate() {
		return targetDate;
	}
	public void setTargetDate(String targetDate) {
		this.targetDate = targetDate;
	}
	
	
}
