package com.curing.projectSchedule.model;

public class CulvertMaintenanceNumberSum {
	private Double SiltSum; // 淤泥（m³）
	private Double ConcreteSum; // C30混凝土（m³）
	private Integer BondedRebarsSum; // 长35cm间距30cmφ12植筋（根）
	private Double RubbleSum; // M10浆砌片石（m³）
	
	
	public Double getSiltSum() {
		return SiltSum;
	}
	public void setSiltSum(Double siltSum) {
		SiltSum = siltSum;
	}
	public Double getConcreteSum() {
		return ConcreteSum;
	}
	public void setConcreteSum(Double concreteSum) {
		ConcreteSum = concreteSum;
	}
	public Integer getBondedRebarsSum() {
		return BondedRebarsSum;
	}
	public void setBondedRebarsSum(Integer bondedRebarsSum) {
		BondedRebarsSum = bondedRebarsSum;
	}
	public Double getRubbleSum() {
		return RubbleSum;
	}
	public void setRubbleSum(Double rubbleSum) {
		RubbleSum = rubbleSum;
	}

	

}
