// 实际完成量id
var id = "";

// 标识
var flag;

// 更新之前的实际施工数据
var befData = "";
// 工程量数据
var resultData = "";
var pdFlag = 0;
$(function() {
	id = sessionStorage.getItem("id");
	flag = sessionStorage.getItem("flag");
	sessionStorage.clear();
	if(id != null && id != ''){
		$.ajax({
			type : "POST",
			url : "../../ConstructionProgress/getInfoById.action",
			data:{"id":id},
			dataType : "json",
			success : function(data) {
				// 实际完成工程量信息
				var actualCompletionQuantity = data.actualCompletionQuantity;
				// 累计量change事件
				befData = actualCompletionQuantity;
				pdFlag = 1;
				// 桥梁明细信息
				var bridgeConstructionDetails = data.bridgeConstructionDetails;
				if(actualCompletionQuantity != null && actualCompletionQuantity.length > 0){
					// 施工单位不可编辑
					$("#constructionOrgs").val(actualCompletionQuantity[0].uniqueIdentifier);
					$("#constructionOrgs").attr("disabled",true);
					// 获取显示桥梁信息
					changeOrg(actualCompletionQuantity[0].uniqueIdentifier); 

					SetFromValues($("#form"), actualCompletionQuantity[0]);
					// 计算完成%
					if(actualCompletionQuantity[0].subgrade_Total_1 != null && actualCompletionQuantity[0].subgrade_Total_1 != 0){
						var subgrade_Total_2 = (actualCompletionQuantity[0].sumSubgrade_Total/actualCompletionQuantity[0].subgrade_Total_1*100).toFixed(2);
						$("#subgrade_Total_2").val(subgrade_Total_2 + "%");
					}
					if(actualCompletionQuantity[0].cushion_LeftAmplitude_1 != null && actualCompletionQuantity[0].cushion_LeftAmplitude_1 != 0){
						var cushion_LeftAmplitude_2 = (actualCompletionQuantity[0].sumCushion_LeftAmplitude/actualCompletionQuantity[0].cushion_LeftAmplitude_1*100).toFixed(2);
						$("#cushion_LeftAmplitude_2").val(cushion_LeftAmplitude_2 + "%");
					}
					if(actualCompletionQuantity[0].cushion_RightAmplitude_1 != null && actualCompletionQuantity[0].cushion_RightAmplitude_1 != 0){
						var cushion_RightAmplitude_2 = (actualCompletionQuantity[0].sumCushion_RightAmplitude/actualCompletionQuantity[0].cushion_RightAmplitude_1*100).toFixed(2);
						$("#cushion_RightAmplitude_2").val(cushion_RightAmplitude_2 + "%");
					}
					if(actualCompletionQuantity[0].subbase_LeftAmplitude_1 != null && actualCompletionQuantity[0].subbase_LeftAmplitude_1 != 0){
						var subbase_LeftAmplitude_2 = (actualCompletionQuantity[0].sumSubbase_LeftAmplitude/actualCompletionQuantity[0].subbase_LeftAmplitude_1*100).toFixed(2);
						$("#subbase_LeftAmplitude_2").val(subbase_LeftAmplitude_2 + "%");
					}
					if(actualCompletionQuantity[0].subbase_RightAmplitude_1 != null && actualCompletionQuantity[0].subbase_RightAmplitude_1 != 0){
						var subbase_RightAmplitude_2 = (actualCompletionQuantity[0].sumSubbase_RightAmplitude/actualCompletionQuantity[0].subbase_RightAmplitude_1*100).toFixed(2);
						$("#subbase_RightAmplitude_2").val(subbase_RightAmplitude_2 + "%");
					}
					if(actualCompletionQuantity[0].substrate_LeftAmplitude_1 != null && actualCompletionQuantity[0].substrate_LeftAmplitude_1 != 0){
						var substrate_LeftAmplitude_2 = (actualCompletionQuantity[0].sumSubstrate_LeftAmplitude/actualCompletionQuantity[0].substrate_LeftAmplitude_1*100).toFixed(2);
						$("#substrate_LeftAmplitude_2").val(substrate_LeftAmplitude_2 + "%");
					}
					if(actualCompletionQuantity[0].substrate_RightAmplitude_1 != null && actualCompletionQuantity[0].substrate_RightAmplitude_1 != 0){
						var substrate_RightAmplitude_2 = (actualCompletionQuantity[0].sumSubstrate_RightAmplitude/actualCompletionQuantity[0].substrate_RightAmplitude_1*100).toFixed(2);
						$("#substrate_RightAmplitude_2").val(substrate_RightAmplitude_2 + "%");
					}
					if(actualCompletionQuantity[0].lowerLayer_LeftAmplitude_1 != null && actualCompletionQuantity[0].lowerLayer_LeftAmplitude_1 != 0){
						var lowerLayer_LeftAmplitude_2 = (actualCompletionQuantity[0].sumLowerLayer_LeftAmplitude/actualCompletionQuantity[0].lowerLayer_LeftAmplitude_1*100).toFixed(2);
						$("#lowerLayer_LeftAmplitude_2").val(lowerLayer_LeftAmplitude_2 + "%");
					}
					if(actualCompletionQuantity[0].lowerLayer_RightAmplitude_1 != null && actualCompletionQuantity[0].lowerLayer_RightAmplitude_1 != 0){
						var lowerLayer_RightAmplitude_2 = (actualCompletionQuantity[0].sumLowerLayer_RightAmplitude/actualCompletionQuantity[0].lowerLayer_RightAmplitude_1*100).toFixed(2);
						$("#lowerLayer_RightAmplitude_2").val(lowerLayer_RightAmplitude_2 + "%");
					}
					if(actualCompletionQuantity[0].mesosphere_LeftAmplitude_1 != null && actualCompletionQuantity[0].mesosphere_LeftAmplitude_1 != 0){
						var mesosphere_LeftAmplitude_2 = (actualCompletionQuantity[0].sumMesosphere_LeftAmplitude/actualCompletionQuantity[0].mesosphere_LeftAmplitude_1*100).toFixed(2);
						$("#mesosphere_LeftAmplitude_2").val(mesosphere_LeftAmplitude_2 + "%");
					}
					if(actualCompletionQuantity[0].mesosphere_RightAmplitude_1 != null && actualCompletionQuantity[0].mesosphere_RightAmplitude_1 != 0){
						var mesosphere_RightAmplitude_2 = (actualCompletionQuantity[0].sumMesosphere_RightAmplitude/actualCompletionQuantity[0].mesosphere_RightAmplitude_1*100).toFixed(2);
						$("#mesosphere_RightAmplitude_2").val(mesosphere_RightAmplitude_2 + "%");
					}
					if(actualCompletionQuantity[0].upperLayer_LeftAmplitude_1 != null && actualCompletionQuantity[0].upperLayer_LeftAmplitude_1 != 0){
						var upperLayer_LeftAmplitude_2 = (actualCompletionQuantity[0].sumUpperLayer_LeftAmplitude/actualCompletionQuantity[0].upperLayer_LeftAmplitude_1*100).toFixed(2);
						$("#upperLayer_LeftAmplitude_2").val(upperLayer_LeftAmplitude_2 + "%");
					}
					if(actualCompletionQuantity[0].upperLayer_RightAmplitude_1 != null && actualCompletionQuantity[0].upperLayer_RightAmplitude_1 != 0){
						var upperLayer_RightAmplitude_2 = (actualCompletionQuantity[0].sumUpperLayer_RightAmplitude/actualCompletionQuantity[0].upperLayer_RightAmplitude_1*100).toFixed(2);
						$("#upperLayer_RightAmplitude_2").val(upperLayer_RightAmplitude_2 + "%");
					}
				}
				
				// 桥梁明细赋值
				if(bridgeConstructionDetails != null && bridgeConstructionDetails.length > 0){
					for(var i= 0 ; i < bridgeConstructionDetails.length; i++){
					    $("#"+bridgeConstructionDetails[i].bridgeId+"_pileFoundation").val(bridgeConstructionDetails[i].pile_Foundation);
						$("#"+bridgeConstructionDetails[i].bridgeId+"_pileTiedBeam").val(bridgeConstructionDetails[i].pile_TiedBeam);
						$("#"+bridgeConstructionDetails[i].bridgeId+"_abutment").val(bridgeConstructionDetails[i].abutment);
						$("#"+bridgeConstructionDetails[i].bridgeId+"_caps").val(bridgeConstructionDetails[i].caps);
						$("#"+bridgeConstructionDetails[i].bridgeId+"_platformBody").val(bridgeConstructionDetails[i].platform_Body);
						$("#"+bridgeConstructionDetails[i].bridgeId+"_platformCap").val(bridgeConstructionDetails[i].platform_Cap);
						$("#"+bridgeConstructionDetails[i].bridgeId+"_earBackWall").val(bridgeConstructionDetails[i].ear_BackWall);
						$("#"+bridgeConstructionDetails[i].bridgeId+"_baziWall").val(bridgeConstructionDetails[i].bazi_Wall);
						$("#"+bridgeConstructionDetails[i].bridgeId+"_pierShaft").val(bridgeConstructionDetails[i].pier_Shaft);
						$("#"+bridgeConstructionDetails[i].bridgeId+"_pierTiedBeam").val(bridgeConstructionDetails[i].pier_TiedBeam);
						$("#"+bridgeConstructionDetails[i].bridgeId+"_coping").val(bridgeConstructionDetails[i].coping);
						$("#"+bridgeConstructionDetails[i].bridgeId+"_supportCushion").val(bridgeConstructionDetails[i].support_Cushion);
						$("#"+bridgeConstructionDetails[i].bridgeId+"_baffleBlock").val(bridgeConstructionDetails[i].baffle_Block);
						$("#"+bridgeConstructionDetails[i].bridgeId+"_beamSlabPrefabrication").val(bridgeConstructionDetails[i].beam_SlabPrefabrication);
						$("#"+bridgeConstructionDetails[i].bridgeId+"_castInPlaceBeam").val(bridgeConstructionDetails[i].castInPlace_Beam);
						$("#"+bridgeConstructionDetails[i].bridgeId+"_bearing").val(bridgeConstructionDetails[i].bearing);
						$("#"+bridgeConstructionDetails[i].bridgeId+"_diaphragm").val(bridgeConstructionDetails[i].diaphragm);
						$("#"+bridgeConstructionDetails[i].bridgeId+"_wetJoint").val(bridgeConstructionDetails[i].wet_Joint);
						$("#"+bridgeConstructionDetails[i].bridgeId+"_bridgeDeckSystem").val(bridgeConstructionDetails[i].bridgeDeck_System);
						$("#"+bridgeConstructionDetails[i].bridgeId+"_bridgeheadSlab").val(bridgeConstructionDetails[i].bridgehead_Slab);
						$("#"+bridgeConstructionDetails[i].bridgeId+"_guardrail").val(bridgeConstructionDetails[i].guardrail);
						$("#"+bridgeConstructionDetails[i].bridgeId+"_antiCollisionWall").val(bridgeConstructionDetails[i].antiCollision_Wall);
						$("#"+bridgeConstructionDetails[i].bridgeId+"_expansionJoint").val(bridgeConstructionDetails[i].expansion_Joint);
					}
				}
			}
		});
	}
	
	// 查看状态
	if(flag == 0){
		$("input").attr("readonly", true);
		$("textarea").attr('disabled','disabled');
		$("#saveAdd").hide();
	}else{
		$("#subgrade_Fill").attr("readonly", false);
		$("#subgrade_Excavation").attr("readonly", false);
		$("#subgrade_Total").attr("readonly", false);
		$("#cushion_LeftAmplitude").attr("readonly", false);
		$("#cushion_RightAmplitude").attr("readonly", false);
		$("#subbase_LeftAmplitude").attr("readonly", false);
		$("#subbase_RightAmplitude").attr("readonly", false);
		$("#substrate_LeftAmplitude").attr("readonly", false);
		$("#substrate_RightAmplitude").attr("readonly", false);
		$("#lowerLayer_LeftAmplitude").attr("readonly", false);
		$("#lowerLayer_RightAmplitude").attr("readonly", false);
		$("#mesosphere_LeftAmplitude").attr("readonly", false);
		$("#mesosphere_RightAmplitude").attr("readonly", false);
		$("#upperLayer_LeftAmplitude").attr("readonly", false);
		$("#upperLayer_RightAmplitude").attr("readonly", false);
		$("textarea").attr('disabled','');
		$("#saveAdd").show();
	}
});

