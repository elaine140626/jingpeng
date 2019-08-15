package com.curing.projectInfo.model;

public class ProjectInfoSummaryEntity {
	private String Id;
	private String ProjectId; // 项目id
	private String ProjectNumber; // 项目编号
	private String ProjectName; // 项目名称
	private String CityName; // 所属市
	private String LineName; // 线路名称
	private String Engineering; // 工程项目（数据字典）
	private String EngineeringName; // 工程项目名
	private String AgoName; // 原线路名称
	private String PlanBatch; // 计划批次（数据字典）
	private String PlanBatchName; // 计划批次名
	private String Location; // 工程所在地
	private String PileNumber; // 起讫点及桩号
	private String BuildNature; // 建设性质（数据字典）
	private String BuildNatureName; // 建设性质名
	private String TechnicalGrade; // 技术等级（数据字典）
	private String TechnicalGradeName; // 技术等级名
	private String Width; // 宽度
	private String AdministrationGrade; // 行政等级（数据字典）
	private String AdministrationGradeName;// 行政等级名
	private String PlanTime; // 计划年份
	private String ProjectSource; // 项目来源（数据字典）
	private String ProjectSourceName; // 项目来源名
	private String ProjectType; // 项目分类（数据字典checkbox）
	private Integer QualityTarget; // 质量目标
	private String ApprovalsType; // 批文类型（数据字典）
	private String ApprovalsTypeName; // 批文类型名
	private String UploadPicture; // 上传图片
	private String Remarks; // 备注
	private String TenderingNature; // 招标性质（数据字典）
	private String TenderingNatureName; // 招标性质名称
	private String TenderingCompany; // 招标单位
	private String TenderingAgent; // 招标代理
	private String TenderingRelease; // 招标公告发布平台
	private String TenderingReleaseAddress; // 招标公告发布平台地址
	private String WinningBidCompany; // 中标单位公示
	private String WinningBidCompanyAddress; // 中标单位公示地址
	private Integer ContractSign; // 合同签订
	private String UploadPicture2; // 上传图片
	private String Remarks2; // 备注
	private Integer IsDel;
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

	public String getProjectId() {
		return ProjectId;
	}

	public void setProjectId(String projectId) {
		ProjectId = projectId;
	}

	public String getProjectName() {
		return ProjectName;
	}

	public void setProjectName(String projectName) {
		ProjectName = projectName;
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

	public String getBuildNatureName() {
		return BuildNatureName;
	}

	public void setBuildNatureName(String buildNatureName) {
		BuildNatureName = buildNatureName;
	}

	public String getTechnicalGrade() {
		return TechnicalGrade;
	}

	public void setTechnicalGrade(String technicalGrade) {
		TechnicalGrade = technicalGrade;
	}

	public String getTechnicalGradeName() {
		return TechnicalGradeName;
	}

	public void setTechnicalGradeName(String technicalGradeName) {
		TechnicalGradeName = technicalGradeName;
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

	public String getProjectSourceName() {
		return ProjectSourceName;
	}

	public void setProjectSourceName(String projectSourceName) {
		ProjectSourceName = projectSourceName;
	}

	public String getProjectType() {
		return ProjectType;
	}

	public void setProjectType(String projectType) {
		ProjectType = projectType;
	}

	public Integer getQualityTarget() {
		return QualityTarget;
	}

	public void setQualityTarget(Integer qualityTarget) {
		QualityTarget = qualityTarget;
	}

	public String getApprovalsType() {
		return ApprovalsType;
	}

	public void setApprovalsType(String approvalsType) {
		ApprovalsType = approvalsType;
	}

	public String getApprovalsTypeName() {
		return ApprovalsTypeName;
	}

	public void setApprovalsTypeName(String approvalsTypeName) {
		ApprovalsTypeName = approvalsTypeName;
	}

	public String getUploadPicture() {
		return UploadPicture;
	}

	public void setUploadPicture(String uploadPicture) {
		UploadPicture = uploadPicture;
	}

	public String getRemarks() {
		return Remarks;
	}

	public void setRemarks(String remarks) {
		Remarks = remarks;
	}

	public Integer getIsDel() {
		return IsDel;
	}

	public void setIsDel(Integer isDel) {
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

	public String getTenderingNature() {
		return TenderingNature;
	}

	public void setTenderingNature(String tenderingNature) {
		TenderingNature = tenderingNature;
	}

	public String getTenderingNatureName() {
		return TenderingNatureName;
	}

	public void setTenderingNatureName(String tenderingNatureName) {
		TenderingNatureName = tenderingNatureName;
	}

	public String getTenderingCompany() {
		return TenderingCompany;
	}

	public void setTenderingCompany(String tenderingCompany) {
		TenderingCompany = tenderingCompany;
	}

	public String getTenderingAgent() {
		return TenderingAgent;
	}

	public void setTenderingAgent(String tenderingAgent) {
		TenderingAgent = tenderingAgent;
	}

	public String getTenderingRelease() {
		return TenderingRelease;
	}

	public void setTenderingRelease(String tenderingRelease) {
		TenderingRelease = tenderingRelease;
	}

	public String getTenderingReleaseAddress() {
		return TenderingReleaseAddress;
	}

	public void setTenderingReleaseAddress(String tenderingReleaseAddress) {
		TenderingReleaseAddress = tenderingReleaseAddress;
	}

	public String getWinningBidCompany() {
		return WinningBidCompany;
	}

	public void setWinningBidCompany(String winningBidCompany) {
		WinningBidCompany = winningBidCompany;
	}

	public String getWinningBidCompanyAddress() {
		return WinningBidCompanyAddress;
	}

	public void setWinningBidCompanyAddress(String winningBidCompanyAddress) {
		WinningBidCompanyAddress = winningBidCompanyAddress;
	}

	public Integer getContractSign() {
		return ContractSign;
	}

	public void setContractSign(Integer contractSign) {
		ContractSign = contractSign;
	}

	public String getUploadPicture2() {
		return UploadPicture2;
	}

	public void setUploadPicture2(String uploadPicture2) {
		UploadPicture2 = uploadPicture2;
	}

	public String getRemarks2() {
		return Remarks2;
	}

	public void setRemarks2(String remarks2) {
		Remarks2 = remarks2;
	}
}
