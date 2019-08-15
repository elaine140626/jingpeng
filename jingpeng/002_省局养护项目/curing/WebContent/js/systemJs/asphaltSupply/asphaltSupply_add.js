// 当前登录用户id
var userId = ''; 
// 主键id
var id = '';
// 项目id
var ProjectId = '';
// 工程信息id
var ProjectInfoId = "";
// 查看/修改的标识
var flag = "";
$(function(){
	// 获取当前登录用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){
		userId = userInfo.id;
	}

	// 项目性质列表取得
	var ProjectNature = getDataDictionaryMultiple('xmxz');
	$("#projectNature").empty();
	$("#projectNature").html(ProjectNature);
	
	// 资金来源列表取得
	var CapitalSource = getDataDictionaryMultiple('zjly');
	$("#capitalSource").empty();
	$("#capitalSource").html(CapitalSource);
	
	// 改性90#列表取得
	var modifiedType = getDataDictionaryMultiple('gx90');
	$("#modifiedType").empty();
	$("#modifiedType").html(modifiedType);
	
	// 资金来源列表取得
	var otherType = getDataDictionaryMultiple('qt90');
	$("#otherType").empty();
	$("#otherType").html(otherType);
	
	// 乳化沥青类别列表取得
	var emulsifyType = getDataDictionaryMultiple('rhlqlb');
	$("#emulsifyType").empty();
	$("#emulsifyType").html(emulsifyType);
	
	id = getUrlParam("id");
	ProjectInfoId = getUrlParam("projectInfoId");
	flag = getUrlParam("flag");

	// 修改的场合
	if (id != null && id != "null" && id != ""){
		// 获取合同信息
		$.ajax({
			type : "post",
			url : "../../FirstAsphaltSupply/getFirstAsphaltSupplyById.action",
			data : {"id" : id},
			dataType : "json",
			success : function(data) {
				if(data != null && data.length > 0){
					SetFromValues($("#form1"), data[0]);
				}
			}
		});
	}
	
	if(flag == 0){
		// 查看
		$('input[type="text"]').attr('disabled','disabled');
		$('select').attr('disabled','disabled');
		$('select').css('background-color','rgb(235, 235, 228)');
		$('#finishTime').css('background-color','rgb(235, 235, 228)');
		// 隐藏保存按钮
		$("#saveButton").hide();
	}else if(flag == 1){
		// 修改
		$('input[type="text"]').removeAttr('readonly','readonly');
		$('select').removeAttr('disabled','disabled');
		$('select').css('background-color','#fff');
		$('#finishTime').css('background-color','#fff');
		// 小计不可编辑
		$("#modifiedSubtotal").attr('readonly','readonly');
		$('#modifiedSubtotal').css('background-color','rgb(235, 235, 228)');
		$("#otherSubtotal").attr('readonly','readonly');
		$('#otherSubtotal').css('background-color','rgb(235, 235, 228)');
		$("#emulsifySubtotal").attr('readonly','readonly');
		$('#emulsifySubtotal').css('background-color','rgb(235, 235, 228)');
		$("#total").attr('readonly','readonly');
		$('#total').css('background-color','rgb(235, 235, 228)');
		// 显示保存按钮
		$("#saveButton").show();
	}
})

// 改性90#小计
function modifiedTotal(){
	// 平数
	var modifiedNumber = $("#modifiedNumber").val();
	// 厚数
	var modifiedThickness = $("#modifiedThickness").val();
	// 配合比
	var modifiedProportion = $("#modifiedProportion").val();
	
	if(modifiedNumber != "" && modifiedThickness != "" && modifiedProportion != ""){
		// 改性90#小计=平数*厚度*配合比沥青用量*2.35/10000
		var modifiedSubtotal = modifiedNumber * modifiedThickness * (modifiedProportion/100) * 2.35 / 10000;
		$("#modifiedSubtotal").val(modifiedSubtotal.toFixed(2));
		addTotal();
	}
}

// 其他90#小计
function otherTotal(){
	// 平数
	var otherNumber = $("#otherNumber").val();
	// 厚数
	var otherThickness = $("#otherThickness").val();
	// 配合比
	var otherProportion = $("#otherProportion").val();
	
	if(otherNumber != "" && otherThickness != "" && otherProportion != ""){
		// 其他90#=平数*厚度*配合比沥青用量*2.35/10000
		var otherSubtotal = otherNumber * otherThickness * (otherProportion/100) * 2.35 / 10000;
		$("#otherSubtotal").val(otherSubtotal.toFixed(2));
		addTotal();
	}
}

//乳化沥青小计
function emulsifyTotal(){
	// 平数
	var emulsifyNumber = $("#emulsifyNumber").val();
	// 厚数
	var emulsifyThickness = $("#emulsifyThickness").val();
	
	if(emulsifyNumber != "" && emulsifyThickness != ""){
		// 乳化沥青=平数*厚度/1000	
		var emulsifySubtotal = emulsifyNumber * emulsifyThickness / 1000;
		$("#emulsifySubtotal").val(emulsifySubtotal.toFixed(2));
		addTotal();
	}
}				
	
// 总量
function addTotal(){
	// 总量计算
	var total = $("#modifiedSubtotal").val()*1 +$("#otherSubtotal").val() *1+ $("#emulsifySubtotal").val()*1 + $("#asphalt140").val()*1;
	$("#total").val(total.toFixed(2));
}

// 保存
function save(){	
	var params = formToJson($("#form1"));

	// 投标完成时间 
	if($("#finishTime").val() == ""){
		params.finishTime = null;
	}
	var url = '';
	if (id != null && id != "null" && id != ""){
		// 修改
		url = '../../FirstAsphaltSupply/updateFirstAsphaltSupply.action';
		params.id = id;
		params.reviser=userId;
	}else{
		url = '../../FirstAsphaltSupply/insertFirstAsphaltSupply.action';
		params.id = id;
		params.projectInfoId = ProjectInfoId;
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