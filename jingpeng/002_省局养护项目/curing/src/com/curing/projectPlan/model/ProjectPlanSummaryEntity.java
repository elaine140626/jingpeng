package com.curing.projectPlan.model;

public class ProjectPlanSummaryEntity {
	private Integer Id; // id
	private Integer ProjectId; // 项目id
	private Integer ProjectInfo; // 工程信息id
	private String ProjectNumber; // 项目编号
	private String CityName; // 所属市
	private String LineName; // 路线名称
	private String Engineering; // 工程项目
	private String EngineeringName; // 工程项目名称
	private String AgoName; // 原路线名称
	private String PlanBatch; // 计划批次
	private String PlanBatchName; // 计划批次名称
	private String Location; // 工程所在地
	private String PileNumber; // 起讫点及桩号
	private String BuildNature; // 建设性质
	private String BuildNatureName; // 建设性质名称
	private String TechnicalGrade; // 技术指标
	private String TechnicalGradeName; // 技术指标名称
	private String Width; // 宽度
	private String AdministrationGrade; // 行政等级
	private String AdministrationGradeName; // 行政等级名称
	private String PlanTime; // 计划年份
	private String ProjectSource; // 项目来源
	private String ProjectSourceName; // 项目来源名称
	private String ProjectType; // 项目分类
	private String ProjectTypeName; // 项目分类名称
	private String QualityTarget; // 质量目标
	private String EngineeringUnit; // 工程量单位（数据字典）
	private String EngineeringUnitName; // 工程量单位名称
	private Double EngineeringAmount; // 工程量计划数量
	private Integer EngineeringCount; // 工程量（/座）
	private Double InvestmentTotal; // 投资额合计
	private Double InvestmentProvince; // 投资额省补总投资
	private Double InvestmentMinistry; // 投资额部投资
	private String DesignUnit; // 设计单位
	private String ConstructionUnit; // 施工单位
	private String SupervisorUnit; // 监理单位
	private String StartDate; // 开工日期
	private String EndDate; // 竣工日期
	private String Remarks; // 备注
	private int IsDel;
	private String Creater;
	private String CreaterDate;
	private String Reviser;
	private String ReviserDate;
	
	public Integer getProjectInfo() {
		return ProjectInfo;
	}
	public void setProjectInfo(Integer projectInfo) {
		ProjectInfo = projectInfo;
	}
	public String getProjectSourceName() {
		return ProjectSourceName;
	}
	public void setProjectSourceName(String projectSourceName) {
		ProjectSourceName = projectSourceName;
	}
	public String getProjectTypeName() {
		return ProjectTypeName;
	}
	public void setProjectTypeName(String projectTypeName) {
		ProjectTypeName = projectTypeName;
	}
	public String getBuildNatureName() {
		return BuildNatureName;
	}
	public void setBuildNatureName(String buildNatureName) {
		BuildNatureName = buildNatureName;
	}
	public String getTechnicalGradeName() {
		return TechnicalGradeName;
	}
	public void setTechnicalGradeName(String technicalGradeName) {
		TechnicalGradeName = technicalGradeName;
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public Integer getProjectId() {
		return ProjectId;
	}
	public void setProjectId(Integer projectId) {
		ProjectId = projectId;
	}
	public String getProjectNumber() {
		return ProjectNumber;
	}
	public void setProjectNumber(String projectNumber) {
		ProjectNumber = projectNumber;
	}
	public String getCityName() {
		return CityName;
	}
	public void setCityName(String cityName) {
		CityName = cityName;
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
	public String getAgoName() {
		return AgoName;
	}
	public void setAgoName(String agoName) {
		AgoName = agoName;
	}
	public String getPlanBatch() {
		return PlanBatch;
	}
	public void setPlanBatch(String planBatch) {
		PlanBatch = planBatch;
	}
	public String getPlanBatchName() {
		return PlanBatchName;
	}
	public void setPlanBatchName(String planBatchName) {
		PlanBatchName = planBatchName;
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
	public String getBuildNature() {
		return BuildNature;
	}
	public void setBuildNature(String buildNature) {
		BuildNature = buildNature;
	}
	public String getTechnicalGrade() {
		return TechnicalGrade;
	}
	public void setTechnicalGrade(String technicalGrade) {
		TechnicalGrade = technicalGrade;
	}
	public String getWidth() {
		return Width;
	}
	public void setWidth(String width) {
		Width = width;
	}
	public String getAdministrationGrade() {
		return AdministrationGrade;
	}
	public void setAdministrationGrade(String administrationGrade) {
		AdministrationGrade = administrationGrade;
	}
	public String getAdministrationGradeName() {
		return AdministrationGradeName;
	}
	public void setAdministrationGradeName(String administrationGradeName) {
		AdministrationGradeName = administrationGradeName;
	}
	public String getPlanTime() {
		return PlanTime;
	}
	public void setPlanTime(String planTime) {
		PlanTime = planTime;
	}
	public String getProjectSource() {
		return ProjectSource;
	}
	public void setProjectSource(String projectSource) {
		ProjectSource = projectSource;
	}
	public String getProjectType() {
		return ProjectType;
	}
	public void setProjectType(String projectType) {
		ProjectType = projectType;
	}
	public String getQualityTarget() {
		return QualityTarget;
	}
	public void setQualityTarget(String qualityTarget) {
		QualityTarget = qualityTarget;
	}
	public String getEngineeringUnit() {
		return EngineeringUnit;
	}
	public void setEngineeringUnit(String engineeringUnit) {
		EngineeringUnit = engineeringUnit;
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
	public Double getInvestmentProvince() {
		return InvestmentProvince;
	}
	public void setInvestmentProvince(Double investmentProvince) {
		InvestmentProvince = investmentProvince;
	}
	public Double getInvestmentMinistry() {
		return InvestmentMinistry;
	}
	public void setInvestmentMinistry(Double investmentMinistry) {
		InvestmentMinistry = investmentMinistry;
	}
	public String getDesignUnit() {
		return DesignUnit;
	}
	public void setDesignUnit(String designUnit) {
		DesignUnit = designUnit;
	}
	public String getConstructionUnit() {
		return ConstructionUnit;
	}
	public void setConstructionUnit(String constructionUnit) {
		ConstructionUnit = constructionUnit;
	}
	public String getSupervisorUnit() {
		return SupervisorUnit;
	}
	public void setSupervisorUnit(String supervisorUnit) {
		SupervisorUnit = supervisorUnit;
	}
	public String getStartDate() {
		return StartDate;
	}
	public void setStartDate(String startDate) {
		StartDate = startDate;
	}
	public String getEndDate() {
		return EndDate;
	}
	public void setEndDate(String endDate) {
		EndDate = endDate;
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
