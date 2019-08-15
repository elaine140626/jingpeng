package com.mixingStation.model.meshInfo;

import java.util.Date;

/**
 * @since 筛孔规格信息表
 * @author Administrator
 *
 */
public class MeshSizeInfo {
	// id
	private int id;
	
	// 组织机构编码
	private int orgId;
	
	// 筛孔规格
	private double meshSize;
	
	// 创建人
	private String operator;
	
	// 创建日期
	private Date createDate;
	
	// 修改人
	private String modifier;
	
	// 修改日期
	private Date modifyDate;
	
	// 有效标识
	private String validFlag;

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

	public double getMeshSize() {
		return meshSize;
	}

	public void setMeshSize(double meshSize) {
		this.meshSize = meshSize;
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
}
