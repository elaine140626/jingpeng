package com.testRoom.model;

public class TestSelectEntity {
	private int rownum;
	private int merge;//合并单元格属性
	private Long id; // id
	private String SerialNumber; // 流水号
	private String uniqueidentifier; // 试验室唯一标识
	private String samplecode; // 样品编号
	private Integer samplecount; // 试件个数
	private String testdate; // 试验日期
	private String testoperator; // 试验员
	private String testtype; // 试验类型
	private boolean istestcollection; // 是否自动采集试验
	private boolean istestblind; // 是否盲样试验
	private String collectionstate; // 采集状态
	private String isqualifiedtest; // 试验是否合格
	private String testroomname; // 试验室名称
	private String testname; // 试验名称
	private String testtable; // 试验关联表
	private String testclassification_name; // 试验类别
	private String teststate; // 试验状态
	private String constructionunit; // 施工单位
	private String engineeringname; // 工程名称
	private String purpose; // 工程部位/用途
	private String samplingPerson;//抽样人
	private String samplingdate;//抽样时间
	//水泥混凝土抗压强度试验字段
	private String samplesize;//试件尺寸(mm)
	private String cementgrade;//强度等级
	private Integer age;//龄期(天)
	private Double comprstrength1;//抗压强度1(MPa)
	private Double comprstrength2;//抗压强度2(MPa)
	private Double comprstrength3;//抗压强度3(MPa)
	private Double compressivestrength;//测定值(MPa)
	private Double prop_DesignStrength;//占设计强度(%)
	//砂浆抗压强度试验字段
	private Integer ageday;//龄期(天)
	private Double compressionstrength;//测定值
	private Double comprstrength4;//抗压强度4(MPa)
	private Double comprstrength5;//抗压强度5(MPa)
	private Double comprstrength6;//抗压强度6(MPa)
	//钢筋拉伸强度、屈服强度、伸长率、冷弯试验
	private String strengthgrade;//强度等级或牌号
	private Double yieldstrth1;//屈服强度(MPa)1
	private Double yieldstrth2;//屈服强度(MPa)2
	private Double tensilestrength1;//抗拉强度(MPa)1
	private Double tensilestrength2;//抗拉强度(MPa)2
	//沥青针入度试验
	private String asphaltype;//沥青种类
	private String asphaltgrade;//沥青标号/等级
	private Float temperature;//试验温度
	private Float penetration1;//针入度1
	private Float penetration2;//针入度2
	private Float penetration3;//针入度3
	private Double avgpenetration;//平均针入度
	//沥青软化点试验
	private Float softenpoint1;//软化点1
	private Float softenpoint2;//软化点2
	private Double avgsoftenpoint;//平均软化点
	//沥青混合料马歇尔试验
	private String mixturetype;//混合料种类
	private String gradationtype;//级配类型
	private Double avgstab;//稳定度(kN)平均值
	private Double avgflow;//流值(0.1mm)平均值
	private Float stab;//稳定度(kN)
	private Float flow;//流值(0.1mm)
	public int getRownum() {
		return rownum;
	}

