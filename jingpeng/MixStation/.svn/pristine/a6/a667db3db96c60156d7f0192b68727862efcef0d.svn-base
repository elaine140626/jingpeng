var baseUrl = "";
var i_power_Org_Id = "";
$(function(){
	baseUrl = getContextPath();
	
	getshowInfo();
	
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

function getshowInfo() {
	$.ajax({
		type : "POST",
		url : baseUrl + "/User/getPlatformShowInfo.html",
		data : {},
		dataType : "json",
		success : function(data) {
			var user = data.user;
			$("#str_user_Name").val(user.str_user_Name);
			$("#str_name").val(user.str_name);
			$("#i_id").val(user.i_id);
			var orgNames = data.orgNames;
			for(var i = 0; i < orgNames.length; i++) {
				var name = orgNames[i].str_org_Name;
	        	$("input[type='checkbox']").each(function(){
	        		var cshow = $(this).attr("cshow");
	        		if(cshow == name) {
	        			$(this).attr('checked', 'true');
	        		}
	        	});
			}
			i_power_Org_Id = data.i_power_Org_Id;
		}
	});
}

d.checkNode = function (id,i_parent_Id,flag,obj) {
	if(obj == true) {
		i_power_Org_Id += ","+id
	} else {
		i_power_Org_Id = i_power_Org_Id.replace(","+id,"")
	}
}

function add() {
	if($("#str_user_Name").val() == "") {
		alert("必须输入用户名");
		return
	} 
	var password = $("#str_password").val();
	if(password == "") {
		alert("必须输入密码");
		return
	} 
	
	if($("#str_name").val() == "") {
		alert("必须输入姓名");
		return
	} 
	var passwordAgin = $("#passwordAgin").val();
	if(password != passwordAgin) {
		alert("两次输入的密码不一致");
		return;
	}
	var a = $("input[type='checkbox']:checked").val();
	if(typeof(a) == "undefined") {
		alert("必须选择权限");
		return;
	}
	$.ajax({
		type : "POST",
		url : baseUrl + "/User/updatePlatformUser.html",
		data :{
			"i_id":$("#i_id").val(),
			"str_user_Name":$("#str_user_Name").val(),
			"str_password":$("#str_password").val(),
			"str_name":$("#str_name").val(),
			"i_power_Org_Id":i_power_Org_Id
		},
		dataType : "json",
		async:false,
		success : function(data) {
			alert(data.message);
		}
	});
	window.location.href = baseUrl+"/User/platformUserinfo.html";
}
