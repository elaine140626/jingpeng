package com.blindSample.model;

//Test0900101马歇尔稳定度采集表
public class Test0900101Collection {

	private Long id;	//子表ID
	private String serialnumber;	//主表流水号
	private String code;	//试验编号/样品编号
	private Integer specgroupid;	//试件组号
	private Float stab;	//稳定度(kN)
	private Float flow;	//流值(0.1mm)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSerialnumber() {
		return serialnumber;
	}
	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getSpecgroupid() {
		return specgroupid;
	}
	public void setSpecgroupid(Integer specgroupid) {
		this.specgroupid = specgroupid;
	}
	public Float getStab() {
		return stab;
	}
	public void setStab(Float stab) {
		this.stab = stab;
	}
	public Float getFlow() {
		return flow;
	}
	public void setFlow(Float flow) {
		this.flow = flow;
	}
	
}
