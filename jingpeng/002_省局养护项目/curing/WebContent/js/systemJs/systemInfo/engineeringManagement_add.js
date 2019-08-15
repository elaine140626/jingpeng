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
	id = getUrlParam("id");
	ProjectId = getUrlParam("ProjectId");
	flag = getUrlParam("flag");
	if(flag == 2){
		var cityInfoList = getCityInfoList()
		$("#cityId").empty();
		$("#cityId").html(cityInfoList);
	}
	// 修改的场合
	if (id != null && id != ""){
		// 获取单条信息
		var url = "";
		switch (flag) {
		case "1":
			url =  "../../EngineeringManagement/getCityInfoList.action"
			break;
		case "2":
			url =  "../../EngineeringManagement/getCountyInfo.action"
			break;
		case "3":
			url =  "../../EngineeringManagement/getEngineeringManagementList.action"
			break;
			
		default:
			break;
		}
		$.ajax({
			type : "post",
			url : url,
			data : {"id" : id},
			dataType: 'json',
			success : function(data) {
				if(flag == 3){
					var cityInfoList = getCityInfoList()
					$("#cityPid").empty();
					$("#cityPid").html(cityInfoList);
					var countyInfoList = getCountyInfo(data.data[0].cityPid)
					$("#cityId").empty();
					$("#cityId").html(countyInfoList);
				}
				if(data != null && data.data.length > 0){
					SetFromValues($("#form1"), data.data[0]);
				}
			}
		});
	}else{
		var cityInfoList = getCityInfoList()
		if(flag == 2){
			$("#cityId").empty();
			$("#cityId").html(cityInfoList);
		}else if(flag == 3){
			$("#cityPid").empty();
			$("#cityPid").html(cityInfoList);
		}
	}
})

// 保存
function save(){	
	var params = formToJson($("#form1"));	
	var url = '';
	if (id == null){
		// 新增
		switch (flag) {
		case "1":
			url =  "../../EngineeringManagement/insertCityInfo.action"
			break;
		case "2":
			url =  "../../EngineeringManagement/insertCountyInfo.action"
			break;
		case "3":
			url =  "../../EngineeringManagement/insertEntryName.action"
			break;
			
		default:
			break;
		}
		params.creater=userId;
	} else {
		// 修改
		switch (flag) {
		case "1":
			url =  "../../EngineeringManagement/updateCityInfo.action"
			break;
		case "2":
			url =  "../../EngineeringManagement/updateCountyInfo.action"
			break;
		case "3":
			url =  "../../EngineeringManagement/updateEntryName.action"
			break;
			
		default:
			break;
		}
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

function onchangeCity(){
	var cid = $("#cityPid").val();
	var countyInfo = getCountyInfo(cid);
	$("#cityId").empty();
	$("#cityId").html(countyInfo);
}