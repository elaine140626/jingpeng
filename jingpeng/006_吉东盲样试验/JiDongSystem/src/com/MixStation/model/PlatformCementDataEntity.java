package com.MixStation.model;

public class PlatformCementDataEntity {
	/*private String I_Id;
	private int serialNumber;// 序号
	private String Org_ID;//组织机构ID
	private String Product_ID;//产品Id
	private String Equ_ID;//拌和机Id
	private String i_cons_Mix_ID;//施工配合比ID
	private String ProdPlan_No;//生产计划编号
	private String str_collect_Date;//采集时间
	private String d_set_Cement1;//水泥仓1设定值
	private String d_set_Cement2;//水泥仓2设定值
	private String d_set_Cement3;//水泥仓3设定值
	private String d_set_Cement4;//水泥仓4设定值
	private String d_weight_Cement1;//水泥仓1采集值
	private String d_weight_Cement2;//水泥仓2采集值
	private String d_weight_Cement3;//水泥仓3采集值
	private String d_weight_Cement4;//水泥仓4采集值
	private String d_set_Aggregate1;//骨料仓1设定值
	private String d_set_Aggregate2;//骨料仓2设定值
	private String d_set_Aggregate3;//骨料仓3设定值
	private String d_set_Aggregate4;//骨料仓4设定值
	private String d_set_Aggregate5;//骨料仓5设定值
	private String d_set_Aggregate6;//骨料仓6设定值
	private String d_weight_Aggregate1;//骨料仓1采集值
	private String d_weight_Aggregate2;//骨料仓2采集值
	private String d_weight_Aggregate3;//骨料仓3采集值
	private String d_weight_Aggregate4;//骨料仓4采集值
	private String d_weight_Aggregate5;//骨料仓5采集值
	private String d_weight_Aggregate6;//骨料仓6采集值
	private String d_set_Water;//水仓设定值
	private String d_weight_Water;//水仓采集值
	private String d_set_Admixture1;//外掺剂1仓设定值
	private String d_set_Admixture2;//外掺剂2仓设定值
	private String d_weight_Admixture1;//外掺剂1仓采集值
	private String d_weight_Admixture2;//外掺剂2仓采集值
	private String Analysis_Result;//数据分析结果
	private String d_total_Weight;//拌和总量
	private String i_mix_Time; //拌和时间
	private String Temperature_Meter;
	private String Construction_Unit;//施工地点
	private String Proj_Pos;//工程部位
	private String str_equipment_Name;//设备名称
	private String str_equipment_Type;
	private String productInfo;//产品名称
	private String Proportion_Code;//生产配合比编号
	private String Grade_Code;//级配编号
	private String Valid_Flag;
	private String Org_Name;//拌和站名称
*/	
	private int id;//主键
	private int equ_Id;//设备id
	private String equipment_Name;//拌合机名称
	private int productId;//产品Id
	private String prodPlan_No;//生产计划编号
	private int org_Id;//组织 id
	private String  material_Name;//产品名称
	private String  material_Model;//产品型号
	private String product;//产品加型号
	private int bin_Relationship_Id;// 料仓对应关系ID
	private String collecti_Type;// 采集方式
	private String collect_Date;// 采集时间
	private int mix_Time;// 拌和时间
	private String Grade;//强度等级
	private String Disk_Number;//盘号
	private double set_Cement1;// 水泥仓1设定值
	private double set_Cement2;// 水泥仓2设定值
	private double set_Cement3;// 水泥仓3设定值
	private double set_Cement4;// 水泥仓4设定值
	private double set_Aggregate1;// 骨料仓1设定值
	private double set_Aggregate6;// 骨料仓6设定值
	private double set_Aggregate5;// 骨料仓5设定值
	private double set_Aggregate4;// 骨料仓4设定值
	private double set_Aggregate3;// 骨料仓3设定值
	private double set_Aggregate2;// 骨料仓2设定值
	private double set_Water;// 水仓设定值
	private double set_Admixture1;// 外掺剂1仓设定值
	private double set_Admixture2;// 外掺剂2仓设定值
	private double weight_Cement1;// 水泥仓1采集值
	private double weight_Cement2;// 水泥仓2采集值
	private double weight_Cement3;// 水泥仓3采集值
	private double weight_Cement4;// 水泥仓4采集值
	private double weight_Aggregate1;// 骨料仓1采集值
	private double weight_Aggregate2;// 骨料仓2采集值
	private double weight_Aggregate3;// 骨料仓3采集值
	private double weight_Aggregate4;// 骨料仓4采集值
	private double weight_Aggregate5;// 骨料仓5采集值
	private double weight_Aggregate6;// 骨料仓6采集值
	private double weight_Water;// 水仓采集值
	private double weight_Admixture1;// 外掺剂1仓采集值
	private double weight_Admixture2;// 外掺剂2仓采集值
	private double total_Weight;// 拌和总量
	private double mixing_Volume;// 拌和方量
	private String proj_Pos;// 施工地点
	private String watering_Site;// 浇灌部位
	private String analysis_Results;// 数据分析结果
	private int upload;// 上传标识
	private int alarm_Info_Id;// 预警处理信息ID
	private String create_Date;// 创建时间
	private String construction_Unit;//施工单位
	private int valid_Flag;// 有效标识
	private int total_number;//总条数
	private int serialNumber;//序列
	private int cementWeight;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEqu_Id() {
		return equ_Id;
	}
	public void setEqu_Id(int equ_Id) {
		this.equ_Id = equ_Id;
	}
	public String getEquipment_Name() {
		return equipment_Name;
	}
	public void setEquipment_Name(String equipment_Name) {
		this.equipment_Name = equipment_Name;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProdPlan_No() {
		return prodPlan_No;
	}
	public void setProdPlan_No(String prodPlan_No) {
		this.prodPlan_No = prodPlan_No;
	}
	public int getOrg_Id() {
		return org_Id;
	}
	public void setOrg_Id(int org_Id) {
		this.org_Id = org_Id;
	}
	public String getMaterial_Name() {
		return material_Name;
	}
	public void setMaterial_Name(String material_Name) {
		this.material_Name = material_Name;
	}
	public String getMaterial_Model() {
		return material_Model;
	}
	public void setMaterial_Model(String material_Model) {
		this.material_Model = material_Model;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public int getBin_Relationship_Id() {
		return bin_Relationship_Id;
	}
	public void setBin_Relationship_Id(int bin_Relationship_Id) {
		this.bin_Relationship_Id = bin_Relationship_Id;
	}
	public String getCollecti_Type() {
		return collecti_Type;
	}
	public void setCollecti_Type(String collecti_Type) {
		this.collecti_Type = collecti_Type;
	}
	public String getCollect_Date() {
		return collect_Date;
	}
	public void setCollect_Date(String collect_Date) {
		this.collect_Date = collect_Date;
	}
	public int getMix_Time() {
		return mix_Time;
	}
	public void setMix_Time(int mix_Time) {
		this.mix_Time = mix_Time;
	}
	public String getGrade() {
		return Grade;
	}
	public void setGrade(String grade) {
		Grade = grade;
	}
	public String getDisk_Number() {
		return Disk_Number;
	}
	public void setDisk_Number(String disk_Number) {
		Disk_Number = disk_Number;
	}
	public double getSet_Cement1() {
		return set_Cement1;
	}
	public void setSet_Cement1(double set_Cement1) {
		this.set_Cement1 = set_Cement1;
	}
	public double getSet_Cement2() {
		return set_Cement2;
	}
	public void setSet_Cement2(double set_Cement2) {
		this.set_Cement2 = set_Cement2;
	}
	public double getSet_Cement3() {
		return set_Cement3;
	}
	public void setSet_Cement3(double set_Cement3) {
		this.set_Cement3 = set_Cement3;
	}
	public double getSet_Cement4() {
		return set_Cement4;
	}
	public void setSet_Cement4(double set_Cement4) {
		this.set_Cement4 = set_Cement4;
	}
	public double getSet_Aggregate1() {
		return set_Aggregate1;
	}
	public void setSet_Aggregate1(double set_Aggregate1) {
		this.set_Aggregate1 = set_Aggregate1;
	}
	public double getSet_Aggregate6() {
		return set_Aggregate6;
	}
	public void setSet_Aggregate6(double set_Aggregate6) {
		this.set_Aggregate6 = set_Aggregate6;
	}
	public double getSet_Aggregate5() {
		return set_Aggregate5;
	}
	public void setSet_Aggregate5(double set_Aggregate5) {
		this.set_Aggregate5 = set_Aggregate5;
	}
	public double getSet_Aggregate4() {
		return set_Aggregate4;
	}
	public void setSet_Aggregate4(double set_Aggregate4) {
		this.set_Aggregate4 = set_Aggregate4;
	}
	public double getSet_Aggregate3() {
		return set_Aggregate3;
	}
	public void setSet_Aggregate3(double set_Aggregate3) {
		this.set_Aggregate3 = set_Aggregate3;
	}
	public double getSet_Aggregate2() {
		return set_Aggregate2;
	}
	public void setSet_Aggregate2(double set_Aggregate2) {
		this.set_Aggregate2 = set_Aggregate2;
	}
	public double getSet_Water() {
		return set_Water;
	}
	public void setSet_Water(double set_Water) {
		this.set_Water = set_Water;
	}
	public double getSet_Admixture1() {
		return set_Admixture1;
	}
	public void setSet_Admixture1(double set_Admixture1) {
		this.set_Admixture1 = set_Admixture1;
	}
	public double getSet_Admixture2() {
		return set_Admixture2;
	}
	public void setSet_Admixture2(double set_Admixture2) {
		this.set_Admixture2 = set_Admixture2;
	}
	public double getWeight_Cement1() {
		return weight_Cement1;
	}
	public void setWeight_Cement1(double weight_Cement1) {
		this.weight_Cement1 = weight_Cement1;
	}
	public double getWeight_Cement2() {
		return weight_Cement2;
	}
	public void setWeight_Cement2(double weight_Cement2) {
		this.weight_Cement2 = weight_Cement2;
	}
	public double getWeight_Cement3() {
		return weight_Cement3;
	}
	public void setWeight_Cement3(double weight_Cement3) {
		this.weight_Cement3 = weight_Cement3;
	}
	public double getWeight_Cement4() {
		return weight_Cement4;
	}
	public void setWeight_Cement4(double weight_Cement4) {
		this.weight_Cement4 = weight_Cement4;
	}
	public double getWeight_Aggregate1() {
		return weight_Aggregate1;
	}
	public void setWeight_Aggregate1(double weight_Aggregate1) {
		this.weight_Aggregate1 = weight_Aggregate1;
	}
	public double getWeight_Aggregate2() {
		return weight_Aggregate2;
	}
	public void setWeight_Aggregate2(double weight_Aggregate2) {
		this.weight_Aggregate2 = weight_Aggregate2;
	}
	public double getWeight_Aggregate3() {
		return weight_Aggregate3;
	}
	public void setWeight_Aggregate3(double weight_Aggregate3) {
		this.weight_Aggregate3 = weight_Aggregate3;
	}
	public double getWeight_Aggregate4() {
		return weight_Aggregate4;
	}
	public void setWeight_Aggregate4(double weight_Aggregate4) {
		this.weight_Aggregate4 = weight_Aggregate4;
	}
	public double getWeight_Aggregate5() {
		return weight_Aggregate5;
	}
	public void setWeight_Aggregate5(double weight_Aggregate5) {
		this.weight_Aggregate5 = weight_Aggregate5;
	}
	public double getWeight_Aggregate6() {
		return weight_Aggregate6;
	}
	public void setWeight_Aggregate6(double weight_Aggregate6) {
		this.weight_Aggregate6 = weight_Aggregate6;
	}
	public double getWeight_Water() {
		return weight_Water;
	}
	public void setWeight_Water(double weight_Water) {
		this.weight_Water = weight_Water;
	}
	public double getWeight_Admixture1() {
		return weight_Admixture1;
	}
	public void setWeight_Admixture1(double weight_Admixture1) {
		this.weight_Admixture1 = weight_Admixture1;
	}
	public double getWeight_Admixture2() {
		return weight_Admixture2;
	}
	public void setWeight_Admixture2(double weight_Admixture2) {
		this.weight_Admixture2 = weight_Admixture2;
	}
	public double getTotal_Weight() {
		return total_Weight;
	}
	public void setTotal_Weight(double total_Weight) {
		this.total_Weight = total_Weight;
	}
	public double getMixing_Volume() {
		return mixing_Volume;
	}
	public void setMixing_Volume(double mixing_Volume) {
		this.mixing_Volume = mixing_Volume;
	}
	public String getProj_Pos() {
		return proj_Pos;
	}
	public void setProj_Pos(String proj_Pos) {
		this.proj_Pos = proj_Pos;
	}
	public String getWatering_Site() {
		return watering_Site;
	}
	public void setWatering_Site(String watering_Site) {
		this.watering_Site = watering_Site;
	}
	public String getAnalysis_Results() {
		return analysis_Results;
	}
	public void setAnalysis_Results(String analysis_Results) {
		this.analysis_Results = analysis_Results;
	}
	public int getUpload() {
		return upload;
	}
	public void setUpload(int upload) {
		this.upload = upload;
	}
	public int getAlarm_Info_Id() {
		return alarm_Info_Id;
	}
	public void setAlarm_Info_Id(int alarm_Info_Id) {
		this.alarm_Info_Id = alarm_Info_Id;
	}
	public String getCreate_Date() {
		return create_Date;
	}
	public void setCreate_Date(String create_Date) {
		this.create_Date = create_Date;
	}
	public String getConstruction_Unit() {
		return construction_Unit;
	}
	public void setConstruction_Unit(String construction_Unit) {
		this.construction_Unit = construction_Unit;
	}
	public int getValid_Flag() {
		return valid_Flag;
	}
	public void setValid_Flag(int valid_Flag) {
		this.valid_Flag = valid_Flag;
	}
	public int getTotal_number() {
		return total_number;
	}
	public void setTotal_number(int total_number) {
		this.total_number = total_number;
	}
	public int getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}
	public int getCementWeight() {
		return cementWeight;
	}
	public void setCementWeight(int cementWeight) {
		this.cementWeight = cementWeight;
	}
	
}
