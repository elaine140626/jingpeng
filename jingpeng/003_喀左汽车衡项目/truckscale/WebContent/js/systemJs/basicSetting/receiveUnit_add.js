// 获取的id
var id = "";
// 用户的id
var userId = "";

var generateNumber;
$(function(){
	
	uesrId = getUrlParam("userId");

	uesrInfo = getUserInfo(uesrId);
	
	// 获取传值id
	id = getUrlParam("id");
	//传所需的前缀英文简写
	$.ajax({
		type : "POST",
		url : "../../ReceiveUnit/getGenerateNumber.action",
		async:false,
		data : {"type":"CL"},
		dataType : "json",
		success : function(data) {
			generateNumber = data.generateNumber;
		}
	});
	if(id != null && id != ''){
		// 获取收料单位信息
		$.ajax({
			type : "post",
			url : "../../ReceiveUnit/getReceiveUnitList.action",
			data : {"id" : id},
			dataType : "json",
			success : function(data) {
				// 画面赋值
				if(data != null && data.data.length > 0){
					SetFromValues($("#form1"), data.data[0]);
				}
			}
		});
	}else{
		$("#receiveUnitNumber").val(generateNumber);
	}
})

// 保存按钮
function save(){
	
	var params = formToJson($("#form1"));
	
	// 收料单位编号
	if(params.receiveUnitNumber == ''){
		layer.alert("收料单位编号不能为空!", {
			icon: 2,
			title: "提示"
		});
		return;
	}
	
	// 收料单位名称
	if(params.receiveUnitName == ''){
		layer.alert("收料单位名称不能为空!", {
			icon: 2,
			title: "提示"
		});
		return;
	}
	
	// 用途属性
	if(params.purpose == ''){
		layer.alert("用途属性不能为空!", {
			icon: 2,
			title: "提示"
		});
		return;
	}
	
	// 联系人
//	if(params.contacts == ''){
//		layer.alert("联系人不能为空!", {
//			icon: 2,
//			title: "提示"
//		});
//		return;
//	}
	// 联系电话
//	if(params.telephone == ''){
//		layer.alert("联系电话不能为空!", {
//			icon: 2,
//			title: "提示"
//		});
//		return;
//	}
	
	if (id == null){
		// 新增
		url = '../../ReceiveUnit/insertReceiveUnit.action';
		params.creater=userId;
	} else {
		// 修改
		url = '../../ReceiveUnit/updateReceiveUnit.action';
		params.id = id;
		params.reviser=userId;
	}
	
	// 保存操作
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