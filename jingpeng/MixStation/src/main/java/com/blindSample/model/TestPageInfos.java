package com.blindSample.model;

//试验页面信息表
public class TestPageInfos {

	private Integer id;	//自增长ID
	private String testClass_code;	//试验分类
	private String testpagename;	//试验页面名称
	private String testname;	//试验名称
	private String testtable;	//试验主表名
	private String testrules;	//试验规程
	private String test_report_mark;	//试验报告标识
	private String test_record_mark;	//试验记录标识
	private boolean isblindtest;	//是否盲样试验
	private String blindtpagename;	//盲样试验页面名称
	private boolean isautotest;	//是否是自动采集试验
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTestClass_code() {
		return testClass_code;
	}
	public void setTestClass_code(String testClass_code) {
		this.testClass_code = testClass_code;
	}
	public String getTestpagename() {
		return testpagename;
	}
	public void setTestpagename(String testpagename) {
		this.testpagename = testpagename;
	}
	public String getTestname() {
		return testname;
	}
	public void setTestname(String testname) {
		this.testname = testname;
	}
	public String getTesttable() {
		return testtable;
	}
	public void setTesttable(String testtable) {
		this.testtable = testtable;
	}
	public String getTestrules() {
		return testrules;
	}
	public void setTestrules(String testrules) {
		this.testrules = testrules;
	}
	public String getTest_report_mark() {
		return test_report_mark;
	}
	public void setTest_report_mark(String test_report_mark) {
		this.test_report_mark = test_report_mark;
	}
	public String getTest_record_mark() {
		return test_record_mark;
	}
	public void setTest_record_mark(String test_record_mark) {
		this.test_record_mark = test_record_mark;
	}
	public boolean isIsblindtest() {
		return isblindtest;
	}
	public void setIsblindtest(boolean isblindtest) {
		this.isblindtest = isblindtest;
	}
	public String getBlindtpagename() {
		return blindtpagename;
	}
	public void setBlindtpagename(String blindtpagename) {
		this.blindtpagename = blindtpagename;
	}
	public boolean isIsautotest() {
		return isautotest;
	}
	public void setIsautotest(boolean isautotest) {
		this.isautotest = isautotest;
	}
	
	
}