//新增进度页面切换施工单位 桥梁名称更换，下方表格遍历新增，默认选中第一个
function changeOrg(i) {
	$('#bridgeName').empty();
	$('#brigdeTables').empty();
	var st = "";
	var tableStr = '';
	if(i != '') {	
		
		// 获取试验室对应的工程设计内容
		$.ajax({
			type : "POST",
			url : "../../ConstructionProgress/getEngineeringDesignContent.action",
			data:{"UniqueIdentifier":i},
			dataType : "json",
			success : function(data) {
				if(data != null && data.length > 0){
					resultData = data;
					$("#subgrade_Fill_1").val(data[0].subgrade_Fill);
					$("#subgrade_FillUnit").val(data[0].subgrade_FillUnit);
					$("#subgrade_Excavation_1").val(data[0].subgrade_Excavation);
					$("#subgrade_ExcavationUnit").val(data[0].subgrade_ExcavationUnit);
					$("#subgrade_Total_1").val(data[0].subgrade_Total);
					$("#subgrade_TotalUnit").val(data[0].subgrade_TotalUnit);
					$("#cushion_LeftAmplitude_1").val(data[0].cushion_LeftAmplitude);
					$("#cushion_LeftAmplitudeUnit").val(data[0].cushion_LeftAmplitudeUnit);
					$("#cushion_RightAmplitude_1").val(data[0].cushion_RightAmplitude);
					$("#cushion_RightAmplitudeUnit").val(data[0].cushion_RightAmplitudeUnit);
					$("#subbase_LeftAmplitude_1").val(data[0].subbase_LeftAmplitude);
					$("#subbase_LeftAmplitudeUnit").val(data[0].subbase_LeftAmplitudeUnit);
					$("#subbase_RightAmplitude_1").val(data[0].subbase_RightAmplitude);
					$("#subbase_RightAmplitudeUnit").val(data[0].subbase_RightAmplitudeUnit);
					$("#substrate_LeftAmplitude_1").val(data[0].substrate_LeftAmplitude);
					$("#substrate_LeftAmplitudeUnit").val(data[0].substrate_LeftAmplitudeUnit);
					$("#substrate_RightAmplitude_1").val(data[0].substrate_RightAmplitude);
					$("#substrate_RightAmplitudeUnit").val(data[0].substrate_RightAmplitudeUnit);
					$("#lowerLayer_LeftAmplitude_1").val(data[0].lowerLayer_LeftAmplitude);
					$("#lowerLayer_LeftAmplitudeUnit").val(data[0].lowerLayer_LeftAmplitudeUnit);
					$("#lowerLayer_RightAmplitude_1").val(data[0].lowerLayer_RightAmplitude);
					$("#lowerLayer_RightAmplitudeUnit").val(data[0].lowerLayer_RightAmplitudeUnit);
					$("#mesosphere_LeftAmplitude_1").val(data[0].mesosphere_LeftAmplitude);
					$("#mesosphere_LeftAmplitudeUnit").val(data[0].mesosphere_LeftAmplitudeUnit);
					$("#mesosphere_RightAmplitude_1").val(data[0].mesosphere_RightAmplitude);
					$("#mesosphere_RightAmplitudeUnit").val(data[0].mesosphere_RightAmplitudeUnit);
					$("#upperLayer_LeftAmplitude_1").val(data[0].upperLayer_LeftAmplitude);
					$("#upperLayer_LeftAmplitudeUnit").val(data[0].upperLayer_LeftAmplitudeUnit);
					$("#upperLayer_RightAmplitude_1").val(data[0].upperLayer_RightAmplitude);
					$("#upperLayer_RightAmplitudeUnit").val(data[0].upperLayer_RightAmplitudeUnit);
				}else{
					$("#table1 input").val("");
				}
			}
		});
		
		// 桥梁明细显示
		$.each(constructionDetalis,function(w,g) {
			if(g.name.indexOf(i) != -1 ) {
				for(var f = 0 ; f < g.bridge.length ; f ++) {
					st += "<option value="+g.bridge[f].id+" data-scan="+g.bridge[f].span+" data-mileage="+g.bridge[f].mileage+">"+ g.bridge[f].bridgeName +"</option>"
					tableStr += '<table id=' + i + '_' + g.bridge[f].id +' style="text-align:center" width="100%" border="1" cellpadding="0" cellspacing="0" class="fazhi tablealls">'
						+ '<tr><th width="20%" colspan="2" scope="col">桥梁结构</th><th width="80%"  scope="col">施工进度情况</th></tr>'
						+ '<tr><td width="9%" rowspan="13">桥梁结构</td><td width="11%">基桩</td><td><textarea id='+ g.bridge[f].id + '_pileFoundation' + ' style="width:99%;resize:none;height:50px;margin-top: 6px;"></textarea></td></tr>'
						+ '<tr><td>桩系梁</td><td><textarea id='+ g.bridge[f].id + '_pileTiedBeam' + ' style="width:99%;resize:none;height:50px;margin-top: 6px;" maxlength="200"></textarea></td></tr>'
						+ '<tr><td>桥台</td><td><textarea id='+ g.bridge[f].id + '_abutment' + ' style="width:99%;resize:none;height:50px;margin-top: 6px;" maxlength="200"></textarea></td></tr>'
						+ '<tr><td>承台</td><td><textarea id='+ g.bridge[f].id + '_caps' + ' style="width:99%;resize:none;height:50px;margin-top: 6px;" maxlength="200"></textarea></td></tr>'
						+ '<tr><td>台身</td><td><textarea id='+ g.bridge[f].id + '_platformBody' + ' style="width:99%;resize:none;height:50px;margin-top: 6px;" maxlength="200"></textarea></td></tr>'
						+ '<tr><td>台帽</td><td><textarea id='+ g.bridge[f].id + '_platformCap' + ' style="width:99%;resize:none;height:50px;margin-top: 6px;" maxlength="200"></textarea></td></tr>'
						+ '<tr><td>耳背墙</td><td><textarea id='+ g.bridge[f].id + '_earBackWall' + ' style="width:99%;resize:none;height:50px;margin-top: 6px;" maxlength="200"></textarea></td></tr>'
						+ '<tr><td>八字墙</td><td><textarea id='+ g.bridge[f].id + '_baziWall' + ' style="width:99%;resize:none;height:50px;margin-top: 6px;" maxlength="200"></textarea></td></tr>'
						+ '<tr><td>墩身</td><td><textarea id='+ g.bridge[f].id + '_pierShaft' + ' style="width:99%;resize:none;height:50px;margin-top: 6px;" maxlength="200"></textarea></td></tr>'
						+ '<tr><td>墩系梁</td><td><textarea id='+ g.bridge[f].id + '_pierTiedBeam' + ' style="width:99%;resize:none;height:50px;margin-top: 6px;" maxlength="200"></textarea></td></tr>'
						+ '<tr><td>盖梁</td><td><textarea id='+ g.bridge[f].id + '_coping' + ' style="width:99%;resize:none;height:50px;margin-top: 6px;" maxlength="200"></textarea></td></tr>'
						+ '<tr><td>支座垫石</td><td><textarea id='+ g.bridge[f].id + '_supportCushion' + ' style="width:99%;resize:none;height:50px;margin-top: 6px;" maxlength="200"></textarea></td></tr>'
						+ '<tr><td>挡块</td><td><textarea id='+ g.bridge[f].id + '_baffleBlock' + ' style="width:99%;resize:none;height:50px;margin-top: 6px;" maxlength="200"></textarea></td></tr>'
						+ '<tr><td width="9%" rowspan="5">上部结构</td><td width="11%">梁板预制</td><td><textarea id='+ g.bridge[f].id + '_beamSlabPrefabrication' + ' style="width:99%;resize:none;height:50px;margin-top: 6px;" maxlength="200"></textarea></td></tr>'
						+ '<tr><td>现浇梁</td><td><textarea id='+ g.bridge[f].id + '_castInPlaceBeam' + ' style="width:99%;resize:none;height:50px;margin-top: 6px;" maxlength="200"></textarea></td></tr>'
						+ '<tr><td>支座</td><td><textarea id='+ g.bridge[f].id + '_bearing' + ' style="width:99%;resize:none;height:50px;margin-top: 6px;" maxlength="200"></textarea></td></tr>'
						+ '<tr><td>横隔板</td><td><textarea id='+ g.bridge[f].id + '_diaphragm' + ' style="width:99%;resize:none;height:50px;margin-top: 6px;" maxlength="200"></textarea></td></tr>'
						+ '<tr><td>湿接缝</td><td><textarea id='+ g.bridge[f].id + '_wetJoint' + ' style="width:99%;resize:none;height:50px;margin-top: 6px;" maxlength="200"></textarea></td></tr>'
						+ '<tr><td width="9%" rowspan="5">附属结构</td><td width="11%">桥面系</td><td><textarea id='+ g.bridge[f].id + '_bridgeDeckSystem' + ' style="width:99%;resize:none;height:50px;margin-top: 6px;" maxlength="200"></textarea></td></tr>'
						+ '<tr><td>桥头搭板</td><td><textarea id='+ g.bridge[f].id + '_bridgeheadSlab' + ' style="width:99%;resize:none;height:50px;margin-top: 6px;" maxlength="200"></textarea></td></tr>'
						+ '<tr><td>护栏</td><td><textarea id='+ g.bridge[f].id + '_guardrail' + ' style="width:99%;resize:none;height:50px;margin-top: 6px;" maxlength="200"></textarea></td></tr>'
						+ '<tr><td>防撞墙</td><td><textarea id='+ g.bridge[f].id + '_antiCollisionWall' + ' style="width:99%;resize:none;height:50px;margin-top: 6px;" maxlength="200"></textarea></td></tr>'
						+ '<tr><td>伸缩缝</td><td><textarea id='+ g.bridge[f].id + '_expansionJoint' + ' style="width:99%;resize:none;height:50px;margin-top: 6px;" maxlength="200"></textarea></td></tr>'
						+ '</table>';
				}
			}
		})
	}
	
	$('#bridgeName').append(st);
	$('#brigdeTables').append(tableStr);
	changeBrige($('#bridgeName'));

}

