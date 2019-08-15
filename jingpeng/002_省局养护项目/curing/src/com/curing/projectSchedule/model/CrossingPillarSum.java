package com.curing.projectSchedule.model;

public class CrossingPillarSum {
	private Double AcrossLeftSum; // 被交叉道路宽度左
	private Double AcrossRightSum; // 被交叉道路宽度右
	private Integer AmountSum; // 应设数量（个）
	public Double getAcrossLeftSum() {
		return AcrossLeftSum;
	}
	public void setAcrossLeftSum(Double acrossLeftSum) {
		AcrossLeftSum = acrossLeftSum;
	}
	public Double getAcrossRightSum() {
		return AcrossRightSum;
	}
	public void setAcrossRightSum(Double acrossRightSum) {
		AcrossRightSum = acrossRightSum;
	}
	public Integer getAmountSum() {
		return AmountSum;
	}
	public void setAmountSum(Integer amountSum) {
		AmountSum = amountSum;
	}
	
}
