var baseUrl = getContextPath();

function login() {
	var str_user_Name = $("#str_user_Name").val();
	var str_password = $("#str_password").val();
	if(str_user_Name == "") {
		alert("请输入账号");
		return;
	}
	if(str_password == "") {
		alert("请输入密码");
		return;
	}
	var params = $("#loginForm").serialize();
	
	$.ajax({
		type : "POST",
		url : baseUrl + "/Login/platformUserLogin.html",
		data : params,
		async:false,
		dataType : "json",
		success : function(data) {
			if(data.code == "error") {
				alert(data.message);
			} else {
				window.location.href=baseUrl+"/Platform/index.html"
			}
		}
	});
	
}