//切换桥梁
function changeBrige(i) {
	$('#mileage').val($(i).find("option:selected").attr('data-mileage'));
	$('#scan').val($(i).find("option:selected").attr('data-scan'));
	$('.tablealls').hide();
	$('#'+$('#constructionOrgs').find("option:selected").val()+"_"+$('#bridgeName').find("option:selected").val()).show();
}

// 保存方法
function saveAddMsg(){
	// 获取画面数据
	var param = {};
	// 实际施工量id
	param.id = id;
	param.uniqueIdentifier = $("#constructionOrgs").val();
	if(param.uniqueIdentifier == null || param.uniqueIdentifier == ""){
		swal({
			title: "",
			text: "请选择施工单位!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}
	// 获取实际完成量数据
	param.subgrade_Fill = $("#subgrade_Fill").val();
	param.subgrade_Excavation = $("#subgrade_Excavation").val();
	param.subgrade_Total = $("#subgrade_Total").val();
	param.cushion_LeftAmplitude = $("#cushion_LeftAmplitude").val();
	param.cushion_RightAmplitude = $("#cushion_RightAmplitude").val();
	param.subbase_LeftAmplitude = $("#subbase_LeftAmplitude").val();
	param.subbase_RightAmplitude = $("#subbase_RightAmplitude").val();
	param.substrate_LeftAmplitude = $("#substrate_LeftAmplitude").val();
	param.substrate_RightAmplitude = $("#substrate_RightAmplitude").val();
	param.lowerLayer_LeftAmplitude = $("#lowerLayer_LeftAmplitude").val();
	param.lowerLayer_RightAmplitude = $("#lowerLayer_RightAmplitude").val();
	param.mesosphere_LeftAmplitude = $("#mesosphere_LeftAmplitude").val();
	param.mesosphere_RightAmplitude = $("#mesosphere_RightAmplitude").val();
	param.upperLayer_LeftAmplitude = $("#upperLayer_LeftAmplitude").val();
	param.upperLayer_RightAmplitude = $("#upperLayer_RightAmplitude").val();
	
	// 桥梁明细数据
	var bridgeConstructionDetailsList = [];
	$('#brigdeTables table').each(function(w,n) {
		var everyUniqueIdentifier = $(n).attr("id").split('_')[0];
		var everyBridgeId = $(n).attr("id").split('_')[1];
		var bridgeConstructionDetailsListObj = {};
		bridgeConstructionDetailsListObj.uniqueIdentifier = everyUniqueIdentifier;
		bridgeConstructionDetailsListObj.bridgeId = everyBridgeId;
		bridgeConstructionDetailsListObj.pileFoundation = $('#'+everyBridgeId+"_pileFoundation").val();
		bridgeConstructionDetailsListObj.pileTiedBeam = $('#'+everyBridgeId+"_pileTiedBeam").val();
		bridgeConstructionDetailsListObj.abutment = $('#'+everyBridgeId+"_abutment").val();
		bridgeConstructionDetailsListObj.caps = $('#'+everyBridgeId+"_caps").val();
		bridgeConstructionDetailsListObj.platformBoby = $('#'+everyBridgeId+"_platformBody").val();
		bridgeConstructionDetailsListObj.platformCap = $('#'+everyBridgeId+"_platformCap").val();
		bridgeConstructionDetailsListObj.earBackWall = $('#'+everyBridgeId+"_earBackWall").val();
		bridgeConstructionDetailsListObj.baziWall = $('#'+everyBridgeId+"_baziWall").val();
		bridgeConstructionDetailsListObj.pierShaft = $('#'+everyBridgeId+"_pierShaft").val();
		bridgeConstructionDetailsListObj.pierTiedBeam = $('#'+everyBridgeId+"_pierTiedBeam").val();
		bridgeConstructionDetailsListObj.coping = $('#'+everyBridgeId+"_coping").val();
		bridgeConstructionDetailsListObj.supportCushion = $('#'+everyBridgeId+"_supportCushion").val();
		bridgeConstructionDetailsListObj.baffleBlock = $('#'+everyBridgeId+"_baffleBlock").val();
		bridgeConstructionDetailsListObj.beamSlabPrefabrication = $('#'+everyBridgeId+"_beamSlabPrefabrication").val();
		bridgeConstructionDetailsListObj.castInPlaceBeam = $('#'+everyBridgeId+"_castInPlaceBeam").val();
		bridgeConstructionDetailsListObj.bearing = $('#'+everyBridgeId+"_bearing").val();
		bridgeConstructionDetailsListObj.diaphragm = $('#'+everyBridgeId+"_diaphragm").val();
		bridgeConstructionDetailsListObj.wetJoint = $('#'+everyBridgeId+"_wetJoint").val();
		bridgeConstructionDetailsListObj.bridgeDeckSystem = $('#'+everyBridgeId+"_bridgeDeckSystem").val();
		bridgeConstructionDetailsListObj.bridgeheadSlab = $('#'+everyBridgeId+"_bridgeheadSlab").val();
		bridgeConstructionDetailsListObj.guardrail = $('#'+everyBridgeId+"_guardrail").val();
		bridgeConstructionDetailsListObj.antiCollisionWall = $('#'+everyBridgeId+"_antiCollisionWall").val();
		bridgeConstructionDetailsListObj.expansionJoint = $('#'+everyBridgeId+"_expansionJoint").val();
		bridgeConstructionDetailsList.push(bridgeConstructionDetailsListObj);
	})
	param.bridgeConstructionDetailsList = JSON.stringify(bridgeConstructionDetailsList);
	$.ajax({
		type : "POST",
		url : "../../ConstructionProgress/addInfo.action",
		data:param,
		dataType : "json",
		success : function(data) {
			if(data.code == "success"){
				swal({
					title: data.message,
					text: "",
					type: data.code,
					confirmButtonText: '确定',
					cancelButtonFont: '微软雅黑',
				}, function() {
					back();
				});
			}else{
				swal({
					title: data.message,
					text: "",
					type: data.code,
					confirmButtonText: '确定',
					cancelButtonFont: '微软雅黑',
				});
			}
		}
	});
}

// 获取路基总量
function getSubgrade_Total1(){
	// 路基的总量=路基的填方量+路基的挖方量
	var subgrade_Fill = $("#subgrade_Fill").val();
	var subgrade_Excavation = $("#subgrade_Excavation").val();
	var sum = subgrade_Fill*1 + subgrade_Excavation*1;
	$("#subgrade_Total").val(sum);
	
	changeSum('Subgrade_Total', sum);
}

// 累计完成量事件
function changeSum(name, value){
	var param = {};
	param.id = id;
	param.name = name;
	param.UniqueIdentifier = $("#constructionOrgs").val();
	$.ajax({
		type : "POST",
		url : "../../ConstructionProgress/getSum.action",
		data:param,
		dataType : "json",
		success : function(data) {
			if(data == null){
				data = 0;
			}
			var sum = 0;
			var result = 0;
			if(name == 'Subgrade_Total'){	
				if( pdFlag == 1 && befData != null && befData.length > 0){
					// 修改
					// 有累计的情况
					if(befData[0].subgrade_Total != null){
						// 累计值 - 当条数据之前的录入值 + 新输入的值
						sum = data*1 - befData[0].subgrade_Total*1 + value*1;
					}else{
						// 存在累计值的情况
						sum = data*1 + value*1;
					}
					result = (sum/befData[0].subgrade_Total_1 * 100).toFixed(2);
				}else if(pdFlag == 0 && resultData != null && resultData.length > 0){
					// 新增
					// 存在累计值的情况
					sum = data*1 + value*1;
					if(resultData[0].subgrade_Total != null && resultData[0].subgrade_Total != 0){
						result = (sum/resultData[0].subgrade_Total* 100).toFixed(2);
					}else{
						result = 0;
					}
				}else{
					// 存在累计值的情况
					sum = data*1 + value*1;
					if(data != null && data != 0){
						result = (sum/data*1* 100).toFixed(2);
					}else{
						result = 0;
					}
				}
				$("#sumSubgrade_Total").val(sum);
				$("#subgrade_Total_2").val(result + "%");
			}else if(name == "Cushion_LeftAmplitude"){
				if (pdFlag == 1 && befData != null && befData.length > 0) {
					// 修改
					// 有累计的情况
					if (befData[0].sumCushion_LeftAmplitude != null) {
						// 累计值 - 当条数据之前的录入值 + 新输入的值
						sum = data * 1 - befData[0].cushion_LeftAmplitude * 1+ value * 1;
					} else {
						sum = data * 1 + value * 1
					}
					result = (sum / befData[0].cushion_LeftAmplitude_1 * 100).toFixed(2);
				} else if (pdFlag == 0 && resultData != null && resultData.length > 0) {
					// 新增
					// 存在累计值的情况
					sum = data * 1 + value * 1;
					if (resultData[0].cushion_LeftAmplitude != null && resultData[0].cushion_LeftAmplitude != 0) {
						// 累计值/工程设计量内容
						result = (sum / resultData[0].cushion_LeftAmplitude * 100).toFixed(2);
					} else {
						result = 0;
					}
				} else {
					// 存在累计值的情况
					sum = data * 1 + value * 1;
					if (data != null && data != 0) {
						result = (sum / data * 1 * 100).toFixed(2);
					} else {
						result = 0;
					}
				}
				$("#sumCushion_LeftAmplitude").val(sum);
				$("#cushion_LeftAmplitude_2").val(result + "%");
			}else if(name == "Cushion_RightAmplitude"){
				if (pdFlag == 1 && befData != null && befData.length > 0) {
					// 修改
					// 有累计的情况
					if (befData[0].sumCushion_RightAmplitude != null) {
						// 累计值 - 当条数据之前的录入值 + 新输入的值
						sum = data * 1 - befData[0].cushion_RightAmplitude * 1+ value * 1;
					} else {
						sum = data * 1 + value * 1
					}
					result = (sum / befData[0].cushion_RightAmplitude_1 * 100).toFixed(2);
				} else if (pdFlag == 0 && resultData != null && resultData.length > 0) {
					// 新增
					// 存在累计值的情况
					sum = data * 1 + value * 1;
					if (resultData[0].cushion_RightAmplitude != null && resultData[0].cushion_RightAmplitude != 0) {
						// 累计值/工程设计量内容
						result = (sum / resultData[0].cushion_RightAmplitude * 100).toFixed(2);
					} else {
						result = 0;
					}
				}else{
					// 存在累计值的情况
					sum = data*1 + value*1;
					if(data != null && data != 0){
						result = (sum/data*1* 100).toFixed(2);
					}else{
						result = 0;
					}
				}
				
				$("#sumCushion_RightAmplitude").val(sum);
				$("#cushion_RightAmplitude_2").val(result + "%");
			}else if(name == "Subbase_LeftAmplitude"){
				
				if(pdFlag == 1 && befData != null && befData.length > 0){
					// 修改
					// 有累计的情况
					if(befData[0].sumSubbase_LeftAmplitude != null){
						// 累计值 - 当条数据之前的录入值 + 新输入的值
						sum = data*1 - befData[0].subbase_LeftAmplitude*1 + value*1;
					}else{
						sum = data*1 + value*1
					}
					result = (sum/befData[0].subbase_LeftAmplitude_1 * 100).toFixed(2);
				}else if(pdFlag == 0 && resultData != null && resultData.length > 0){
					// 新增
					// 存在累计值的情况
					sum = data*1 + value*1;
					if(resultData[0].subbase_LeftAmplitude != null && resultData[0].subbase_LeftAmplitude != 0){
						// 累计值/工程设计量内容
						result = (sum/resultData[0].subbase_LeftAmplitude* 100).toFixed(2);
					}else{
						result = 0;
					}
				}else{
					sum = data*1 + value*1;
					if(data != null && data != 0){
						result = (sum/data*1* 100).toFixed(2);
					}else{
						result = 0;
					}
				}
				
				$("#sumSubbase_LeftAmplitude").val(sum);
				$("#subbase_LeftAmplitude_2").val(result + "%");
			}else if(name == "Subbase_RightAmplitude"){
				if(pdFlag == 1 && befData != null && befData.length > 0){
					// 修改
					// 有累计的情况
					if(befData[0].sumSubbase_RightAmplitude != null){
						// 累计值 - 当条数据之前的录入值 + 新输入的值
						sum = data*1 - befData[0].subbase_RightAmplitude*1 + value*1;
					}else{
						sum = data*1 + value*1;
					}
					result = (sum/befData[0].subbase_RightAmplitude_1 * 100).toFixed(2);
				}else if(pdFlag == 0 && resultData != null && resultData.length > 0){
					// 新增
					// 存在累计值的情况
					sum = data*1 + value*1;
					if(resultData[0].subbase_RightAmplitude != null && resultData[0].subbase_RightAmplitude != 0){
						// 累计值/工程设计量内容
						result = (sum/resultData[0].subbase_RightAmplitude* 100).toFixed(2);
					}else{
						result = 0;
					}
				}else{
					sum = data*1 + value*1;
					if(data != null && data != 0){
						result = (sum/data*1* 100).toFixed(2);
					}else{
						result = 0;
					}
				}
				
				$("#sumSubbase_RightAmplitude").val(sum);
				$("#subbase_RightAmplitude_2").val(result + "%");
			}else if(name == "Substrate_LeftAmplitude"){
				
				if(pdFlag == 1 && befData != null && befData.length > 0){
					
					// 修改
					// 有累计的情况
					if(befData[0].sumSubstrate_LeftAmplitude != null){
						// 累计值 - 当条数据之前的录入值 + 新输入的值
						sum = data*1 - befData[0].substrate_LeftAmplitude*1 + value*1;
					}else{
						sum = data*1 + value*1;
					}
					result = (sum/befData[0].substrate_LeftAmplitude_1 * 100).toFixed(2);
				}else if(pdFlag == 0 && resultData != null && resultData.length > 0){
					// 新增
					// 存在累计值的情况
					sum = data*1 + value*1;
					if(resultData[0].substrate_LeftAmplitude != null && resultData[0].substrate_LeftAmplitude != 0){
						// 累计值/工程设计量内容
						result = (sum/resultData[0].substrate_LeftAmplitude* 100).toFixed(2);
					}else{
						result = 0;
					}
				}else{
					sum = data*1 + value*1;
					if(data != null && data != 0){
						result = (sum/data*1* 100).toFixed(2);
					}else{
						result = 0;
					}
				}
				
				$("#sumSubstrate_LeftAmplitude").val(sum);
				$("#substrate_LeftAmplitude_2").val(result + "%");
			}else if(name == "Substrate_RightAmplitude"){
				if(pdFlag == 1 && befData != null && befData.length > 0){
					// 修改
					// 有累计的情况
					if(befData[0].sumSubstrate_RightAmplitude != null){
						// 累计值 - 当条数据之前的录入值 + 新输入的值
						sum = data*1 - befData[0].substrate_RightAmplitude*1 + value*1;
					}else{
						sum = data*1 + value*1;
					}
					result = (sum/befData[0].substrate_RightAmplitude_1 * 100).toFixed(2);
				}else if(pdFlag == 0 && resultData != null && resultData.length > 0){
					// 新增
					// 存在累计值的情况
					sum = data*1 + value*1;
					if(resultData[0].substrate_RightAmplitude != null && resultData[0].substrate_RightAmplitude != 0){
						// 累计值/工程设计量内容
						result = (sum/resultData[0].substrate_RightAmplitude* 100).toFixed(2);
					}else{
						result = 0;
					}
				}else{
					sum = data*1 + value*1;
					if(data != null && data != 0){
						result = (sum/data*1* 100).toFixed(2);
					}else{
						result = 0;
					}
				}
				$("#sumSubstrate_RightAmplitude").val(sum);
				$("#substrate_RightAmplitude_2").val(result + "%");
			}else if(name == "LowerLayer_LeftAmplitude"){
				if(pdFlag == 1 && befData != null && befData.length > 0){
					// 修改
					// 有累计的情况
					if(befData[0].sumLowerLayer_LeftAmplitude != null){
						// 累计值 - 当条数据之前的录入值 + 新输入的值
						sum = data*1 - befData[0].lowerLayer_LeftAmplitude*1 + value*1;
					}else{
						sum = data*1 + value*1;
					}
					result = (sum/befData[0].lowerLayer_LeftAmplitude_1 * 100).toFixed(2);
				}else if(pdFlag == 0 && resultData != null && resultData.length > 0){
					// 新增
					// 存在累计值的情况
					sum = data*1 + value*1;
					if(resultData[0].lowerLayer_LeftAmplitude != null && resultData[0].lowerLayer_LeftAmplitude != 0){
						// 累计值/工程设计量内容
						result = (sum/resultData[0].lowerLayer_LeftAmplitude* 100).toFixed(2);
					}else{
						result = 0;
					}
				}else{
					sum = data*1 + value*1;
					if(data != null && data != 0){
						result = (sum/data*1* 100).toFixed(2);
					}else{
						result = 0;
					}
				}
				
				$("#sumLowerLayer_LeftAmplitude").val(sum);
				$("#lowerLayer_LeftAmplitude_2").val(result + "%");
			}else if(name == "LowerLayer_RightAmplitude"){
				if(pdFlag == 1 && befData != null && befData.length > 0){
					// 修改
					// 有累计的情况
					if(befData[0].sumLowerLayer_RightAmplitude != null){
						// 累计值 - 当条数据之前的录入值 + 新输入的值
						sum = data*1 - befData[0].lowerLayer_RightAmplitude*1 + value*1;
					}else{
						sum = data*1 + value*1;
					}
					result = (sum/befData[0].lowerLayer_RightAmplitude_1 * 100).toFixed(2);
				}else if(pdFlag == 0 && resultData != null && resultData.length > 0){
					// 新增
					// 存在累计值的情况
					sum = data*1 + value*1;
					if(resultData[0].lowerLayer_RightAmplitude != null && resultData[0].lowerLayer_RightAmplitude != 0){
						// 累计值/工程设计量内容
						result = (sum/resultData[0].lowerLayer_RightAmplitude* 100).toFixed(2);
					}else{
						result = 0;
					}
				}else{
					sum = data*1 + value*1;
					if(data != null && data != 0){
						result = (sum/data*1* 100).toFixed(2);
					}else{
						result = 0;
					}
				}
				
				$("#sumLowerLayer_RightAmplitude").val(sum);
				$("#lowerLayer_RightAmplitude_2").val(result + "%");
			}else if(name == "Mesosphere_LeftAmplitude"){
				if( pdFlag == 1 && befData != null && befData.length > 0){
					// 修改
					// 有累计的情况
					if(befData[0].sumMesosphere_LeftAmplitude != null){
						// 累计值 - 当条数据之前的录入值 + 新输入的值
						sum = data*1 - befData[0].mesosphere_LeftAmplitude*1 + value*1;
					}else{
						sum = data*1 + value*1;
					}
					result = (sum/befData[0].mesosphere_LeftAmplitude_1 * 100).toFixed(2);
				}else if(pdFlag == 0 && resultData != null && resultData.length > 0){
					// 新增
					// 存在累计值的情况
					sum = data*1 + value*1;
					if(resultData[0].mesosphere_LeftAmplitude != null && resultData[0].mesosphere_LeftAmplitude != 0){
						// 累计值/工程设计量内容
						result = (sum/resultData[0].mesosphere_LeftAmplitude* 100).toFixed(2);
					}else{
						result = 0;
					}
				}else{
					sum = data*1 + value*1;
					if(data != null && data != 0){
						result = (sum/data*1* 100).toFixed(2);
					}else{
						result = 0;
					}
				}
				
				$("#sumMesosphere_LeftAmplitude").val(sum);
				$("#mesosphere_LeftAmplitude_2").val(result + "%");
			}else if(name == "Mesosphere_RightAmplitude"){
				if(pdFlag == 1 && befData != null && befData.length > 0){
					
					// 修改
					// 有累计的情况
					if(befData[0].sumMesosphere_RightAmplitude != null){
						// 累计值 - 当条数据之前的录入值 + 新输入的值
						sum = data*1 - befData[0].mesosphere_RightAmplitude*1 + value*1;
					}else{
						sum = data*1 + value*1;
					}
					result = (sum/befData[0].mesosphere_RightAmplitude_1 * 100).toFixed(2);
				}else if(pdFlag == 0 && resultData != null && resultData.length > 0){
					// 新增
					// 存在累计值的情况
					sum = data*1 + value*1;
					if(resultData[0].mesosphere_RightAmplitude != null && resultData[0].mesosphere_RightAmplitude != 0){
						// 累计值/工程设计量内容
						result = (sum/resultData[0].mesosphere_RightAmplitude* 100).toFixed(2);
					}else{
						result = 0;
					}
				}else{
					sum = data*1 + value*1;
					if(data != null && data != 0){
						result = (sum/data*1* 100).toFixed(2);
					}else{
						result = 0;
					}
				}
				
				$("#sumMesosphere_RightAmplitude").val(sum);
				$("#mesosphere_RightAmplitude_2").val(result + "%");
			}else if(name == "UpperLayer_LeftAmplitude"){
				if( pdFlag == 1 && befData != null && befData.length > 0){
					// 修改
					// 有累计的情况
					if(befData[0].sumUpperLayer_LeftAmplitude != null){
						// 累计值 - 当条数据之前的录入值 + 新输入的值
						sum = data*1 - befData[0].upperLayer_LeftAmplitude*1 + value*1;
					}else{
						sum = data*1 + value*1;
					}
					result = (sum/befData[0].upperLayer_LeftAmplitude_1 * 100).toFixed(2);
				}else if(pdFlag == 0 && resultData != null && resultData.length > 0){
					// 新增
					// 存在累计值的情况
					sum = data*1 + value*1;
					if(resultData[0].upperLayer_LeftAmplitude != null && resultData[0].upperLayer_LeftAmplitude != 0){
						// 累计值/工程设计量内容
						result = (sum/resultData[0].upperLayer_LeftAmplitude* 100).toFixed(2);
					}else{
						result = 0;
					}
				}else{
					sum = data*1 + value*1;
					if(data != null && data != 0){
						result = (sum/data*1* 100).toFixed(2);
					}else{
						result = 0;
					}
				}
				
				$("#sumUpperLayer_LeftAmplitude").val(sum);
				$("#upperLayer_LeftAmplitude_2").val(result + "%");
			}else if(name == "UpperLayer_RightAmplitude"){
				if(pdFlag == 1 && befData != null && befData.length > 0){
					// 修改
					// 有累计的情况
					if(befData[0].sumUpperLayer_RightAmplitude != null){
						// 累计值 - 当条数据之前的录入值 + 新输入的值
						sum = data*1 - befData[0].upperLayer_RightAmplitude*1 + value*1;
					}else{
						sum = data*1 + value*1;
					}
					result = (sum/befData[0].upperLayer_RightAmplitude_1 * 100).toFixed(2);
				}else if(pdFlag == 0 && resultData != null && resultData.length > 0){
					// 新增
					// 存在累计值的情况
					sum = data*1 + value*1;
					if(resultData[0].upperLayer_RightAmplitude != null && resultData[0].upperLayer_RightAmplitude != 0 ){
						// 累计值/工程设计量内容
						result = (sum/resultData[0].upperLayer_RightAmplitude* 100).toFixed(2);
					}else{
						result = 0;
					}
				}else{
					sum = data*1 + value*1;
					if(data != null && data != 0){
						result = (sum/data*1* 100).toFixed(2);
					}else{
						result = 0;
					}
				}
				
				$("#sumUpperLayer_RightAmplitude").val(sum);
				$("#upperLayer_RightAmplitude_2").val(result + "%");
			}
		}
	});
	
}

//新增进度数据页返回到施工进度页面
function back(){
	$.ajax({
		type: "get",
		url: 'construction_schedule.html',
		dataType: 'html',
		success: function(data) {
			$('.rightcon_content').html(data);
		},
		error: function(xhr, ajaxOptions, thrownError) {
		}
	});
}