package com.oil.model.system;
//前缀表
public class Prefix {
	private int id;//主键id
	private String Distinguish;//销售合同编号前缀
	private String AllPrefix;//销售订单编号前缀
	private String Year;//出库单编号前缀
	private String Month;//入库单编号前缀
	private String Day;
	private String Hour;
	private String Branch;
	private String Second;
	private String FlowNumber;
	private int isDel;//删除标记
	private String creater;//创建人
	private String createrDate;//创建日期
	private String reviser;//修改人
	private String reviserDate;//修改日期
	private String prefixs;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDistinguish() {
		return Distinguish;
	}
	public void setDistinguish(String distinguish) {
		Distinguish = distinguish;
	}
	public String getAllPrefix() {
		return AllPrefix;
	}
	public void setAllPrefix(String allPrefix) {
		AllPrefix = allPrefix;
	}
	public String getYear() {
		return Year;
	}
	public void setYear(String year) {
		Year = year;
	}
	public String getMonth() {
		return Month;
	}
	public void setMonth(String month) {
		Month = month;
	}
	public String getDay() {
		return Day;
	}
	public void setDay(String day) {
		Day = day;
	}
	public String getHour() {
		return Hour;
	}
	public void setHour(String hour) {
		Hour = hour;
	}
	public String getBranch() {
		return Branch;
	}
	public void setBranch(String branch) {
		Branch = branch;
	}
	public String getSecond() {
		return Second;
	}
	public void setSecond(String second) {
		Second = second;
	}
	public String getFlowNumber() {
		return FlowNumber;
	}
	public void setFlowNumber(String flowNumber) {
		FlowNumber = flowNumber;
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
	public String getPrefixs() {
		return prefixs;
	}
	public void setPrefixs(String prefixs) {
		this.prefixs = prefixs;
	}
	
}
