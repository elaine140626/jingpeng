package com.mixingStation.model.asphalt;
/**
 * 
 * @Title 生产计划
 * @author Administrator
 * @date 2019年1月29日
 */
public class AsphaltProductionPlan {
	/**
	 * 生产计划表:指定拌合机生产使用，并明确指明该计划下的拌和数据使用去向及部位；
	        可根据生产计划追踪 生产数据、生产数据去向使用部位、试验检测结果；
	 */
	private int id;                      //自增长ID
	private int orgId;                   //组织机构ID
	private String planNo;               //生产计划编号
	private String startTime;            //生产计划开始时间
	private int productId;               //产品Id
	private int prodId;                  //生产配比ID
	private int gradId;                  //级配比ID
	private int equId;                   //拌和机Id
	private String projPos;              //工程部位/用途
	private String constructionUnit;     //施工单位
	private Double scheduledProduction;  //计划生产量
	private int isCancel;                //计划是否作废
	private String cancelTime;           //作废时间
	private String invalidPerson;        //作废人
	private String cancelReason;         //作废原因
	private String remarks;              //备注
	private String operator;             //创建人
	private String createDate;           //创建日期
	private String modifier;             //修改人
	private String modifyDate;           //修改日期
	private int validFlag;               //有效标识
	
	private String proportionCode;       //目标配合比编号
	private String equipmentName;        //拌合设备名称
	private int mateNameId;              //产品名称id
	private String materialName;         //产品名称
	private int mateModelId;             //产品型号id
	private String materialModel;        //产品型号
	private String proportion_Code;      //生产配合比编号
	private String gradeCode;            //级配编号
	private int targPropId;              //目标配比ID
	private int i_prodId;                //生产配比ID
	private String orgName;              //机构名称
	private String nameAndModel;         //物料和型号
	private String str_isCancel;         //已生效/已作废
	private String data_Type;
	
	public String getData_Type() {
		return data_Type;
	}
	public void setData_Type(String data_Type) {
		this.data_Type = data_Type;
	}
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
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public int getGradId() {
		return gradId;
	}
	public void setGradId(int gradId) {
		this.gradId = gradId;
	}
	public int getEquId() {
		return equId;
	}
	public void setEquId(int equId) {
		this.equId = equId;
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
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public int getValidFlag() {
		return validFlag;
	}
	public void setValidFlag(int validFlag) {
		this.validFlag = validFlag;
	}
	public String getProportionCode() {
		return proportionCode;
	}
	public void setProportionCode(String proportionCode) {
		this.proportionCode = proportionCode;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public int getMateNameId() {
		return mateNameId;
	}
	public void setMateNameId(int mateNameId) {
		this.mateNameId = mateNameId;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public int getMateModelId() {
		return mateModelId;
	}
	public void setMateModelId(int mateModelId) {
		this.mateModelId = mateModelId;
	}
	public String getMaterialModel() {
		return materialModel;
	}
	public void setMaterialModel(String materialModel) {
		this.materialModel = materialModel;
	}
	public String getGradeCode() {
		return gradeCode;
	}
	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}
	public String getProportion_Code() {
		return proportion_Code;
	}
	public void setProportion_Code(String proportion_Code) {
		this.proportion_Code = proportion_Code;
	}
	public int getTargPropId() {
		return targPropId;
	}
	public void setTargPropId(int targPropId) {
		this.targPropId = targPropId;
	}
	public int getI_prodId() {
		return i_prodId;
	}
	public void setI_prodId(int i_prodId) {
		this.i_prodId = i_prodId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getNameAndModel() {
		return nameAndModel;
	}
	public void setNameAndModel(String nameAndModel) {
		this.nameAndModel = nameAndModel;
	}
	public String getStr_isCancel() {
		return str_isCancel;
	}
	public void setStr_isCancel(String str_isCancel) {
		this.str_isCancel = str_isCancel;
	}
	
}
