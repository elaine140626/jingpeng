package com.mixingStation.model.cement;
/**
 * 
 * 
 * @author Administrator
 *
 */
public class CementProductionFigures  {
	private int orgId;//组织id
	private String collectDate;// 采集时间
	private String strengthGrade;// 等级强度
	private int totalNumber;//生产总盘数
	private int qualifiedNumber;//合格盘数
	private int unqualifiedNumber;//不合格盘数
	private double cementWeight;//水泥总量
	private double totalWeight;//拌和总量
	private double productionVolume;//生产方量
	
	public int getOrgId() {
		return orgId;
	}
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}
	public String getCollectDate() {
		return collectDate;
	}
	public void setCollectDate(String collectDate) {
		this.collectDate = collectDate;
	}
	public String getStrengthGrade() {
		return strengthGrade;
	}
	public void setStrengthGrade(String strengthGrade) {
		this.strengthGrade = strengthGrade;
	}
	public int getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}
	public int getQualifiedNumber() {
		return qualifiedNumber;
	}
	public void setQualifiedNumber(int qualifiedNumber) {
		this.qualifiedNumber = qualifiedNumber;
	}
	public int getUnqualifiedNumber() {
		return unqualifiedNumber;
	}
	public void setUnqualifiedNumber(int unqualifiedNumber) {
		this.unqualifiedNumber = unqualifiedNumber;
	}
	public double getCementWeight() {
		return cementWeight;
	}
	public void setCementWeight(double cementWeight) {
		this.cementWeight = cementWeight;
	}
	public double getTotalWeight() {
		return totalWeight;
	}
	public void setTotalWeight(double totalWeight) {
		this.totalWeight = totalWeight;
	}
	public double getProductionVolume() {
		return productionVolume;
	}
	public void setProductionVolume(double productionVolume) {
		this.productionVolume = productionVolume;
	}
	 
}
