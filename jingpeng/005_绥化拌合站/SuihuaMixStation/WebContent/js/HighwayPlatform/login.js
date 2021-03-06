// 注册验证码
var VerificationCode = '';
$(function() {
	getCookie();
	VerificationCode = getVerificationCode();
});
function login() {
	var str_user_Name = $("#str_user_Name").val();
	var str_password = $("#str_password").val();
	if(str_user_Name == "") {
		swal("操作失败","请输入账号", "info");
		return;
	}
	if(str_password == "") {
		swal("操作失败","请输入密码", "info");
		return;
	}
	
	if(str_user_Name.length>50) {
		swal("操作失败","账号长度过长", "info");
		return;
	}
	if(str_password.length>100) {
		swal("操作失败","密码长度过长", "info");
		return;
	}
	
	var params ={};
	
	params.userCode=$("#str_user_Name").val();
	params.passWord=$("#str_password").val();
	
	if(document.getElementById("remember").checked){
		setCookie(str_user_Name,str_password);
	}else{
		clearCookie(str_user_Name);
	}
	
	$.ajax({
		type : "POST",
		url : "../../UserInfo/userLogin.action",
		data : params,
		async:false,
		dataType : "json",
		success : function(data) {
			if(data.code == 'error'){
				// 失败信息
				swal("操作失败",data.message, data.code);
			}else{
				// 首页
				$(location).attr('href', '../../page/index.html');	
			}
		}
	});
}

//注册验证
function change(){
	$("#Modal").attr("style","display:block");
}

//确定
function ok() {
	
	var pd = $("#yanzhengma").val();
	// 验证
	if(pd == VerificationCode){
		
		// 注册界面
		$(location).attr('href', '../centerLogin/user.html?flag=0');	
	}else{
		swal({
			title: "验证码错误，无法操作!",
			text: "",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
	}
};

//关闭
function guanBi() {
	$("#Modal").attr("style","display:none");
	$("#yanzhengma").val("");
};

function setCookie(name,value)
{
	var Days = 30;
	var exp = new Date();
	exp.setTime(exp.getTime() + Days*24*60*60*1000);
	document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}
function getCookie()
{
	if(document.cookie!="" && document.cookie!=null){
		if(document.cookie.indexOf(';')>-1){
			var list = [];
			var li = [];
			list = document.cookie.split(";");
			list[list.length-1] = list[list.length-1].replace(/(^\s*)|(\s*$)/g, ""); 
			li = list[list.length-1].split("=");
			if(li.length>=2){
				if(li[0]!="" && li[0]!=null && li[1]!="" &&li[1]!=null){
					$("#str_user_Name").val(li[0]);
					$("#str_password").val(li[1]);
					$("#remember")[0].checked="checked";
				}
			}
		}else{
			var list = [];
			list = document.cookie.trim().split("=");
			if(list.length>0){
				if(list[0]!="" && list[0]!=null && list[1]!="" && list[1]!=null){
					$("#str_user_Name").val(list[0]);
					$("#str_password").val(list[1]);
					$("#remember")[0].checked="checked";
				}
			}
		}
	}
	
}
//清除cookie  
function clearCookie(name) { 
    setCookie(name, "", -1);  
} 


