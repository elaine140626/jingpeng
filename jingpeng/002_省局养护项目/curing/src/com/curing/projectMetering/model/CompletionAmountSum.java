package com.curing.projectMetering.model;

public class CompletionAmountSum {
	private Double TotalSum; // 总量
	private Double ContractAmountSum; // 合同金额
	private Double MonthCompletionSum; // 本月完成金额
	private Double YearCompletionSum; // 本年累计完成金额
	private Double StartCompletionSum; // 自开工起累计完成金额
	private Double MonthThisCompleteSum; // 本月完成工程量
	private Double YearThisCompleteSum; // 本年累计完成工程量
	private Double StartThisCompleteSum; // 自开工起累计完成工程量
	public Double getTotalSum() {
		return TotalSum;
	}
	public void setTotalSum(Double totalSum) {
		TotalSum = totalSum;
	}
	public Double getContractAmountSum() {
		return ContractAmountSum;
	}
	public void setContractAmountSum(Double contractAmountSum) {
		ContractAmountSum = contractAmountSum;
	}
	public Double getMonthCompletionSum() {
		return MonthCompletionSum;
	}
	public void setMonthCompletionSum(Double monthCompletionSum) {
		MonthCompletionSum = monthCompletionSum;
	}
	public Double getYearCompletionSum() {
		return YearCompletionSum;
	}
	public void setYearCompletionSum(Double yearCompletionSum) {
		YearCompletionSum = yearCompletionSum;
	}
	public Double getStartCompletionSum() {
		return StartCompletionSum;
	}
	public void setStartCompletionSum(Double startCompletionSum) {
		StartCompletionSum = startCompletionSum;
	}
	public Double getMonthThisCompleteSum() {
		return MonthThisCompleteSum;
	}
	public void setMonthThisCompleteSum(Double monthThisCompleteSum) {
		MonthThisCompleteSum = monthThisCompleteSum;
	}
	public Double getYearThisCompleteSum() {
		return YearThisCompleteSum;
	}
	public void setYearThisCompleteSum(Double yearThisCompleteSum) {
		YearThisCompleteSum = yearThisCompleteSum;
	}
	public Double getStartThisCompleteSum() {
		return StartThisCompleteSum;
	}
	public void setStartThisCompleteSum(Double startThisCompleteSum) {
		StartThisCompleteSum = startThisCompleteSum;
	}
}
