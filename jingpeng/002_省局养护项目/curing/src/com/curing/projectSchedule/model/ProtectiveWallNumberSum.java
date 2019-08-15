package com.curing.projectSchedule.model;

public class ProtectiveWallNumberSum {
	private Double LengthSum; // 修建长度（m）
	private Double AmountSum; // 数量（个）
	private Double C25ConcreteSum; // C25混凝土（m³）
	private Double LacquerSum; // 涂漆（㎡）

	public Double getLengthSum() {
		return LengthSum;
	}

	public void setLengthSum(Double lengthSum) {
		LengthSum = lengthSum;
	}

	public Double getAmountSum() {
		return AmountSum;
	}

	public void setAmountSum(Double amountSum) {
		AmountSum = amountSum;
	}

	public Double getC25ConcreteSum() {
		return C25ConcreteSum;
	}

	public void setC25ConcreteSum(Double c25ConcreteSum) {
		C25ConcreteSum = c25ConcreteSum;
	}

	public Double getLacquerSum() {
		return LacquerSum;
	}

	public void setLacquerSum(Double lacquerSum) {
		LacquerSum = lacquerSum;
	}

}
