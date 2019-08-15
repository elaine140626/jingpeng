var baseUrl = "";
var id ='';
$(function() {
	baseUrl = getContextPath();
	id = getUrlParam("id");
	powerOrgID =  getUrlParam("powerOrgID");
		getshowInfo();
});

function getshowInfo() {

	$.ajax({
		type : "POST",
		url : baseUrl + "/User/getshowInfo.action",
		data : {
			"id" : id,"powerOrgID" : powerOrgID
		},
		dataType : "json",
		success : function(data) {
			console.log(data);
			var user = data.user;
			$('#orgId').val(id);
			$("#userName").val(user.userName);
			$("#name").val(user.name);
			if(powerOrgID == 0){
				$("#name").attr("readOnly","true");
				$('.org').hide();
				var html = "";
				var orgs = data.orgs;
				for(var i = 0; i < orgs.length; i++) {
					html += "<div class='danxuan'><input name='powerOrgID' id='powerOrgID' type='radio' value='"+orgs[i].id+"'>"
					+"<label for='checkbox'>"+orgs[i].orgName+"</label></div>";
				}
				$("#org").html(html);
				$("input[name='powerOrgID'][value="+user.powerOrgID+"]").attr("checked",true); 
			}else {
				$('.quanxian').hide();
				$('#password,#passwordAgin').val(user.password);
			}
			
		}
	});
}

function add() {
	if(powerOrgID != 0) {
		if($('#password').val() == "") {
			swal("操作失败","密码不能为空", "info");
			return
		} 
		if($('#password').val().length < 6 ) {
			swal("操作失败","密码长度不得低于6位", "info");
			return;
		}
		if($('#passwordAgin').val() != $('#password').val()) {
			swal("操作失败","两次输入的密码不一致", "info");
			return;
		}
		if($('#name').val() == "") {
			swal("操作失败","姓名不能为空", "info");
			return
		} 
		var params = $("#tab").serialize();
	}
	if(powerOrgID == 0){
		var a = $("input[name='powerOrgID']:checked").val();
		if(typeof(a) == "undefined") {
			swal("操作失败","必须选择权限", "info");
			return;
		}else {
			var params = $("#tab").serialize();
			params.powerOrgID = a;
		}
	}
	console.log(params);

	$.ajax({
		type : "POST",
		url : baseUrl + "/User/updateUser.action",
		data : params,
		async:false,
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
					if(powerOrgID != 0) {
						window.location.href= '../../User/out.action';
					}else {
						history.go(-1);
					}
					
				});
				
			}else{
				swal("操作失败",data.message, "error");
			}
		}
	});
	
}