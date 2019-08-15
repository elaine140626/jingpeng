var id = "";
var uesrInfo = ""; //用户信息
var username = "出库单测试";
$(function(){
	id = getUrlParam("id")
	//uesrInfo = getUserInfo(uesrId);
	if(id != "undefined" && id != null){
		FindWeighingQueryOtherOutById(id);
	}
})
//根据id查询出库单信息
function FindWeighingQueryOtherOutById(id){
			$.ajax({
				type: 'POST',
				url: '../../WeighingQueryOtherOut/getWeighingQueryOtherOut.action',
				data: {"id":id},
				dataType: 'json',
				success: function(data){
					data = data.data[0];	
					$("#plateNumber").val(data.plateNumber);			//车牌号码
					$("#serial_ID").val(data.serial_ID);				//流水号
					$("#client").val(data.client);					    //客户名称
					$("#unit").val(data.unit);					   		//客户名称
					$("#materielName").val(data.materielName);			//产品名称
					$("#materielModel").val(data.materielModel);		//规格型号
					$("#grossWeight").val(data.grossWeight);			//毛重
					$("#tareWeight").val(data.tareWeight);				//皮重
					$("#suttle").val(data.suttle);						//净重
					$("#deliveryMan").val(data.deliveryMan);			//承运人
					$("#deliveryManPhone").val(data.deliveryManPhone);	//承运电话
					$("#fleetName").val(data.fleetName);				//车队名称
					$("#consignee").val(data.consignee);				//收货人
					$("#consigneePhone").val(data.consigneePhone);		//收货电话
					$("#testReport").val(data.testReport);				//报告编号
					$("#temperature").val(data.temperature);			//温度
					$("#qualityInspector").val(data.qualityInspector);	//检斤员
					$("#measureTime").val(data.measureTime);			//称重时间
					$("#facingSlipNum").val(data.facingSlipNum);		//封签号
					$("#facingSlipNum2").val(data.facingSlipNum2);		//封签号2
					$("#facingSlipNum3").val(data.facingSlipNum3);		//封签号3
					$("#facingSlipNum4").val(data.facingSlipNum4);		//封签号4
					$("#isSelfMention").val(data.isSelfMention);		//是否自提
					$("#remarks").val(data.remarks);					//备注
					if(data.driverAutograph){
						var fileName = data.driverAutograph
						$("#fileName").val(fileName);
						$("#electronicsName1").show();
						$("#file").html(fileName.substring(0,fileName.indexOf('_')) + fileName.substring(fileName.indexOf('.'), fileName.length));
						$("#files").val(fileName);
					} else {
						$("#electronicsName1").hide();
					}
				}
			});
}
//编辑其他出库单信息
function save(){
	/*	
		if(params.plateNumber == ""){layer.alert("车牌号码不能为空!",{icon: 2,title: "提示"});return;}
		if(params.serial_ID == ""){layer.alert("流水号不能为空!",{icon: 2,title: "提示"});return;}
		if(params.client == ""){layer.alert("客户名称不能为空!",{icon: 2,title: "提示"});return;}
		if(params.unit == ""){layer.alert("供料单位不能为空!",{icon: 2,title: "提示"});return;}
		if(params.materielName == ""){layer.alert("产品名称不能为空!",{icon: 2,title: "提示"});return;}
		if(params.materielModel == ""){layer.alert("规格型号不能为空!",{icon: 2,title: "提示"});return;}
		if(params.grossWeight == ""){layer.alert("毛重不能为空!",{icon: 2,title: "提示"});return;}
		if(params.tareWeight == ""){layer.alert("皮重不能为空!",{icon: 2,title: "提示"});return;}
		if(params.suttle == ""){layer.alert("净重不能为空!",{icon: 2,title: "提示"});return;}
		if(params.deliveryMan == ""){layer.alert("承运人不能为空!",{icon: 2,title: "提示"});return;}
		if(params.deliveryManPhone == ""){layer.alert("承运电话不能为空!",{icon: 2,title: "提示"});return;}
		if(params.fleetName == ""){layer.alert("车队名称不能为空!",{icon: 2,title: "提示"});return;}
		if(params.consignee == ""){layer.alert("收货人不能为空!",{icon: 2,title: "提示"});return;}
		if(params.consigneePhone == ""){layer.alert("收货电话不能为空!",{icon: 2,title: "提示"});return;}
		if(params.reportNum == ""){layer.alert("报告编号不能为空!",{icon: 2,title: "提示"});return;}
		if(params.temperature == ""){layer.alert("温度不能为空!",{icon: 2,title: "提示"});return;}
		if(params.qualityInspector == ""){layer.alert("检斤员不能为空!",{icon: 2,title: "提示"});return;}
		if(params.measureTime == ""){layer.alert("称重时间不能为空!",{icon: 2,title: "提示"});return;}
		if(params.facingSlipNum == ""){layer.alert("封签号1不能为空!",{icon: 2,title: "提示"});return;}
		if(params.facingSlipNum2 == ""){layer.alert("封签号2不能为空!",{icon: 2,title: "提示"});return;}
		if(params.facingSlipNum3 == ""){layer.alert("封签号3不能为空!",{icon: 2,title: "提示"});return;}
		if(params.facingSlipNum4 == ""){layer.alert("封签号4不能为空!",{icon: 2,title: "提示"});return;}*/
		var params = formToJson($("#form1"));
		if(params.measureTime == ""){
				layer.alert("称重时间不能为空!",{
					icon: 2,
					title: "提示"
				});
				return;
		}
		if(id == "undefined"){
			url= '../../WeighingQueryOtherOut/insertWeighingQueryOtherOut.action';
			params.creater=username;
		}else{
			url= '../../WeighingQueryOtherOut/updateWeighingQueryOtherOut.action';
			params.id = id;
			params.reviser=username;
		}
	    // 当已经上传过封签号时，再次上传。先删除历史文件，再执行上传操作，否做直接进行操作
		if ($("#files").val() != null && $("#files").val().indexOf('.') != -1 && $("#myBlogImage").val() != "") {
			layer.confirm("该操作会覆盖历史文件！" , {
				btn: ['确定','取消'] //按钮
			}, function(){
				$.ajax({
					url : "../../WeighingQueryOtherOut/deleteElectronicsName.action",
					async : false,
					dataType:'json',
					data : {
						"id": id
					},
					type : "post",
					success : function(_info) {
						$.ajaxFileUpload({  
							url: '../../file/fileUpload.action',  
							secureuri:false,                           //是否启用安全提交,默认为false   
							fileElementId:'myBlogImage',               //文件选择框的id属性  
							dataType:'json',                           //服务器返回的格式,选择json或者xml貌似有问题
							success:function(fileData){
								if (fileData.fileName != null) {
									// 文件名
									$("#fileName").val(fileData.fileName);
									params.driverAutograph = fileData.fileName;
									$.ajax({
										type : 'POST',
										url : url,
										data:JSON.stringify(params),
										dataType: 'json',
										contentType: 'application/json',
										success : function(data) {
											if (data.code = "success") {
												layer.alert("覆盖成功", {
													icon : 1,
													title : "提示"
												}, function() {
													window.parent.location.reload();
													var index=parent.layer.getFrameIndex(window.name);
													parent.layer.close(index);
												});
											} else {
												layer.msg(data.message, {
													icon: 2,
													time: 1000
												},function(){
													window.location.reload();
												});
											}
										}
									});
								} else {
									swal({
										title: "错误提示",
										text: "文件上传失败!",
										type: "error",
										confirmButtonText: '确定',
										cancelButtonFont: '微软雅黑',
									});
									return;
								}
							}
						});	
					}
				});
			}, function(){
				// 点击取消时触发，清空选择的文件
				var file = document.getElementById('myBlogImage');
				file.value = ''; //虽然file的value不能设为有字符的值，但是可以设置为空值
				//或者
				file.outerHTML = file.outerHTML;
			});
		} else {
			// 判断是否上传了封签号。若没上传则执行普通保存，否则先上传再保存
			if ($("#myBlogImage")[0].files.length > 0 && $("#myBlogImage")[0].files[0].name != "") {
				// 文件上传
				$.ajaxFileUpload({  
					url: '../../file/fileUpload.action',  
					secureuri:false,                           //是否启用安全提交,默认为false   
					fileElementId:'myBlogImage',               //文件选择框的id属性  
					dataType:'json',                           //服务器返回的格式,选择json或者xml貌似有问题
					success:function(fileData){
						if (fileData.fileName != null) {
							// 文件名
							$("#fileName").val(fileData.fileName);
							params.driverAutograph = fileData.fileName;
							$.ajax({
								type : 'POST',
								url : url,
								data:JSON.stringify(params),
								dataType: 'json',
								contentType: 'application/json',
								success : function(data) {
									if (data.code = "success") {
										layer.alert(data.message, {
											icon : 1,
											title : "提示"
										}, function() {
											window.parent.location.reload();
											var index=parent.layer.getFrameIndex(window.name);
											parent.layer.close(index);
										});
									} else {
										layer.msg(data.message, {
											icon: 2,
											time: 1000
										},function(){
											window.location.reload();
										});
									}
								}
							});
						} else {
							swal({
								title: "错误提示",
								text: "文件上传失败!",
								type: "error",
								confirmButtonText: '确定',
								cancelButtonFont: '微软雅黑',
							});
							return;
						}
					}
				});
			} else {
				params.driverAutograph = $("#files").val();
				$.ajax({
					type : 'POST',
					url : url,
					data:JSON.stringify(params),
					dataType: 'json',
					contentType: 'application/json',
					success : function(data) {
						if (data.code = "success") {
							layer.alert(data.message, {
								icon : 1,
								title : "提示"
							}, function() {
								window.parent.location.reload();
								var index=parent.layer.getFrameIndex(window.name);
								parent.layer.close(index);
							});
						} else {
							layer.msg(data.message, {
								icon: 2,
								time: 1000
							},function(){
								window.location.reload();
							});
						}
					}
				});
			}
		}
	}
