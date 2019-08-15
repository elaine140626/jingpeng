// 当前登录用户id
var userId = ''; 
// 主键id
var id = '';
// 项目id
var ProjectId = '';
$(function(){
	// 获取当前登录用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){
		userId = userInfo.id;
	}
	
	// 工程量单位列表取得
	/*var EngineeringUnit = getDataDictionaryMultiple('gcldw');
	$("#engineeringUnit").empty();
	$("#engineeringUnit").html(EngineeringUnit);*/

	id = getUrlParam("id");
	// 修改的场合
	if (id != null && id != "null" && id != ""){
		// 获取合同信息
		$.ajax({
			type : "post",
			url : "../../ProjectPlanSummary/getProjectPlanSummaryById.action",
			data : {"id" : id},
			dataType : "json",
			success : function(data) {
				if(data != null && data.length > 0){
					SetFromValues($("#form1"), data[0]);
				}
			}
		});
	}
})

// 投资额总计
function changeInvestmentTotal(){
    // 投资额省补总投资
	var investmentProvince = $("#investmentProvince").val();
	
	// 投资额部投资
	var investmentMinistry = $("#investmentMinistry").val();
	
	var investmentTotal = Number(investmentProvince) + Number(investmentMinistry);
	$("#investmentTotal").val(investmentTotal.toFixed(4));
}

// 保存
function save(){	
	var params = formToJson($("#form1"));	
	
	// 开工时间 
	if($("#startDate").val() == ""){
		params.startDate = null;
	}
	
	// 竣工时间 
	if($("#endDate").val() == ""){
		params.endDate = null;
	}
	
	var url = '';
	if (id != null && id != "null" && id != ""){
		// 修改
		url = '../../ProjectPlanSummary/updateProjectPlanSummary.action';
		params.id = id;
		params.reviser=userId;
	}
	$.ajax({
		type: 'POST',
		url: url,
		data:JSON.stringify(params),
		dataType: 'json',
		contentType: 'application/json',
		success: function(data){
			if (data.code == 'success'){
				// 操作成功
				layer.alert(data.message, {
					icon: 1,
					title: "提示"
				},function(){
					window.parent.location.reload();
				});
			} else {
				// 操作失败
				layer.alert(data.message, {
					icon: 2,
					title: "提示"
				});
			}			
		}
	});		
}