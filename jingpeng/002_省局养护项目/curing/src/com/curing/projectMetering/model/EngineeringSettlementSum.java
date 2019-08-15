package com.curing.projectMetering.model;

public class EngineeringSettlementSum {

	private Double CurrentAmountSum; // 本期金额
	private Double YearAmountSum; // 本年累计金额
	private Double StartAmountSum; // 自开工累计金额
	public Double getCurrentAmountSum() {
		return CurrentAmountSum;
	}
	public void setCurrentAmountSum(Double currentAmountSum) {
		CurrentAmountSum = currentAmountSum;
	}
	public Double getYearAmountSum() {
		return YearAmountSum;
	}
	public void setYearAmountSum(Double yearAmountSum) {
		YearAmountSum = yearAmountSum;
	}
	public Double getStartAmountSum() {
		return StartAmountSum;
	}
	public void setStartAmountSum(Double startAmountSum) {
		StartAmountSum = startAmountSum;
	}
}
