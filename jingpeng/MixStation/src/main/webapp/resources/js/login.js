var baseUrl = getContextPath();

function login() {
	var str_user_Name = $("#str_user_Name").val();
	var str_password = $("#str_password").val();
	if(str_user_Name == "") {
		/*alert("请输入账号");*/
		swal("操作失败","请输入账号", "info");
		return;
	}
	if(str_password == "") {
		/*alert("请输入密码");*/
		swal("操作失败","请输入密码", "info");
		return;
	}
	var params = $("#loginForm").serialize();
	
	
	if(document.getElementById("remember").checked){
		setCookie(str_user_Name,str_password);
	}else{
		clearCookie(str_user_Name);
	}
	
	
	$.ajax({
		type : "POST",
		url : baseUrl + "/Login/userLogin.html",
		data : params,
		async:false,
		dataType : "json",
		success : function(data) {
			if(data.code == "error") {
				/*alert(data.message);*/
				swal("操作失败",data.message, "info");
			} else {
				window.location.href=baseUrl+"/AsphaltProduceStatistic/asphaltProduceStatistic.html"
			}
		}
	});
}

function setCookie(name,value)
{
	var Days = 30;
	var exp = new Date();
	exp.setTime(exp.getTime() + Days*24*60*60*1000);
	document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}
function getCookie(name)
{
	var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	if(arr=document.cookie.match(reg))
		alert(unescape(arr[2]));
	return unescape(arr[2]);
	
}
//清除cookie  
function clearCookie(name) {  
    setCookie(name, "", -1);  
} 


function loadStore(){
	
	var dataCookie = [];
	dataCookie = document.cookie.split("=");
	if(dataCookie.length>1){
		$("#str_user_Name").val(dataCookie[0]);
		$("#str_password").val(dataCookie[1]);
		$("#remember")[0].checked="checked";
		
	}
}

