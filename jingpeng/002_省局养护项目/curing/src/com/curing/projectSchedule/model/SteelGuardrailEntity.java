package com.curing.projectSchedule.model;
public class SteelGuardrailEntity {
	private String Id;
	private String ProjectId; // 项目id
	private String PileNumber; // 设置桩号
	private Integer BridgePart; // 桥梁过渡段（个）
	private Double OrdinaryPart; // 普通段（米）
	private Integer AbductionPart; // 外展段（个）
	private Double GuardrailLeft; // 护栏长度左侧（m）
	private Double GuardrailRight; // 护栏长度右侧（m）
	private Integer SteelPlate432; // 波形钢板L=4.32米（节）
	private Integer SteelPlate417; // 波形钢板L=4.17米（节）
	private Integer SteelColumn250; // φ140钢立柱L=2.50米立柱（根）
	private Integer SteelColumn250Strengthen; // φ140钢立柱L=2.50米加强立柱（根）
	private Integer SteelColumn165; // φ140钢立柱K=1.65米立柱（根）
	private Integer SteelColumn165Strengthen; // φ140钢立柱K=1.65米加强立柱（根）
	private Double EndD1; // 半圆形端头（个）D-1（重10.8）
	private Double EndRT1; // 半圆形端头（个）RT1（重22.1）
	private Integer FacadeMark; // 立面标记（个）
	private Double TransitionPlate; // 过渡段材料数量（Kg）板
	private Double TransitionColumn; // 过渡段材料数量（Kg）柱
	private Double TransitionEmbedment; // 过渡段材料数量（Kg）预埋板
	private Double OrdinaryPlate; // 普通段材料数量（Kg）板
	private Double OrdinaryColumn; // 普通段材料数量（Kg）柱
	private Double AbductionPlate; // 外展段材料数量（Kg）板
	private Double AbductionColumn; // 外展段材料数量（Kg）柱
	private Double AbductionC20; // 外展段材料数量（Kg）C20
	private String Remarks; // 备注
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
	public String getProjectId() {
		return ProjectId;
	}
	public void setProjectId(String projectId) {
		ProjectId = projectId;
	}
	public String getPileNumber() {
		return PileNumber;
	}
	public void setPileNumber(String pileNumber) {
		PileNumber = pileNumber;
	}
	public Integer getBridgePart() {
		return BridgePart;
	}
	public void setBridgePart(Integer bridgePart) {
		BridgePart = bridgePart;
	}
	public Double getOrdinaryPart() {
		return OrdinaryPart;
	}
	public void setOrdinaryPart(Double ordinaryPart) {
		OrdinaryPart = ordinaryPart;
	}
	public Integer getAbductionPart() {
		return AbductionPart;
	}
	public void setAbductionPart(Integer abductionPart) {
		AbductionPart = abductionPart;
	}
	public Double getGuardrailLeft() {
		return GuardrailLeft;
	}
	public void setGuardrailLeft(Double guardrailLeft) {
		GuardrailLeft = guardrailLeft;
	}
	public Double getGuardrailRight() {
		return GuardrailRight;
	}
	public void setGuardrailRight(Double guardrailRight) {
		GuardrailRight = guardrailRight;
	}
	public Integer getSteelPlate432() {
		return SteelPlate432;
	}
	public void setSteelPlate432(Integer steelPlate432) {
		SteelPlate432 = steelPlate432;
	}
	public Integer getSteelPlate417() {
		return SteelPlate417;
	}
	public void setSteelPlate417(Integer steelPlate417) {
		SteelPlate417 = steelPlate417;
	}
	public Integer getSteelColumn250() {
		return SteelColumn250;
	}
	public void setSteelColumn250(Integer steelColumn250) {
		SteelColumn250 = steelColumn250;
	}
	public Integer getSteelColumn250Strengthen() {
		return SteelColumn250Strengthen;
	}
	public void setSteelColumn250Strengthen(Integer steelColumn250Strengthen) {
		SteelColumn250Strengthen = steelColumn250Strengthen;
	}
	public Integer getSteelColumn165() {
		return SteelColumn165;
	}
	public void setSteelColumn165(Integer steelColumn165) {
		SteelColumn165 = steelColumn165;
	}
	public Integer getSteelColumn165Strengthen() {
		return SteelColumn165Strengthen;
	}
	public void setSteelColumn165Strengthen(Integer steelColumn165Strengthen) {
		SteelColumn165Strengthen = steelColumn165Strengthen;
	}
	public Double getEndD1() {
		return EndD1;
	}
	public void setEndD1(Double endD1) {
		EndD1 = endD1;
	}
	public Double getEndRT1() {
		return EndRT1;
	}
	public void setEndRT1(Double endRT1) {
		EndRT1 = endRT1;
	}
	public Integer getFacadeMark() {
		return FacadeMark;
	}
	public void setFacadeMark(Integer facadeMark) {
		FacadeMark = facadeMark;
	}
	public Double getTransitionPlate() {
		return TransitionPlate;
	}
	public void setTransitionPlate(Double transitionPlate) {
		TransitionPlate = transitionPlate;
	}
	public Double getTransitionColumn() {
		return TransitionColumn;
	}
	public void setTransitionColumn(Double transitionColumn) {
		TransitionColumn = transitionColumn;
	}
	public Double getTransitionEmbedment() {
		return TransitionEmbedment;
	}
	public void setTransitionEmbedment(Double transitionEmbedment) {
		TransitionEmbedment = transitionEmbedment;
	}
	public Double getOrdinaryPlate() {
		return OrdinaryPlate;
	}
	public void setOrdinaryPlate(Double ordinaryPlate) {
		OrdinaryPlate = ordinaryPlate;
	}
	public Double getOrdinaryColumn() {
		return OrdinaryColumn;
	}
	public void setOrdinaryColumn(Double ordinaryColumn) {
		OrdinaryColumn = ordinaryColumn;
	}
	public Double getAbductionPlate() {
		return AbductionPlate;
	}
	public void setAbductionPlate(Double abductionPlate) {
		AbductionPlate = abductionPlate;
	}
	public Double getAbductionColumn() {
		return AbductionColumn;
	}
	public void setAbductionColumn(Double abductionColumn) {
		AbductionColumn = abductionColumn;
	}
	public Double getAbductionC20() {
		return AbductionC20;
	}
	public void setAbductionC20(Double abductionC20) {
		AbductionC20 = abductionC20;
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