	public void setRownum(int rownum) {
		this.rownum = rownum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSerialNumber() {
		return SerialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		SerialNumber = serialNumber;
	}

	public String getUniqueidentifier() {
		return uniqueidentifier;
	}

	public void setUniqueidentifier(String uniqueidentifier) {
		this.uniqueidentifier = uniqueidentifier;
	}

	public String getSamplecode() {
		return samplecode;
	}

	public void setSamplecode(String samplecode) {
		this.samplecode = samplecode;
	}

	public Integer getSamplecount() {
		return samplecount;
	}

	public void setSamplecount(Integer samplecount) {
		this.samplecount = samplecount;
	}

	public String getTestdate() {
		return testdate;
	}

	public void setTestdate(String testdate) {
		this.testdate = testdate;
	}

	public String getTestoperator() {
		return testoperator;
	}

	public void setTestoperator(String testoperator) {
		this.testoperator = testoperator;
	}

	public boolean isIstestcollection() {
		return istestcollection;
	}

	public void setIstestcollection(boolean istestcollection) {
		this.istestcollection = istestcollection;
	}

	public boolean isIstestblind() {
		return istestblind;
	}

	public void setIstestblind(boolean istestblind) {
		this.istestblind = istestblind;
	}

	public String getIsqualifiedtest() {
		return isqualifiedtest;
	}

	public void setIsqualifiedtest(String isqualifiedtest) {
		this.isqualifiedtest = isqualifiedtest;
	}

	public String getTestroomname() {
		return testroomname;
	}

	public void setTestroomname(String testroomname) {
		this.testroomname = testroomname;
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

	public String getTestclassification_name() {
		return testclassification_name;
	}

	public void setTestclassification_name(String testclassification_name) {
		this.testclassification_name = testclassification_name;
	}

	public String getTeststate() {
		return teststate;
	}

	public void setTeststate(String teststate) {
		this.teststate = teststate;
	}

	public String getConstructionunit() {
		return constructionunit;
	}

	public void setConstructionunit(String constructionunit) {
		this.constructionunit = constructionunit;
	}

	public String getEngineeringname() {
		return engineeringname;
	}

	public void setEngineeringname(String engineeringname) {
		this.engineeringname = engineeringname;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getCollectionstate() {
		return collectionstate;
	}

	public void setCollectionstate(String collectionstate) {
		this.collectionstate = collectionstate;
	}

	public String getTesttype() {
		return testtype;
	}

	public void setTesttype(String testtype) {
		this.testtype = testtype;
	}
	public String getSamplingPerson() {
		return samplingPerson;
	}

	public void setSamplingPerson(String samplingPerson) {
		this.samplingPerson = samplingPerson;
	}

	public String getSamplingdate() {
		return samplingdate;
	}

	public void setSamplingdate(String samplingdate) {
		this.samplingdate = samplingdate;
	}

	public String getSamplesize() {
		return samplesize;
	}

	public void setSamplesize(String samplesize) {
		this.samplesize = samplesize;
	}

	public String getCementgrade() {
		return cementgrade;
	}

	public void setCementgrade(String cementgrade) {
		this.cementgrade = cementgrade;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Double getComprstrength1() {
		return comprstrength1;
	}

	public void setComprstrength1(Double comprstrength1) {
		this.comprstrength1 = comprstrength1;
	}

	public Double getComprstrength2() {
		return comprstrength2;
	}

	public void setComprstrength2(Double comprstrength2) {
		this.comprstrength2 = comprstrength2;
	}

	public Double getComprstrength3() {
		return comprstrength3;
	}

	public void setComprstrength3(Double comprstrength3) {
		this.comprstrength3 = comprstrength3;
	}

	public Double getCompressivestrength() {
		return compressivestrength;
	}

	public void setCompressivestrength(Double compressivestrength) {
		this.compressivestrength = compressivestrength;
	}

	public Double getProp_DesignStrength() {
		return prop_DesignStrength;
	}

	public void setProp_DesignStrength(Double prop_DesignStrength) {
		this.prop_DesignStrength = prop_DesignStrength;
	}

	public Integer getAgeday() {
		return ageday;
	}

	public void setAgeday(Integer ageday) {
		this.ageday = ageday;
	}

	public Double getCompressionstrength() {
		return compressionstrength;
	}

	public void setCompressionstrength(Double compressionstrength) {
		this.compressionstrength = compressionstrength;
	}

	public Double getComprstrength4() {
		return comprstrength4;
	}

	public void setComprstrength4(Double comprstrength4) {
		this.comprstrength4 = comprstrength4;
	}

	public Double getComprstrength5() {
		return comprstrength5;
	}

	public void setComprstrength5(Double comprstrength5) {
		this.comprstrength5 = comprstrength5;
	}

	public Double getComprstrength6() {
		return comprstrength6;
	}

	public void setComprstrength6(Double comprstrength6) {
		this.comprstrength6 = comprstrength6;
	}

	public Double getYieldstrth1() {
		return yieldstrth1;
	}

	public void setYieldstrth1(Double yieldstrth1) {
		this.yieldstrth1 = yieldstrth1;
	}

	public Double getYieldstrth2() {
		return yieldstrth2;
	}

	public void setYieldstrth2(Double yieldstrth2) {
		this.yieldstrth2 = yieldstrth2;
	}

	public Double getTensilestrength1() {
		return tensilestrength1;
	}

	public void setTensilestrength1(Double tensilestrength1) {
		this.tensilestrength1 = tensilestrength1;
	}

	public Double getTensilestrength2() {
		return tensilestrength2;
	}

	public void setTensilestrength2(Double tensilestrength2) {
		this.tensilestrength2 = tensilestrength2;
	}

	public String getStrengthgrade() {
		return strengthgrade;
	}

	public void setStrengthgrade(String strengthgrade) {
		this.strengthgrade = strengthgrade;
	}

	public String getAsphaltype() {
		return asphaltype;
	}

	public void setAsphaltype(String asphaltype) {
		this.asphaltype = asphaltype;
	}

	public String getAsphaltgrade() {
		return asphaltgrade;
	}

	public void setAsphaltgrade(String asphaltgrade) {
		this.asphaltgrade = asphaltgrade;
	}

	public Float getTemperature() {
		return temperature;
	}

	public void setTemperature(Float temperature) {
		this.temperature = temperature;
	}

	public Float getPenetration1() {
		return penetration1;
	}

	public void setPenetration1(Float penetration1) {
		this.penetration1 = penetration1;
	}

	public Float getPenetration2() {
		return penetration2;
	}

	public void setPenetration2(Float penetration2) {
		this.penetration2 = penetration2;
	}

	public Float getPenetration3() {
		return penetration3;
	}

	public void setPenetration3(Float penetration3) {
		this.penetration3 = penetration3;
	}

	public Double getAvgpenetration() {
		return avgpenetration;
	}

	public void setAvgpenetration(Double avgpenetration) {
		this.avgpenetration = avgpenetration;
	}

	public Float getSoftenpoint1() {
		return softenpoint1;
	}

	public void setSoftenpoint1(Float softenpoint1) {
		this.softenpoint1 = softenpoint1;
	}

	public Float getSoftenpoint2() {
		return softenpoint2;
	}

	public void setSoftenpoint2(Float softenpoint2) {
		this.softenpoint2 = softenpoint2;
	}

	public Double getAvgsoftenpoint() {
		return avgsoftenpoint;
	}

	public void setAvgsoftenpoint(Double avgsoftenpoint) {
		this.avgsoftenpoint = avgsoftenpoint;
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

	public Double getAvgstab() {
		return avgstab;
	}

	public void setAvgstab(Double avgstab) {
		this.avgstab = avgstab;
	}

	public Double getAvgflow() {
		return avgflow;
	}

	public void setAvgflow(Double avgflow) {
		this.avgflow = avgflow;
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

	public int getMerge() {
		return merge;
	}

	public void setMerge(int merge) {
		this.merge = merge;
	}
	
}
