var baseUrl = getContextPath();
$(function() {
	   		 var str_user_Name = $.cookie("str_user_Name"); //获取cookie中的用户名    
	         var str_password =  $.cookie("str_password"); //获取cookie中的登陆密码    
	         if(str_password){//密码存在的话把“记住用户名和密码”复选框勾选住    
	            $("[name='checkbox']").attr("checked","true");    
	         }    
	         if(str_user_Name){//用户名存在的话把用户名填充到用户名文本框    
	            $("#str_user_Name").val(str_user_Name);    
	         }    
	         if(str_password){//密码存在的话把密码填充到密码文本框    
	            $("#str_password").val(str_password);   
	         } 
	});
function login() {
	var str_user_Name = $("#str_user_Name").val();
	var str_password = $("#str_password").val();
	if($("#remember").is(":checked")){
			jQuery.cookie('str_user_Name', $("#str_user_Name").val());
			jQuery.cookie('str_password', $("#str_password").val());
		}else{
			jQuery.cookie("str_user_Name",$("#str_user_Name").val()); 
			jQuery.cookie("str_password", '', { expires: -1 });
		}
	if(str_user_Name == "") {
		swal("操作失败","请输入账号", "info");
		return;
	}
	if(str_password == "") {
		swal("操作失败","请输入密码", "info");
		return;
	}
	if(str_password.length < 6 ) {
		swal("操作失败","密码长度不得低于6位", "info");
		return;
	}
	var params = $("#loginForm").serialize();
	
	$.ajax({
		type : "POST",
		url : baseUrl + "/Login/userLogin.action",
		data : params,
		async:false,
		dataType : "json",
		success : function(data) {
			if(data.code == "error") {
				/*alert(data.message);*/
				swal("操作失败",data.message, "info");
			} else {
				window.location.href=baseUrl+"/AsphaltProduceStatistic/asphaltProduceStatistic.action"
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


