// 用户id
var id = "";
// 当前用户id
var userId = '';

$(document).ready(function() {
	// 获取传值的用户id
	id = getUrlParam("id");
	// 获取省市
	getCityInfoList();
	
	// 获取当前登录用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){
		userId = userInfo.id;
	}
	
	// 性别
	$("#sex").empty();
	$("#sex").append(getDataDictionaryMultiple("sex"));
	
	// 获取用户信息
	if (id != null && id != "") {
		$.ajax({
			type : 'POST',
			url : '../../Login/getUserInfoList.action',
			data : {
				"id" : id,
			},
			dataType : 'json',
			success : function(data) {
				console.log(data);
				if (data != null && data.data.length > 0) {
					SetFromValues($("#userForm"), data.data[0]);
					getCountyInfo(data.data[0].cityId);
					$("#passWordAlign").val(data.data[0].passWord);
					
					// 用户名不可修改
					$("#userName").attr("readonly", "readonly");
					$("#userName").css('background-color','rgb(235, 235, 228)');
				}
			}
		});
	}

	// 用户角色
/*	$("#roleCode").empty();
	$("#roleCode").append(getDataDictionaryMultiple("yhje"));*/
});

//获取市(下拉列表用--默认请选择)
function getCityInfoList(){
	var DataDictionaryInfo;
	$.ajax({
		type : "POST",
		url : "../../EngineeringManagement/getCityInfoList.action",
		async:false,
		dataType : "json",
		success : function(data) {
			var list = data.data;
			var html = "<option value='0' selected='selected'>全部市</option>";
			for (var i = 0; i < list.length; i++) {
				html += "<option value='" + list[i].id + "'>"
				+ list[i].cityName + "</option>"							
			}
			DataDictionaryInfo = html;
		}
	});
	$("#cityId").html(DataDictionaryInfo);
	$("#countyId").html("<option value='0' selected='selected'>全部县</option>");
}

// 保存
function save() {
	// 获取用户信息
	var param = formToJson($("#userForm"));

	// id
	param.id = id;
	
	// 出生年月
	if($("#birthday").val() == ""){
		param.birthday = null;
	}
	
	// 用户名
	if (param.userName == "") {
		layer.alert('用户名不能必须填写！', {
			icon : 2,
			title : "提示"
		});
		return;
	}

	// 密码
	if (param.passWord == "") {
		layer.alert('密码不能必须填写！', {
			icon : 2,
			title : "提示"
		});
		return;
	}

	// 确认密码
	if (param.passWordAlign == "") {
		layer.alert('请确认输入密码！', {
			icon : 2,
			title : "提示"
		});
		return;
	}
	
	var url = "";
	if(id != null && id != ""){
		url =  '../../Login/updateUserInfo.action';
		// 修改人
		param.reviser = userId;
	}else{
		url =  '../../Login/saveUserInfo.action';
		// 创建人
		param.creater = userId;
	}

	$.ajax({
		type : 'POST',
		url : url,
		data:JSON.stringify(param),
		dataType: 'json',
		contentType: 'application/json',
		success : function(data) {
			if (data.code = "success") {
				layer.alert(data.message, {
					icon : 1,
					title : "提示"
				}, function() {
					// 修改
					if(id != null && id != ''){
						if(userId == id){
							// 本用户修改
							location.href = "login.html";
						}else{
							// 非本用户修改
							window.history.go(-1);
						}
					}else{
						// 注册
						location.href = "login.html";
					}
				});
			} else {
				layer.alert(data.message, {
					icon : 2,
					title : "提示"
				});
			}
		}
	});
}

// 用户名判断
function ChangeUserName(value) {
	if (value == '') {
		layer.alert('用户名不能必须填写！', {
			icon : 2,
			title : "提示",
		});
		return;
	} else {
		$.ajax({
			type : 'POST',
			url : '../../Login/getUserNameCount.action',
			data : {
				"userName" : value
			},
			dataType : 'json',
			success : function(data) {
				if (data.code != "success") {
					layer.alert(data.message, {
						icon : 2,
						title : "提示"
					});

					// 清空用户名
					$("#userName").val("");
					
					// 保存按钮(可用)
					$("#saveUserInfo").attr("disabled", true);
					$("#saveUserInfo").removeClass("btn-primary");
					$("#saveUserInfo").addClass("btn-default");
				} else {
					// 保存按钮(不可用)
					$("#saveUserInfo").attr("disabled", false);
					$("#saveUserInfo").removeClass("btn-default");
					$("#saveUserInfo").addClass("btn-primary");
				}
			}
		});
	}
}

// 关闭
function layer_close(){
	history.go(-1)
}

// 两次密码判断是否一致
function judgePassword(value){
	var password = $("#passWord").val();
	if(value != password){
		layer.alert('两次密码输入不一致！', {
			icon : 2,
			title : "提示",
		});
		// 确认密码清空
		$("#passWordAlign").val("");
	}
}

//电话号码
function changeTelephone(value){
	var regBox = {
	        regMobile : /^0?1[3|4|5|8][0-9]\d{8}$/,//手机
	        regTel : /^0[\d]{2,3}-[\d]{7,8}$/ // 固定电话
	    }
	if(value != null && value != ''){
		var mflag = regBox.regMobile.test(value);
	    var tflag = regBox.regTel.test(value);
	    if (!(mflag || tflag)) {
	    	layer.alert('电话号码格式错误！', {
				icon : 2,
				title : "提示",
			});
	    	// 清空处理
			$("#telephone").val("");
	    }
	}     
}

//获取县(下拉列表用--默认请选择)
function getCountyInfo(type){
	var DataDictionaryInfo;
	$.ajax({
		type : "POST",
		url : "../../EngineeringManagement/getCountyInfo.action",
		async:false,
		data : {CityId:type},
		dataType : "json",
		success : function(data) {
			var list = data.data
			var html = "<option value='0' selected='selected'>全部县</option>";
			for (var i = 0; i < list.length; i++) {
				html += "<option value='" + list[i].id + "'>"
				+ list[i].county + "</option>"							
			}
			DataDictionaryInfo = html;
		}
	});
	$("#countyId").html(DataDictionaryInfo);
}