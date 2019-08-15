package com.blindSample.model;

//Test0900101沥青混合料马歇尔稳定度试验主表
public class Test0900101T0 {

	private Long id;	//自增长ID
	private String serialnumber;	//流水号
	private Integer groupno;	//试验组号
	private String mixturetype;	//混合料种类
	private String gradationtype;	//级配类型
	private String asphalttype;	//沥青种类
	private String asphaltgrade;	//沥青标号/等级
	private String densitymethod;	//密度试验方法
	private boolean maxdmethod;	//理论最大密度试验方法
	private boolean isasphcont;	//沥青含量/油石比
	private Float asphaltcontent;	//沥青含量/油石比值
	private double tmaxdensity;	//理论最大相对密度
	private double asphaltrd;	//沥青相对密度
	private double waxrd;	//蜡的相对密度
	private Float rsa;	//Rsa
	private Float rsb;	//Rsb
	private Float rse;	//Rse
	private Integer admixturetype;	//外加剂种类
	private String fiberstabilizertype;	//纤维稳定剂种类
	private double fiberstabilizerrd;	//纤维稳定剂相对密度
	private Float fiberstabilizer;	//纤维稳定剂掺量(%)
	private Float mixtemperature;	//拌合温度(℃)
	private Float compactiontemp;	//击实温度(℃)
	private Integer compactiontimes;	//击实次数
	private double avgactual;	//实测密度(g/cm3)平均值
	private double avgvv;	//空隙率(%)平均值
	private double avgva;	//沥青体积百分率(%)平均值
	private double avgvma;	//矿料间隙率(%)平均值
	private double avgvfa;	//沥青饱和度(%)平均值
	private double avgstab;	//稳定度(kN)平均值
	private double avgflow;	//流值(0.1mm)平均值
	private double avgmarsmodulus;	//马歇尔模数(kN/mm)平均值
	private double avgstab48;	//浸水48小时后稳定度(kN)平均值
	private double avgresistab48;	//浸水48小时后的残留稳定度(%)平均值
	private String operator;	//创建人
	private String createdate;	//创建日期
	private String modifier;	//修改人
	private String modifydate;	//修改日期
	private boolean isvaliddata;	//有效标识
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
	public Integer getGroupno() {
		return groupno;
	}
	public void setGroupno(Integer groupno) {
		this.groupno = groupno;
	}
	public String getMixturetype() {
		return mixturetype;
	}
	public void setMixturetype(String mixturetype) {
		this.mixturetype = mixturetype;
	}
	public String getGradationtype() {
		return gradationtype;
	}
	public void setGradationtype(String gradationtype) {
		this.gradationtype = gradationtype;
	}
	public String getAsphalttype() {
		return asphalttype;
	}
	public void setAsphalttype(String asphalttype) {
		this.asphalttype = asphalttype;
	}
	public String getAsphaltgrade() {
		return asphaltgrade;
	}
	public void setAsphaltgrade(String asphaltgrade) {
		this.asphaltgrade = asphaltgrade;
	}
	public String getDensitymethod() {
		return densitymethod;
	}
	public void setDensitymethod(String densitymethod) {
		this.densitymethod = densitymethod;
	}
	public boolean isMaxdmethod() {
		return maxdmethod;
	}
	public void setMaxdmethod(boolean maxdmethod) {
		this.maxdmethod = maxdmethod;
	}
	public boolean isIsasphcont() {
		return isasphcont;
	}
	public void setIsasphcont(boolean isasphcont) {
		this.isasphcont = isasphcont;
	}
	public Float getAsphaltcontent() {
		return asphaltcontent;
	}
	public void setAsphaltcontent(Float asphaltcontent) {
		this.asphaltcontent = asphaltcontent;
	}
	public double getTmaxdensity() {
		return tmaxdensity;
	}
	public void setTmaxdensity(double tmaxdensity) {
		this.tmaxdensity = tmaxdensity;
	}
	public double getAsphaltrd() {
		return asphaltrd;
	}
	public void setAsphaltrd(double asphaltrd) {
		this.asphaltrd = asphaltrd;
	}
	public double getWaxrd() {
		return waxrd;
	}
	public void setWaxrd(double waxrd) {
		this.waxrd = waxrd;
	}
	public Float getRsa() {
		return rsa;
	}
	public void setRsa(Float rsa) {
		this.rsa = rsa;
	}
	public Float getRsb() {
		return rsb;
	}
	public void setRsb(Float rsb) {
		this.rsb = rsb;
	}
	public Float getRse() {
		return rse;
	}
	public void setRse(Float rse) {
		this.rse = rse;
	}
	public Integer getAdmixturetype() {
		return admixturetype;
	}
	public void setAdmixturetype(Integer admixturetype) {
		this.admixturetype = admixturetype;
	}
	public String getFiberstabilizertype() {
		return fiberstabilizertype;
	}
	public void setFiberstabilizertype(String fiberstabilizertype) {
		this.fiberstabilizertype = fiberstabilizertype;
	}
	public double getFiberstabilizerrd() {
		return fiberstabilizerrd;
	}
	public void setFiberstabilizerrd(double fiberstabilizerrd) {
		this.fiberstabilizerrd = fiberstabilizerrd;
	}
	public Float getFiberstabilizer() {
		return fiberstabilizer;
	}
	public void setFiberstabilizer(Float fiberstabilizer) {
		this.fiberstabilizer = fiberstabilizer;
	}
	public Float getMixtemperature() {
		return mixtemperature;
	}
	public void setMixtemperature(Float mixtemperature) {
		this.mixtemperature = mixtemperature;
	}
	public Float getCompactiontemp() {
		return compactiontemp;
	}
	public void setCompactiontemp(Float compactiontemp) {
		this.compactiontemp = compactiontemp;
	}
	public Integer getCompactiontimes() {
		return compactiontimes;
	}
	public void setCompactiontimes(Integer compactiontimes) {
		this.compactiontimes = compactiontimes;
	}
	public double getAvgactual() {
		return avgactual;
	}
	public void setAvgactual(double avgactual) {
		this.avgactual = avgactual;
	}
	public double getAvgvv() {
		return avgvv;
	}
	public void setAvgvv(double avgvv) {
		this.avgvv = avgvv;
	}
	public double getAvgva() {
		return avgva;
	}
	public void setAvgva(double avgva) {
		this.avgva = avgva;
	}
	public double getAvgvma() {
		return avgvma;
	}
	public void setAvgvma(double avgvma) {
		this.avgvma = avgvma;
	}
	public double getAvgvfa() {
		return avgvfa;
	}
	public void setAvgvfa(double avgvfa) {
		this.avgvfa = avgvfa;
	}
	public double getAvgstab() {
		return avgstab;
	}
	public void setAvgstab(double avgstab) {
		this.avgstab = avgstab;
	}
	public double getAvgflow() {
		return avgflow;
	}
	public void setAvgflow(double avgflow) {
		this.avgflow = avgflow;
	}
	public double getAvgmarsmodulus() {
		return avgmarsmodulus;
	}
	public void setAvgmarsmodulus(double avgmarsmodulus) {
		this.avgmarsmodulus = avgmarsmodulus;
	}
	public double getAvgstab48() {
		return avgstab48;
	}
	public void setAvgstab48(double avgstab48) {
		this.avgstab48 = avgstab48;
	}
	public double getAvgresistab48() {
		return avgresistab48;
	}
	public void setAvgresistab48(double avgresistab48) {
		this.avgresistab48 = avgresistab48;
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
	public boolean isIsvaliddata() {
		return isvaliddata;
	}
	public void setIsvaliddata(boolean isvaliddata) {
		this.isvaliddata = isvaliddata;
	}
	
	
}
