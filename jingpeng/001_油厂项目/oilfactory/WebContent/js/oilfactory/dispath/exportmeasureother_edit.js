var id = "";
var uesrInfo; //用户信息
var username;
$(function(){
	// 获取用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){	
		username = userInfo.username;	
	}
	
	// 获取页面的传值
	id = getUrlParam("id");
	// 修改
	if(id != "" && id != null){
		getInfo(id);
	}
})

//根据id查询出库单信息
function getInfo(id){
	$.ajax({
		type: 'POST',
		url: '../../exportmeasureother/getExportmeasureotherList.action',
		data: {"id":id},
		dataType: 'json',
		success: function(data){
			data = data.data[0];
			// 表单赋值
			SetFromValues($("#submitForm"), data);
			if(data.driverAutograph != null && data.driverAutograph != ""){
				var fileName = data.driverAutograph;
				$("#electronicsName1").show();
				$("#fileName").val(fileName);
				$("#file").html(fileName.substring(0,fileName.indexOf('_')) + fileName.substring(fileName.indexOf('.'), fileName.length));
				$("#files").val(fileName);
			} else {
				$("#electronicsName1").hide();
			}
		}
	});
}

//form表单赋值方法
function SetFromValues(el, data)
{
    for (var p in data)
    {
        el.find(":input[name='" + p + "']").val(data[p]);
    } 
}

//编辑其他出库单信息
function save(){
	var params = formToJson($("#submitForm"));
	if(params.measureTime == ""){
		swal({
			title: "错误提示",
			text: "称重时间不能为空!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}
	if(id != null && id != ""){
		url= '../../exportmeasureother/updateExportmeasureother.action';
		params.id = id;
		params.reviser=username;
	}else{
		url= '../../exportmeasureother/insertExportmeasureother.action';
		params.creater=username;
	}
	
	params.driverAutograph = $("#files").val();
	
	
    // 当已经上传过封签号时，再次上传。先删除历史文件，再执行上传操作，否做直接进行操作
	if ($("#files").val() != null && $("#files").val().indexOf('.') != -1 && $("#myBlogImage").val() != "") {
		layer.confirm("该操作会覆盖历史文件！" , {
			btn: ['确定','取消'] //按钮
		}, function(){
			$.ajax({
				url : "../../exportmeasureother/deleteElectronicsName.action",
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
											swal({
												title: "提示",
												text: data.message,
												type: data.code,
												confirmButtonText: '确定',
												cancelButtonFont: '微软雅黑',
											}, function() {
												window.parent.location.reload();
											});
										} else {
											swal({
												title: "提示",
												text: data.message,
												type: data.code,
												confirmButtonText: '确定',
												cancelButtonFont: '微软雅黑',
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
									swal({
										title: "提示",
										text: data.message,
										type: data.code,
										confirmButtonText: '确定',
										cancelButtonFont: '微软雅黑',
									}, function() {
										window.parent.location.reload();
									});
								} else {
									swal({
										title: "提示",
										text: data.message,
										type: data.code,
										confirmButtonText: '确定',
										cancelButtonFont: '微软雅黑',
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
						swal({
							title: "提示",
							text: data.message,
							type: data.code,
							confirmButtonText: '确定',
							cancelButtonFont: '微软雅黑',
						}, function() {
							window.parent.location.reload();
						});
					} else {
						swal({
							title: "提示",
							text: data.message,
							type: data.code,
							confirmButtonText: '确定',
							cancelButtonFont: '微软雅黑',
						},function(){
							window.location.reload();
						});
					}
				}
			});
		}
	}
}

//计算皮重
function getNetWeight(){
	//毛重
	var grossWeight = $("#grossWeight").val();
	//皮重
	var tareWeight = $("#tareWeight").val();
	if(grossWeight != "" && tareWeight != ""){
		$("#suttle").val((grossWeight - tareWeight).toFixed(4));
	}
}

//删除司机电子签名并更新数据库
function deleteFiles(){
	$.ajax({ 
		type : "POST",
	    url: '../../exportmeasureother/deleteElectronicsName.action',  
	    async:false,
		data : {"id": id},
		dataType : "json",                         //服务器返回的格式,选择json或者xml貌似有问题
	    success:function(fileData){
	    	swal({
				title: "提示",
				text: "删除成功",
				type: "success",
				confirmButtonText: '确定',
				cancelButtonFont: '微软雅黑',
			});
	    	// 文件名
			$("#files").val("");
	    	$("#electronicsName1").hide();
	   }
	});
}