//用户的id
var userId = "";
//用户信息
var userInfo = "";
$(function(){
	//用户信息
	userId = getUrlParam("userId");
	if(userId != null && userId != ""){
		userInfo = getUserInfo(userId);
	}
})

//保存
function save(){
	var params = formToJson($("#form1"));
	if(params.password == ""){
		layer.alert("请输入密码!",{
			icon: 2,
			title: "提示"
		});
		return;
	}
	if(params.password2 == ""){
		layer.alert("再次输入密码不能为空!",{
			icon: 2,
			title: "提示"
		});
		return;
	}
	if(params.password2 != params.password){
		layer.alert("两次输入密码不符!",{
			icon: 2,
			title: "提示"
		});
		return;
	}
	params.id = userId;
	params.Reviser = userInfo.name;
	$.ajax({
		type : "POST",
		url : '../../UserManage/updatePass.action',
		data : {
			"id" : userId,
			"password" : params.password,
			"Reviser" : userInfo.nam
		},
		dataType : 'json',
		success : function(data){
			if(data.code == 'success'){
				//操作成功
				layer.alert(data.message,{
					icon: 1,
					title: "提示"
				},function(){
					window.location.reload();
				});
			}else{
				//操作失败
				layer.alert(data.message,{
					icon: 2,
					title: "提示"
				},function(){
					window.location.reload();
				});
			}
		}
	});
}

