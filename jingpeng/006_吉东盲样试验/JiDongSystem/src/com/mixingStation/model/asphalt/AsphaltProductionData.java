package com.mixingStation.model.asphalt;

import java.util.Date;

/**
 * 
 * @Title 生产数据
 * @author Administrator
 * @date 2019年1月29日
 */
public class AsphaltProductionData {
	/**
	 * 生产数据
	 */
	private int id;                      //自增长ID
	private int orgId;                   //组织机构ID
	private int productId;            	 //产品Id	
	private int equId;                   //拌和机Id
	private String prodPlanNo;           //生产计划
	private char collectiType;			 //采集方式: 0 软件采集 1 硬件采集
	private String collectTime;             //拌和机电脑本地时间 ,配合比采用根据该时间划分
	private Double no1MeterValue;          //一号仓测量值
	private Double no2MeterValue;          //二号仓测量值
	private Double no3MeterValue;          //三号仓测量值
	private Double no4MeterValue;          //四号仓测量值
	private Double no5MeterValue;          //五号仓测量值
	private Double no6MeterValue;          //六号仓测量值
	private Double no7MeterValue;          //七号仓测量值
	private Double no8MeterValue;          //八号仓测量值
	private Double hotPMeterValue;         //热粉仓测量值
	private Double coldPMeterValue;        //冷粉仓测量值
	private Double coldP2MeterValue;       //冷粉仓2测量值
	private Double totalPMeterValue;       //总粉仓2测量值
	private Double asphaltMeter;           //沥青测量值
	private Double temperatureMeter;       //温度测量值
	private Double admixture1Meter;        //一号外参剂仓测量值
	private Double admixture2Meter;        //二号外参剂仓测量值
	private Double totalWeight;            //拌和总量
	private String constructionUnit;       // 施工单位
	private String projPos;                // 工程部位/用途
	private String equipmentName;          // 拌和机名称
	private String productInfo;            // 物料名称+物料型号
	private String proportionCode;         // 生产配比编码
	private String gradeCode;              // 级配编码
	private int gradId; 					//级配id
	private String orgName;                //机构名称
	private int serialNumber;
	private String analysisResult;         //分析结果（返回值不是1 都未不合格）
	private int prodId;                    // 生产配比ID
	private int targPropId;               //目标配比ID
	private int validFlag;                //有效标识
	private String operator;             //创建人
	private String createDate;           //创建日期
	private String modifier;             //修改人
	private String modifyDate;           //修改日期
	
