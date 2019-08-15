package com.mixingStation.model.userInfo;

import java.util.Date;

/**
 * @since 组织机构表
 * @author Administrator
 *
 */
public class OrganizationInfo {
	
	// Id
	private int id;
	
	// 父节点的自增长Id
	private int parentId;
	
	// 机构名称
	private String orgName;
	
	// 机构编码
	private String orgCode;
	
	// 机构类型：0其他部门,1合同段拌和站，2 拌和站
	private String orgType;
	
	// 机构地址
	private String orgAddress;
	
	// 合同段编码
	private String sectioncode;
	
	// 项目部码
	private String projOrgcode;
	
	// 
	private String contact;
	
	// 电话
	private String tel;
	
	// 
	private double latitude;
	
	// 
	private double longitude;
	
	// 备注
	private String remark;
	
	// 拌和机数量:限示2位数
	private int amoun;
	
	// 
	private String unit;
	
	// 
	private String superUnit;
	
	// 
	private String dataSourceVendor;
	
	// 生产分析类型: 0 级配分析,1配比分析,2级配加配比分析
	private String proportionType;
	
	// 拌和站类型:  0 沥青拌和站； 1水泥拌和站； 2 水稳拌和站；
	private String mixStationType;
	
	// 创建人
	private String operator;
	
	// 创建日期
	private Date createDate;
	
	// 修改人
	private String modifier;
	
	// 修改日期
	private Date modifyDate;
	
	// 用户是否有效：1有效，0无效
	private String validFlag;
	
	// 上传标识
	private String upload;
	
	// 类型
	private int MixStation_Type;


	public int getMixStation_Type() {
		return MixStation_Type;
	}

	public void setMixStation_Type(int mixStation_Type) {
		MixStation_Type = mixStation_Type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getOrgAddress() {
		return orgAddress;
	}

	public void setOrgAddress(String orgAddress) {
		this.orgAddress = orgAddress;
	}

	public String getSectioncode() {
		return sectioncode;
	}

	public void setSectioncode(String sectioncode) {
		this.sectioncode = sectioncode;
	}

	public String getProjOrgcode() {
		return projOrgcode;
	}

	public void setProjOrgcode(String projOrgcode) {
		this.projOrgcode = projOrgcode;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getAmoun() {
		return amoun;
	}

	public void setAmoun(int amoun) {
		this.amoun = amoun;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getSuperUnit() {
		return superUnit;
	}

	public void setSuperUnit(String superUnit) {
		this.superUnit = superUnit;
	}

	public String getDataSourceVendor() {
		return dataSourceVendor;
	}

	public void setDataSourceVendor(String dataSourceVendor) {
		this.dataSourceVendor = dataSourceVendor;
	}

	public String getProportionType() {
		return proportionType;
	}

	public void setProportionType(String proportionType) {
		this.proportionType = proportionType;
	}

	public String getMixStationType() {
		return mixStationType;
	}

	public void setMixStationType(String mixStationType) {
		this.mixStationType = mixStationType;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
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

	public String getValidFlag() {
		return validFlag;
	}

	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}

	public String getUpload() {
		return upload;
	}

	public void setUpload(String upload) {
		this.upload = upload;
	}
	
	
}
