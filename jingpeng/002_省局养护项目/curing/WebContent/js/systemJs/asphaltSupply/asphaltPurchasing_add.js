// 当前登录用户id
var userId = ''; 
// 沥青供应id
var firstAsphaltSupplyId = '';
// 查看/修改的标识
var flag = "";
// 新增/修改判断标识
var result = false;
$(function(){
	// 获取当前登录用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){
		userId = userInfo.id;
	}

	firstAsphaltSupplyId = getUrlParam("firstAsphaltSupplyId");
	flag = getUrlParam("flag");
	// 修改的场合
	if (firstAsphaltSupplyId != null && firstAsphaltSupplyId != ""){
		// 获取合同信息
		$.ajax({
			type : "post",
			url : "../../AsphaltPurchasing/getAsphaltPurchasing.action",
			data : {"firstAsphaltSupplyId" : firstAsphaltSupplyId},
			dataType : "json",
			success : function(data) {
				if(data != null && data.length > 0){
					// 修改标识
					result = false;
					SetFromValues($("#form1"), data[0]);
				}else{
					// 新增标识
					result = true;
				}
			}
		});
	}
	
	if(flag == 0){
		// 查看
		$('input[type="text"]').attr('disabled','disabled');
		// 隐藏保存按钮
		$("#saveButton").hide();
	}else if(flag == 1){
		// 修改
		$('input[type="text"]').removeAttr('readonly','readonly');
		// 仍未供应不可编辑
		$("#differenceApril").attr('disabled','disabled');
		$("#differenceMay").attr('disabled','disabled');
		$("#differenceJune").attr('disabled','disabled');
		$("#differenceJuly").attr('disabled','disabled');
		$("#differenceAug").attr('disabled','disabled');
		$("#differenceSep").attr('disabled','disabled');
		$("#differenceOct").attr('disabled','disabled');
		// 显示保存按钮
		$("#saveButton").show();
	}
})

// 保存
function save(){	
	var params = formToJson($("#form1"));
	var url = '';
	if (result){
		url = '../../AsphaltPurchasing/insertAsphaltPurchasing.action';
		params.firstAsphaltSupplyId = firstAsphaltSupplyId;
		params.reviser=userId;
		
	}else{
		// 修改
		url = '../../AsphaltPurchasing/updateAsphaltPurchasing.action';
		params.firstAsphaltSupplyId = firstAsphaltSupplyId;
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

// 仍未供应计算
function changeDifference(index){
	if(index == 4){
		var differenceApril = $("#monthNeedApril").val()*1 - $("#actualSupplyApril").val()*1;
		$("#differenceApril").val(differenceApril);
	}else if(index == 5){
		var differenceMay = $("#monthNeedMay").val()*1 - $("#actualSupplyMay").val()*1;
		$("#differenceMay").val(differenceMay);
	}else if(index == 6){
		var differenceJune = $("#monthNeedJune").val()*1 - $("#actualSupplyJune").val()*1;
		$("#differenceJune").val(differenceJune);
	}else if(index == 7){
		var differenceJuly = $("#monthNeedJuly").val()*1 - $("#actualSupplyJuly").val()*1;
		$("#differenceJuly").val(differenceJuly);
	}else if(index == 8){
		var differenceAug = $("#monthNeedAug").val()*1 - $("#actualSupplyAug").val()*1;
		$("#differenceAug").val(differenceAug);
	}else if(index == 9){
		var differenceSep = $("#monthNeedSep").val()*1 - $("#actualSupplySep").val()*1;
		$("#differenceSep").val(differenceSep);
	}else if(index == 10){
		var differenceOct = $("#monthNeedOct").val()*1 - $("#actualSupplyOct").val()*1;
		$("#differenceOct").val(differenceOct);
	}
}