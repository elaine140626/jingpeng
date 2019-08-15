var baseUrl = "";
var id = "";
$(function() {
	$("#indexType").html(getDataDictionary('indexType'));
	baseUrl = getContextPath();
	var param = parseUrl();// 解析所有参数 
	if(param != null && param != ""){
		if(param.continuity != null && param.continuity != ""){
			continuity = param['continuity']
			if(continuity == "true"){
				$("#continuityAdd").attr("checked", true);
			}
		}
		if(param.id != null && param.id != ""){
			id = param['id'];
			$("#continuityTr").css('display','none');
			//mode = param['mode'];
			getDetectionindex(id);
		}
	}
	// 获取当前登录用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){	
		creater = userInfo.name;
	}
});

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
function addDetectionIndex(){
	var continuityAdd = $("#continuityAdd").is(':checked')
	var testingItems = $("#testingItems").val();
	var indexType = $("#indexType").val();
	var indexUnit = $("#indexUnit").val();
	var testMethod = $("#testMethod").val();
	var cid = id
	
	if(testingItems==""){
		swal("操作失败", "必须输入检测项目", "info");
		return;
	}
	if(indexType==""){
		swal("操作失败", "必输输入指标类型", "info");
		return;
	}
	if(indexUnit==""){
		swal("操作失败", "必输输入单位", "info");
		return;
	}
	if(testMethod==""){
		swal("操作失败", "必输输入试验方法", "info");
		return;
	}
//	if(remarks.length>200){
//		swal("操作失败", "备注内容过长", "info");
//		return;
//	}
	if(cid!=""&&cid!=null){
		options = {
				"testingItems" : testingItems,
				"indexType" : indexType,
				"indexUnit":indexUnit,
				"testMethod":testMethod,
				"reviser":creater,
				"id":cid
		};

		 $.ajax({
	         type: "post",
	         url: baseUrl+'/Detectionindex/updateDetectionindex.action',
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
			"testingItems" : testingItems,
			"indexType" : indexType,
			"indexUnit":indexUnit,
			"testMethod":testMethod,
			"creater":creater
	};
	 $.ajax({
         type: "post",
         url: baseUrl+'/Detectionindex/addDetectionindex.action',
         async:false,
         data:JSON.stringify(options),
         dataType: "json",
         contentType : 'application/json;charset=UTF-8',
         success: function (data) {
        	 if(data.code == "200"){
        		 if(continuityAdd){
						window.location.href = 'detectionIndex_edit.html?continuity='+continuityAdd;
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
						});						}
        	 }else{
        		 swal("操作失败", data.message, "error");
        	 }
         }
    });
  }
}

function getDetectionindex(id){
	$.ajax({
        type: "post",
        url: baseUrl+'/Detectionindex/getDetectionindex.action',
        async:false,
        data:{"id":id},
        dataType: "json",
        success: function (data) {
        	var detectionindex = data.data;
        	$("#testingItems").val(detectionindex[0].testingItems);
        	$("#indexType option[value='"+detectionindex[0].indexTypes+"']").attr("selected", true);
        	$("#indexUnit").val(detectionindex[0].indexUnit);
        	$("#testMethod").val(detectionindex[0].testMethod);
        }
   });
}

//验证方法
function isJudge(){
	//运输起点
	var testingItems = $("#testingItems").val();
	if(testingItems.length>200){
		swal("操作失败", "检测指标长度过长,请重新填写", "info");
		$("#testingItems").val("");
		return;
	}
}