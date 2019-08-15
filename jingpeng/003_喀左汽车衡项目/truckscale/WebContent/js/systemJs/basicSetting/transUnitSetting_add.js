// 获取的id
var id = "";
// 用户的id
var userId = "";
//用户信息
var userInfo = "";
//运输单位
var companyName = "";
//自增编号
var generateCount;

$(function(){
	//用户信息
	userId = getUrlParam("userId");
	if(userId != null && userId != ""){
		userInfo = getUserInfo(userId);
	}
	
	// 获取传值id
	id = getUrlParam("id");
	
	//传所需的前缀英文简写
	$.ajax({
		type : "POST",
		url : "../../transportCompany/getGenerateCount.action",
		async:false,
		data : {"type":"YS"},
		dataType : "json",
		success : function(data) {
			generateCount = data.getGenerateCount;
		}
	});
	
	if(id != null && id != ''){
		// 获取运输单位信息
		$.ajax({
			type : "post",
			url : "../../transportCompany/getAllTransportCompany.action",
			data : {"id" : id},
			dataType : "json",
			success : function(data) {
				// 画面赋值
				if(data != null && data.data.length > 0){
					SetFromValues($("#form1"), data.data[0]);
					//运输单位
					companyName = data.data[0].transportCompanyName;
				}
			}
		});
	}else{
		$("#transportCompanyNumber").val(generateCount);
	}
});

//保存按钮
function save(){
	var params = formToJson($("#form1"));
	//运输单位编号 
	if(params.transportCompanyNumber == ''){
		layer.alert("运输单位编号不能为空!", {
			icon: 2,
			title: "提示"
		});
		return;
	}
	//运输单位
	if(params.transportCompanyName == ''){
		layer.alert("运输单位不能为空!", {
			icon: 2,
			title: "提示"
		});
		return;
	}
	if (id == null){
		// 新增
		url = '../../transportCompany/addTransportCompany.action';
		params.creater=userInfo.name;
	} else {
		// 修改
		url = '../../transportCompany/updateTransportCompany.action';
		params.id = id;
		params.reviser=userInfo.name;
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

//运输单位去重
function changeTransportCompanyName(value){
	if(id != null && id != ""){
		if(companyName == value){
			return;
		}
	}
	$.ajax({
		type: 'POST',
		url: "../../transportCompany/getTransportCompany.action",
		data:{"transportCompanyName" : value},
		dataType: 'json',
		success: function(data){
			if(data != null && data.length > 0){
				layer.alert("运输单位已经存在,请确认!", {
					icon: 2,
					title: "提示"
				});
				$("#transportCompanyName").val("");
				return;
			}
		}
	});
}