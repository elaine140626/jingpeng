// 用户id
var userId = '';
var flag = false;
$(function(){ 
	// 获取当前登录用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){	
		rolecode = userInfo.rolecode;
		// 当前用户权限
		if(rolecode != ""){
			var roleList = rolecode.split(',');
			for(var i = 0;i < roleList.length; i++){
				// 管理员权限
				if(roleList[i] == 0){
					flag = true;
					break;
				}
			}
		}
	}
	
	userId = getUrlParam("id");
	
	// 获取用户信息getUserInfoList
	$.ajax({
		type: "post",
		url: "../../login/getUserInfoList.action",
		data:{"id":userId},
		dataType: "json",
		success: function (data) {
			if(data != null){
				// 用户名
				$("#id").val(data.data[0].id);
				// 用户名
				$("#username").val(data.data[0].username);
				// 密码
				$("#password").val(data.data[0].password);
				// 确认密码
				$("#confirmPw").val(data.data[0].password);
				// 权限
				var rolecodeList = data.data[0].rolecode.split(',');
				var list = document.getElementsByName('rolecode');
				for (var i = 0; i < rolecodeList.length; i++) {
					for (var j = 0; j < list.length; j++) {
						if (rolecodeList[i] == list[j].value) {
							list[j].checked = true;
							break;
						}
					}
				}
				// 姓名
				$("#name").val(data.data[0].name);
				// 年龄
				$("#age").val(data.data[0].age);
				// 性别
				//$("input[type=radio]").attr("checked",data.data[0].sex);
				$("input[name=sex][value="+data.data[0].sex+"]").attr("checked",true);
				// 出生年月
				$("#birthday").val(data.data[0].birthday);
				// 联系电话
				$("#telephone").val(data.data[0].telephone);
				// 其他联系方式
				$("#othernumbers").val(data.data[0].othernumbers);
				// 地址
				$("#address").val(data.data[0].address);
			}
		}
	});
 });

// 保存方法
function update() {
	var data = formToJson($("#form"));
	// 密码
	if($("#password").val() == ''){
		swal({
			title: "错误提示",
			text: "密码必须填写!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}
	// 用户权限
	var rolecode=",";
    $("[name='rolecode']:checked").each(function(index, element) {
    	rolecode += $(this).val() + ",";
    });
    if(rolecode == ","){
    	swal({
			title: "错误提示",
			text: "请选择用户权限!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
    }else{
    	data.rolecode = rolecode;
    } 
    
	// 姓名
	if($("#name").val()  == ''){
		swal({
			title: "错误提示",
			text: "姓名必须填写!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}
	// 出生日期
	if($("#birthday").val()  == ''){
		swal({
			title: "错误提示",
			text: "出生日期必须填写!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}
	// 联系电话
	if($("#telephone").val()  == ''){
		swal({
			title: "错误提示",
			text: "联系电话必须填写!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}

	// 地址
	if($("#address").val()  == ''){
		swal({
			title: "错误提示",
			text: "地址必须填写!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}
	var isoutLogin = true;
	if(userInfo.id == data.id){
		isoutLogin = false;
	}
	$.ajax({
		type: "post",
		url:  "../../login/updateUserInfo.action",
		data: data,
		dataType: "json",
		success: function (data) {
			if(data.code == "success"){
				swal({
					title: "提示信息",
					text: data.message,
					type: data.code,
					confirmButtonText: '确定',
					cancelButtonFont: '微软雅黑',
				},
				function(){
					if(flag == true && isoutLogin == true){
						window.history.back(-1);
					}else{
						$.ajax({
							type : "POST",
							url : "../../login/delUserSession.action",
							data : {},
							dataType : "json",
							success : function(data) {
								if (data.code == 'error') {
									// 失败信息
									swal("操作失败", data.message, data.code);
								} else {
									// 首页
									window.location.href = "login.html";
								}
							}
						});
					}
					
				});
			}else{
				swal({
					title: "错误提示",
					text: data.message,
					type: data.code,
					confirmButtonText: '确定',
					cancelButtonFont: '微软雅黑',
				});
			}
			
		}
	});
};

//密码判定
function changePassWord(value){
	if(value == ''){
		swal({
			title: "错误提示",
			text: "密码必须填写!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
   }else if(value.length < 6){
	   swal({
			title: "错误提示",
			text: "密码长度不小于6位!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
   }else if(value.length > 128){
	   swal({
			title: "错误提示",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
  }else{
	  $("#password").val(hex_md5(value));
  }
}

// 验证两次密码是否一致
function changeConfirmPw(value){
    var password = $("#password").val();
    
    if(hex_md5(value) != password){
    	swal({
			title: "错误提示",
			text: "两次密码不一致,请确认!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		},
		function(){
			// 清空处理
			$("#confirmPw").val("");
		});
    }else{
    	$("#confirmPw").val(hex_md5(value));
    }
}

//姓名判定
function changeName(value){
	if(value == ''){
		swal({
			title: "错误提示",
			text: "姓名必须填写!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
   }else if(value.length > 20 ){
		swal({
			title: "错误提示",
			text: "姓名长度过长重新填写!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
   }
}
// 电话号码
function changeTelephone(value){
	var regBox = {
	        regMobile : /^0?1[3|4|5|8][0-9]\d{8}$/,//手机
	        regTel : /^0[\d]{2,3}-[\d]{7,8}$/ // 固定电话
	    }
	if(value != null && value != ''){
		var mflag = regBox.regMobile.test(value);
	    var tflag = regBox.regTel.test(value);
	    if (!(mflag || tflag)) {
	    	 swal({
	 			title: "错误提示",
	 			text: "固定电话号码或手机号码格式错误,请重新填写!",
	 			type: "error",
	 			confirmButtonText: '确定',
	 			cancelButtonFont: '微软雅黑',
	 		},function(){
	 		// 清空处理
				$("#telephone").val("");
	 		});
	    }
	}     
}

function changeBirthday(value){
	var nowDate = new Date();
	var startDate = new Date(value);
	var userDate = nowDate.getTime() - startDate.getTime();
	var userAge = Math.ceil(userDate / 1000 / 60 / 60 / 24 /365);


     var oldDate = startDate;//模拟用户输入的时间并已经转换成当前格式；
    

	 if (nowDate<oldDate){
		 $("#birthday").val("");
		 $("#age").val("");
		 swal({
				title: "错误提示",
				text: "日期大于当前日期,请输入正确的出生日期!",
				type: "error",
				confirmButtonText: '确定',
				cancelButtonFont: '微软雅黑',
			});
			return;
     }else{
    	 if($("#birthday").val()==""){
    		 $("#age").val("");
    	 }else{
    		 $("#age").val(userAge);
    	 }
     }
}


