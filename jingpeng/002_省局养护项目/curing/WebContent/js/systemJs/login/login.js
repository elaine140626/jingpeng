// 注册
function login(){
	// 获取画面参数
	var param = formToJson($("#form"));
	// 用户名
	if(param.userName == ""){
		layer.alert('用户名不能必须填写！', {
			icon: 2,
			title: "提示"
		});
		return;
	}
	
	// 密码
	if(param.passWord == ""){
		layer.alert('密码不能必须填写！', {
			icon: 2,
			title: "提示"
		});
		return;
	}
	
	$.ajax({
		type: 'POST',
		url: '../../Login/login.action',
		data: param,
		dataType: 'json',
		success: function(data){
			if(data.user != null){
				//location.href = "../../index.html";
				location.href = "../../indexNew.html";
			}else{
				layer.alert("用户名或密码错误!", {
					icon: 2,
					title: "提示"
				});
			}
		}
	});
}

// 注册
function register(){
	location.href = "userInfo.html";
}