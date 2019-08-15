var baseUrl = "";
var uuid = "";
$(function(){
	baseUrl = getContextPath();
	var param = parseUrl();//解析所有的参数
	//新增界面信息初始化
	$.ajax({
		type: "post",
		url: baseUrl+'/client/getDatadictionaryList.action',
		async:false,
		dataType: "json",
		success: function (data) {
			$.each(data, function (i, v) {
				//意向客户级别
				$('<option></option>').attr("value", v.content).html(v.content).appendTo($("#intentionallevel"));
				//采购级别
				$('<option></option>').attr("value", v.content).html(v.content).appendTo($("#procurementlevel"));
				//诚信级别
				$('<option></option>').attr("value", v.content).html(v.content).appendTo($("#creditlevel"));
			})
		}
	});
	//省
	$.ajax({
		type: "post",
		async:false,
		url: baseUrl+'/client/getProvinceinfoList.action',
		dataType: "json",
		success: function (data) {
			for (var i = 0; i < data.length; i++) {
				var str = '<option value="' + data[i].id + '">'
   				+ data[i].province  + '</option>';
				$('#province').append(str);
			}
			//手动触发change事件
			$("#province").change();
		}
	});
	$("#province").change(function(){
		$.ajax({
			type: "post",
			async:false,
			url: baseUrl+'/client/getCityInfoList.action',
			dataType: "json",
			data:"id="+$(this).val(),
			success: function (data) {
				$("#city").empty();
				$.each(data, function (i, v) {
					$('<option></option>').attr("value", v.id).html(v.cityName).appendTo($("#city"));
				})
			}
		});
	});
	//param不为空为修改 否则 为新增
	if(param != null && param != ""){
		uuid = param['uuid'];
		mode = param['mode'];
		//根据id查询客户信息
		findCustomerinfoByid(uuid);
	}

});
//关闭按钮
function closeCus(){
	window.parent.$.fancybox.close();
}
//新增/修改
function addCustomerinfo(){
	var customerCode = $("#customerCode").val();
	var customername = $("#customername").val();
	var province = $("#province").val();
	var city = $("#city").val();
	var intentionallevel = $("#intentionallevel").val();
	var procurementlevel = $("#procurementlevel").val();
	var creditlevel = $("#creditlevel").val();
	var address = $("#address").val();
	var contacts = $("#contacts").val();
	var telephone = $("#telephone").val();
	var othernumbers = $("#othernumbers").val();
	var remarks = $("#remarks").val();
	var isincoming = $("input[name='radio']:checked").val();
	var issupplier = $("input[name='radio1']:checked").val();
	var arrearsmoney = $("#arrearsmoney").val();
	var arrearsamount = $("#arrearsamount").val();
	if(customerCode == ""){
		swal("操作失败","必须输入客户编号","info");
		return;
	}
	if(customername == ""){
		swal("操作失败","必须输入客户名称","info");
		return;
	}
	if(province == ""){
		swal("操作失败","必须选择所属的省份","info");
		return;
	}
	if(city == ""){
		swal("操作失败","必须选择所属的市区","info");
		return;
	}
	if(intentionallevel == ""){
		swal("操作失败","必须选择意向客户的级别","info");
		return;
	}
	if(procurementlevel == ""){
		swal("操作失败","必须选择采购级别","info");
		return;
	}
	if(creditlevel == ""){
		swal("操作失败","必须选择诚级别","info");
		return;
	}
	if(address == ""){
		swal("操作失败","必须输入客户地址","info");
		return;
	}
	if(contacts == ""){
		swal("操作失败","必须输入客户联系人","info");
		return;
	}
	if(telephone == ""){
		swal("操作失败","必须输入联系电话","info");
		return;
	}
	if(isincoming == ""){
		swal("操作失败","必须选择是否来料加客户","info");
		return;
	}
	if(issupplier == ""){
		swal("操作失败","必须选择是否也是供应商","info");
		return;
	}
	var isArrearsNull = false;
	if(arrearsmoney != "" && arrearsmoney != "0" && (arrearsamount == "" || arrearsamount == "0")){
		swal("操作失败","必须输入年期初欠款数量","info");
		return;
	}else if(arrearsamount != "" && arrearsamount != "0" && (arrearsmoney == "" || arrearsmoney == "0")){
		swal("操作失败","必须输入年期初欠款金额","info");
		return;
	}
	if((arrearsmoney == "" || arrearsmoney == "0") && (arrearsamount == "" || arrearsamount == "0")){
		 $('#arrearsdetailList').find('tr').each(function () {               //tableId 是table表格的id
 		     $(this).find('td').each(function () {

 		        $(this).find('input').each(function () {                        //获取td中input的值
 		           if($(this).attr("name")) {                                    //myName是input标签的一个自定义属性
 		              if($(this).val()!=""){
 		            	swal("操作失败","年期初欠款为空，欠款明细不能有值","info");
 		            	isArrearsNull = true;
 		            	 return;
 		              }
 		           }
 		        })

 		     })
		  })
	}
	if(arrearsmoney != "" && arrearsamount != ""){
		$('#arrearsdetailList').find('tr').each(function () {               //tableId 是table表格的id
		     $(this).find('td').each(function () {

		        $(this).find('input').each(function () {                        //获取td中input的值
		           if($(this).attr("name")) {                                    //myName是input标签的一个自定义属性
		             if($(this).val().length > 10){
		            		swal("操作失败","长度过长请重新输入","info");
		            		$(this).val("");
		            		isArrearsNull = true;
	 		            	 return;
		            	}else if($(this).val() == ""){
		            		swal("操作失败","欠款明细和欠款数量要同时有值","info");
		            		isArrearsNull = true;
	 		            	 return;
		            	}
		           	}
		           if($(this).attr("name") == "amount"){
		        	   if(!(/^\-?\d+(\.\d{0,2})?$/).test($(this).val())){
		        		   swal({
		        				title: "错误提示",
		        				text: "请输入有效的数字!",
		        				type: "error",
		        				confirmButtonText: '确定',
		        				cancelButtonFont: '微软雅黑',
		        			});
		        		   $(this).val("");
		        		   isArrearsNull = true;
		        		   return;
		        	   }
		           }
		        })
		     })
		  })
	}
	if(isArrearsNull){
		return;
	}
	if(arrearsmoney == ""){
		arrearsmoney = 0.00;
	}
	
	if(arrearsamount == ""){
		arrearsamount = 0.00;
	}
		  
	var params = {
			"customerCode":customerCode,
			"customername":customername,
			"province":province,
			"city":city,
			"intentionallevel":intentionallevel,
			"procurementlevel":procurementlevel,
			"creditlevel":creditlevel,
			"address":address,
			"contacts":contacts,
			"telephone":telephone,
			"othernumbers":othernumbers,
			"remarks":remarks,
			"isincoming":isincoming,
			"issupplier":issupplier,
			"arrearsmoney":arrearsmoney,
			"arrearsamount":arrearsamount
	};
	if(uuid != null && uuid != ""){
		params.uuid = uuid;
		$.ajax({
			type : "POST",
			url : baseUrl+"/client/updateCustomerinfo.action",
			data : params,
			dataType : "json",
			success : function(data){
				if(data.code == "success"){
					var cid = data.sid;
					 var jsonStr = "{\"scientificProject\":[";
	        		  $('#arrearsdetailList').find('tr').each(function () {               //tableId 是table表格的id
	        		     jsonStr += "{";
	        		     $(this).find('td').each(function () {

	        		        $(this).find('input').each(function () {                        //获取td中input的值
	        		           if($(this).attr("name")) {                                    //myName是input标签的一个自定义属性
	        		              jsonStr += "\""+$(this).attr("name")+"\":\""+$(this).val()+"\",";
	        		           }
	        		        })

	        		     })
	        		     jsonStr = jsonStr.substring(0,jsonStr.length - 1);
	        		     jsonStr += "},";
	        		  })
	        		  jsonStr = jsonStr.substring(0,jsonStr.length - 1);
	        		  jsonStr += "]}";

	        		  var scientificProject = "{\"scientificProject\":]}"
		        		  if(jsonStr == scientificProject){
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
	        		  var bToObj=JSON.parse(jsonStr);
	        		  for (var i = 0; i < bToObj.scientificProject.length; i++) {
	        			  bToObj.scientificProject[i].customerId = cid;
					}
	        		  var aToStr=JSON.stringify(bToObj.scientificProject);
	        		  $.ajax({
	        		         type: "post",
	        		         url: baseUrl+'/client/updateArrears.action',
	        		         async:false,
	        		         data:aToStr,
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
					swal("操作失败",data.message,"error");
				}
			}
		});
	}else{
		var par = params;
		$.ajax({
			type:"POST",
			url:baseUrl+"/client/addCusttomerinfo.action",
			data:params,
			dataType:"json",
			success:function(data){
				if(data.code == "success"){
					var cid = data.sid;
					 var jsonStr = "{\"scientificProject\":[";
	        		  $('#arrearsdetailList').find('tr').each(function () {               //tableId 是table表格的id
	        		     jsonStr += "{";
	        		     $(this).find('td').each(function () {

	        		        $(this).find('input').each(function () {                        //获取td中input的值
	        		           if($(this).attr("name")) {                                    //myName是input标签的一个自定义属性
	        		              jsonStr += "\""+$(this).attr("name")+"\":\""+$(this).val()+"\",";
	        		           }
	        		        })

	        		     })
	        		     jsonStr = jsonStr.substring(0,jsonStr.length - 1);
	        		     jsonStr += "},";
	        		  })
	        		  jsonStr = jsonStr.substring(0,jsonStr.length - 1);
	        		  jsonStr += "]}";

	        		  var scientificProject = "{\"scientificProject\":]}"
		        		  if(jsonStr == scientificProject){
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
	        		  var bToObj=JSON.parse(jsonStr);
	        		  for (var i = 0; i < bToObj.scientificProject.length; i++) {
	        			  bToObj.scientificProject[i].customerId = cid;
					}
	        		  var aToStr=JSON.stringify(bToObj.scientificProject);
	        		  $.ajax({
	        		         type: "post",
	        		         url: baseUrl+'/client/addArrears.action',
	        		         async:false,
	        		         data:aToStr,
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
					swal("操作失败", data.message, "error");
				}
			}
		});
	}
}
//查询一条客户信息
function findCustomerinfoByid(uuid) {
	$.ajax({
		type : "POST",
		url : baseUrl + "/client/findCustomerinfoById.action",
		data : {"uuid" : uuid},
		async : false,
		dataType : "json",
		success : function(data) {
			$("#customerCode").val(data.customerCode);
			$("#customername").val(data.customername);	
			$("#intentionallevel").val(data.intentionallevel);
			$("#procurementlevel").val(data.procurementlevel);
			$("#creditlevel").val(data.creditlevel);
			$("#address").val(data.address);
			$("#contacts").val(data.contacts);
			$("#telephone").val(data.telephone);
			$("#othernumbers").val(data.othernumbers);
			$("#remarks").val(data.remarks);
			$("input[name='radio'][value="+data.isincoming+"]").attr("checked",true);
			$("input[name='radio1'][value="+data.issupplier+"]").attr("checked",true);
			$("#arrearsmoney").val(data.arrearsmoney);
			$("#arrearsamount").val(data.arrearsamount);
			$("#arrearsdetail").val(data.arrearsdetail);
			$("#detailamount").val(data.detailamount);
			$.ajax({
				type:"POST",
				url :baseUrl + "/client/getCityInfo.action",
				data:{"city":data.city},
				async : false,
				dataType : "json",
				success:function(data){
					$.ajax({
						type:"POST",
						url :baseUrl + "/client/getProvinceinfo.action",
						data:{"id":data.provinceId},
						async : false,
						dataType : "json",
						success:function(data){
							$("#province").val(data.id);
							var ctid = $("#province").val();
							$.ajax({
								type: "post",
								async:false,
								url: baseUrl+'/client/getCityInfoList.action',
								dataType: "json",
								data:{"id":ctid},
								success: function (data) {
									$.ajax({
										type : "POST",
										url : baseUrl + "/client/getArrears.action",
										data : {"customerId" : uuid},
										async : false,
										dataType : "json",
										success : function(data) {
											var result="";
											for(var i = 0;i<data.length;i++){
								    	        result +="<tr>";
								    	    	result +="<td class=\"ui_text_rt\">欠款明细：</td>";
								    	    	result +="<td class=\"ui_text_lt\">&nbsp;<input name=\"detailed\" type=\"text\" value=\""+data[i].detailed+"\" class=\"ui_input_txt01 \" id=\"arrearsdetail\"/></td>";
								    	    	result +="<td class=\"ui_text_rt\">&nbsp;&nbsp;明细数量：</td>";
								    	    	result +="<td class=\"ui_text_lt\">&nbsp;&nbsp;&nbsp;<input name=\"amount\" type=\"text\" value=\""+data[i].amount+"\" id=\"detailamount\" class=\"ui_input_txt01 \" /></td>";
								    	    	result +="<td ><a href=\"javascript:void(0)\" onclick=\"removeTr(this,"+data[i].id+")\"><img src=\"../../img/common/del.png\" width=\"20\" height=\"20\" alt=\"删除\" title=\"删除\"></a></td>";
								    	    	result +="</tr>";
											}
											$("#arrearsdetailList").append(result);
										}
									});
									$("#city").empty();
									$.each(data, function (i, v) {
										$('<option></option>').attr("value", v.id).html(v.cityName).appendTo($("#city"));
									})
								}
							});
						}
					});
				}
			});
			$("#city").val(data.city)
		}
	});
}
//客户编号
function changeCustomerCode(value){
	if(value.length > 20){
		swal({
			title: "错误提示",
			text: "长度过长重新输入!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		$("#customerCode").val("");
	}
	if(value !=''){
		$.ajax({
			type: "post",
			async:false,
			url: baseUrl+'/client/getclient.action',
			dataType: "json",
			data:{"customerCode":value},
			success: function (data) {
				if(data.data.length>0){
					swal({
						title: "错误提示",
						text: "客户编号重复重新输入!",
						type: "error",
						confirmButtonText: '确定',
						cancelButtonFont: '微软雅黑',
					});
					$("#customerCode").val("");
				}
			}
		});
	}
}
//客户名称
function changeCustomername(value){
	if(value != ""){
		$.ajax({
			type: "post",
			async:false,
			url: baseUrl+'/client/findCustomerinfo.action',
			dataType: "json",
			data:{"customername":value},
			success: function (data) {
				if(data.data.length>0){
					for(var i = 0;i<data.data.length;i++){
						if(data.data[i].customername == value){
							swal({
								title: "错误提示",
								text: "客户名称重复重新输入!",
								type: "error",
								confirmButtonText: '确定',
								cancelButtonFont: '微软雅黑',
							});
							$("#customername").val("");
						}
					}
				}
			}
		});
	}
	if(value.length > 100){
		swal({
			title: "错误提示",
			text: "长度过长重新输入!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		$("#customername").val("");
	}
}
//地址
function changeAddress(value){
	if(value.length > 15){
		swal({
			title: "错误提示",
			text: "长度过长重新输入!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		$("#address").val("");
	}
}
//联系人
function changeContacts(value){
	if(value.length > 15){
		swal({
			title: "错误提示",
			text: "长度过长重新输入!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		$("#contacts").val("");
	}
}
//联系电话
function changeTelephone(value){
	if(value.length > 11){
		swal({
			title: "错误提示",
			text: "长度过长重新输入!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		$("#telephone").val("");
	}
	if(!(/^1[34578]\d{9}$/.test(value))){ 
		swal({
			title: "错误提示",
			text: "请输入有效11位手机号码!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		$("#telephone").val("");
	} 
}
//其他联系方式
function changeOthernumers(value){
	if(value.length > 25){
		swal({
			title: "错误提示",
			text: "长度过长重新输入!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		$("#othernumbers").val("");
	}
}
//备注
function changeRemarks(value){
	if(value.length > 200){
		swal({
			title: "错误提示",
			text: "长度过长重新输入!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		$("#remarks").val("");
	}
}
//年期初欠款金额
function changeArrearsmoney(value){
	if(value.length > 10){
		swal({
			title: "错误提示",
			text: "长度过长重新输入!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		$("#arrearsmoney").val("");
	}
	if(value != ""){
		if(!(/^\-?\d+(\.\d{0,2})?$/).test(value)){
			swal({
				title: "错误提示",
				text: "请输入有效数字!",
				type: "error",
				confirmButtonText: '确定',
				cancelButtonFont: '微软雅黑',
			});
			$("#arrearsmoney").val("");
		}
	}
}
//年期初欠款数量
function changeArrearsamount(value){
	if(value.length > 10){
		swal({
			title: "错误提示",
			text: "长度过长重新输入!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		$("#arrearsamount").val("");
	}
	if(value != ""){
		if(!(/^\-?\d+(\.\d{0,2})?$/).test(value)){
			swal({
				title: "错误提示",
				text: "请输入有效数字!",
				type: "error",
				confirmButtonText: '确定',
				cancelButtonFont: '微软雅黑',
			});
			$("#arrearsamount").val("");
		}
	}
}
//新增一条明细
function addTable(){
	var len = $('#arrearsdetailList tr').length;
	var row;
	var result="";
	if(len==1){
		row = 1;
	}else{
		for(var i = 1;i<len;i++){
			row = i+1;
		}
	}
	result +="<tr>";
	result +="<td class=\"ui_text_rt\">欠款明细: </td>";
	result +="<td class=\"ui_text_lt\">&nbsp;<input name=\"detailed\" type=\"text\" value=\"\" class=\"ui_input_txt01 \" id=\"arrearsdetail\"/></td>";
	result +="<td class=\"ui_text_rt\">&nbsp;&nbsp;明细数量：</td>";
	result +="<td class=\"ui_text_lt\">&nbsp;&nbsp;&nbsp;<input name=\"amount\" type=\"text\" value=\"\" id=\"detailamount\" class=\"ui_input_txt01 \" /></td>";
	result +="<td ><a href=\"javascript:void(0)\" onclick=\"removeTr(this)\"><img src=\"../../img/common/del.png\" width=\"20\" height=\"20\" alt=\"删除\" title=\"删除\"></a></td>";
	result +="</tr>";
	$("#arrearsdetailList").append(result);
}


//删除功能
function removeTr(obj,id) {
	if(id!=undefined){
	swal({
		title: "确定操作吗？",
		text:"删除后将无法恢复，请谨慎操作！",
		type: "warning",
		showCancelButton: true,
		confirmButtonColor: '#AEDEF4',
		confirmButtonText: '确定',
		cancelButtonText: '取消',
		cancelButtonFont: '微软雅黑',
	},
	function(){
	    	var cid = id
	    	$.ajax({
	    		type: "post",
	    		url: baseUrl+'/client/delArrearsInfoById.action',
	    		async:false,
	    		data:{"id":cid},
	    		dataType: "json",
	    		success: function (data) {
	    			
	    		}
	    	});
	    var tr= $(obj).parent().parent();
	    tr.remove();
});
	}else{
		 var tr= $(obj).parent().parent();
		    tr.remove();
	}
}
//解析url携带参数
function parseUrl(){
	var url = location.href;
	var i = url.indexOf('?');
	if(i == -1){
		return;
	}
	var querystr = url.substr(i+1);
	var arr1 = querystr.split('&');
	var arr2 = new Object();
	for(i in arr1){
		var ta = arr1[i].split('=');
		arr2[ta[0]] = ta[1];
	}
	return arr2;
}