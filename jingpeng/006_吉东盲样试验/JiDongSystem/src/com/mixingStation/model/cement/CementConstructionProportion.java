package com.mixingStation.model.cement;
/**
 * 
 * @Title CementConstructionProportion(水泥施工配比表)
 * @author Administrator
 * @date 2019年3月7日
 */
public class CementConstructionProportion {
	private int id;                  //自增长ID
	private int orgId;               //组织机构ID
	private String propCode;         //配比编码
	private int theoPropId;          //理论配比ID
	private int productId;           //产品Id
	private String designStrength;   //设计强度（单位：MPa）
	private Double waterCementRatio; //水灰比
	private Double sandRatio;        //砂率
	private int slumpLow;            //坍落度1（单位：mm）
	private int slumpHigh;           //坍落度2
	private String remarks;          //备注
	private String operator;         //创建人
	private String createDate;       //创建日期
	private String modifier;         //修改人
	private String modifyDate;       //修改日期
	private int validFlag;           //有效标识
	private int upload;              //上传标识
	
	private String materialName;     //物料名称
	private String materialModel;    //物料型号
	private String lowAndHight;       //塌落度
	private String lLPropCode;       //理论配比编号
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
	public String getPropCode() {
		return propCode;
	}
	public void setPropCode(String propCode) {
		this.propCode = propCode;
	}
	public int getTheoPropId() {
		return theoPropId;
	}
	public void setTheoPropId(int theoPropId) {
		this.theoPropId = theoPropId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getDesignStrength() {
		return designStrength;
	}
	public void setDesignStrength(String designStrength) {
		this.designStrength = designStrength;
	}
	public Double getSandRatio() {
		return sandRatio;
	}
	public void setSandRatio(Double sandRatio) {
		this.sandRatio = sandRatio;
	}
	public int getSlumpLow() {
		return slumpLow;
	}
	public void setSlumpLow(int slumpLow) {
		this.slumpLow = slumpLow;
	}
	public int getSlumpHigh() {
		return slumpHigh;
	}
	public void setSlumpHigh(int slumpHigh) {
		this.slumpHigh = slumpHigh;
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
	public int getUpload() {
		return upload;
	}
	public void setUpload(int upload) {
		this.upload = upload;
	}
	public Double getWaterCementRatio() {
		return waterCementRatio;
	}
	public void setWaterCementRatio(Double waterCementRatio) {
		this.waterCementRatio = waterCementRatio;
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
	public String getLowAndHight() {
		return lowAndHight;
	}
	public void setLowAndHight(String lowAndHight) {
		this.lowAndHight = lowAndHight;
	}
	public String getlLPropCode() {
		return lLPropCode;
	}
	public void setlLPropCode(String lLPropCode) {
		this.lLPropCode = lLPropCode;
	}
	
}
