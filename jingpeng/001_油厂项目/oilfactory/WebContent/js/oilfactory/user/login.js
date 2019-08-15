var baseUrl = "";
$(function(){ 
	baseUrl = getContextPath();
	
	// 注册按钮
	$("#login_reg").click(function(){
		// 注册页面
		window.location.href = 'register.html';
	});

 });

//  登录按钮
function login(){

	// 用户名
	var username = $("#username").val();
	if(username == ''){
		swal({
			title: "错误提示",
			text: "用户名必须输入!",
			type: '',
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}
	// 密码
	var password = $("#password").val();
	if(password == ''){
		swal({
			title: "错误提示",
			text: "密码必须输入!",
			type: '',
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}else{
		$("#password").val(hex_md5(password));
	}
	
	$.ajax({
		type: "post",
		url: baseUrl+"/login/login.action",
		data:$("#submitForm").serializeArray(),
		dataType: "json",
		success: function (data) {
			if(data.code == "error") {
				swal("操作失败",data.message, "info"),
				function(){
					// 清空处理
					$("#submitForm input").val("");
				};
			}else{
				window.location.href= "../../index.html";
				$.ajax({
					type: "post",
					url: baseUrl+"/index/appJpush.action",
					data:{},
					dataType: "json",
					success: function (data) {
						}
					});
			}
			/*if(data.code == "success"){
				// 首页
				window.location.href = '../../index.html';
			}else{
				swal({
					title: "错误提示",
					text: data.message,
					type: data.code,
					confirmButtonText: '确定',
					cancelButtonFont: '微软雅黑',
				},
				function(){
					// 清空处理
					$("#submitForm input").val("");
				});
			}*/
		}
	});
}

// 回车事件
function EnterPress(e){ //传入 event 
	var e = e || window.event; 
	if(e.keyCode == 13){ 
		// 登录
		login();
	} 
} 