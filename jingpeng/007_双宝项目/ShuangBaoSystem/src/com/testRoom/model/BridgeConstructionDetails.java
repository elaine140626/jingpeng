package com.testRoom.model;

// 桥梁施工明细表
public class BridgeConstructionDetails {
	private Integer Id;  
	private String UniqueIdentifier;         // 试验室唯一标识
	private Integer ActualCompletionId;      // 实际完成量id
	private Integer BridgeId;                // 各标段桥梁明细id
	private String Pile_Foundation;          // 桩基施工进度
	private String Pile_TiedBeam;            // 桩系梁施工进度
	private String Abutment;                 // 桥台施工进度
	private String Caps;                     // 承台施工进度
	private String Platform_Body;            // 台身施工进度
	private String Platform_Cap;             // 台帽施工进度
	private String Ear_BackWall;             // 耳背墙施工进度
	private String Bazi_Wall;                // 八字墙施工进度
	private String Pier_Shaft;               // 墩身施工进度
	private String Pier_TiedBeam;            // 墩系梁施工进度
	private String Coping;                   // 盖梁施工进度
	private String Support_Cushion;          // 支座垫石施工进度
	private String Baffle_Block;             // 挡块施工进度
	private String Beam_SlabPrefabrication;  // 梁板预制施工进度
	private String CastInPlace_Beam;         // 现浇梁施工进度
	private String Bearing;                  // 支座施工进度
	private String Diaphragm;                // 横隔板施工进度
	private String Wet_Joint;                // 湿接缝施工进度
	private String BridgeDeck_System;        // 桥面系施工进度
	private String Bridgehead_Slab;          // 桥头搭板施工进度
	private String Guardrail;                // 护栏施工进度
	private String AntiCollision_Wall;       // 防撞墙施工进度
	private String Expansion_Joint;          // 伸缩缝施工进度
	private int Isdel;
	private String Creater;
	private String CreaterDate;
	private String Reviser;
	private String ReviserDate;
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getUniqueIdentifier() {
		return UniqueIdentifier;
	}
	public void setUniqueIdentifier(String uniqueIdentifier) {
		UniqueIdentifier = uniqueIdentifier;
	}
	public Integer getActualCompletionId() {
		return ActualCompletionId;
	}
	public void setActualCompletionId(Integer actualCompletionId) {
		ActualCompletionId = actualCompletionId;
	}
	public Integer getBridgeId() {
		return BridgeId;
	}
	public void setBridgeId(Integer bridgeId) {
		BridgeId = bridgeId;
	}
	public String getPile_Foundation() {
		return Pile_Foundation;
	}
	public void setPile_Foundation(String pile_Foundation) {
		Pile_Foundation = pile_Foundation;
	}
	public String getPile_TiedBeam() {
		return Pile_TiedBeam;
	}
	public void setPile_TiedBeam(String pile_TiedBeam) {
		Pile_TiedBeam = pile_TiedBeam;
	}
	public String getAbutment() {
		return Abutment;
	}
	public void setAbutment(String abutment) {
		Abutment = abutment;
	}
	public String getCaps() {
		return Caps;
	}
	public void setCaps(String caps) {
		Caps = caps;
	}
	public String getPlatform_Body() {
		return Platform_Body;
	}
	public void setPlatform_Body(String platform_Body) {
		Platform_Body = platform_Body;
	}
	public String getPlatform_Cap() {
		return Platform_Cap;
	}
	public void setPlatform_Cap(String platform_Cap) {
		Platform_Cap = platform_Cap;
	}
	public String getEar_BackWall() {
		return Ear_BackWall;
	}
	public void setEar_BackWall(String ear_BackWall) {
		Ear_BackWall = ear_BackWall;
	}
	public String getBazi_Wall() {
		return Bazi_Wall;
	}
	public void setBazi_Wall(String bazi_Wall) {
		Bazi_Wall = bazi_Wall;
	}
	public String getPier_Shaft() {
		return Pier_Shaft;
	}
	public void setPier_Shaft(String pier_Shaft) {
		Pier_Shaft = pier_Shaft;
	}
	public String getPier_TiedBeam() {
		return Pier_TiedBeam;
	}
	public void setPier_TiedBeam(String pier_TiedBeam) {
		Pier_TiedBeam = pier_TiedBeam;
	}
	public String getCoping() {
		return Coping;
	}
	public void setCoping(String coping) {
		Coping = coping;
	}
	public String getSupport_Cushion() {
		return Support_Cushion;
	}
	public void setSupport_Cushion(String support_Cushion) {
		Support_Cushion = support_Cushion;
	}
	public String getBaffle_Block() {
		return Baffle_Block;
	}
	public void setBaffle_Block(String baffle_Block) {
		Baffle_Block = baffle_Block;
	}
	public String getBeam_SlabPrefabrication() {
		return Beam_SlabPrefabrication;
	}
	public void setBeam_SlabPrefabrication(String beam_SlabPrefabrication) {
		Beam_SlabPrefabrication = beam_SlabPrefabrication;
	}
	public String getCastInPlace_Beam() {
		return CastInPlace_Beam;
	}
	public void setCastInPlace_Beam(String castInPlace_Beam) {
		CastInPlace_Beam = castInPlace_Beam;
	}
	public String getBearing() {
		return Bearing;
	}
	public void setBearing(String bearing) {
		Bearing = bearing;
	}
	public String getDiaphragm() {
		return Diaphragm;
	}
	public void setDiaphragm(String diaphragm) {
		Diaphragm = diaphragm;
	}
	public String getWet_Joint() {
		return Wet_Joint;
	}
	public void setWet_Joint(String wet_Joint) {
		Wet_Joint = wet_Joint;
	}
	public String getBridgeDeck_System() {
		return BridgeDeck_System;
	}
	public void setBridgeDeck_System(String bridgeDeck_System) {
		BridgeDeck_System = bridgeDeck_System;
	}
	public String getBridgehead_Slab() {
		return Bridgehead_Slab;
	}
	public void setBridgehead_Slab(String bridgehead_Slab) {
		Bridgehead_Slab = bridgehead_Slab;
	}
	public String getGuardrail() {
		return Guardrail;
	}
	public void setGuardrail(String guardrail) {
		Guardrail = guardrail;
	}
	public String getAntiCollision_Wall() {
		return AntiCollision_Wall;
	}
	public void setAntiCollision_Wall(String antiCollision_Wall) {
		AntiCollision_Wall = antiCollision_Wall;
	}
	public String getExpansion_Joint() {
		return Expansion_Joint;
	}
	public void setExpansion_Joint(String expansion_Joint) {
		Expansion_Joint = expansion_Joint;
	}
	public int getIsdel() {
		return Isdel;
	}
	public void setIsdel(int isdel) {
		Isdel = isdel;
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
