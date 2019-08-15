package com.blindSample.model;

//Test07003无机结合无侧限抗压强度试验
public class Test07003Test0 {

	private Long id; //自增长ID
	private String serialnumber; //流水号	
	private String mixturetype;	//混合料种类
	private String cemengrade;	//水泥/石灰品种及强度等级
	private Float dosage;	//用量（%）
	private String spectype;	//试件类型
	private Float watercontent;	//最佳含水率(%)
	private Float maxdrydensity;	//最大干密度(g/cm3)
	private Float designstrth;	//设计强度(Mpa)
	private String productmethod;	//制件方法
	private String molddate;	//成型日期
	private String roadgrade;	//公路等级
	private String pilenumber;	//施工桩号
	private Double maxvalue;	//最大值
	private Double minvalue;	//最小值
	private Double avgvalue;	//平均值
	private Double s;	//标准差
	private Double cv;	//变异系数
	private Double rc095;	//Rc095
	private Double calcrd;	//Rd/（1-Za×Cv）
	private String operator;	//创建人
	private String createdate;	//创建日期
	private String modifier;	//修改人
	private String modifydate;	//修改日期
	private Boolean isValidData; //数据是否有效
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
	public String getMixturetype() {
		return mixturetype;
	}
	public void setMixturetype(String mixturetype) {
		this.mixturetype = mixturetype;
	}
	public String getCemengrade() {
		return cemengrade;
	}
	public void setCemengrade(String cemengrade) {
		this.cemengrade = cemengrade;
	}
	public Float getDosage() {
		return dosage;
	}
	public void setDosage(Float dosage) {
		this.dosage = dosage;
	}
	public String getSpectype() {
		return spectype;
	}
	public void setSpectype(String spectype) {
		this.spectype = spectype;
	}
	public Float getWatercontent() {
		return watercontent;
	}
	public void setWatercontent(Float watercontent) {
		this.watercontent = watercontent;
	}
	public Float getMaxdrydensity() {
		return maxdrydensity;
	}
	public void setMaxdrydensity(Float maxdrydensity) {
		this.maxdrydensity = maxdrydensity;
	}
	public Float getDesignstrth() {
		return designstrth;
	}
	public void setDesignstrth(Float designstrth) {
		this.designstrth = designstrth;
	}
	public String getProductmethod() {
		return productmethod;
	}
	public void setProductmethod(String productmethod) {
		this.productmethod = productmethod;
	}
	public String getMolddate() {
		return molddate;
	}
	public void setMolddate(String molddate) {
		this.molddate = molddate;
	}
	public String getRoadgrade() {
		return roadgrade;
	}
	public void setRoadgrade(String roadgrade) {
		this.roadgrade = roadgrade;
	}
	public String getPilenumber() {
		return pilenumber;
	}
	public void setPilenumber(String pilenumber) {
		this.pilenumber = pilenumber;
	}
	public Double getMaxvalue() {
		return maxvalue;
	}
	public void setMaxvalue(Double maxvalue) {
		this.maxvalue = maxvalue;
	}
	public Double getMinvalue() {
		return minvalue;
	}
	public void setMinvalue(Double minvalue) {
		this.minvalue = minvalue;
	}
	public Double getAvgvalue() {
		return avgvalue;
	}
	public void setAvgvalue(Double avgvalue) {
		this.avgvalue = avgvalue;
	}
	public Double getS() {
		return s;
	}
	public void setS(Double s) {
		this.s = s;
	}
	public Double getCv() {
		return cv;
	}
	public void setCv(Double cv) {
		this.cv = cv;
	}
	public Double getRc095() {
		return rc095;
	}
	public void setRc095(Double rc095) {
		this.rc095 = rc095;
	}
	public Double getCalcrd() {
		return calcrd;
	}
	public void setCalcrd(Double calcrd) {
		this.calcrd = calcrd;
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
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public String getModifydate() {
		return modifydate;
	}
	public void setModifydate(String modifydate) {
		this.modifydate = modifydate;
	}
	public Boolean getIsValidData() {
		return isValidData;
	}
	public void setIsValidData(Boolean isValidData) {
		this.isValidData = isValidData;
	}
	
}
