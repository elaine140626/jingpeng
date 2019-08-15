package com.mixingStation.model.cement;
/**
 * 
 * @Title CementProductionPlan(水泥生产计划)
 * @author Administrator
 * @date 2019年3月11日
 */
public class CementProductionPlan {
	private int id;                     //自增长ID
	private int orgId;                  //组织机构ID
	private String planNo;              //生产计划编号
	private String startTime;           //生产计划开始时间
	private int equId;                  //拌和机Id
	private int productId;              //产品Id
	private int consPropId;             //施工配比ID
	private int bunkerCorId;            //料仓对应关系ID
	private String projPos;             //施工地点
	private String constructionUnit;    //施工单位
	private String wateringSite;        //浇灌部位
	private Double scheduledProduction; //计划生产量
	private int isCancel;               //计划是否作废
	private String cancelTime;          //作废时间
	private String invalidPerson;       //作废人
	private String cancelReason;        //作废原因
	private String remarks;             //备注
	private String operator;            //创建人
	private String createDate;          //创建日期
	private String modifier;            //修改人
	private String modifydDate;         //修改日期
	private int validFlag;              //有效标识
	
	private String materialName;        //产品名称
	private String materialModel;       //产品型号
	private String nameAndModel;        //产品名称+产品型号
	private String bunkerCode;          //料仓对应关心编号
	private String equipmentName;       //拌合机设备名称
	private String propCode;            //施工配比编码
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
	public String getPlanNo() {
		return planNo;
	}
	public void setPlanNo(String planNo) {
		this.planNo = planNo;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public int getEquId() {
		return equId;
	}
	public void setEquId(int equId) {
		this.equId = equId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getConsPropId() {
		return consPropId;
	}
	public void setConsPropId(int consPropId) {
		this.consPropId = consPropId;
	}
	public int getBunkerCorId() {
		return bunkerCorId;
	}
	public void setBunkerCorId(int bunkerCorId) {
		this.bunkerCorId = bunkerCorId;
	}
	public String getProjPos() {
		return projPos;
	}
	public void setProjPos(String projPos) {
		this.projPos = projPos;
	}
	public String getConstructionUnit() {
		return constructionUnit;
	}
	public void setConstructionUnit(String constructionUnit) {
		this.constructionUnit = constructionUnit;
	}
	public String getWateringSite() {
		return wateringSite;
	}
	public void setWateringSite(String wateringSite) {
		this.wateringSite = wateringSite;
	}
	public Double getScheduledProduction() {
		return scheduledProduction;
	}
	public void setScheduledProduction(Double scheduledProduction) {
		this.scheduledProduction = scheduledProduction;
	}
	public int getIsCancel() {
		return isCancel;
	}
	public void setIsCancel(int isCancel) {
		this.isCancel = isCancel;
	}
	public String getCancelTime() {
		return cancelTime;
	}
	public void setCancelTime(String cancelTime) {
		this.cancelTime = cancelTime;
	}
	public String getInvalidPerson() {
		return invalidPerson;
	}
	public void setInvalidPerson(String invalidPerson) {
		this.invalidPerson = invalidPerson;
	}
	public String getCancelReason() {
		return cancelReason;
	}
	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
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
	public String getModifydDate() {
		return modifydDate;
	}
	public void setModifydDate(String modifydDate) {
		this.modifydDate = modifydDate;
	}
	public int getValidFlag() {
		return validFlag;
	}
	public void setValidFlag(int validFlag) {
		this.validFlag = validFlag;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getMaterialModel() {
		return materialModel;
	}
	public void setMaterialModel(String materialModel) {
		this.materialModel = materialModel;
	}
	public String getNameAndModel() {
		return nameAndModel;
	}
	public void setNameAndModel(String nameAndModel) {
		this.nameAndModel = nameAndModel;
	}
	public String getBunkerCode() {
		return bunkerCode;
	}
	public void setBunkerCode(String bunkerCode) {
		this.bunkerCode = bunkerCode;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public String getPropCode() {
		return propCode;
	}
	public void setPropCode(String propCode) {
		this.propCode = propCode;
	}
	
}
