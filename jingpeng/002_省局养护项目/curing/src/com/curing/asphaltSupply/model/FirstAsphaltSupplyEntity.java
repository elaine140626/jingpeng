package com.curing.asphaltSupply.model;

public class FirstAsphaltSupplyEntity {
	private String Id; // 主键id
	private String ProjectInfoId; // 工程信息id
	private String ProjectId; // 项目id
	private String LineName; // 路线名称
	private String Engineering; // 工程项目
	private String EngineeringName; // 工程项目名称
	private String Location; // 工程所在地
	private String PileNumber; // 起讫点及桩号
	private String BuildNatureName; // 建设性质名称
	private String EngineeringUnitName; // 工程量单位名称
	private Double EngineeringAmount; // 工程量计划数量
	private Integer EngineeringCount; // 工程量（/座）
	private Double InvestmentTotal; // 投资额合计
	private String CityName; // 所属市
	private String County; // 所属县区
	private String PlanBatchName; // 计划批次名称
	private String ProjectType; // 项目分类
	private String StandardStructure; // 技术标准与结构
	private String FinishTime; // 招投标完成时间
	private String ProjectNature; // 项目性质
	private String ProjectNatureName; // 项目性质名称
	private String CapitalSource; // 资金来源
	private String CapitalSourceName; // 资金来源名称
	private Double Total; // 总量
	private String ModifiedType; // 改性90#类别（数据字典）
	private String ModifiedTypeName; // 改性90#类别名称
	private Double ModifiedNumber; // 改性90#平数
	private Double ModifiedThickness; // 改性90#厚度
	private Double ModifiedProportion; // 改性90#配合比沥青用量（%）
	private Double ModifiedSubtotal; // 改性90#小计
	private String OtherType; // 其他90#类别（数据字典）
	private String OtherTypeName; // 其他90#类别名称
	private Double OtherNumber; // 其他90#平数
	private Double OtherThickness; // 其他90#厚度
	private Double OtherProportion; // 其他90#配合比沥青用量（%）
	private Double OtherSubtotal; // 其他90#小计
	private String EmulsifyType; // 乳化沥青类别（数据字典）
	private String EmulsifyTypeName; // 乳化沥青类别名称
	private Double EmulsifyNumber; // 乳化沥青平数
	private Double EmulsifyThickness; // 乳化沥青厚度
	private Double EmulsifySubtotal; // 乳化沥青小计
	private Double Asphalt140; // 140#沥青
	private String Remarks; // 备注
	private int IsDel;
	private String Creater;
	private String CreaterDate;
	private String Reviser;
	private String ReviserDate;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getProjectInfoId() {
		return ProjectInfoId;
	}

	public void setProjectInfoId(String projectInfoId) {
		ProjectInfoId = projectInfoId;
	}

	public String getProjectId() {
		return ProjectId;
	}

	public void setProjectId(String projectId) {
		ProjectId = projectId;
	}

	public String getLineName() {
		return LineName;
	}

	public void setLineName(String lineName) {
		LineName = lineName;
	}

	public String getEngineering() {
		return Engineering;
	}

	public void setEngineering(String engineering) {
		Engineering = engineering;
	}

	public String getEngineeringName() {
		return EngineeringName;
	}

