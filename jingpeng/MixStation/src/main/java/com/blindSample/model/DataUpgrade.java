package com.blindSample.model;

//数据库升级记录表
public class DataUpgrade {

	private Long id;	//自增长ID
	private String dataversion;	//数据库版本
	private String content;	//升级脚本内容
	private String operator;	//创建人
	private String createdate;	//创建日期
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDataversion() {
		return dataversion;
	}
	public void setDataversion(String dataversion) {
		this.dataversion = dataversion;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	
	
}
