package com.curing.projectSchedule.model;

public class SupervisionApplicationEntity {
	private String Id;
	private String ProjectId; // 项目id
	private String ProjectNumber; // 项目编号
	private String ProjectName; // 项目名称
	private String SupervisorCompany; // 监理单位
	private String SupervisorPlan; // 监理计划（文档）
	private String SupervisorDetailed; // 监理细则（文档）
	private Integer Reply; // 监督申请批复
	private String UploadPicture; // 上传图片
	private String Remarks; // 备注
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

	public String getSupervisorCompany() {
		return SupervisorCompany;
	}

	public void setSupervisorCompany(String supervisorCompany) {
		SupervisorCompany = supervisorCompany;
	}

	public String getSupervisorPlan() {
		return SupervisorPlan;
	}

	public void setSupervisorPlan(String supervisorPlan) {
		SupervisorPlan = supervisorPlan;
	}

	public String getSupervisorDetailed() {
		return SupervisorDetailed;
	}

	public void setSupervisorDetailed(String supervisorDetailed) {
		SupervisorDetailed = supervisorDetailed;
	}

	public Integer getReply() {
		return Reply;
	}

	public void setReply(Integer reply) {
		Reply = reply;
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