	public void setEngineeringName(String engineeringName) {
		EngineeringName = engineeringName;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public String getPileNumber() {
		return PileNumber;
	}

	public void setPileNumber(String pileNumber) {
		PileNumber = pileNumber;
	}

	public String getBuildNatureName() {
		return BuildNatureName;
	}

	public void setBuildNatureName(String buildNatureName) {
		BuildNatureName = buildNatureName;
	}

	public String getEngineeringUnitName() {
		return EngineeringUnitName;
	}

	public void setEngineeringUnitName(String engineeringUnitName) {
		EngineeringUnitName = engineeringUnitName;
	}

	public Double getEngineeringAmount() {
		return EngineeringAmount;
	}

	public void setEngineeringAmount(Double engineeringAmount) {
		EngineeringAmount = engineeringAmount;
	}

	public Integer getEngineeringCount() {
		return EngineeringCount;
	}

	public void setEngineeringCount(Integer engineeringCount) {
		EngineeringCount = engineeringCount;
	}

	public Double getInvestmentTotal() {
		return InvestmentTotal;
	}

	public void setInvestmentTotal(Double investmentTotal) {
		InvestmentTotal = investmentTotal;
	}

	public String getCityName() {
		return CityName;
	}

	public void setCityName(String cityName) {
		CityName = cityName;
	}

	public String getCounty() {
		return County;
	}

	public void setCounty(String county) {
		County = county;
	}

	public String getPlanBatchName() {
		return PlanBatchName;
	}

	public void setPlanBatchName(String planBatchName) {
		PlanBatchName = planBatchName;
	}

	public String getProjectType() {
		return ProjectType;
	}

	public void setProjectType(String projectType) {
		ProjectType = projectType;
	}

	public String getStandardStructure() {
		return StandardStructure;
	}

	public void setStandardStructure(String standardStructure) {
		StandardStructure = standardStructure;
	}

	public String getFinishTime() {
		return FinishTime;
	}

	public void setFinishTime(String finishTime) {
		FinishTime = finishTime;
	}

	public String getProjectNature() {
		return ProjectNature;
	}

	public void setProjectNature(String projectNature) {
		ProjectNature = projectNature;
	}

	public String getProjectNatureName() {
		return ProjectNatureName;
	}

	public void setProjectNatureName(String projectNatureName) {
		ProjectNatureName = projectNatureName;
	}

	public String getCapitalSource() {
		return CapitalSource;
	}

	public void setCapitalSource(String capitalSource) {
		CapitalSource = capitalSource;
	}

	public String getCapitalSourceName() {
		return CapitalSourceName;
	}

	public void setCapitalSourceName(String capitalSourceName) {
		CapitalSourceName = capitalSourceName;
	}

	public Double getTotal() {
		return Total;
	}

	public void setTotal(Double total) {
		Total = total;
	}

	public String getModifiedType() {
		return ModifiedType;
	}

	public void setModifiedType(String modifiedType) {
		ModifiedType = modifiedType;
	}

	public String getModifiedTypeName() {
		return ModifiedTypeName;
	}

	public void setModifiedTypeName(String modifiedTypeName) {
		ModifiedTypeName = modifiedTypeName;
	}

	public Double getModifiedNumber() {
		return ModifiedNumber;
	}

	public void setModifiedNumber(Double modifiedNumber) {
		ModifiedNumber = modifiedNumber;
	}

	public Double getModifiedThickness() {
		return ModifiedThickness;
	}

	public void setModifiedThickness(Double modifiedThickness) {
		ModifiedThickness = modifiedThickness;
	}

	public Double getModifiedProportion() {
		return ModifiedProportion;
	}

	public void setModifiedProportion(Double modifiedProportion) {
		ModifiedProportion = modifiedProportion;
	}

	public Double getModifiedSubtotal() {
		return ModifiedSubtotal;
	}

	public void setModifiedSubtotal(Double modifiedSubtotal) {
		ModifiedSubtotal = modifiedSubtotal;
	}

	public String getOtherType() {
		return OtherType;
	}

	public void setOtherType(String otherType) {
		OtherType = otherType;
	}

	public String getOtherTypeName() {
		return OtherTypeName;
	}

	public void setOtherTypeName(String otherTypeName) {
		OtherTypeName = otherTypeName;
	}

	public Double getOtherNumber() {
		return OtherNumber;
	}

	public void setOtherNumber(Double otherNumber) {
		OtherNumber = otherNumber;
	}

	public Double getOtherThickness() {
		return OtherThickness;
	}

	public void setOtherThickness(Double otherThickness) {
		OtherThickness = otherThickness;
	}

	public Double getOtherProportion() {
		return OtherProportion;
	}

	public void setOtherProportion(Double otherProportion) {
		OtherProportion = otherProportion;
	}

	public Double getOtherSubtotal() {
		return OtherSubtotal;
	}

	public void setOtherSubtotal(Double otherSubtotal) {
		OtherSubtotal = otherSubtotal;
	}

	public String getEmulsifyType() {
		return EmulsifyType;
	}

	public void setEmulsifyType(String emulsifyType) {
		EmulsifyType = emulsifyType;
	}

	public String getEmulsifyTypeName() {
		return EmulsifyTypeName;
	}

	public void setEmulsifyTypeName(String emulsifyTypeName) {
		EmulsifyTypeName = emulsifyTypeName;
	}

	public Double getEmulsifyNumber() {
		return EmulsifyNumber;
	}

	public void setEmulsifyNumber(Double emulsifyNumber) {
		EmulsifyNumber = emulsifyNumber;
	}

	public Double getEmulsifyThickness() {
		return EmulsifyThickness;
	}

	public void setEmulsifyThickness(Double emulsifyThickness) {
		EmulsifyThickness = emulsifyThickness;
	}

	public Double getEmulsifySubtotal() {
		return EmulsifySubtotal;
	}

	public void setEmulsifySubtotal(Double emulsifySubtotal) {
		EmulsifySubtotal = emulsifySubtotal;
	}

	public Double getAsphalt140() {
		return Asphalt140;
	}

	public void setAsphalt140(Double asphalt140) {
		Asphalt140 = asphalt140;
	}

	public String getRemarks() {
		return Remarks;
	}

	public void setRemarks(String remarks) {
		Remarks = remarks;
	}

	public int getIsDel() {
		return IsDel;
	}

	public void setIsDel(int isDel) {
		IsDel = isDel;
	}

	public String getCreater() {
		return Creater;
	}

	public void setCreater(String creater) {
		Creater = creater;
	}

	public String getCreaterDate() {
		return CreaterDate;
	}

	public void setCreaterDate(String createrDate) {
		CreaterDate = createrDate;
	}

	public String getReviser() {
		return Reviser;
	}

	public void setReviser(String reviser) {
		Reviser = reviser;
	}

	public String getReviserDate() {
		return ReviserDate;
	}

	public void setReviserDate(String reviserDate) {
		ReviserDate = reviserDate;
	}

}
