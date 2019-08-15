package com.mixingStation.model.asphalt;

/**
 * 
 * @Title AsphaltGrading(沥青级配基本信息表)
 * @author Administrator
 * @date 2019年2月13日
 */
public class AsphaltGrading {
	/**
	 * 级配基本信息：级配是集料各级粒径颗粒的分配情况，通过筛析试验确定。供施工生产分析使用。
	         使用范围：可以生产配合比中选择，也可单独与生产数据做通过率的分析。
	 */
	private int id;            //自增长ID
	private int orgId;         //组织机构ID
	private String gradeCode;  //级配编码
	private int productId;     //产品Id
	private String materialName;    //产品名称
	private String materialModel;    //产品型号
	private String remarks;    //备注
	private String operator;   //创建人
	private String createDate; //创建日期
	private String modifier;   //修改人
	private String modifyDate; //修改日期
	private String uniqueCode; //唯一标识
	private int validFlag;     //有效标识
	
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
	public String getGradeCode() {
		return gradeCode;
	}
	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
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
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getUniqueCode() {
		return uniqueCode;
	}
	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
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
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	
}
