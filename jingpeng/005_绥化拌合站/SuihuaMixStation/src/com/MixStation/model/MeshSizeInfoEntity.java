package com.MixStation.model;
/**
 * 
 * @Title MeshSizeInfoEntity(筛孔信息表)
 * @author ygt
 * @date 2019年7月17日
 */
public class MeshSizeInfoEntity {
	
	private int id;              // 自增长id
	private int orgId;           // 组织机构id
	private Double meshSize;     // 筛孔大小
	private String operator;     // 创建者
	private String createDate;   // 创建时间
	private String modifier;     // 修改人
	private String modifyDate;   // 修改时间
	private int validFlag;       // 是否有效：1有效，0无效
	private String orgName;      // 拌合站名称
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
	public Double getMeshSize() {
		return meshSize;
	}
	public void setMeshSize(Double meshSize) {
		this.meshSize = meshSize;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
}
