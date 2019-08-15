	var baseUrl = "";
var id = "";
$(function() {
//	$("#indexType").html(getDataDictionary('indexType'));
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
			getCachetCompany(id);
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
function addCachetCompany(){
	var continuityAdd = $("#continuityAdd").is(':checked')
	var testCompany = $("#testCompany").val();
	var cid = id;
	var url;
	if(testCompany==""){
		swal("操作失败", "必须输入检测单位", "info");
		return;
	}
	//if(deviation==""){
	//	swal("操作失败", "必输输入允许偏差", "info");
	//	return;
	//}
	//if(remarks.length>200){
	//	swal("操作失败", "备注内容过长", "info");
	//	return;
	//}
	if(cid!=""&&cid!=null){
		options = {
				"testCompany" : testCompany,
				"reviser":creater,
				"id":cid
		};
		url = baseUrl+'/CachetCompany/updateCachetCompany.action';
	}else{
	options = {
			"testCompany" : testCompany,
			"creater":creater
	};
	url = baseUrl+'/CachetCompany/addCachetCompany.action'
	}

    // 当已经上传过封签号时，再次上传。先删除历史文件，再执行上传操作，否做直接进行操作
	if ($("#files").val() != null && $("#files").val().indexOf('.') != -1 && $("#myBlogImage").val() != "") {
		layer.confirm("该操作会覆盖历史文件！" , {
			btn: ['确定','取消'] //按钮
		}, function(){
			$.ajax({
				url : "../../file/fileDelete.action",
				async : false,
				dataType:'json',
				data : {
					"filename" : $("#files").val()
				},
				type : "post",
				success : function(_info) {
					layer.msg('覆盖成功');
					$.ajaxFileUpload({  
						url: '../../file/fileUpload.action',  
						secureuri:false,                           //是否启用安全提交,默认为false   
						fileElementId:'myBlogImage',               //文件选择框的id属性  
						dataType:'json',                           //服务器返回的格式,选择json或者xml貌似有问题
						success:function(fileData){
							if (fileData.fileName != null) {
								// 文件名
								$("#fileName").val(fileData.fileName);
								options.electronicsName = fileData.fileName;
								
								$.ajax({
									type: "post",
									url: url,
									data:JSON.stringify(options),
									dataType: "json",
									contentType : 'application/json;charset=UTF-8',
									success: function (data) {
										if(data != null){
												if(data.code == "200"){
													 if(continuityAdd){
															window.location.href = 'cachetCompany_edit.html?continuity='+continuityAdd;
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
						options.electronicsName = fileData.fileName;
						
						$.ajax({
							type: "post",
							url: url,
							data:JSON.stringify(options),
							dataType: "json",
							contentType : 'application/json;charset=UTF-8',
							success: function (data) {
								if(data != null){
									if(data.code == "200"){if(continuityAdd){
										window.location.href = 'cachetCompany_edit.html?continuity='+continuityAdd;
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
			options.electronicsName = $("#files").val();
			$.ajax({
				type: "post",
				url: url,
				data:JSON.stringify(options),
				dataType: "json",
				contentType : 'application/json;charset=UTF-8',
				success: function (data) {
					if(data != null){
						if(data.code == "200"){
							if(continuityAdd){
								window.location.href = 'cachetCompany_edit.html?continuity='+continuityAdd;
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
				}
			});
		}
	}
}

function getCachetCompany(id){
	$.ajax({
        type: "post",
        url: baseUrl+'/CachetCompany/getCachetCompany.action',
        async:false,
        data:{"id":id},
        dataType: "json",
        success: function (data) {
        	var cachetCompany = data.data;
        	$("#testCompany").val(cachetCompany[0].testCompany);
        	// 封签号
			if(cachetCompany[0].electronicsName){
				var fileName = cachetCompany[0].electronicsName
				$("#fileName").val(fileName);
				$("#electronicsName1").show();
				$("#electronicsName2").show();
				$("#file").html(fileName.substring(0,fileName.indexOf('_')) + fileName.substring(fileName.indexOf('.'), fileName.length));
				$("#files").val(fileName);
			} else {
				$("#electronicsName1").hide();
				$("#electronicsName2").hide();
			}
        }
   });
}

//删除封签号并更新数据库
function deleteFiles(){
	$.ajax({ 
		type : "POST",
	    url: '../../CachetCompany/updateElectronicsName.action',  
	    async:false,
		data : {"id": id},
		dataType : "json",                         //服务器返回的格式,选择json或者xml貌似有问题
	    success:function(fileData){
	    	deleteFile();
	   }
	});
}

//验证方法
function isJudge(){
	//运输起点
	var testCompany = $("#testCompany").val();
	if(testCompany.length>200){
		swal("操作失败", "检测指标长度过长,请重新填写", "info");
		$("#testCompany").val("");
		return;
	}
}