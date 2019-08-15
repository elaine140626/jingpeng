package com.curing.projectMetering.model;

public class ProjectTenderingSummaryEntity {
	private Integer Id; // id
	private Integer ProjectId; // 项目id
	private Integer ProjectInfo; // 工程信息id
	private String ProjectNumber; // 项目编号
	private String ProjectName; // 项目名称
	private String CityName; // 所属市
	private String LineName; // 路线名称
	private String Engineering; // 工程项目
	private String EngineeringName; // 工程项目名称
	private String AgoName; // 原路线名称
	private String TenderingNature; // 招标性质（数据字典）
	private String TenderingNatureName; // 招标性质名称
	private String TenderingCompany; // 招标单位
	private String TenderingAgent; // 招标代理
	private String TenderingRelease; // 招标公告发布平台
	private String TenderingReleaseAddress; // 招标公告发布平台地址
	private String WinningBidCompany; // 中标单位公示
	private String WinningBidCompanyAddress; // 中标单位公示地址
	private Integer ContractSign; // 合同签订
	private String UploadPicture; // 上传图片
	private String Remarks; // 备注
	private Integer IsDel;
	private String Creater;
	private String CreaterDate;
	private String Reviser;
	private String ReviserDate;

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

	public Integer getProjectInfo() {
		return ProjectInfo;
	}

	public void setProjectInfo(Integer projectInfo) {
		ProjectInfo = projectInfo;
	}

	public String getProjectNumber() {
		return ProjectNumber;
	}

	public void setProjectNumber(String projectNumber) {
		ProjectNumber = projectNumber;
	}

	public String getProjectName() {
		return ProjectName;
	}

	public void setProjectName(String projectName) {
		ProjectName = projectName;
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

}
