var baseUrl = "";
var i_power_Org_Id = "";
$(function(){
	baseUrl = getContextPath();
	
	 $.ajax({
	        type: "post",
	        url: "../Andr/getValue.html",
	        data:{},
	        async:false,
	        dataType: "json",
	        success: function (data) {
	            $(".userid").html(data.userName)
	        }
	 })
});

d.checkNode = function (id,i_parent_Id,flag,obj) {
	
	if(obj == true) {
		i_power_Org_Id += ","+id
	} else {
		i_power_Org_Id = i_power_Org_Id.replace(","+id,"")
	}
}

function add() {
	if($("#str_user_Name").val() == "") {
		/*alert("必须输入用户名");*/
		swal("操作失败","必须输入用户名", "info");
		return
	} 
	var password = $("#str_password").val();
	if(password == "") {
		/*alert("必须输入密码");*/
		swal("操作失败","必须输入密码", "info");
		return
	} 
	
	if($("#str_name").val() == "") {
		/*alert("必须输入姓名");*/
		swal("操作失败","必须输入姓名", "info");
		return
	} 
	var passwordAgin = $("#passwordAgin").val();
	if(password != passwordAgin) {
		/*alert("两次输入的密码不一致");*/
		swal("操作失败","两次输入的密码不一致", "info");
		return;
	}
	var a = $("input[type='checkbox']:checked").val();
	if(typeof(a) == "undefined") {
		/*alert("必须选择权限");*/
		swal("操作失败","必须选择权限", "info");
		return;
	}
	$.ajax({
		type : "POST",
		url : baseUrl + "/Register/addPlatformUser.html",
		data :{
			"str_user_Name":$("#str_user_Name").val(),
			"str_password":$("#str_password").val(),
			"str_name":$("#str_name").val(),
			"i_power_Org_Id":i_power_Org_Id
		},
		dataType : "json",
		success : function(data) {
			if(data.code == "300") {
				alert(data.message);
				return;
			}
			alert(data.message);
			window.location.href= baseUrl +"/Platform/index.html";
		}
	});
}
