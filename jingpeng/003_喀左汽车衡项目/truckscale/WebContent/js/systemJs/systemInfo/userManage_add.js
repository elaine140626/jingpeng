var id = "";
var userName = "";
var userInfo = '';
var flag;
var userId = '';
$(document).ready(function() {
	// 获取当前登录用户信息
	/*userInfo = getUserInfo();
	if(userInfo != ""){
		userId = userInfo.id;
	}*/
	userId = getUrlParam("id")
	//获取职务(下拉列表用--默认请选择)
	var userPostList = getDataDictionary('sex')
	// 性别
	$("#sex").empty();
	$("#sex").append(userPostList);
	// 获取用户信息
	if (userId != null && userId != "") {
		// 用户名不可修改
		$("#userName").attr("readonly", true);
		$.ajax({
			type : 'POST',
			url : '../../UserManage/getUserInfoList.action',
			data : {
				"id" : userId,
			},
			dataType : 'json',
			success : function(data) {
				console.log(data);
				if (data.data != null) {
					SetFromValues($("#userForm"), data.data[0]);
					id = data.data.id;
				}
			}
		});
	}

});

// 保存
function save() {
	// 获取用户信息
	var param = formToJson($("#userForm"));
	// id
	param.id = userId;
	
	// 用户名
	if (param.userName == "") {
		layer.alert('用户名不能必须填写！', {
			icon : 2,
			title : "提示"
		});
		return;
	}

	var url = "";
	if(userId != null && userId != ""){
		url =  '../../UserManage/updateUserInfo.action';
	}else{
		url =  '../../UserManage/saveUserInfo.action';
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
					window.location.href="userManage.html";
				});
			} else {
				layer.alert(data.message, {
					icon : 2,
					title : "提示"
				}, function() {
					window.location.reload();
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
			url : '../../UserManage/getUserNameCount.action',
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