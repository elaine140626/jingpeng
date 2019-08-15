var baseUrl = "";
$(function() {
	baseUrl = getContextPath();
	getOrg();
});

function getOrg() {
	$.ajax({
		type : "POST",
		url : baseUrl + "/login/getOrg.action",
		data : {},
		dataType : "json",
		success : function(data) {
			var html = "";
			for(var i = 0; i < data.length; i++) {
				html += "<div class='danxuan'><input name='powerOrgID' type='radio' value='"+data[i].id+"'>"
                        +"<label for='checkbox'>"+data[i].orgName+"</label></div>";
			}
			$("#org").html(html);
		}
	});
}

function add() {
	if($("#userName").val() == "") {
		/*alert("必须输入用户名");*/
		swal("操作失败","用户名不能为空", "info");
		return
	} 
	if($("#password").val() == "") {
		/*alert("必须输入密码");*/
		swal("操作失败","密码不能为空", "info");
		return
	} 
	if($("#password").val().length < 6) {
		/*alert("必须输入密码");*/
		swal("操作失败","密码长度不得低于6位", "info");
		return
	} 
	if($('#passwordAgin').val() != $('#password').val()) {
		swal("操作失败","两次输入的密码不一致", "info");
		return;
	}
	if($('#name').val() == "") {
		swal("操作失败","姓名不能为空", "info");
		return
	} 
	var a = $("input[type='radio']:checked").val();
	if(typeof(a) == "undefined") {
		/*alert("必须选择权限");*/
		swal("操作失败","必须选择权限", "info");
		return;
	}
	var params = $("#tab").serialize();
	params.powerOrgID = a;
	console.log(params);
	
	$.ajax({
		type : "POST",
		url : baseUrl + "/login/addUser.action",
		data : params,
		dataType : "json",
		success : function(data) {
			if(data.code=="success"){
				swal({
					title: data.message,
					text: "",
					type: "success",
					confirmButtonText: '确定',
					cancelButtonFont: '微软雅黑',
				},
				function(){
					history.go(-1);
				});
				
			}else{
				swal("操作失败",data.message, "error");
			}
		}
	});
}