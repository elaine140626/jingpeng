package com.MixStation.model;
/**
 * 
 * @Title AsphaltGradingInfoEntity(级配信息表)
 * @author Administrator
 * @date 2019年7月17日
 */
public class AsphaltGradingInfoEntity {

	private int id;              // 自增长id
	private int orgId;           // 组织机构id
	private String orgName;      // 拌合站名称
	private int mpId;            // 配合比id
	private String mixNumber;    // 生产配比编号
	private String materialName; // 混合料名称
	private String materialModel;// 混合料型号
	private String gradeCode;    // 级配编号
	private String remarks;      // 备注
	private String operator;     // 创建者
	private String createDate;   // 创建时间
	private String modifier;     // 修改人
	private String modifyDate;   // 修改时间
	private int validFlag;       // 是否有效：1有效，0无效
	private int isEnable;        // 是否启用：1启用，0未启用
	private int isUsed;          // 是否启用过：1启用过，0未启用过
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
	public int getMpId() {
		return mpId;
	}
	public void setMpId(int mpId) {
		this.mpId = mpId;
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
	public String getGradeCode() {
		return gradeCode;
	}
	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
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
	public String getMixNumber() {
		return mixNumber;
	}
	public void setMixNumber(String mixNumber) {
		this.mixNumber = mixNumber;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public int getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(int isEnable) {
		this.isEnable = isEnable;
	}
	public int getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}
	
}
