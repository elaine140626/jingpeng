var baseUrl ="";
var id;
$(function(){
	//获取超链接拼接url
	baseUrl = getContextPath();
	var param = parseUrl();// 解析所有参数 
	getCustomerInfo();
	getVisitContent();
	getUserInfoByCode();
	//如果参数不为空 则为修改  否则 新增
	if(param != null && param != ""){
		id = param['id'];
		//根据id查询回访信息
		findFleetByid(id);
	}
})

//新增/修改
function addClient_back(){
	var customerId = $("#customerId").val();
	var visitDate = $("#visitDate").val();
	var visitForm = $("#visitForm").val();
	var userInfoId = $("#userInfoName").val();
	var visitContent = $("#visitContent").val();
	var remarks = $("#remarks").val();
	var cid = id
	
	if(customerId==""){
		swal("操作失败", "必须输入客户编号", "info");
		return;
	}
	if(customerId.length>30){
		swal("操作失败", "客户编号长度不能超过20", "info");
		return;
	}
	
	if(visitDate==""){
		swal("操作失败", "必须输入时间", "info");
		return;
	}
	
	var visitContent = $("#visitContent").val();
	if(visitContent==""){
		swal("操作失败", "回访内容不能为空", "info");
		return;
	}
	if(visitContent.length>200){
		swal("操作失败", "回访内容过长", "info");
		return;
	}
	
	var remarks = $("#remarks").val();

	if(remarks.length>200){
		swal("操作失败", "备注内容过长", "info");
		return;
	}
	if(cid!=""&&cid!=null){
		
		options = {
				"customerId" : customerId,
				"visitDate" : visitDate,
				"visitForm" : visitForm,
				"userInfoId" : userInfoId,
				"visitContent" : visitContent,
				"remarks":remarks,
				"id":cid
		};

		 $.ajax({
	         type: "post",
	         url: baseUrl+'/Client_back/updateClient_back.action',
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
			"customerId" : customerId,
			"visitDate" : visitDate,
			"visitForm" : visitForm,
			"userInfoId" : userInfoId,
			"visitContent" : visitContent,
			"remarks":remarks
	};
	
	 $.ajax({
         type: "post",
         url: baseUrl+'/Client_back/addClient_back.action',
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
  }
}

//客户编号验证
function isJudgeCustomerId(){
	var customerId = $("#customerId").val();
	if(customerId==""){
		swal("操作失败", "必须输入客户编号", "info");
		return;
	}
	if(customerId.length>30){
		swal("操作失败", "客户编号长度不能超过20", "info");
		return;
	}
}

//回访日期验证
function isJudgeVisitDate(){
	var visitDate = $("#visitDate").val();
	if(visitDate==""){
		swal("操作失败", "请输入回访时间", "info");
		return;
	}
}

//回访内容长度验证
function isJudgeVisitContent(){
	var visitContent = $("#visitContent").val();
	if(visitContent==""){
		swal("操作失败", "回访内容不能为空", "info");
		return;
	}
	if(visitContent.length>200){
		swal("操作失败", "回访内容过长", "info");
		return;
	}
}

//备注长度验证
function isJudgeRemarks(){
	var remarks = $("#remarks").val();

	if(remarks.length>200){
		swal("操作失败", "备注内容过长", "info");
		return;
	}
}

//客户评价下拉列表内容查询
function getVisitContent(){
	 $.ajax({
         type: "post",
         url: baseUrl+'/Client_back/getVisitContent.action',
         async:false,
         dataType: "json",
         contentType : 'application/json;charset=UTF-8',
         success: function (data) {
        	  	$.each(data.data, function (i, v) {
            		var str = '<option value="' + v.code + '">'
    				+ v.content + '</option>';
            		$('#visitForm').append(str);
                })
         }
    });
}
//客户名称下拉列表内容查询
function getCustomerInfo(){
	 $.ajax({
        type: "post",
        url: baseUrl+'/Client_back/getCustomerInfo.action',
        async:false,
        dataType: "json",
        contentType : 'application/json;charset=UTF-8',
        success: function (data) {
       	  	$.each(data.data, function (i, v) {
           		var str = '<option value="' + v.uuid + '">'
   				+ v.customerCode + '</option>';
           		$('#customerId').append(str);
               })
        }
   });
}
//销售员下拉列表内容查询
function getUserInfoByCode(){
	 $.ajax({
       type: "post",
       url: baseUrl+'/Client_back/getUserInfoByCode.action',
       async:false,
       dataType: "json",
       contentType : 'application/json;char set=UTF-8',
       success: function (data) {
      	  	$.each(data.data, function (i, v) {
          		var str = '<option value="' + v.id + '">'
  				+ v.name + '</option>';
          		$('#userInfoName').append(str);
              })
       }
  });
}
//根据id查询回访信息
function findFleetByid(id){
	$.ajax({
        type: "post",
        url: baseUrl+'/Client_back/getClient_backById.action',
        async:false,
        data:{"id":id},
        dataType: "json",
        success: function (data) {
        	var client = data.data;
        	//客户名称赋值
        	$("#customerId").val(client.customerId);
        	//回访日期赋值
        	$("#visitDate").val(client.visitDate);
        	//客户评级赋值
        	$("#visitForm").val(client.visitForm);
        	//销售员赋值
        	$("#userInfoName").val(client.userInfoId);
        	//回访内容赋值
        	$("#visitContent").val(client.visitContent);
        	//备注赋值
        	$("#remarks").val(client.remarks);
        }
   });
}

//解析参数
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