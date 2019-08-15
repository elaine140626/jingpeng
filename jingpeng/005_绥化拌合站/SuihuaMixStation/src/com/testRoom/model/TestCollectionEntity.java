package com.testRoom.model;

public class TestCollectionEntity {

	private int row;                 // 序列号
	private Long id;                 // 主键
	private String UniqueIdentifier; // 试验室唯一标识
	private String TestRoomName;     // 试验室名称
	private String TestName;         // 试验名称
	private int TestNameId;          // 试验名称id
	private String SampleName;       // 样品名称
	private String TestOperator;     // 试验员
	private String CreateDate;       // 收样时间
	private String TestDate;         // 试验时间
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUniqueIdentifier() {
		return UniqueIdentifier;
	}
	public void setUniqueIdentifier(String uniqueIdentifier) {
		UniqueIdentifier = uniqueIdentifier;
	}
	public String getTestRoomName() {
		return TestRoomName;
	}
	public void setTestRoomName(String testRoomName) {
		TestRoomName = testRoomName;
	}
	public String getTestName() {
		return TestName;
	}
	public void setTestName(String testName) {
		TestName = testName;
	}
	public int getTestNameId() {
		return TestNameId;
	}
	public void setTestNameId(int testNameId) {
		TestNameId = testNameId;
	}
	public String getSampleName() {
		return SampleName;
	}
	public void setSampleName(String sampleName) {
		SampleName = sampleName;
	}
	public String getTestOperator() {
		return TestOperator;
	}
	public void setTestOperator(String testOperator) {
		TestOperator = testOperator;
	}
	public String getCreateDate() {
		return CreateDate;
	}
	public void setCreateDate(String createDate) {
		CreateDate = createDate;
	}
	public String getTestDate() {
		return TestDate;
	}
	public void setTestDate(String testDate) {
		TestDate = testDate;
	}
	
}
