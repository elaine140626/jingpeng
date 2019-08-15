package com.curing.asphaltSupply.model;

public class AsphaltPurchasingEntity {

	// 主键id
	private String Id;
	// 沥青供应id
	private String FirstAsphaltSupplyId;
	// 月初需求（4月） 
	private Double MonthNeedApril;
	// 月初需求（5月） 
	private Double MonthNeedMay;
	// 月初需求（6月） 
	private Double MonthNeedJune;
	// 月初需求（7月） 
	private Double MonthNeedJuly;
	// 月初需求（8月） 
	private Double MonthNeedAug;
	// 月初需求（9月） 
	private Double MonthNeedSep;
	// 月初需求（10月）
	private Double MonthNeedOct;
	// 实际需求（4月） 
	private Double ActualSupplyApril;
	// 实际需求（5月） 
	private Double ActualSupplyMay;
	// 实际需求（6月） 
	private Double ActualSupplyJune;
	// 实际需求（7月） 
	private Double ActualSupplyJuly;
	// 实际需求（8月） 
	private Double ActualSupplyAug;
	// 实际需求（9月） 
	private Double ActualSupplySep;
	// 实际需求（10月） 
	private Double ActualSupplyOct;
	// 仍未需求（4月） 
	private Double differenceApril;
	// 仍未需求（5月） 
	private Double differenceMay;
	// 仍未需求（6月） 
	private Double differenceJune;
	// 仍未需求（7月） 
	private Double differenceJuly;
	// 仍未需求（8月） 
	private Double differenceAug;
	// 仍未需求（9月） 
	private Double differenceSep;
	// 仍未需求（10月）
	private Double differenceOct;
	private String Remarks;
	private Integer IsDel;
	private String Creater;
	private String CreaterDate;
	private String Reviser;
	private String ReviserDate;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getFirstAsphaltSupplyId() {
		return FirstAsphaltSupplyId;
	}
	public void setFirstAsphaltSupplyId(String firstAsphaltSupplyId) {
		FirstAsphaltSupplyId = firstAsphaltSupplyId;
	}
	public Double getMonthNeedApril() {
		return MonthNeedApril;
	}
	public void setMonthNeedApril(Double monthNeedApril) {
		MonthNeedApril = monthNeedApril;
	}
	public Double getMonthNeedMay() {
		return MonthNeedMay;
	}
	public void setMonthNeedMay(Double monthNeedMay) {
		MonthNeedMay = monthNeedMay;
	}
	public Double getMonthNeedJune() {
		return MonthNeedJune;
	}
	public void setMonthNeedJune(Double monthNeedJune) {
		MonthNeedJune = monthNeedJune;
	}
	public Double getMonthNeedJuly() {
		return MonthNeedJuly;
	}
	public void setMonthNeedJuly(Double monthNeedJuly) {
		MonthNeedJuly = monthNeedJuly;
	}
	public Double getMonthNeedAug() {
		return MonthNeedAug;
	}
	public void setMonthNeedAug(Double monthNeedAug) {
		MonthNeedAug = monthNeedAug;
	}
	public Double getMonthNeedSep() {
		return MonthNeedSep;
	}
	public void setMonthNeedSep(Double monthNeedSep) {
		MonthNeedSep = monthNeedSep;
	}
	public Double getMonthNeedOct() {
		return MonthNeedOct;
	}
	public void setMonthNeedOct(Double monthNeedOct) {
		MonthNeedOct = monthNeedOct;
	}
	public Double getActualSupplyApril() {
		return ActualSupplyApril;
	}
	public void setActualSupplyApril(Double actualSupplyApril) {
		ActualSupplyApril = actualSupplyApril;
	}
	public Double getActualSupplyMay() {
		return ActualSupplyMay;
	}
	public void setActualSupplyMay(Double actualSupplyMay) {
		ActualSupplyMay = actualSupplyMay;
	}
	public Double getActualSupplyJune() {
		return ActualSupplyJune;
	}
	public void setActualSupplyJune(Double actualSupplyJune) {
		ActualSupplyJune = actualSupplyJune;
	}
	public Double getActualSupplyJuly() {
		return ActualSupplyJuly;
	}
	public void setActualSupplyJuly(Double actualSupplyJuly) {
		ActualSupplyJuly = actualSupplyJuly;
	}
	public Double getActualSupplyAug() {
		return ActualSupplyAug;
	}
	public void setActualSupplyAug(Double actualSupplyAug) {
		ActualSupplyAug = actualSupplyAug;
	}
	public Double getActualSupplySep() {
		return ActualSupplySep;
	}
	public void setActualSupplySep(Double actualSupplySep) {
		ActualSupplySep = actualSupplySep;
	}
	public Double getActualSupplyOct() {
		return ActualSupplyOct;
	}
	public void setActualSupplyOct(Double actualSupplyOct) {
		ActualSupplyOct = actualSupplyOct;
	}
	public Double getDifferenceApril() {
		return differenceApril;
	}
	public void setDifferenceApril(Double differenceApril) {
		this.differenceApril = differenceApril;
	}
	public Double getDifferenceMay() {
		return differenceMay;
	}
	public void setDifferenceMay(Double differenceMay) {
		this.differenceMay = differenceMay;
	}
	public Double getDifferenceJune() {
		return differenceJune;
	}
	public void setDifferenceJune(Double differenceJune) {
		this.differenceJune = differenceJune;
	}
	public Double getDifferenceJuly() {
		return differenceJuly;
	}
	public void setDifferenceJuly(Double differenceJuly) {
		this.differenceJuly = differenceJuly;
	}
	public Double getDifferenceAug() {
		return differenceAug;
	}
	public void setDifferenceAug(Double differenceAug) {
		this.differenceAug = differenceAug;
	}
	public Double getDifferenceSep() {
		return differenceSep;
	}
	public void setDifferenceSep(Double differenceSep) {
		this.differenceSep = differenceSep;
	}
	public Double getDifferenceOct() {
		return differenceOct;
	}
	public void setDifferenceOct(Double differenceOct) {
		this.differenceOct = differenceOct;
	}
	public String getRemarks() {
		return Remarks;
	}
	public void setRemarks(String remarks) {
		Remarks = remarks;
	}
	public Integer getIsDel() {
		return IsDel;
	}
	public void setIsDel(Integer isDel) {
		IsDel = isDel;
	}
	public String getCreater() {
		return Creater;
	}
	public void setCreater(String creater) {
		Creater = creater;
	}
	public String getCreaterDate() {
		return CreaterDate;
	}
	public void setCreaterDate(String createrDate) {
		CreaterDate = createrDate;
	}
	public String getReviser() {
		return Reviser;
	}
	public void setReviser(String reviser) {
		Reviser = reviser;
	}
	public String getReviserDate() {
		return ReviserDate;
	}
	public void setReviserDate(String reviserDate) {
		ReviserDate = reviserDate;
	}
	
}
