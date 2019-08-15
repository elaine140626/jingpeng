package com.jingpeng.model;

import com.kdt.base.support.model.ModelSupport;

public class LqAxis extends ModelSupport {
	private static final long serialVersionUID = -20238979925091341L;
	private String Material;
	private int CollectHour;
	private double MaterialConsumption;

	public String getMaterial() {
		return Material;
	}

	public void setMaterial(String material) {
		Material = material;
	}

	public int getCollectHour() {
		return CollectHour;
	}

	public void setCollectHour(int collectHour) {
		CollectHour = collectHour;
	}

	public double getMaterialConsumption() {
		return MaterialConsumption;
	}

	public void setMaterialConsumption(double materialConsumption) {
		MaterialConsumption = materialConsumption;
	}

}
