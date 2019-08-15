//获取路径
var baseUrl = "";
//客户起运地id
var id = "";
$(function() {
	baseUrl = getContextPath();
	var param = parseUrl();// 解析所有参数 
	if(param != null && param != ""){
		//判断是否连续添加
		if(param.continuity != null && param.continuity != ""){
			continuity = param['continuity']
			if(continuity == "true"){
				$("#continuityAdd").attr("checked", true);
			}
		}
		if(param.id != null && param.id != ""){
			id = param['id'];
			$("#continuityTr").css('display','none');
			getTransportsById(id);
		}
	}
	// 获取当前登录用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){	
		creater = userInfo.name;
	}
});

//解析所有参数 
function parseUrl() {
	var url = location.href;
	var i = url.indexOf('?');
	if (i == -1)
		return;
	var querystr = url.substr(i + 1);
	var arr1 = querystr.split('&');
	var arr2 = new Object();
	for (i in arr1) {
		var ta = arr1[i].split('=');
		arr2[ta[0]] = ta[1];
	}
	return arr2;
}

//添加客户起运地
function addTransports(){
	//是否连续添加
	var continuityAdd = $("#continuityAdd").is(':checked')
	//起运地点
	var startAddress = $("#startAddress").val();
	//允许偏差
	var deviation = $("#deviation").val();
	//备注
	var remarks = $("#remarks").val();
	var cid = id
	
	if(startAddress==""){
		swal("操作失败", "必须输入起运地点", "info");
		return;
	}
	if(deviation==""){
		swal("操作失败", "必输输入允许偏差", "info");
		return;
	}
	if(remarks.length>200){
		swal("操作失败", "备注内容过长", "info");
		return;
	}
	if(cid!=""&&cid!=null){
		options = {
				"startAddress" : startAddress,
				"deviation" : deviation,
				"remarks":remarks,
				"reviser":creater,
				"id":cid
		};

		 $.ajax({
	         type: "post",
	         url: baseUrl+'/Transports/updateStartTransports.action',
	         async:false,
	         data:JSON.stringify(options),
	         dataType: "json",
	         contentType : 'application/json;charset=UTF-8',
	         success: function (data) {
	        	 if(data.code == "200"){
						swal({
							title : data.message,
							text : "",
							type : "success",
							confirmButtonText : '确定',
							cancelButtonFont : '微软雅黑',
						}, function() {
							window.parent.$.fancybox.close();
							window.location.reload();
						});	
	        	 }else{
	        		 swal("操作失败", data.message, "error");
	        	 }
	         }
	    });
	}else{
	options = {
			"startAddress" : startAddress,
			"deviation" : deviation,
			"creater":creater,
			"remarks":remarks,
	};
	 $.ajax({
         type: "post",
         url: baseUrl+'/Transports/addStartTransports.action',
         async:false,
         data:JSON.stringify(options),
         dataType: "json",
         contentType : 'application/json;charset=UTF-8',
         success: function (data) {
        	 if(data.code == "200"){
        		 if(continuityAdd){
						window.location.href = 'start_transports_edit.html?continuity='+continuityAdd;
					}else{
						swal({
							title : data.message,
							text : "",
							type : "success",
							confirmButtonText : '确定',
							cancelButtonFont : '微软雅黑',
						}, function() {
							window.parent.$.fancybox.close();
							window.location.reload();
						});	
					}
        	 }else{
        		 swal("操作失败", data.message, "error");
        	 }
         }
    });
  }
}

//通过id查询客户起运地
function getTransportsById(id){
	$.ajax({
        type: "post",
        url: baseUrl+'/Transports/getStartTransports.action',
        async:false,
        data:{"id":id},
        dataType: "json",
        success: function (data) {
        	var startTransports = data.data;
        	$("#startAddress").val(startTransports[0].startAddress);
        	$("#deviation").val(startTransports[0].deviation);
        	$("#remarks").val(startTransports[0].remarks);
        }
   });
}

//验证方法
function isJudge(){
	//运输起点
	var startAddress = $("#startAddress").val();
	if(startAddress.length>200){
		swal("操作失败", "运输地点长度过长,请重新填写", "info");
		$("#startAddress").val("");
		return;
	}
}
//验证方法
function isJudgeDistance(){
	var deviation = $("#deviation").val();
	if(deviation==""){
		swal("操作失败", "运输距离不能为空", "info");
		return;
	}
	if(distance.length>8){
		swal("操作失败", "运输距离数值过大,请重新填写", "info");
		$("#distance").val("");
		return;
	}
}
//验证方法
function isJudgeRemarks(){
	var remarks = $("#remarks").val();
	if(remarks.length>200){
		swal("操作失败", "备注内容过长", "info");
		$("#remarks").val("");
		return;
	}
}
//验证方法
function clearNoNum(obj){ 
    obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
    obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); 
    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数  
    if(obj.value.indexOf(".")< 0 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额 
        obj.value= parseFloat(obj.value); 
    } 
} 