	//沥青生产数据分析表
	private Double no1;          //一号仓
	private Double no2;          //二号仓
	private Double no3;          //三号仓
	private Double no4;          //四号仓
	private Double no5;          //五号仓
	private Double no6;          //六号仓
	private Double no7;          //七号仓
	private Double no8;          //八号仓
	private Double hotPowder;    //热粉仓
	private Double coldPowder;   //冷粉仓
	private Double coldPowder2;  //冷粉仓2
	private Double totalPowder;  //总粉仓
	private Double asphalt;      //冷粉仓2
	private Double temperature;  //温度
	private Double admixture1;   //外掺剂仓1
	private Double admixture2;   //外掺剂仓2
	public int getId() {
		return id;
	}
	public int getGradId() {
		return gradId;
	}
	public void setGradId(int gradId) {
		this.gradId = gradId;
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
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getEquId() {
		return equId;
	}
	public void setEquId(int equId) {
		this.equId = equId;
	}
	public String getCollectTime() {
		return collectTime;
	}
	public void setCollectTime(String collectTime) {
		this.collectTime = collectTime;
	}
	public String getProdPlanNo() {
		return prodPlanNo;
	}
	public void setProdPlanNo(String prodPlanNo) {
		this.prodPlanNo = prodPlanNo;
	}
	public char getCollectiType() {
		return collectiType;
	}
	public void setCollectiType(char collectiType) {
		this.collectiType = collectiType;
	}
	
	public Double getNo1MeterValue() {
		return no1MeterValue;
	}
	public void setNo1MeterValue(Double no1MeterValue) {
		this.no1MeterValue = no1MeterValue;
	}
	public Double getNo2MeterValue() {
		return no2MeterValue;
	}
	public void setNo2MeterValue(Double no2MeterValue) {
		this.no2MeterValue = no2MeterValue;
	}
	public Double getNo3MeterValue() {
		return no3MeterValue;
	}
	public void setNo3MeterValue(Double no3MeterValue) {
		this.no3MeterValue = no3MeterValue;
	}
	public Double getNo4MeterValue() {
		return no4MeterValue;
	}
	public void setNo4MeterValue(Double no4MeterValue) {
		this.no4MeterValue = no4MeterValue;
	}
	public Double getNo5MeterValue() {
		return no5MeterValue;
	}
	public void setNo5MeterValue(Double no5MeterValue) {
		this.no5MeterValue = no5MeterValue;
	}
	public Double getNo6MeterValue() {
		return no6MeterValue;
	}
	public void setNo6MeterValue(Double no6MeterValue) {
		this.no6MeterValue = no6MeterValue;
	}
	public Double getNo7MeterValue() {
		return no7MeterValue;
	}
	public void setNo7MeterValue(Double no7MeterValue) {
		this.no7MeterValue = no7MeterValue;
	}
	public Double getNo8MeterValue() {
		return no8MeterValue;
	}
	public void setNo8MeterValue(Double no8MeterValue) {
		this.no8MeterValue = no8MeterValue;
	}
	public Double getHotPMeterValue() {
		return hotPMeterValue;
	}
	public void setHotPMeterValue(Double hotPMeterValue) {
		this.hotPMeterValue = hotPMeterValue;
	}
	public Double getColdPMeterValue() {
		return coldPMeterValue;
	}
	public void setColdPMeterValue(Double coldPMeterValue) {
		this.coldPMeterValue = coldPMeterValue;
	}
	public Double getColdP2MeterValue() {
		return coldP2MeterValue;
	}
	public void setColdP2MeterValue(Double coldP2MeterValue) {
		this.coldP2MeterValue = coldP2MeterValue;
	}
	public Double getTotalPMeterValue() {
		return totalPMeterValue;
	}
	public void setTotalPMeterValue(Double totalPMeterValue) {
		this.totalPMeterValue = totalPMeterValue;
	}
	public Double getAsphaltMeter() {
		return asphaltMeter;
	}
	public void setAsphaltMeter(Double asphaltMeter) {
		this.asphaltMeter = asphaltMeter;
	}
	public Double getTemperatureMeter() {
		return temperatureMeter;
	}
	public void setTemperatureMeter(Double temperatureMeter) {
		this.temperatureMeter = temperatureMeter;
	}
	public Double getAdmixture1Meter() {
		return admixture1Meter;
	}
	public void setAdmixture1Meter(Double admixture1Meter) {
		this.admixture1Meter = admixture1Meter;
	}
	public Double getAdmixture2Meter() {
		return admixture2Meter;
	}
	public void setAdmixture2Meter(Double admixture2Meter) {
		this.admixture2Meter = admixture2Meter;
	}
	public Double getTotalWeight() {
		return totalWeight;
	}
	public void setTotalWeight(Double totalWeight) {
		this.totalWeight = totalWeight;
	}
	public String getConstructionUnit() {
		return constructionUnit;
	}
	public void setConstructionUnit(String constructionUnit) {
		this.constructionUnit = constructionUnit;
	}
	public String getProjPos() {
		return projPos;
	}
	public void setProjPos(String projPos) {
		this.projPos = projPos;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public String getProductInfo() {
		return productInfo;
	}
	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}
	public String getProportionCode() {
		return proportionCode;
	}
	public void setProportionCode(String proportionCode) {
		this.proportionCode = proportionCode;
	}
	public String getGradeCode() {
		return gradeCode;
	}
	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public int getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getAnalysisResult() {
		return analysisResult;
	}
	public void setAnalysisResult(String analysisResult) {
		this.analysisResult = analysisResult;
	}
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public int getTargPropId() {
		return targPropId;
	}
	public void setTargPropId(int targPropId) {
		this.targPropId = targPropId;
	}
	public int getValidFlag() {
		return validFlag;
	}
	public void setValidFlag(int validFlag) {
		this.validFlag = validFlag;
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
	public Double getNo1() {
		return no1;
	}
	public void setNo1(Double no1) {
		this.no1 = no1;
	}
	public Double getNo2() {
		return no2;
	}
	public void setNo2(Double no2) {
		this.no2 = no2;
	}
	public Double getNo3() {
		return no3;
	}
	public void setNo3(Double no3) {
		this.no3 = no3;
	}
	public Double getNo4() {
		return no4;
	}
	public void setNo4(Double no4) {
		this.no4 = no4;
	}
	public Double getNo5() {
		return no5;
	}
	public void setNo5(Double no5) {
		this.no5 = no5;
	}
	public Double getNo6() {
		return no6;
	}
	public void setNo6(Double no6) {
		this.no6 = no6;
	}
	public Double getNo7() {
		return no7;
	}
	public void setNo7(Double no7) {
		this.no7 = no7;
	}
	public Double getNo8() {
		return no8;
	}
	public void setNo8(Double no8) {
		this.no8 = no8;
	}
	public Double getHotPowder() {
		return hotPowder;
	}
	public void setHotPowder(Double hotPowder) {
		this.hotPowder = hotPowder;
	}
	public Double getColdPowder() {
		return coldPowder;
	}
	public void setColdPowder(Double coldPowder) {
		this.coldPowder = coldPowder;
	}
	public Double getColdPowder2() {
		return coldPowder2;
	}
	public void setColdPowder2(Double coldPowder2) {
		this.coldPowder2 = coldPowder2;
	}
	public Double getTotalPowder() {
		return totalPowder;
	}
	public void setTotalPowder(Double totalPowder) {
		this.totalPowder = totalPowder;
	}
	public Double getAsphalt() {
		return asphalt;
	}
	public void setAsphalt(Double asphalt) {
		this.asphalt = asphalt;
	}
	public Double getTemperature() {
		return temperature;
	}
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	public Double getAdmixture1() {
		return admixture1;
	}
	public void setAdmixture1(Double admixture1) {
		this.admixture1 = admixture1;
	}
	public Double getAdmixture2() {
		return admixture2;
	}
	public void setAdmixture2(Double admixture2) {
		this.admixture2 = admixture2;
	}
}
