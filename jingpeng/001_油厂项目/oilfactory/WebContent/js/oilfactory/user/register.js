var baseUrl = "";
var id = "";
var changeFlag = 0;
$(function(){ 
	baseUrl = getContextPath();
}); 

// 提交按钮
function submitForm(form){
	var data = formToJson($("#form"));
	// 用户名
	if($("#username").val() == ''){
		swal({
			title: "错误提示",
			text: "用户名必须填写!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}
	
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
	if($("#confirmPw").val() == ''){
		swal({
			title: "错误提示",
			text: "确认密码必须填写!",
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

    // 密码加密
    if(changeFlag == 0){
    	data.password = hex_md5($("#password").val());
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
	$.ajax({
		type: "post",
		url: baseUrl+"/login/insertUserInfo.action",
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
					/*window.location.href = 'login.html';*/
					window.location.href = "user_center.html";
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
}

// 用户名查重
function changeUserName(value){
	if(value == ''){
	swal({
		title: "错误提示",
		text: "用户名必须填写!",
		type: "error",
		confirmButtonText: '确定',
		cancelButtonFont: '微软雅黑',
	});
   }else{
	   $.ajax({
			type: "post",
			url: baseUrl+"/login/getUserInfo.action",
			data:{
				"username":value
			},
			dataType: "json",
			success: function (data) {
				if(data.code != "success"){
					swal({
						title: "错误提示",
						text: data.message,
						type: data.code,
						confirmButtonText: '确定',
						cancelButtonFont: '微软雅黑',
					},
					function(){
						// 清空处理
						$("#username").val("");
					});
				}
			}
		});
   }
}

// 密码判定
function changePassWord(value){
	changeFlag++;
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

function outRegister(){
	window.location.href = 'login.html';
}