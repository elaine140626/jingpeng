var baseUrl = getContextPath();
$(function() {
	   		 var userName = $.cookie("userName"); //获取cookie中的用户名    
	         var password =  $.cookie("password"); //获取cookie中的登陆密码    
	         if(password){//密码存在的话把“记住用户名和密码”复选框勾选住    
	            //$("[name='checkbox']").attr("checked","true");    
				$("#remember")[0].checked="checked";
	         }    
	         if(userName){//用户名存在的话把用户名填充到用户名文本框    
	            $("#userName").val(userName);    
	         }    
	         if(password){//密码存在的话把密码填充到密码文本框    
	            $("#password").val(password);   
	         } 
	});
function login() {
	var userName = $("#userName").val();
	var password = $("#password").val();
	if($("#remember").is(":checked")){
			jQuery.cookie('userName', $("#userName").val());
			jQuery.cookie('password', $("#password").val());
		}else{
			jQuery.cookie("userName",$("#userName").val()); 
			jQuery.cookie("password", '', { expires: -1 });
		}
	if(userName == "") {
		swal("操作失败","请输入账号", "info");
		return;
	}
	if(password == "") {
		swal("操作失败","请输入密码", "info");
		return;
	}
	if(password.length < 6 ) {
		swal("操作失败","密码长度不得低于6位", "info");
		return;
	}
	var params = $("#loginForm").serialize();
	
	$.ajax({
		type : "POST",
		url : baseUrl + "/login/userLogin.action",
		data : params,
		async:false,
		dataType : "json",
		success : function(data) {
			console.log(data);
			if(data.code == "error") {
//				alert(data.message);
				swal("操作失败",data.message, "info");
			} else {
				if(data.bean.powerOrgID != 0) {
					window.location.href = baseUrl + '/page/mixStation/index.html';
				} else if(data.bean.powerOrgID == 0) {
					window.location.href = baseUrl + '/page/mixStation/user/userinfo.html';
				}
			}
		}
	});
}
//清除cookie  
function clearCookie() {  
	var keys = document.cookie.match(/[^ =;]+(?=\=)/g);
	if(keys) {
		for(var i = keys.length; i--;)
			document.cookie = keys[i] + '=0;expires=' + new Date(0).toUTCString()
	}
} 

document.onkeyup=function(e){  
	e=e||window.event;  
	e.preventDefault(); 
	if(e.keyCode == 13){
		login();
	}

}
