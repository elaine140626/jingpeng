package com.mixingStation.model.asphalt;
/**
 * 
 * @Title AsphaltProdProportion(沥青生产配合比表)
 * @author Administrator
 * @date 2019年2月12日
 */
public class AsphaltProdProportion {
	/**
	 * 沥青生产配合比：生产配合比是指导生产用的
	         使用范围：拌合采集数据与生产配合比进行比对分析。 
	 */
	private int id;                     //自增长ID
	private int orgId;                  //组织机构ID
	private String proportionCode;      //生产配比编码
	private int targPropId;             //目标配比ID
	private int gradId;                 //级配比ID
	private int productId;              //产品Id
	private Double no1SetValue;         //一号仓
	private Double no2SetValue;         //二号仓
	private Double no3SetValue;         //三号仓
	private Double no4SetValue;         //四号仓
	private Double no5SetValue;         //五号仓
	private Double no6SetValue;         //六号仓
	private Double no7SetValue;         //七号仓
	private Double no8SetValue;         //八号仓
	private Double admixture1SetValue;  //一号外掺剂
	private Double admixture2SetValue;  //二号外掺剂
	private Double hotPowderSetValue;   //热粉仓
	private Double coldPowderSetValue;  //冷粉仓
	private Double coldPowder2SetValue; //冷粉仓2
	private Double powder3SetValue;     //粉料3
	private Double asphaltSetValue;     //油石比
	private String remarks;             //备注
	private String operator;            //创建人
	private String createDate;          //创建日期
	private String modifier;            //修改人
	private String modifyDate;          //修改日期
	private int validFlag;              //有效标识
	private int upload;                 //上传标识
	
	private String orgName;             //机构名称
	private String materialName;        //产品名称
	private String materialModel;       //产品型号
	private String mproportionCode;     //目标配合比配比编码
	private String materNameAndModel;   //材料信息
	private String gradeCode;           //级配编码
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
	public String getProportionCode() {
		return proportionCode;
	}
	public void setProportionCode(String proportionCode) {
		this.proportionCode = proportionCode;
	}
	public int getTargPropId() {
		return targPropId;
	}
	public void setTargPropId(int targPropId) {
		this.targPropId = targPropId;
	}
	public int getGradId() {
		return gradId;
	}
	public void setGradId(int gradId) {
		this.gradId = gradId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public Double getNo1SetValue() {
		return no1SetValue;
	}
	public void setNo1SetValue(Double no1SetValue) {
		this.no1SetValue = no1SetValue;
	}
	public Double getNo2SetValue() {
		return no2SetValue;
	}
	public void setNo2SetValue(Double no2SetValue) {
		this.no2SetValue = no2SetValue;
	}
	public Double getNo3SetValue() {
		return no3SetValue;
	}
	public void setNo3SetValue(Double no3SetValue) {
		this.no3SetValue = no3SetValue;
	}
	public Double getNo4SetValue() {
		return no4SetValue;
	}
	public void setNo4SetValue(Double no4SetValue) {
		this.no4SetValue = no4SetValue;
	}
	public Double getNo5SetValue() {
		return no5SetValue;
	}
	public void setNo5SetValue(Double no5SetValue) {
		this.no5SetValue = no5SetValue;
	}
	public Double getNo6SetValue() {
		return no6SetValue;
	}
	public void setNo6SetValue(Double no6SetValue) {
		this.no6SetValue = no6SetValue;
	}
	public Double getNo7SetValue() {
		return no7SetValue;
	}
	public void setNo7SetValue(Double no7SetValue) {
		this.no7SetValue = no7SetValue;
	}
	public Double getNo8SetValue() {
		return no8SetValue;
	}
	public void setNo8SetValue(Double no8SetValue) {
		this.no8SetValue = no8SetValue;
	}
	public Double getAdmixture1SetValue() {
		return admixture1SetValue;
	}
	public void setAdmixture1SetValue(Double admixture1SetValue) {
		this.admixture1SetValue = admixture1SetValue;
	}
	public Double getAdmixture2SetValue() {
		return admixture2SetValue;
	}
	public void setAdmixture2SetValue(Double admixture2SetValue) {
		this.admixture2SetValue = admixture2SetValue;
	}
	public Double getHotPowderSetValue() {
		return hotPowderSetValue;
	}
	public void setHotPowderSetValue(Double hotPowderSetValue) {
		this.hotPowderSetValue = hotPowderSetValue;
	}
	public Double getColdPowderSetValue() {
		return coldPowderSetValue;
	}
	public void setColdPowderSetValue(Double coldPowderSetValue) {
		this.coldPowderSetValue = coldPowderSetValue;
	}
	public Double getColdPowder2SetValue() {
		return coldPowder2SetValue;
	}
	public void setColdPowder2SetValue(Double coldPowder2SetValue) {
		this.coldPowder2SetValue = coldPowder2SetValue;
	}
	public Double getPowder3SetValue() {
		return powder3SetValue;
	}
	public void setPowder3SetValue(Double powder3SetValue) {
		this.powder3SetValue = powder3SetValue;
	}
	public Double getAsphaltSetValue() {
		return asphaltSetValue;
	}
	public void setAsphaltSetValue(Double asphaltSetValue) {
		this.asphaltSetValue = asphaltSetValue;
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
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
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
	public String getMproportionCode() {
		return mproportionCode;
	}
	public void setMproportionCode(String mproportionCode) {
		this.mproportionCode = mproportionCode;
	}
	public String getMaterNameAndModel() {
		return materNameAndModel;
	}
	public void setMaterNameAndModel(String materNameAndModel) {
		this.materNameAndModel = materNameAndModel;
	}
	public String getGradeCode() {
		return gradeCode;
	}
	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}
	
}