//窗口关闭
function layer_close(){
	var index=parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}

//删除其他出库单信息
function deleteWeighingQueryOtherOut(id){
		layer.confirm('确认要删除吗？', 
			function(index) {
				$.ajax({
					type: 'POST',
					url: '../../WeighingQueryOtherOut/deleteWeighingQueryOtherOut.action',
					data: {"id":id},
					dataType: 'json',
					success: function(data){
						if(data.code = "success"){
							layer.msg(data.message, {
								icon: 1,
								time: 1000
							},function(){
								location.reload();
							});
						}else{
							layer.msg(data.message, {
								icon: 2,
								time: 1000
							},function(){
								location.reload();
							});
						}
					}
				});
			});
}

//计算皮重
function getNetWeight(){
	//毛重
	var grossWeight = $("#grossWeight").val();
	//皮重
	var tareWeight = $("#tareWeight").val();
	if(grossWeight !="" && tareWeight != ""){
		$("#suttle").val((grossWeight-tareWeight).toFixed(3));
	}
}

//删除司机电子签名并更新数据库
function deleteFiles(){
	$.ajax({ 
		type : "POST",
	    url: '../../WeighingQueryOtherOut/deleteElectronicsName.action',  
	    async:false,
		data : {"id": id},
		dataType : "json",                         //服务器返回的格式,选择json或者xml貌似有问题
	    success:function(fileData){
	    	layer.msg('删除成功');
	    	$("#electronicsName1").hide();
	   }
	});
}