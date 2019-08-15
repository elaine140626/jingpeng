package com.curing.statisticsForms.model;

public class FirstPlannedProgress {
	private String Id;
	private String CityId; // 市id
	private Double PavementFinish; // 路面工程完成投资（万元）
	private Double TunnelFinish; // 隧道工程完成投资（万元）
	private String ProjectProgress; // 项目进展情况
	private Double ImageProgress; // 工程形象进度（%）
	private String PlanBatch; // 计划批次（数据字典）
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

	public String getCityId() {
		return CityId;
	}

	public void setCityId(String cityId) {
		CityId = cityId;
	}

	public Double getPavementFinish() {
		return PavementFinish;
	}

	public void setPavementFinish(Double pavementFinish) {
		PavementFinish = pavementFinish;
	}

	public Double getTunnelFinish() {
		return TunnelFinish;
	}

	public void setTunnelFinish(Double tunnelFinish) {
		TunnelFinish = tunnelFinish;
	}

	public String getProjectProgress() {
		return ProjectProgress;
	}

	public void setProjectProgress(String projectProgress) {
		ProjectProgress = projectProgress;
	}

	public Double getImageProgress() {
		return ImageProgress;
	}

	public void setImageProgress(Double imageProgress) {
		ImageProgress = imageProgress;
	}

	public String getPlanBatch() {
		return PlanBatch;
	}

	public void setPlanBatch(String planBatch) {
		PlanBatch = planBatch;
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
