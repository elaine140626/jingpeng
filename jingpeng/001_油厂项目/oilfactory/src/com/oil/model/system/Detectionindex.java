package com.oil.model.system;

public class Detectionindex {

	//检测项目id
	private int id;
	
	//检测项目
	private String testingItems;
	
	//指标类型
	private String indexType;
	
	//单位
	private String indexUnit;
	
	//试验方法
	private String testMethod;
	
	//删除标记
	private int isDel;
	
	//创建人
	private String creater;
	
	//创建日期
	private String createrDate;
	
	//修改人
	private String reviser;
	
	//修改日期
	private String reviserDate;

	private String indexTypes;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTestingItems() {
		return testingItems;
	}

	public void setTestingItems(String testingItems) {
		this.testingItems = testingItems;
	}

	public String getIndexType() {
		return indexType;
	}

	public void setIndexType(String indexType) {
		this.indexType = indexType;
	}

	public String getIndexUnit() {
		return indexUnit;
	}

	public void setIndexUnit(String indexUnit) {
		this.indexUnit = indexUnit;
	}

	public String getTestMethod() {
		return testMethod;
	}

	public void setTestMethod(String testMethod) {
		this.testMethod = testMethod;
	}

	public int getIsDel() {
		return isDel;
	}

	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getCreaterDate() {
		return createrDate;
	}

	public void setCreaterDate(String createrDate) {
		this.createrDate = createrDate;
	}

	public String getReviser() {
		return reviser;
	}

	public void setReviser(String reviser) {
		this.reviser = reviser;
	}

	public String getReviserDate() {
		return reviserDate;
	}

	public void setReviserDate(String reviserDate) {
		this.reviserDate = reviserDate;
	}

	public String getIndexTypes() {
		return indexTypes;
	}

	public void setIndexTypes(String indexTypes) {
		this.indexTypes = indexTypes;
	}
	
	
}
