package com.mixingStation.model.asphalt;

import java.util.Date;

/**
 * 沥青生产数据级配分析
 * 
 * @author Administrator
 *
 */
public class AsphaltGradDataAnalysis  {
		private int id;
		private int proId;// 生产数据Id
		private String CurveType;// 曲线类型
		// 级配id
		private int analysisId;
		// 筛孔id
		private int meshId;
		// 1#仓
		private double ware1;
		// 2#仓
		private double ware2;
		// 3#仓
		private double ware3;
		// 4#仓
		private double ware4;
		// 5#仓
		private double ware5;
		// 6#仓
		private double ware6;
		// 冷粉仓1
		private double coldWare1;
		// 冷粉仓2
		private double coldWare2;
		// 热粉仓
		private double hotWare;
		// 上限
		private double upperLimit;
		// 下限
		private double lowerLimit;
		private int valid_Flag;// 有效标识
		private int upload;// 上传标识
		private double meshSize;
		
		public double getMeshSize() {
			return meshSize;
		}
		public void setMeshSize(double meshSize) {
			this.meshSize = meshSize;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getProId() {
			return proId;
		}
		public void setProId(int proId) {
			this.proId = proId;
		}
		public String getCurveType() {
			return CurveType;
		}
		public void setCurveType(String curveType) {
			CurveType = curveType;
		}
		public int getAnalysisId() {
			return analysisId;
		}
		public void setAnalysisId(int analysisId) {
			this.analysisId = analysisId;
		}
		public int getMeshId() {
			return meshId;
		}
		public void setMeshId(int meshId) {
			this.meshId = meshId;
		}
		public double getWare1() {
			return ware1;
		}
		public void setWare1(double ware1) {
			this.ware1 = ware1;
		}
		public double getWare2() {
			return ware2;
		}
		public void setWare2(double ware2) {
			this.ware2 = ware2;
		}
		public double getWare3() {
			return ware3;
		}
		public void setWare3(double ware3) {
			this.ware3 = ware3;
		}
		public double getWare4() {
			return ware4;
		}
		public void setWare4(double ware4) {
			this.ware4 = ware4;
		}
		public double getWare5() {
			return ware5;
		}
		public void setWare5(double ware5) {
			this.ware5 = ware5;
		}
		public double getWare6() {
			return ware6;
		}
		public void setWare6(double ware6) {
			this.ware6 = ware6;
		}
		public double getColdWare1() {
			return coldWare1;
		}
		public void setColdWare1(double coldWare1) {
			this.coldWare1 = coldWare1;
		}
		public double getColdWare2() {
			return coldWare2;
		}
		public void setColdWare2(double coldWare2) {
			this.coldWare2 = coldWare2;
		}
		public double getHotWare() {
			return hotWare;
		}
		public void setHotWare(double hotWare) {
			this.hotWare = hotWare;
		}
		public double getUpperLimit() {
			return upperLimit;
		}
		public void setUpperLimit(double upperLimit) {
			this.upperLimit = upperLimit;
		}
		public double getLowerLimit() {
			return lowerLimit;
		}
		public void setLowerLimit(double lowerLimit) {
			this.lowerLimit = lowerLimit;
		}
		public int getValid_Flag() {
			return valid_Flag;
		}
		public void setValid_Flag(int valid_Flag) {
			this.valid_Flag = valid_Flag;
		}
		public int getUpload() {
			return upload;
		}
		public void setUpload(int upload) {
			this.upload = upload;
		}

}
