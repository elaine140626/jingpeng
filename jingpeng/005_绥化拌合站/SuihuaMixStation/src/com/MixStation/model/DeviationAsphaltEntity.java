package com.MixStation.model;

public class DeviationAsphaltEntity {
	private Integer orgId;
	private Double devAggregate; // 骨料仓偏差高级预警
	private Double devPowder; // 粉料仓偏差高级预警
	private Double devAsphalt; // 沥青仓偏差高级预警

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Double getDevAggregate() {
		return devAggregate;
	}

	public void setDevAggregate(Double devAggregate) {
		this.devAggregate = devAggregate;
	}

	public Double getDevPowder() {
		return devPowder;
	}

	public void setDevPowder(Double devPowder) {
		this.devPowder = devPowder;
	}

	public Double getDevAsphalt() {
		return devAsphalt;
	}

	public void setDevAsphalt(Double devAsphalt) {
		this.devAsphalt = devAsphalt;
	}

}
